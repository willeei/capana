package tech.rpe.capana.application.product.retrieve.get;

import java.time.Instant;
import java.util.Set;

import tech.rpe.capana.domain.product.Product;
import tech.rpe.capana.domain.product.ProductType;

public record ProductOutput(
        long id,
        String title,
        long externalCode,
        String image,
        ProductType type,
        String description,
        Set<String> commercialOrigins,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt) {

    public static ProductOutput from(final Product aProduct) {
        return new ProductOutput(
                aProduct.getId().getValue(),
                aProduct.getTitle(),
                aProduct.getExternalCode(),
                aProduct.getImage(),
                aProduct.getType(),
                aProduct.getDescription(),
                aProduct.getCommercialOrigins(),
                aProduct.isActive(),
                aProduct.getCreatedAt(),
                aProduct.getUpdatedAt(),
                aProduct.getDeletedAt()
        );
    }
}
