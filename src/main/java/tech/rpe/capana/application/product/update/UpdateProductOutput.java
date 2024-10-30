package tech.rpe.capana.application.product.update;

import tech.rpe.capana.domain.product.Product;

public record UpdateProductOutput(Long id) {

    public static UpdateProductOutput from(final Product aProduct) {
        return new UpdateProductOutput(aProduct.getId().getValue());
    }

}
