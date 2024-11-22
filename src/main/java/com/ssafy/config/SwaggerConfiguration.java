package com.ssafy.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//Swagger-UI 확인
//http://localhost/swagger-ui/index.html

@Configuration
public class SwaggerConfiguration {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info().title("DamDa API 명세서").description(
				"<h3>SSAFY API Reference for Developers</h3>Swagger를 이용한 DamDa API")
				.version("v1").contact(new io.swagger.v3.oas.models.info.Contact().name("lha0"));

		return new OpenAPI().components(new Components()).info(info);
	}
	
	@Bean
	public GroupedOpenApi allApi() {
		return GroupedOpenApi.builder().group("damda-all").pathsToMatch("/**").build();
	}

	@Bean
	public GroupedOpenApi tripApi() {
		return GroupedOpenApi.builder().group("damda-trip").pathsToMatch("/trip/**").build();
	}

	@Bean
	public GroupedOpenApi userApi() {
		return GroupedOpenApi.builder().group("damda-user").pathsToMatch("/user/**").build();
	}
	
	@Bean
	public GroupedOpenApi faqApi() {
		return GroupedOpenApi.builder().group("damda-faq").pathsToMatch("/faq/**").build();
	}
	
	@Bean
	public GroupedOpenApi journeApi() {
		return GroupedOpenApi.builder().group("damda-journey").pathsToMatch("/journey/**").build();
	}

}