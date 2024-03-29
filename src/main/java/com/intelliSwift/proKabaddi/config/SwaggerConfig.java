package com.intelliSwift.proKabaddi.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	
	    @Bean
	    public Docket productApi() {
	    	
	    	List<SecurityScheme> schemeList = new ArrayList<>();	    	
			schemeList.add(new BasicAuth("basicAuth"));
			
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select().apis(RequestHandlerSelectors.basePackage("com.intelliSwift.proKabaddi"))
	                .paths(PathSelectors.any())
	                .build().apiInfo(metaInfo()).securitySchemes(schemeList); 	    
	}
	    
	
	  private ApiInfo metaInfo() {
	  
	  
	  @SuppressWarnings("deprecation") ApiInfo apiInfo = new
	  ApiInfo("Prokabaddi Appplication", "IntelliSwift Pro Kabaddi app","version 1.0",
	  null, null, null, null);
	  
	  return apiInfo; }
	 
}
