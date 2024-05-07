package ru.zia.mi3metr.security.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.zia.mi3metr.repository.IUserRepository;
import ru.zia.mi3metr.security.model.RegistrationForm;

@Component
public class RegistrationFormValidator implements Validator {

    private final IUserRepository userRepository;

    public RegistrationFormValidator(IUserRepository aUserRepository) {
        super();
        userRepository = aUserRepository;
    }

    @Override
    public boolean supports(Class<?> aClazz) {
        return RegistrationForm.class.equals(aClazz);
    }

    @Override
    public void validate(Object aTarget, Errors aErrors) {
        if (aErrors.hasErrors()) {
            return;
        }
        RegistrationForm person = (RegistrationForm) aTarget;
        String userName = person.getUserName();
        boolean hasUser = userRepository.findByUsername(userName).isPresent();
        if (hasUser) {
            aErrors.rejectValue("userName", "", "Пользователь с таким именем уже существует");
        }
    }

}
