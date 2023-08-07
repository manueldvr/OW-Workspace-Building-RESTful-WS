# c22 Servicio de Subida de ficheros


**Contenido Multiparte**

Tipo de mensaje que permite que una **petición** tenga varias partes
delimitadas, con su correspondiente *Content-Type*.

## Multipart con Spring

Cuando Spring procesa una petición multiparte, nos deja acceder a
ella (o ellas) a través de `@RequestParam`.

```
	@PostMapping(“/upload”)
	public String handleUpload(... , @RequestParam(“file”) MultipartFile file)
		{ … }
```

● La clase `MultipartFile` tiene métodos convenientes para permitirnos
procesar el fichero.


### MultipartFile

A representation of an uploaded file received in a multipart request.

The file contents are either stored in memory or temporarily on disk. In either case, the user is responsible for copying file contents to a session-level or persistent store as and if desired. The temporary storage will be cleared at the end of request processing.


<div id="method-summary-table.tabpanel" role="tabpanel">
<div class="summary-table three-column-summary" aria-labelledby="method-summary-table-tab0">
<div class="table-header col-first">Modifier and Type</div>
<div class="table-header col-second">Method</div>
<div class="table-header col-last">Description</div>
<div class="col-first even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code>byte[]</code></div>
<div class="col-second even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#getBytes()" class="member-name-link">getBytes</a>()</code></div>
<div class="col-last even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return the contents of the file as an array of bytes.</div>
</div>
<div class="col-first odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html" title="class or interface in java.lang" class="external-link">String</a></code></div>
<div class="col-second odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#getContentType()" class="member-name-link">getContentType</a>()</code></div>
<div class="col-last odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return the content type of the file.</div>
</div>
<div class="col-first even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/InputStream.html" title="class or interface in java.io" class="external-link">InputStream</a></code></div>
<div class="col-second even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#getInputStream()" class="member-name-link">getInputStream</a>()</code></div>
<div class="col-last even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return an InputStream to read the contents of the file from.</div>
</div>
<div class="col-first odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html" title="class or interface in java.lang" class="external-link">String</a></code></div>
<div class="col-second odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#getName()" class="member-name-link">getName</a>()</code></div>
<div class="col-last odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return the name of the parameter in the multipart form.</div>
</div>
<div class="col-first even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html" title="class or interface in java.lang" class="external-link">String</a></code></div>
<div class="col-second even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#getOriginalFilename()" class="member-name-link">getOriginalFilename</a>()</code></div>
<div class="col-last even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return the original filename in the client's filesystem.</div>
</div>
<div class="col-first odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab5"><code>default <a href="../../core/io/Resource.html" title="interface in org.springframework.core.io">Resource</a></code></div>
<div class="col-second odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab5"><code><a href="#getResource()" class="member-name-link">getResource</a>()</code></div>
<div class="col-last odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab5">
<div class="block">Return a Resource representation of this MultipartFile.</div>
</div>
<div class="col-first even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code>long</code></div>
<div class="col-second even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#getSize()" class="member-name-link">getSize</a>()</code></div>
<div class="col-last even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return the size of the file in bytes.</div>
</div>
<div class="col-first odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code>boolean</code></div>
<div class="col-second odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#isEmpty()" class="member-name-link">isEmpty</a>()</code></div>
<div class="col-last odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Return whether the uploaded file is empty, that is, either no file has
 been chosen in the multipart form or the chosen file has no content.</div>
</div>
<div class="col-first even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code>void</code></div>
<div class="col-second even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3"><code><a href="#transferTo(java.io.File)" class="member-name-link">transferTo</a><wbr>(<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/File.html" title="class or interface in java.io" class="external-link">File</a>&nbsp;dest)</code></div>
<div class="col-last even-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab3">
<div class="block">Transfer the received file to the given destination file.</div>
</div>
<div class="col-first odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab5"><code>default void</code></div>
<div class="col-second odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab5"><code><a href="#transferTo(java.nio.file.Path)" class="member-name-link">transferTo</a><wbr>(<a href="https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/Path.html" title="class or interface in java.nio.file" class="external-link">Path</a>&nbsp;dest)</code></div>
<div class="col-last odd-row-color method-summary-table method-summary-table-tab2 method-summary-table-tab5">
<div class="block">Transfer the received file to the given destination file.</div>
</div>
</div>
</div>




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








---

## Referencias

- MultipartFile Class: [MultipartFile](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/multipart/MultipartFile.html).

- CORS with Spring [CORS](https://www.baeldung.com/spring-cors#:~:text=Global%20CORS%20Configuration&text=This%20is%20similar%20to%20using,and%20POST%20methods%20are%20allowed.)

- [Cross-Origin Resource Sharing and Why We Need Preflight Requests](https://www.baeldung.com/cs/cors-preflight-requests)

- [Spring Boot CORS Configuration Example](https://howtodoinjava.com/spring-boot2/spring-cors-configuration/)