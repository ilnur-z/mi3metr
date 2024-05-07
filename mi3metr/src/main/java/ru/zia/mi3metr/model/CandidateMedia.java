package ru.zia.mi3metr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Хранимая сущность медиаданных кандидата. Служит для lazy-подгрузки медиаданных кандидата.
 */
@Entity
@Table(name = "candidate_media")
@Getter
@Setter
public class CandidateMedia {

    /** Идентификатор сущности */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** BLOB фотографии кандидата */
    @Lob
    private byte[] photo;

}
