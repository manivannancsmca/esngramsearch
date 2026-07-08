package com.esngramsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.esngramsearch.repository")
public class EsngramsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsngramsearchApplication.class, args);
	}

}
