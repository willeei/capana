package tech.rpe.capana.domain.validation.handler;

import java.util.ArrayList;
import java.util.List;

import tech.rpe.capana.domain.exceptions.DomainException;
import tech.rpe.capana.domain.validation.DomainError;
import tech.rpe.capana.domain.validation.ValidationHandler;

public class Notification implements ValidationHandler {

    private final List<DomainError> errors;

    private Notification(final List<DomainError> errors) {
        this.errors = errors;
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
    }

    public static Notification create(final Throwable t) {
        return create(new DomainError(t.getMessage()));
    }

    public static Notification create(final DomainError anError) {
        return new Notification(new ArrayList<>()).append(anError);
    }

    @Override
    public Notification append(final DomainError anError) {
        this.errors.add(anError);
        return this;
    }

    @Override
    public Notification append(final ValidationHandler anHandler) {
        this.errors.addAll(anHandler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final DomainException ex) {
            this.errors.addAll(ex.getErrors());
        } catch (final Exception t) {
            this.errors.add(new DomainError(t.getMessage()));
        }
        return null;
    }

    @Override
    public List<DomainError> getErrors() {
        return this.errors;
    }
}
