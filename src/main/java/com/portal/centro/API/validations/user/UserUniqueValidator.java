package com.portal.centro.API.validations.user;

import com.portal.centro.API.model.User;
import com.portal.centro.API.repository.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.Optional;

public record UserUniqueValidator(UserRepository userRepository) implements ConstraintValidator<UserUniqueConstraint, User> {

    private static final String messageConstraint = "O %s informado já está sendo utilizado por outro cadastro. Por favor, informe outro.";

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        return userValid(user,
                Optional.ofNullable(userRepository.findByEmail(user.getEmail())),
                constraintValidatorContext,
                String.format(messageConstraint, "E-mail"),
                "email"
        );

    }

    private Boolean userValid(User user, Optional<User> userOptional, ConstraintValidatorContext constraintValidatorContext, String messageConstraint, String fieldName) {
        if ((userOptional.isPresent()) && (!Objects.equals(userOptional.get().getId(), user.getId()))) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(messageConstraint)
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
