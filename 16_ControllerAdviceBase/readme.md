# c16.1 Manejo de Errores con @ControllerAdvice

En el proyecto anterior se uso el manejo de errores a nivel personalizado.

Ahora sería la Gestion Globel de errores. Ya que `@ExceptionHandler`se ubica a 
nivel de método y funciona a nivel de clase.

## @ControllerAdvice

- Especializaciòn de @Component para clases que declaran los métodos 
`@ExceptionHandler`, `@InitBinder` o `@ModelAttribute` para ser compartidos entre 
múltiples clases de `@Controller`.
- `@RestControllerAdvice` es una especialización que unifica `@ControllerAdvice` y 
`@ResponseBody`. 
- Para manejar una excepción, es escogerá el **primer método** dentro de la clase 
`@ControllerAdvice` que esté anotado para tratar la excepción (con 
`@ExceptionHandler`). Hay q tener cuidado con el orden. 


- Pueden existir más de una clase anotada con `@ControllerAdvice`.
- Puede ser recomendable el uso de `@Order` o `@Priority` para establecer una 
presedencia en el tratamiento de errores.
- En caso de varias opciones para una excepción dentro de una clase, escogerá la 
más cercana a la raíz (FileNorFoundException vs IOException).


Si no indicamos nada, la anotación hace que la clase trate posibles
excepciones producidas en cualquier controlador.

Podemos acotar el radio de acción:

- `@ControllerAdvice("my.chosen.package")`
- `@ControllerAdvice(value = "my.chosen.package")`
- `@ControllerAdvice(basePackages = "my.chosen.package")`
- `@ControllerAdvice(basePackageClasses = MyClass.class)`
- `@ControllerAdvice(assignableTypes = MyController.class)`
- `@ControllerAdvice(annotations = RestController.class)`

## Ejemplo

```
@RestControllerAdvice
public class GlobalControllerAdvice {

	//  Manejo de errores personalzado

}
```

Incluimos dentro todos los `@ExceptionHandler` que hemos definido hasta ahora.

y algunas mejoras en la clase  modelo de error:

```
@Setter 
@Getter 
@RequiredArgsConstructor 
@NoArgsConstructor
public class ApiError {

	@NonNull
	private HttpStatus estado;

	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy	hh:mm:ss")
	private LocalDateTime fecha = LocalDateTime.now();

	@NonNull
	private String mensaje;
}
```






---

## Referencias

- [Guide to Spring Boot REST API Error Handling](https://www.toptal.com/java/spring-boot-rest-api-error-handling).
- Para más información sobre la librería: [ModelMapper getting started](http://modelmapper.org/getting-started/).