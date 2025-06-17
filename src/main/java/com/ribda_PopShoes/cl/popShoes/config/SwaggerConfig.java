package com.ribda_PopShoes.cl.popShoes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
            .title("Api venta de clazados")
            .version("2.0")
            .description("Con esta api se puede vender calzado segun los estilos del cliente, ")


        );
    }
}
