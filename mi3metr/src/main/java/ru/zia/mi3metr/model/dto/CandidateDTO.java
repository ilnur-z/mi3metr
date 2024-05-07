package ru.zia.mi3metr.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO-объект кандидата.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateDTO {

    /** Идентификатор кандидата */
    private Integer id;

    /** Имя кандидата */
    @NotEmpty
    private String name;

    /** Фотография кандидата в формате Base64 */
    private String photo;

}
