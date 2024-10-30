package tech.rpe.capana.application.product.retrieve.list;

import java.time.Instant;
import java.util.Set;

import tech.rpe.capana.domain.product.Product;
import tech.rpe.capana.domain.product.ProductType;

public record ProductListOutput(
        long id,
        String title,
        long externalCode,
        ProductType type,
        String description,
        Set<String> commercialOrigins,
        boolean active,
        Instant createdAt,
        Instant updatedAt) {

    public static ProductListOutput from(final Product aProduct) {
        return new ProductListOutput(
                aProduct.getId().getValue(),
                aProduct.getTitle(),
                aProduct.getExternalCode(),
                aProduct.getType(),
                aProduct.getDescription(),
                aProduct.getCommercialOrigins(),
                aProduct.isActive(),
                aProduct.getCreatedAt(),
                aProduct.getUpdatedAt()
        );
    }
}
