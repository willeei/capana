package tech.rpe.capana.domain.commercialorigin;

import tech.rpe.capana.domain.Identifier;

public class CommercialOriginID extends Identifier {

    private final long value;

    public CommercialOriginID(final long value) {
        this.value = value;
    }

    public static CommercialOriginID from(final long anId) {
        return new CommercialOriginID(anId);
    }

    @Override
    public long getValue() {
        return value;
    }
}
