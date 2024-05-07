package ru.zia.mi3metr.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.zia.mi3metr.model.User;
import ru.zia.mi3metr.model.dto.VotingStageDTO;
import ru.zia.mi3metr.model.dto.VotingStageResultDTO;
import ru.zia.mi3metr.service.ICandidateService;
import ru.zia.mi3metr.service.IVoteService;

/**
 * Контроллер для голосования.
 */
@Controller
@RequestMapping("/vote")
public class VoteController {

    /** Сервис голосования */
    private final IVoteService voteService;

    /** Сервис по работе с кандидатами */
    private final ICandidateService candidateService;

    /**
     * Конструктор.
     *
     * @param aVoteService      сервис голосования
     * @param aCandidateService сервис по работе с кандидатами
     */
    public VoteController(IVoteService aVoteService, ICandidateService aCandidateService) {
        super();
        voteService = aVoteService;
        this.candidateService = aCandidateService;
    }

    /**
     * Отображает страницу текущего этапа голосования.
     *
     * @param aModel модель представления
     * @return имя шаблона
     */
    @GetMapping
    public String showVoteStage(Model aModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VotingStageDTO stage = voteService.getCurrentVoteStage(user);
        if (stage == null) {
            return "redirect:/vote/rating";
        } else {
            aModel.addAttribute("votingStage", stage);
            aModel.addAttribute("votingStageResult", new VotingStageResultDTO());
            return "voteStage";
        }
    }

    /**
     * Обрабатывает результат голосования на текущем этапе.
     *
     * @param aResult результат этапа голосования
     * @return имя шаблона для отображения следующего этапа голосования
     */
    @PostMapping
    public String processVoting(@ModelAttribute VotingStageResultDTO aResult) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        voteService.updateVotingStage(user, aResult);
        return "redirect:/vote";
    }

    /**
     * Отображает рейтинг (Топ 10) кандидатов.
     *
     * @param aModel модель представления
     * @return имя шаблона
     */
    @GetMapping("/rating")
    public String showRating(Model aModel) {
        var candidates = candidateService.getTopList();
        aModel.addAttribute("candidates", candidates);
        return "rating";
    }

}
