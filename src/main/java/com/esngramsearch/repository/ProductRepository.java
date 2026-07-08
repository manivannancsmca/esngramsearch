package com.esngramsearch.repository;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.esngramsearch.model.Product;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, String> {
   
    
}
