package ru.zia.mi3metr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.zia.mi3metr.model.VotingStage;

/**
 * Интерфейс репозитория этапов голосования.
 */
@Repository
public interface IVotingStageRepository extends JpaRepository<VotingStage, Integer> {

}
