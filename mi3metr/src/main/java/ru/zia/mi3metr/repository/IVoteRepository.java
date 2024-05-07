package ru.zia.mi3metr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zia.mi3metr.model.User;
import ru.zia.mi3metr.model.Vote;

/**
 * Интерфейс репозитория голосований.
 */
@Repository
public interface IVoteRepository extends JpaRepository<Vote, Integer> {

    /**
     * Возвращает голосование для заданного пользователя.
     *
     * @param aUser пользователь
     * @return голосование
     */
    Optional<Vote> findByUser(User aUser);

}
