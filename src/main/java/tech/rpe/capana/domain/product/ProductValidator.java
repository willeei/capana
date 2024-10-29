package tech.rpe.capana.domain.product;

import tech.rpe.capana.domain.validation.DomainError;
import tech.rpe.capana.domain.validation.ValidationHandler;
import tech.rpe.capana.domain.validation.Validator;

public class ProductValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 255;
    private static final int NAME_MIN_LENGTH = 1;

    private final Product product;

    protected ProductValidator(final Product aPortador, final ValidationHandler aHandler) {
        super(aHandler);
        this.product = aPortador;
    }

    @Override
    public void validate() {
        checkTitleConstraints();
    }

    private void checkTitleConstraints() {
        String name = this.product.getTitle();
        if (name == null) {
            this.validationHandler().append(new DomainError("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new DomainError("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new DomainError("'name' must be between 1 and 255 characters"));
        }
    }
}
