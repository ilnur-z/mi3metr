package ru.zia.mi3metr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zia.mi3metr.model.User;

/**
 * Интерфейс репозитория пользователей.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    /**
     * Возвращает пользователя по имени.
     *
     * @param userName имя пользователя
     * @return пользователь
     */
    Optional<User> findByUsername(String userName);

}
