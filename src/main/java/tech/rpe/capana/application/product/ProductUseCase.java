package tech.rpe.capana.application.product;

import tech.rpe.capana.application.product.create.CreateProductCommand;
import tech.rpe.capana.application.product.create.CreateProductOutput;
import tech.rpe.capana.application.product.retrieve.get.ProductOutput;
import tech.rpe.capana.application.product.retrieve.list.ProductListOutput;
import tech.rpe.capana.application.product.update.UpdateProductCommand;
import tech.rpe.capana.application.product.update.UpdateProductOutput;
import tech.rpe.capana.domain.pagination.Pagination;
import tech.rpe.capana.domain.pagination.SearchQuery;

public abstract sealed class ProductUseCase permits DefaultProductUseCase {

    public abstract CreateProductOutput create(CreateProductCommand aCommand);

    public abstract UpdateProductOutput update(UpdateProductCommand aCommand);

    public abstract void delete(Long anId);

    public abstract ProductOutput get(Long anId);

    public abstract Pagination<ProductListOutput> list(SearchQuery aQuery);
}
