package com.esngramsearch.service;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import lombok.RequiredArgsConstructor;
import co.elastic.clients.elasticsearch._types.query_dsl.ChildScoreMode;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import com.esngramsearch.model.Product;
import com.esngramsearch.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @SuppressWarnings("null")
    public List<Product> suggestProducts(String keyword) {
        // Multi-match query targeting fields configured with NGram analyzer
        Query multiMatchQuery = Query.of(q -> q
                .multiMatch(m -> m
                        .query(keyword)
                        .fields("name^3", "brand") // Boosting Name relevancy over brand
                        .fuzziness("AUTO") // Allows minor typos
                )
        );

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(multiMatchQuery)
                .build();

        SearchHits<Product> searchHits = elasticsearchOperations.search(nativeQuery, Product.class);

        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
