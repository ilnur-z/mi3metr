package ru.zia.mi3metr.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ru.zia.mi3metr.model.dto.CandidateDTO;

/**
 * Сервис кандидатов голосования.
 */
public interface ICandidateService {

    /**
     * Добавляет кандидата для голосования.
     *
     * @param aCandidateDto данные кандидата для голосования
     * @param aPhoto        фотография кандидата для голосования
     * @throws IOException при ошибке
     */
    void addCandidate(CandidateDTO aCandidateDto, MultipartFile aPhoto) throws IOException;

    /**
     * Возвращает Топ-10 кандидатов с максимальным количеством голосов.
     *
     * @return список кандидатов
     */
    List<CandidateDTO> getTopList();

}
