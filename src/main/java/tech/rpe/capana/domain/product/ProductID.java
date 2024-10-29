package tech.rpe.capana.domain.product;

import tech.rpe.capana.domain.Identifier;

public class ProductID extends Identifier {

    private final long value;

    public ProductID(final long value) {
        this.value = value;
    }

    public static ProductID from(final long anId) {
        return new ProductID(anId);
    }

    @Override
    public long getValue() {
        return value;
    }
}
