package ru.zia.mi3metr.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Этап голосования.
 */
@Entity
@Getter
@Setter
public class VotingStage {

    /** Идентификатор сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Голосование */
    @ManyToOne(optional = false)
    @JoinColumn(name = "vote_id", nullable = false)
    private Vote vote;

    /** Список кандидатов, за которых можно голосовать на данном этапе */
    @ManyToMany
    @JoinTable(name = "stage_candidate")
    List<Candidate> candidates;

}
