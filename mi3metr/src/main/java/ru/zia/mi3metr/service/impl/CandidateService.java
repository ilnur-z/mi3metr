package ru.zia.mi3metr.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ru.zia.mi3metr.model.dto.CandidateConverter;
import ru.zia.mi3metr.model.dto.CandidateDTO;
import ru.zia.mi3metr.repository.ICandidateRepository;
import ru.zia.mi3metr.service.ICandidateService;

/**
 * Реализация сервиса кандидатов.
 */
@Service
@Transactional(readOnly = true)
public class CandidateService implements ICandidateService {

    /** Репозиторий кандидатов */
    private final ICandidateRepository candidateRepository;

    /** Конвертер кандидатов */
    private final CandidateConverter candidateConverter;

    /**
     * Конструктор.
     *
     * @param aCandidateRepository репозиторий кандидатов
     * @param aCandidateConverter  конвертер кандидатов
     */
    public CandidateService(ICandidateRepository aCandidateRepository,
            CandidateConverter aCandidateConverter) {
        super();
        candidateRepository = aCandidateRepository;
        candidateConverter = aCandidateConverter;
    }

    @Transactional
    @Override
    public void addCandidate(CandidateDTO aCandidateDto, MultipartFile aPhoto) throws IOException {
        var entity = candidateConverter.convertToEntity(aCandidateDto, aPhoto.getBytes());
        candidateRepository.save(entity);
    }

    @Override
    public List<CandidateDTO> getTopList() {
        var candidates = candidateRepository.findTop10ByOrderByLikesDesc();
        return candidates.stream().map(candidateConverter::convertToDto)
                .collect(Collectors.toList());
    }

}
