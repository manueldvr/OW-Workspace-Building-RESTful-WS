# Building a RESTful Web Service

This guide walks you through the process of creating a “Hello, World” RESTful web service with Spring.

## Considerations

Motivated by the HTTP technology and several small RFC standards.

Characteristics:

- Basado en el protocolo HTTP
- Stateless
- Representados por una URI
- Interfaz uniforme
- Sistema de capas


### REST vs RPC

- RPC (remote procedure call) function or service oriented.
- REST resource oriented, -orientado a ofrecer recursos.

URI examples:

| RPC      | REST |
| :--- | :--- |
| /myapi/beerService/getAll      | /myapi/beers       |
| /myapi/beerService/getById   | /myapi/beer/123        |


### MVC vs RESTful

A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. 

**Rather than relying on a view technology to perform server-side rendering** of the greeting data to HTML, the RESTful web service controller populates and returns a Greeting object.<br> 
The object data will be written directly to the HTTP response as JSON.

This code uses Spring `@RestController` annotation, which marks the class as a controller where every method returns a domain object instead of a view. It is shorthand for including both `@Controller` and `@ResponseBody`.

The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support, you need not do this conversion manually. Because **Jackson 2** is on the classpath, Spring’s `MappingJackson2HttpMessageConverter` is automatically chosen to convert the Greeting instance to JSON.

`@SpringBootApplication` is a convenience annotation that adds all of the following:
- `@Configuration`: Tags the class as a source of bean definitions for the application context.
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings. For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors, such as setting up a `DispatcherServlet`.
- `@ComponentScan`: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.


### Recursos vs Representación

- REST está orientado al concepto de recurso
- Cada recurso debe ser accesible a través de una URI
- El servidor puede ofrecer diferentes representaciones de un
mismo recurso (por ejemplo en XML, JSON o HTML).

### Ventajas del uso de REST

- Separación cliente - servidor
- Visibilidad, fiabilidad y escalabilidad
- Heterogeneidad
- Variedad de formatos: JSON, XML, …
- En general, es más rápido y utiliza menos ancho de banda.

### HTTP protocol

Protocolo de transferencia de hipertexto.
- Permite transferencias de
información en la WWW.
- Desarrollado por el WWW
Consortium y la IETF
- Versión actual: HTTP/2
(2.4.39 - 02/04/2019)


#### Características de HTTP
- Esquema de petición - respuesta (request/response)
- Sin estados
- HTTP/1.X orientador a carácter, HTTP/2 binario.
- Conjunto de verbos u operaciones.


#### Petición / respuesta
- El cliente (agente de usuario-user agent) realiza una petición
enviando un mensaje al servidor.
- El servidor recibe la petición, y envía un mensaje de respuesta al
cliente.


#### Formato del mensaje
- Válido tanto para request como response.
- Solo cambia la línea inicial.
- Structure:  Header + BODY

Metadatos que contextualizan el mensaje

- Estructura clave : valor

Content-Type: application/json


#### Métodos (a.k.a verbos)
- Tipos de peticiones diferentes.
- Indica el tipo de acción a realizar sobre el recurso indicado.
- Más conocidos:

- - GET: solicita un recurso al servidor
- - POST: envía información para crear un nuevo recurso
- - PUT: actualiza un recurso de forma completa
- - DELETE: borra un recurso

#### Códigos de respuesta
- El servidor debe incluirlos en la primera línea
- Indica qué ha pasado con la petición.
- Cada código tiene un significado concreto.
- Código numérico de 3 cifras


#### Tipos de encabezados
- De petición: solo los encontraremos en peticiones
- De respuesta: solo los encontraremos en mensajes de respuesta
- Petición y respuesta: pueden aparecer en mensajes de ambos
tipos.

#### Clasificación según función
- Capacidades aceptadas por el agente que envía el mensaje
○ Accept (tipo MIME)
○ Accept-Charset (código de caracteres)
○ Accept-Encoding
○ Accept-Language
○ User-Agent (descripción del cliente)
○ Allow (métodos permitidos)

####Clasificación según función
- Que describen el contenido:
○ Content-Type (tipo MIME del contenido)
○ Content-Length
○ Content-Range
○ Content-Encoding
○ Content-Language
○ Content-Location


