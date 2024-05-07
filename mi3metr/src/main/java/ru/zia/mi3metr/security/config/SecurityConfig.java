package ru.zia.mi3metr.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.zia.mi3metr.repository.IUserRepository;

/**
 * Конфигурация аутентификации и авторизации.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Возвращает бин кодирования паролей.
     *
     * @return бин кодирования паролей
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Возвращает сервис security-данных пользователей.
     *
     * @param userRepository репозиторий пользователей
     * @return сервис security-данных пользователей
     */
    @Bean
    UserDetailsService userDetailsService(IUserRepository userRepository) {
        return (userName) -> {
            var u = userRepository.findByUsername(userName);
            return u.orElseThrow(() -> new UsernameNotFoundException(
                    "User \"%s\" not found".formatted(userName)));
        };
    }

    /**
     * Возвращает цепочку фильтров запросов.
     *
     * @param http security-конфигурация HTTP
     * @return цепочку фильтров запросов
     * @throws Exception при ошибке
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        conf -> conf.requestMatchers("/vote", "/candidate", "/candidate/**")
                                .hasRole("USER").requestMatchers("/", "/**").permitAll())
                .formLogin(login -> login.defaultSuccessUrl("/")).build();
    }

}
