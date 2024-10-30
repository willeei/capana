package tech.rpe.capana.domain.product;

import java.util.List;
import java.util.Optional;

import tech.rpe.capana.domain.pagination.Pagination;
import tech.rpe.capana.domain.pagination.SearchQuery;

public interface ProductGateway {

    Product create(Product product);

    void deleteById(ProductID anId);

    List<String> existsByIds(Iterable<ProductID> ids);

    Optional<Product> findById(ProductID anId);

    Pagination<Product> findAll(SearchQuery aQuery);

    Product update(Product product);
}
