package tech.rpe.capana.domain.exceptions;

import java.io.Serial;
import java.util.Collections;
import java.util.List;

import tech.rpe.capana.domain.AggregateRoot;
import tech.rpe.capana.domain.Identifier;
import tech.rpe.capana.domain.validation.DomainError;

public class NotFoundException extends DomainException {

    @Serial
    private static final long serialVersionUID = -6314296608568347729L;

    protected NotFoundException(final String aMessage, final List<DomainError> anErrors) {
        super(aMessage, anErrors);
    }

    public static NotFoundException with(
            final Class<? extends AggregateRoot<?>> anAggregate,
            final Identifier id
    ) {
        final var anError = "%s with ID %s was not found".formatted(
                anAggregate.getSimpleName(),
                id.getValue()
        );

        return new NotFoundException(anError, Collections.emptyList());
    }

    public static NotFoundException with(final DomainError error) {
        return new NotFoundException(error.message(), List.of(error));
    }
}