### Clasificación según función
- Que hacen referencia a las URIs:
○ Location (indica donde está el contenido)
○ Referer (indica el origen de la petición)
- Para autenticación:
○ Authorization
○ WWW-Authenticate


### Métodos
- GET:
Solicita un recurso al servidor.
Solo deben recuperar datos, y no deben tener otro efecto.
- POST:
Envía datos para que sean procesados.
Los datos se incluyen en el cuerpo de la petición.
Creación / NO Edición.
- PUT:
 Suele utilizarse en operaciones de actualización completa. Debe ser idempotente
- DELETE:
 Borra el recurso especificado
- HEAD:
 Idéntico a GET, pero la respuesta no devuelve el cuerpo. Útil para obtener metadatos
- OPTIONS:
 Devuelve la lista de métodos HTTP que soporta un recurso.
- PATCH:
 Actualización parcial de un recurso


---

## Soporte para REST
- Controladores orientadores a REST con `@RestController`
○ Combinación de `@Controller` y `@ResponseBody`
○ Se modifica el mecanismo de renderización de la vista
○ En lugar de redirigirnos a una plantilla Thymeleaf, JSP… se devuelve directamente el contenido que se envía al cliente.


## HttpMessageConverter
Ya que no vamos a utilizar un motor de plantillas en la vista,
tenemos que entregar al cliente el contenido en algún formato.

Spring Boot, al incluir la dependencia starter web, incluye algunos
conversores por defecto.

- StringHttpMessageConverter: convierte a cadenas de
caracteres.
- MappingJackson[2]HttpMessageConverter: convierte a JSON
usando Jackson (o Jackson 2) si está presente en el
classpath.

Algunas clases que utilizaremos

- `HttpEntity<T>`: representa una petición o respuesta HTTP

- - `RequestEntity<T>`
- - `ResponseEntity<T>`: añade el código de estado (`HttpStatus`)

- `MediaType`: subclase de MIME. Listado de constantes.
- `HttpHeaders`: representa los encabezados de una petición o de una respuesta.


---


## What You Will Build

You will build a service that will accept HTTP GET requests at:

`http://localhost:8080/greeting`
 
It will respond with a JSON representation of a greeting, as the following listing shows:

```
{"id":1,"content":"Hello, World!"}
```

You can customize the greeting with an optional `name` parameter in the query string, as the following listing shows:

`http://localhost:8080/greeting?name=User`

The `name` parameter value overrides the default value of `World` and is reflected in the response, as the following listing shows:

{"id":1,"content":"Hello, User!"}

## Source study



```
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
```


## Clases y Anotaciones de Spring

Clases (que necesitamos ¡ya!)
`ResponseEntity<T>`

- Nos permite manejar de una forma más conveniente la
respuesta que enviamos al cliente.
- Hereda de HttpEntity<T>
- Nos permite indicar el código de respuesta, qué se envía en el
cuerpo, responder peticiones sin el mismo, ...

## Codigos de retorno

@GetMapping: obtener
- Devolvemos 200 OK si localizamos el recurso
- Si no, devolvemos 404 Not Found

@PostMapping: insertar/crear
- Devolvemos 201 Created

@PutMapping: modificar/update
- Devolvemos 200 OK si localizamos y modificamos el recurso
- Si no, devolvemos 404 Not Found.

@DeleteMapping: borrar
- Devolvemos 204 No Content

---

## Patron DTO

### 1ro definición de Entidad

Si son de JPA version 2.0: <br>
Requisitos:
- Constructor sin argumento y puede tener otros constructores
- El constructor sin atgs. puede ser público o protegido.
- Debe tener una clase de superior, aunque sea Object, no puede ser Enumeraciòn o Interfaz.
- No debería ser final, ni serlo ningún metodo o variable de instancia persistente de dicha clase.
- Si una instancia de la entidad se va a pasar por valor, por ejemplo a través de una interfaz remota, debería ser serializable.
- Pueden ser clases abstractas o concretas.
- Pueden extender clases que no son entidades.

Su objetivo es modelar la lógica de la capa de negocio.

### DTO : Data Transfer Object

