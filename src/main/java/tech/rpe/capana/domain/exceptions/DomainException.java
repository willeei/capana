package tech.rpe.capana.domain.exceptions;

import java.io.Serial;
import java.util.List;

import tech.rpe.capana.domain.validation.DomainError;

public class DomainException extends NoStacktraceException {

    @Serial
    private static final long serialVersionUID = -2367794948726104488L;

    protected final transient List<DomainError> errors;

    protected DomainException(final String aMessage, final List<DomainError> anErrors) {
        super(aMessage);
        this.errors = anErrors;
    }

    public static DomainException with(final DomainError anErrors) {
        return new DomainException(anErrors.message(), List.of(anErrors));
    }

    public static DomainException with(final List<DomainError> anErrors) {
        return new DomainException("", anErrors);
    }

    public List<DomainError> getErrors() {
        return errors;
    }
}
