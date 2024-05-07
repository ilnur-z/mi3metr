package ru.zia.mi3metr.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO-объект результата этапа голосования.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VotingStageResultDTO {

    /** Порядковый номер этапа голосования */
    private int number;

    /** Идентификатор кандидата, за которого был отдан голос */
    private Integer votedCandidateId;

}
