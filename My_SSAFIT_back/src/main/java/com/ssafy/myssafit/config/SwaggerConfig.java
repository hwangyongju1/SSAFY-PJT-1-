package com.ssafy.myssafit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	  @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info()
	            		  .title("MY SSAFIT 프로젝트")
	            		  .description("SSAFY 관통 마지막 프로젝트입니다.")
	            		  .version("0.0.1")
	            		  .license(new License().name("SSAFY").url("https://www.ssafy.com"))
	              );
	  }
}
