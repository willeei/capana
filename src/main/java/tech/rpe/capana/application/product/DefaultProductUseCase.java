package tech.rpe.capana.application.product;

import java.util.Objects;

import tech.rpe.capana.application.product.create.CreateProductCommand;
import tech.rpe.capana.application.product.create.CreateProductOutput;
import tech.rpe.capana.application.product.retrieve.get.ProductOutput;
import tech.rpe.capana.application.product.retrieve.list.ProductListOutput;
import tech.rpe.capana.application.product.update.UpdateProductCommand;
import tech.rpe.capana.application.product.update.UpdateProductOutput;
import tech.rpe.capana.domain.exceptions.NotFoundException;
import tech.rpe.capana.domain.exceptions.NotificationException;
import tech.rpe.capana.domain.pagination.Pagination;
import tech.rpe.capana.domain.pagination.SearchQuery;
import tech.rpe.capana.domain.product.Product;
import tech.rpe.capana.domain.product.ProductGateway;
import tech.rpe.capana.domain.product.ProductID;
import tech.rpe.capana.domain.validation.handler.Notification;

public non-sealed class DefaultProductUseCase extends ProductUseCase {

    private final ProductGateway productGateway;

    public DefaultProductUseCase(final ProductGateway productGateway) {
        this.productGateway = Objects.requireNonNull(productGateway);
    }

    @Override
    public CreateProductOutput create(final CreateProductCommand aCommand) {
        final var notification = Notification.create();

        final var aProduct = notification.validate(
                () -> Product.newProduct(
                        aCommand.title(),
                        aCommand.externalCode(),
                        aCommand.image(),
                        aCommand.type(),
                        aCommand.description(),
                        aCommand.active()
                )
        );

        if (notification.hasError()) {
            throw new NotificationException("Could not create aggregate product", notification);
        }

        aProduct.addCommercialOrigins(aCommand.commercialOrigins());

        return CreateProductOutput.from(this.productGateway.create(aProduct));
    }

    @Override
    public UpdateProductOutput update(final UpdateProductCommand aCommand) {
        final var aProductID = ProductID.from(aCommand.id());
        final var aTitle = aCommand.title();
        final var aExternalCode = aCommand.externalCode();
        final var aImage = aCommand.image();
        final var aType = aCommand.type();
        final var aDescription = aCommand.description();
        final var aCommercialOrigins = aCommand.commercialOrigins();
        final var isActive = aCommand.active();

        final var aProduct = this.productGateway.findById(aProductID)
                .orElseThrow(() -> NotFoundException.with(Product.class, aProductID));

        final var notification = Notification.create();
        notification.validate(() -> aProduct.update(
                aTitle,
                aExternalCode,
                aImage,
                aType,
                aDescription,
                aCommercialOrigins,
                isActive
        ));

        if (notification.hasError()) {
            throw new NotificationException("Could not update aggregate product %s".formatted(aCommand.id()),
                    notification);
        }

        return UpdateProductOutput.from(this.productGateway.update(aProduct));
    }

    @Override
    public void delete(final Long anId) {
        this.productGateway.deleteById(ProductID.from(anId));
    }

    @Override
    public ProductOutput get(final Long anId) {
        final var aProductID = ProductID.from(anId);
        return this.productGateway.findById(aProductID)
                .map(ProductOutput::from)
                .orElseThrow(() -> NotFoundException.with(Product.class, aProductID));
    }

    @Override
    public Pagination<ProductListOutput> list(final SearchQuery aQuery) {
        return this.productGateway.findAll(aQuery).map(ProductListOutput::from);
    }
}
