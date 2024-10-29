package tech.rpe.capana.domain.validation.handler;

import java.util.List;

import tech.rpe.capana.domain.exceptions.DomainException;
import tech.rpe.capana.domain.validation.DomainError;
import tech.rpe.capana.domain.validation.ValidationHandler;

public class ThrowsValidationHandler implements ValidationHandler {

    @Override
    public ValidationHandler append(final DomainError anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new DomainError(ex.getMessage()));
        }
    }

    @Override
    public List<DomainError> getErrors() {
        return List.of();
    }
}
