package org.anb.mii.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {   
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .apiInfo(apiInfo())
          .select()                   
          .apis(RequestHandlerSelectors.basePackage("org.anb.mii.controller"))
          //.apis(RequestHandlerSelectors.any())
          .paths(PathSelectors.any())                          
          .build()
          .pathMapping("/");                                           
    }
	
	//create api metadata that goes at the top of the generated page
	private ApiInfo apiInfo() {
	  return new ApiInfoBuilder()
	  	.title("ATAM Nirbhar Bharat")
	  	.description("MII API  Spring Data Rest and Swagger.")
	  	.contact(new Contact("Mayank Verma","https://www.whitehalltechnologies.com/contact","mayank@whitehalltechnologies.com"))
	  	.license("©2016-2020 Whitehall Technologies")
	  	.version("1.0")
	  	.build();
	}
}