package ru.zia.mi3metr.security.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.zia.mi3metr.model.User;

/**
 * Данные пользователя для регистрации.
 */
@Data
public class RegistrationForm {

    /** Имя пользователя */
    @NotEmpty
    private String userName;

    /** Пароль пользователя */
    @NotEmpty
    private String password;

    /**
     * Конвертирует данные регастрационной формы в сущность пользователя.
     *
     * @param passwordEncoder кодировщик паролей
     * @return сущность пользователя
     */
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(userName, passwordEncoder.encode(password));
    }

}
