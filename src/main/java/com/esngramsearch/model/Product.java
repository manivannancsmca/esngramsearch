package com.esngramsearch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
@Document(indexName = "products")
@Setting(settingPath = "elastic-settings.json")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String id;

    @Field(
        type = FieldType.Text, 
        analyzer = "autocomplete_index_analyzer", 
        searchAnalyzer = "autocomplete_search_analyzer"
    )
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(
        type = FieldType.Text, 
        analyzer = "autocomplete_index_analyzer", 
        searchAnalyzer = "autocomplete_search_analyzer"
    )
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Keyword)
    private String category;
}