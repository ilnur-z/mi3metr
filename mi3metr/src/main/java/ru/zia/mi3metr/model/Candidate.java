package ru.zia.mi3metr.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Хранимая сущность кандидата.
 */
@Entity
@Getter
@Setter
@Table(name = "candidate")
public class Candidate {

    /** Идентификатор сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Наименование */
    @Column(name = "name")
    private String name;

    /** Количество лайков (отданных голосов) */
    @Column(name = "likes")
    private int likes;

    @OneToOne(cascade = { CascadeType.REMOVE, CascadeType.PERSIST,
            CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "media_id")
    private CandidateMedia media;

}
