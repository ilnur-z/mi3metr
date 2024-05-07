package ru.zia.mi3metr.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO-объект этапа голосования.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VotingStageDTO {

    /**
     * Номер этапа голосования
     */
    private int number;

    /** Список кандидатов */
    List<CandidateDTO> candidates;

}
