# c22 Servicio de Subida de ficheros


**Contenido Multiparte**

Tipo de mensaje que permite que una **petición** tenga varias partes
delimitadas, con su correspondiente *Content-Type*.

## Multipart con Spring

Cuando Spring procesa una petición multiparte, nos deja acceder a
ella (o ellas) a través de `@RequestParam`.

```java
	@PostMapping(“/upload”)
	public String handleUpload(... , @RequestParam(“file”) MultipartFile file)
		{ … }
```

● La clase `MultipartFile` tiene métodos convenientes para permitirnos
procesar el fichero.


### MultipartFile

A representation of an uploaded file received in a multipart request.

The file contents are either stored in memory or temporarily on disk. In either case, the user is responsible for copying file contents to a session-level or persistent store as and if desired. The temporary storage will be cleared at the end of request processing.





## ¿Dónde lo almacenamos?

Propio proyecto:

- Fácil para aprender
- No es buena práctica en producción

Servicio de almacenamiento externo:

- Nube (Amazon, Azure, Drive...)
- GridFS
- Si son imágenes, servicios específicos, como imgur. 


### Punto de partida

Spring nos ofrece un tutorial completo de como implementar la
subida y almacenamiento de ficheros.

https://spring.io/guides/gs/uploading-files/

- Nos permite crear un servicio estándar, que luego podemos
modificar para pasar del almacenamiento propio a uno externo.



### INTERFAZ StorageService

- Tiene los métodos que debería proporcionarnos un servicio de
almacenamiento de ficheros.
- Algunos están modificados sobre el ejemplo original de Spring.


### CLASE FileSystemStorageService

Almacenamiento en nuestro sistema de ficheros.

Método *store*:

- Modifica el nombre del fichero, añadiendo la fecha y hora como
milisegundos.
- Método para prevenir problemas a la hora de subir dos ficheros
que se llamen igual.
- Si aún así, el fichero existe, se sobrescribe.
- Devuelve el nombre del fichero para almacenarlo en el modelo

Método *load*:

- Devuelve la ruta de un fichero desde su nombre.

Método *loadAsResource*:

- Recibe el nombre de un fichero.
- Busca el fichero, y lo devuelve como una instancia de Resource
(envoltorio conveniente para un fichero)


### CLASES DE ERROR

`StorageException`

- Error general de almacenamiento.

`StorageFileNotFoundException`

- Fichero no encontrado.


### Inicialización

Clase `Application`:

- Durante el desarrollo, limpiamos siempre el sistema de
almacenamiento para no acumular demasiadas fotos.
- Seguimos el mismo esquema que utilizamos con la base de
datos.

### CONTROLADOR FicherosController
Método `serveFile`:

- Método especial que será capaz de devolvernos el fichero como
respuesta a una petición.
- Nos aísla tener que configurar el almacenamiento estático para
obtener los ficheros.


<br>
<br>
<br>
<br>

---j
uuyegy


# c23 Implementación de la subida de ficheros


## Método de subida

Nuestro método *nuevoProducto* con algunas modificaciones:

- Aunque no es obligatorio, añadimos el tipo mime de los datos
que consume `MediaType.MULTIPART_FORM_DATA_VALUE`.
- Ya no podemos obtener nuestros datos a partir de
`@RequestBody`, ya que el cuerpo de la petición tiene varias
partes.
- Necesitamos utilizar otras anotaciones, como:
	- `@RequestParam` o 
	- `@RequestPart`.


## `@RequestParam` vs  `@RequestPart`

### `@RequestParam`: 

Asocia un parámetro de la petición a un argumento de un método de un controlador.

- Puede usarse en peticiones multiparte.
- Válido para anotar *MultipartFile*.
- Inconveniente: si no es de tipo *String* o *MultipartFile*, necesita
un *Converter* registrado.
- Nosotros transformamos *JSON* - Objeto Java vía un
`HttpMessageConverter` (no es lo mismo).


### @RequestPart


## Subida de la Imagen

Subimos la imagen al almacenamiento a través de nuestro
servicio.
1. Obtenemos el nombre del fichero.
2. A partir del nombre del fichero, obtenemos la URI del mismo.
3. Asignamos la URI al producto
4. Nota: también modificamos el DTO para que se incluya la imagen en el listado de productos.



---

<br>
<br>



## Referencias

- MultipartFile Class: [MultipartFile](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html).

- CORS with Spring [CORS](https://www.baeldung.com/spring-cors#:~:text=Global%20CORS%20Configuration&text=This%20is%20similar%20to%20using,and%20POST%20methods%20are%20allowed.)

- [Cross-Origin Resource Sharing and Why We Need Preflight Requests](https://www.baeldung.com/cs/cors-preflight-requests)

- [Spring Boot CORS Configuration Example](https://howtodoinjava.com/spring-boot2/spring-cors-configuration/)