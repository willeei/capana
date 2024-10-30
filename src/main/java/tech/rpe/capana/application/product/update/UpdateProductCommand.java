package tech.rpe.capana.application.product.update;

import java.util.Set;

import tech.rpe.capana.domain.product.ProductType;

public record UpdateProductCommand(
        long id,
        String title,
        long externalCode,
        String image,
        ProductType type,
        String description,
        Set<String> commercialOrigins,
        boolean active) {

    public static UpdateProductCommand with(
            final Long id,
            final String title,
            final long externalCode,
            final String image,
            final ProductType type,
            final String description,
            final Set<String> commercialOrigins,
            final boolean active
    ) {
        return new UpdateProductCommand(
                id,
                title,
                externalCode,
                image,
                type,
                description,
                commercialOrigins,
                active
        );
    }
}
