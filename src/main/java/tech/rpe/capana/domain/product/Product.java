package tech.rpe.capana.domain.product;

import java.time.Instant;
import java.util.Set;

import tech.rpe.capana.domain.AggregateRoot;
import tech.rpe.capana.domain.Identifier;
import tech.rpe.capana.domain.commercialorigin.CommercialOriginID;
import tech.rpe.capana.domain.validation.ValidationHandler;

public class Product extends AggregateRoot<ProductID> {

    private String title;
    private long externalCode;
    private String image;
    private ProductType type;
    private String description;
    private Set<CommercialOriginID> commercialOrigins;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Product(
            ProductID id,
            String title,
            long externalCode,
            String image,
            ProductType type,
            String description,
            Set<CommercialOriginID> commercialOrigins,
            boolean active,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        super(id);
        this.title = title;
        this.externalCode = externalCode;
        this.image = image;
        this.type = type;
        this.description = description;
        this.commercialOrigins = commercialOrigins;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Product newProduct(
            ProductID id,
            String title,
            long externalCode,
            String image,
            ProductType type,
            String description,
            Set<CommercialOriginID> commercialOrigins,
            boolean active
    ) {
        final var now = Instant.now();
        final var deletedAt = active ? null : now;
        return new Product(
                id, title, externalCode, image, type, description, commercialOrigins, active, now, now, deletedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validate'");
    }

}
