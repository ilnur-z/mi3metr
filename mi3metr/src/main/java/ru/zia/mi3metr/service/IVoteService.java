package ru.zia.mi3metr.service;

import ru.zia.mi3metr.model.User;
import ru.zia.mi3metr.model.dto.VotingStageDTO;
import ru.zia.mi3metr.model.dto.VotingStageResultDTO;

/**
 * Сервис голосования.
 */
public interface IVoteService {

    /**
     * Возвращает текущий этап голосования для заданного пользователя.
     *
     * @param aUser пользователь
     * @return данные текущего этапа голосования
     */
    VotingStageDTO getCurrentVoteStage(User aUser);

    /**
     * Обновляет данные текущего этапа голосования.
     *
     * @param aUser   пользователь
     * @param aResult результат этапа голосования
     */
    void updateVotingStage(User aUser, VotingStageResultDTO aResult);

}
