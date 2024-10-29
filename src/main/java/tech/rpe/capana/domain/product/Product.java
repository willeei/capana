package tech.rpe.capana.domain.product;

import java.time.Instant;
import java.util.Set;

import tech.rpe.capana.domain.AggregateRoot;
import tech.rpe.capana.domain.commercialorigin.CommercialOriginID;
import tech.rpe.capana.domain.exceptions.NotificationException;
import tech.rpe.capana.domain.utils.InstantUtils;
import tech.rpe.capana.domain.validation.ValidationHandler;
import tech.rpe.capana.domain.validation.handler.Notification;

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
            final ProductID id,
            final String title,
            final long externalCode,
            final String image,
            final ProductType type,
            final String description,
            final Set<CommercialOriginID> commercialOrigins,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
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

        selfValidate();
    }

    public static Product newProduct(
            final ProductID id,
            final String title,
            final long externalCode,
            final String image,
            final ProductType type,
            final String description,
            final Set<CommercialOriginID> commercialOrigins,
            final boolean active
    ) {
        final var now = InstantUtils.now();
        final var deletedAt = active ? null : now;
        return new Product(
                id, title, externalCode, image, type, description, commercialOrigins, active, now, now, deletedAt);
    }

    public static Product with(
            final ProductID id,
            final String title,
            final long externalCode,
            final String image,
            final ProductType type,
            final String description,
            final Set<CommercialOriginID> commercialOrigins,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new Product(
                id,
                title,
                externalCode,
                image,
                type,
                description,
                commercialOrigins,
                active,
                createdAt,
                updatedAt,
                deletedAt);
    }

    public static Product with(final Product aProduct) {
        return new Product(
                aProduct.getId(),
                aProduct.getTitle(),
                aProduct.getExternalCode(),
                aProduct.getImage(),
                aProduct.getType(),
                aProduct.getDescription(),
                aProduct.getCommercialOrigins(),
                aProduct.isActive(),
                aProduct.getCreatedAt(),
                aProduct.getUpdatedAt(),
                aProduct.getDeletedAt());
    }

    public void update(
            final String title,
            final long externalCode,
            final String image,
            final ProductType type,
            final String description,
            final Set<CommercialOriginID> commercialOrigins,
            final boolean isActive
    ) {

        if (isActive) {
            this.activate();
        } else {
            this.deactivate();
        }
        this.title = title;
        this.externalCode = externalCode;
        this.image = image;
        this.type = type;
        this.description = description;
        this.commercialOrigins = commercialOrigins;
        this.active = isActive;
        this.updatedAt = Instant.now();
        this.deletedAt = active ? null : this.deletedAt;

        selfValidate();
    }

    public void deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
    }

    public void activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    public String getTitle() {
        return title;
    }

    public long getExternalCode() {
        return externalCode;
    }

    public String getImage() {
        return image;
    }

    public ProductType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Set<CommercialOriginID> getCommercialOrigins() {
        return commercialOrigins;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate Product", notification);
        }
    }
}
