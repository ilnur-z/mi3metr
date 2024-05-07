package ru.zia.mi3metr.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import lombok.Getter;
import lombok.Setter;

/**
 * Хранимая сущность голосования.
 */
@Entity
@Setter
@Getter
public class Vote {

    /** Идентификатор сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Пользователь, выполняющий процедуру голосования */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /** Этапы голосования */
    @OrderBy
    @OneToMany(mappedBy = "vote")
    private List<VotingStage> stages;

    /** Индекс (порядковый номер, начиная с 0) текущего этапа голосования */
    private int currentStageIndex;

    /** Признак завершения процедуры голосования */
    private boolean finished;

}
