package com.danicode.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Cliente http para acceder a recursos que están en otros microservicios
 * Otra forma es usar Feign (Netflix) forma parte de springcloud => clic derecho |
 * spring | Edit starters | Spring Cloud Routing | OpenFeign (agregar lalibreria)
 * Para configurar, ir a la clase principal (SpringbootServicioApplication.java)
 * */

@Configuration
public class AppConfig {

	@Bean("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
}
