package tech.rpe.capana.application.product.create;

import java.util.Set;

import tech.rpe.capana.domain.product.ProductType;

public record CreateProductCommand(
        String title,
        long externalCode,
        String image,
        ProductType type,
        String description,
        Set<String> commercialOrigins,
        boolean active) {

    public static CreateProductCommand with(
            final String title,
            final long externalCode,
            final String image,
            final ProductType type,
            final String description,
            final Set<String> commercialOrigins,
            final boolean active
    ) {
        return new CreateProductCommand(
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
