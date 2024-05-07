package ru.zia.mi3metr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zia.mi3metr.model.Candidate;

/**
 * Интерфейс репозитория кандидатов.
 */
@Repository
public interface ICandidateRepository extends JpaRepository<Candidate, Integer> {

    /**
     * Возвращает Топ 10 кандидатов с максимальным набором голосов.
     *
     * @return список кандидатов
     */
    List<Candidate> findTop10ByOrderByLikesDesc();

}
