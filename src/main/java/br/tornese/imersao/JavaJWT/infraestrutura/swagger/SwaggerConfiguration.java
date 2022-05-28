package br.tornese.imersao.JavaJWT.infraestrutura.swagger;

import com.google.common.base.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static final String EMAIL = "danilo@torneseumprogramador.com.br";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(apis())
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }
    
    private Predicate<RequestHandler> apis() {
        return RequestHandlerSelectors.basePackage("br.tornese.imersao.JavaJWT.controllers");
    }
    
    private ApiInfo apiInfo() {
   	 
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title ("JavaJWT API")
                .contact(EMAIL)
                .description ("Documentação da API criada com alunos da comunidade do torne-se um programador.")
                .version("1.0.0")
                .build();
 
        return apiInfo;
    }
}