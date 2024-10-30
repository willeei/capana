package tech.rpe.capana.application.product.create;

import tech.rpe.capana.domain.product.Product;

public record CreateProductOutput(Long id) {

    public static CreateProductOutput from(final Product aProduct) {
        return new CreateProductOutput(aProduct.getId().getValue());
    }

}
