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


### Recursos vs. Representación

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