package ru.zia.mi3metr.security.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import ru.zia.mi3metr.repository.IUserRepository;
import ru.zia.mi3metr.security.model.RegistrationForm;
import ru.zia.mi3metr.security.utils.RegistrationFormValidator;

/**
 * Контроллер регистрации новых пользователей.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    /** Репозиторий пользователей */
    private final IUserRepository userRepository;

    /** Кодировщик паролей */
    private final PasswordEncoder passwordEncoder;

    private final RegistrationFormValidator validator;

    /**
     *
     * @param aUserRepository  репозиторий пользователей
     * @param aPasswordEncoder кодировщик паролей
     */
    public RegistrationController(IUserRepository aUserRepository, PasswordEncoder aPasswordEncoder,
            RegistrationFormValidator aValidator) {
        super();
        userRepository = aUserRepository;
        passwordEncoder = aPasswordEncoder;
        validator = aValidator;
    }

    /**
     * Отображает форму регистрации новых пользователей.
     *
     * @return имя шаблона
     */
    @GetMapping
    public String registerForm(@ModelAttribute RegistrationForm aForm) {
        return "register";
    }

    /**
     * Регистрирует пользователя.
     *
     * @param aForm данные регистрации пользователя
     * @return имя шаблона для открытия в случае успеха
     */
    @PostMapping
    public String processRegistration(@Valid RegistrationForm aForm,
            BindingResult aValidationResult) {
        validator.validate(aForm, aValidationResult);
        if (aValidationResult.hasErrors()) {
            return "register";
        }
        userRepository.save(aForm.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
