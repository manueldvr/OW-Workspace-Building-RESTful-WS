# c17.2 Manejo de Errores con @ControllerAdvice

En el proyecto anterior se uso el manejo de errores Global.

Ahora, ¿No hay ninguna interfaz/clase base que podamos extender?

-Sí:

## @ResonseEntityExceptionHandler

Según la propia documentación de Spring, se trata de una clase base conveniente 
para las clases `@ControllerAdvice`que desean proporcionae un manejo centralizado 
de excepciones en todos los métodos `@RequestMapping` a través de los métodos `@ExceptionHandler`.

Esta clase tiene varios métodos para tender todo tipo de excepciones.

Su método `handleExceptionInternalhandleExceptionInternal` sirve como único lugar 
para customizar el body de respuesta.

#### ResponseEntityExceptionHandler

Proporciona una larga lista de métodos que podemos sobrescribir
- `handleExceptionInternal`
- `handleMissingPathVariable`
- `handleTypeMismatch`

También podemos añadir los métodos de nuestra cosecha, como
hasta ahora.

#### handleExceptionInternal

- Según la documentación, es un único lugar para personalizar el
cuerpo de respuesta de todos los tipos de excepción.

Si lo sobrescribimos, y lo personalizamos con nuestra clase
`APIError`, el resto de errores manejados por la clase base
`ResponseEntityExceptionHandler` utilizarán nuestra clase de error.

## Ejemplo

```

```


```

```






---

## Referencias

- Class: [ResponseEntityExceptionHandler](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html).

