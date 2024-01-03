package com.Inventario.ControldeInventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ControldeInventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControldeInventarioApplication.class, args);
	}
        
        //Para realizar la configuración del CORS y se deben agregar dos lineas en el filtro de abajo
    @Bean
    public WebMvcConfigurer corsConfigurer2() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost")
                        .allowCredentials(true)
                        .allowedHeaders("Origin", "Authorization", "Content-Type")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

}
