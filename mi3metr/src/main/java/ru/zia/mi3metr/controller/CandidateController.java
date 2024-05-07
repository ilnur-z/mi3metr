package ru.zia.mi3metr.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import ru.zia.mi3metr.model.dto.CandidateDTO;
import ru.zia.mi3metr.service.ICandidateService;

/**
 * Контроллер по созданию нового кандидата для голосования.
 */
@Controller
@RequestMapping("/candidate")
public class CandidateController {

    /** Сервис по работе с кандидатами для голосования */
    private final ICandidateService candidateService;

    /**
     * Конструктор.
     *
     * @param aCandidateService сервис по работе с кандидатами для голосования
     */
    public CandidateController(ICandidateService aCandidateService) {
        super();
        candidateService = aCandidateService;
    }

    /**
     * Отображает диалог создания нового кандидата для голосования.
     *
     * @param aCandidateDTO DTO-объект для заполнения данными кандидата
     * @return имя шаблона
     */
    @GetMapping("/create")
    public String showCreateDialog(@ModelAttribute CandidateDTO aCandidateDTO) {
        return "createCandidate";
    }

    /**
     * Обрабатывает POST-запрос на создание нового кандидата.
     *
     * @param aCandidateDTO     данные кандидата
     * @param aValidationResult результат проверки данных
     * @param aPhoto            фотография кандидата
     * @return имя шаблона для отображения при завершении операции
     * @throws IOException при ошибке
     */
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addUser(@ModelAttribute @Valid CandidateDTO aCandidateDTO,
            BindingResult aValidationResult, @RequestPart(name = "ppp") MultipartFile aPhoto)
            throws IOException {
        if (aValidationResult.hasErrors()) {
            return "createCandidate";
        }
        candidateService.addCandidate(aCandidateDTO, aPhoto);
        return "redirect:/";
    }

}
