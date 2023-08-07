# c21 Configuración CORS GLOBAL


`+` ver proyecto: `21_CORSGlobalBase`.

## Configuración a nivel de método/clase

Si el número de métodos/clases es grande no es asumible.

Además, si queremos actualizar dicha configuración (lista de
orígenes) es difícilmente mantenible.

Spring permite realizar una configuración global.

Similar al uso de filtros.

Se puede combinar con `@CrossOrigin`:

- Definir algunos elementos a nivel global.
- Matizar otros a nivel de método/clase.



## Configuración básica (Spring Boot)

Configuración básica, sin filtros:

```
	@Configuration
	public class MyConfiguration {
	
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurerAdapter() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**");
				}
			};
		}
	}
```


Configuración más restrictiva para determnadas rutas para permitir, ciertos origines, ciertos orígenes:

```
@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistryregistry) {
			registry.addMapping("/producto/**")
				.allowedOrigins("http://localhost:9001")
				.allowedMethods("GET",
				"POST", "PUT", "DELETE")
				.maxAge(3600);
		}
	};
}
```

ver en la clase `MiConfiguracion` que permite lanzar beans.


## Tareas 

- Por ejemplo, limita que se puedan realizar peticiones GET y
POST, y trata de hacer una petición PUT o DELETE, para ver
si funciona.
- Intenta configurar dos orígenes diferentes, con
configuraciones distintas (uno que solo pueda hacer GET, y
otro que pueda realizar todos los métodos).


---

## Referencias

- Class: [ResponseEntityExceptionHandler](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html).

- CORS with Spring [CORS](https://www.baeldung.com/spring-cors#:~:text=Global%20CORS%20Configuration&text=This%20is%20similar%20to%20using,and%20POST%20methods%20are%20allowed.)

- [Cross-Origin Resource Sharing and Why We Need Preflight Requests](https://www.baeldung.com/cs/cors-preflight-requests)

- [Spring Boot CORS Configuration Example](https://howtodoinjava.com/spring-boot2/spring-cors-configuration/)