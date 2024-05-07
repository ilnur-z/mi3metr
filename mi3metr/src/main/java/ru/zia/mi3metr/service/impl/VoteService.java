package ru.zia.mi3metr.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.zia.mi3metr.model.Candidate;
import ru.zia.mi3metr.model.User;
import ru.zia.mi3metr.model.Vote;
import ru.zia.mi3metr.model.VotingStage;
import ru.zia.mi3metr.model.dto.CandidateConverter;
import ru.zia.mi3metr.model.dto.VotingStageDTO;
import ru.zia.mi3metr.model.dto.VotingStageResultDTO;
import ru.zia.mi3metr.repository.ICandidateRepository;
import ru.zia.mi3metr.repository.IVoteRepository;
import ru.zia.mi3metr.repository.IVotingStageRepository;
import ru.zia.mi3metr.service.IVoteService;

/**
 * Реализация сервиса голосования.
 */
@Service
@Transactional
public class VoteService implements IVoteService {

    /** Репозиторий кандидатов */
    private final ICandidateRepository candidateRepository;

    /** Репозиторий голосований */
    private final IVoteRepository voteRepository;

    /** Репозиторий этапов голосований */
    private final IVotingStageRepository votingStageRepository;

    /** Конвертер кандидатов */
    private final CandidateConverter candidateConverter;

    /**
     * Конструктор.
     *
     * @param aCandidateRepository   репозиторий кандидатов
     * @param aVoteRepository        репозиторий голосований
     * @param aVotingStageRepository репозиторий этапов голосований
     * @param aCandidateConverter    конвертер кандидатов
     */
    public VoteService(ICandidateRepository aCandidateRepository, IVoteRepository aVoteRepository,
            IVotingStageRepository aVotingStageRepository, CandidateConverter aCandidateConverter) {
        super();
        this.candidateRepository = aCandidateRepository;
        this.voteRepository = aVoteRepository;
        this.votingStageRepository = aVotingStageRepository;
        this.candidateConverter = aCandidateConverter;
    }

    @Override
    public VotingStageDTO getCurrentVoteStage(User aUser) {
        Vote vote = voteRepository.findByUser(aUser).orElseGet(() -> createVote(aUser));
        VotingStageDTO result = null;
        if (!vote.isFinished()) {
            int currentIdx = vote.getCurrentStageIndex();
            VotingStage stage = vote.getStages().get(currentIdx);
            var candidates = stage.getCandidates().stream().map(candidateConverter::convertToDto)
                    .collect(Collectors.toList());
            result = new VotingStageDTO(currentIdx, candidates);
        }
        return result;
    }

    /**
     * Создает новое голосование для пользователя.
     *
     * @param aUser пользователь
     * @return голосование
     */
    private Vote createVote(User aUser) {
        Vote vote = new Vote();
        vote.setUser(aUser);
        vote = voteRepository.save(vote);

        var stages = createStages();
        for (var stage : stages) {
            stage.setVote(vote);
        }
        stages = votingStageRepository.saveAll(stages);
        vote.setStages(stages);
        return vote;
    }

    /**
     * Создает этапы голосования на основе существующих кандидатов.
     *
     * @return список этапов голосования
     */
    private List<VotingStage> createStages() {
        var candidates = candidateRepository.findAll();
        List<VotingStage> result = new ArrayList<>();
        calculateCandidateCombinations(candidates, result);
        Collections.shuffle(result);
        return result;
    }

    /**
     * Вычисляет различные сочетания кандидатов голосования и формирует список этапов голосования.
     *
     * @param aCandidates   список всех кандидатов
     * @param aResultStages результирующий список этапов голосования
     */
    private void calculateCandidateCombinations(List<Candidate> aCandidates,
            List<VotingStage> aResultStages) {
        Candidate first = aCandidates.get(0);
        for (int i = 1; i < aCandidates.size(); ++i) {
            Candidate second = aCandidates.get(i);
            VotingStage stage = new VotingStage();
            stage.setCandidates(List.of(first, second));
            aResultStages.add(stage);
        }
        if (aCandidates.size() > 2) {
            calculateCandidateCombinations(aCandidates.subList(1, aCandidates.size()),
                    aResultStages);
        }
    }

    @Override
    public void updateVotingStage(User aUser, VotingStageResultDTO aResult) {
        candidateRepository.findById(aResult.getVotedCandidateId())
                .ifPresent(this::addLikeToCandidate);
        voteRepository.findByUser(aUser).ifPresent(this::nextStage);
    }

    /**
     * Добавляет голос для сущности кандидата.
     *
     * @param aCandidate хранимая сущность кандидата
     */
    private void addLikeToCandidate(Candidate aCandidate) {
        aCandidate.setLikes(aCandidate.getLikes() + 1);
        candidateRepository.save(aCandidate);
    }

    /**
     * Устанавливает следующий этап в качестве текущего. В случае отсутствия следующего этапа
     * устанавливает признак завершения голосования.
     *
     * @param aVote хранимая сущность голосования
     */
    private void nextStage(Vote aVote) {
        int next = aVote.getCurrentStageIndex() + 1;
        if (next < aVote.getStages().size()) {
            aVote.setCurrentStageIndex(next);
        } else {
            aVote.setFinished(true);
            aVote.setCurrentStageIndex(-1);
        }
        voteRepository.save(aVote);
    }

}