Objeto POJO que agrupa datos de la capa de negocio y sirve para transferir datos entre diferentes capas.<br>
- Objeto Plano -POJO-. Nada de lógica del negocio.
- Getters, Setters o constructores necesarios.
- Serializable.
- Puede tener parte de los datos de una sola entidad.
- Puede tener algunos datos de más de una entidad.
- Puede aglutinar todos los datos de varias entidades.


También conocido como **Value Object**.

Pensado para *aligerar* las transacciones entre cliente/servidor.<br>

ejemplo: un DTO podrìa ser la construcciòn de una clase ProductoDTO en base a a la unión de los datos de Producto y Categoría.

#### Cómo y dónde usarlos

Suponiendo que tenemos un catálogo de productos como el de nuestro ejemplo:
- GET /producto/ <br>
EL listado de todos los productos puede ser orientado a ser visualizado en una paguina con todos los productos (on un subconjnto si x ejemplo hay paginaión). Y no necesariamiente todos los campos del objeto Producto, tal vez solo el nombre.
- GET /producto/{id} <br>
En este caso es un producto individual. No necesariamente el DTO anterior. Una alternativa es usar la Entidad, otra es crear un DTO específico.

#### Multiples DTOs para un solo BO -Business Object-

Es posible tener más de un DTO para un solo OB.

Ejemplo es el *Usuario*, según la capa puede ser:
- UserEntity para la capa de *Persistencia*
- User para la capa de *capa de Seguridad*
- CreateUserDTO para la *petición de creación*
- GetUserDTO para la *solicitud de datos*


#### Implementación de DTOs

**Manualmente**
- con un builder (lombok)

**ModelMapper**

**JsonViews**
- A través de anotaciones, un mimso objeto puede devolver más o menos campos.




#### Value Object - VO vs DTO

A value object is a simple object whose equality isn't based on identity. <br> 
A DTO is an object used to transfer data between software application subsystems, usually between business layers and UI. It is focused just on plain data, so it doesn't have any behaviour.

VO does not have to map directly against a domain entity, rather than to some fields of it or a diferent "picture" of it.


## Cómo transformar BO <--> DTO

Puede ser manualmente.

Con *ModelMapper*
- Evita código repetitivo
- Facilita la creación de DTO mediante asignación dinámica.
- CoC y configurable.


##### 1_ pom.xml

```
<dependency>
	<groupId>org.modelmapper</groupId>
	<artifactId>modelmapper</artifactId>
	<version>2.3.5</version>
</dependency>
```

##### 2_ Configuración básica

Creamos un bean de tipo ModelMapper para toda nuestra aplicación.

```
@Bean
public ModelMapper modelMapper() {
return new ModelMapper();
}
```


##### 3_ Creamos nuestro DTO

```
@Getter @Setter
public class ProductoDTO {
	private long id;
	private String nombre;
	private String categoriaNombre;
}
```

##### 4_ Dónde hacer la transformación

Explícitamente, lo podemos hacer en el controlador.

Sin embargo, creamos un componente independiente, que inyectaremos para usar donde haga falta.

```
@Component 
@RequiredArgsConstructor
public class ProductoDTOConverter {

	private final ModelMapper modelMapper;

	public ProductoDTO convertToDto(Producto producto) {
		return modelMapper.map(producto, ProductoDTO.class);
	}
}
```

##### 5_ Dónde hacer la transformación

```
@RestController 
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoDTOConverter productoDTOConverter;

	@GetMapping("/producto")
	public ResponseEntity<?> obtenerTodos() {
		List<Producto> result = productoRepositorio.findAll();
		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			List<ProductoDTO> dtoList = result.stream()
							.map(productoDTOConverter::convertToDto)
							.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}
}
```

##### Reto
- Crear un nuevo DTO para editar un producto. Reflexionar sobre si
es necesario o se puede reutilizar CreateProductoDTO.
- Añadir al conversor de producto un método que permita
transformar rápidamente un CreateProductoDTO en un Producto.
- Añadir más atributos al modelo de Producto, pero sin cambiar los
atributos de ProductoDTO, la evidenciar de una manera mayor la
diferencia de solicitar uno u otro. Se puede usar
https://mockaroo.com/ para obtener datos de ejemplo.





<br><br><br>

---


## Referencias


1. This guide walks you through the process of creating a “Hello, World” RESTful web service with Spring.
[Guides: Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)





