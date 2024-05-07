package ru.zia.mi3metr.model.dto;

import java.util.Base64;

import org.springframework.stereotype.Component;

import ru.zia.mi3metr.model.Candidate;
import ru.zia.mi3metr.model.CandidateMedia;

/**
 * Конвертер модели данных кандидата.
 */
@Component
public class CandidateConverter {

    /**
     * Конверитирует сущность кандидата в DTO-объект.
     *
     * @param aCandidate хранимая сущность кандидата
     * @return DTO-объект кандидата
     */
    public CandidateDTO convertToDto(Candidate aCandidate) {
        String encodedImg = Base64.getEncoder().encodeToString(aCandidate.getMedia().getPhoto());
        return new CandidateDTO(aCandidate.getId(), aCandidate.getName(), encodedImg);
    }

    /**
     * Конвертирует DTO-объект в сущность кандидата.
     *
     * @param aCandidateDto DTO-объект кандидата
     * @param aPhoto        фотография кандидата
     * @return сущность кандидата
     */
    public Candidate convertToEntity(CandidateDTO aCandidateDto, byte[] aPhoto) {
        Candidate candidate = new Candidate();
        candidate.setName(aCandidateDto.getName());
        CandidateMedia media = new CandidateMedia();
        media.setPhoto(aPhoto);
        candidate.setMedia(media);
        return candidate;
    }

}
