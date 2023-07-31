# c12 Implementando DTO con ModelMapper


## Uso de Lombok

Anotación de los DTOs con:

- `@Value` (como `@Data pero` sin setters).
- `@Builder` argumentando que normalmente un DTO no se va a editar. 
- `@Data` al igual que las entidades, por si en el proceso de tansformación utilizan varios pasos, y por tanto, usan algún método setter.

## ModelMapper 

ofrece además la posibilidad de escoger entre 3 estrategias diferentes de matching. 

Utiliza el concepto de token, que podríamos entender como una de las partes del nombre de cada propiedad. <br>
Por ejemplo categoriaNombre tiene dos tokens: categoria y nombre

- **Estándar** (por defecto): los tokens se pueden emparejar en cualquier orden, todas las propiedades del 
modelo de origen se deben emparejar con una del destino, 
y todas las del destino deben ser emparejadas.
- **Loose** (más laxo): los tokens se pueden emparejar en cualquier orden, 
la última propiedad del modelo de destino debe tener todos los tokens emparejados y 
la última propiedad del modelo de origen debe tener al menos un token emparejado.
- **Estricto**: como su nombre indica, es el más estricto de todo. 
Los tokens deben encontrarse en el mismo orden, y todo debe emparejarse perfectamente.



### Para cambiar la estrategia

podemos usar el siguiente código:

```
modelMapper.getConfiguration()
  .setMatchingStrategy(MatchingStrategies.LOOSE);
```  
 
### Ajustando manualmente la transformación

En ocasiones, nos interesará ajustar manualmente la transformación entre dos objetos, bien porque no queramos cambiar la estrategia para todos los atributos, o porque se trate de una asignación muy particular. 

Para ello tenemos varios mecanismos, pero uno de ellos a través de la creación de un `PropertyMap<S,D>`.

Por ejemplo, si en nuestro ejemplo queremos que la clase DTO quede como sigue:

```
@Getter
@Setter
public class ProductoDTO {

    private long id;
    private String nombre;
    private String categoria;

}
```

Podemos realizar manualmente la transformación de categoria.nombre en Producto a categoria en ProductoDTO. 
Para ello, podemos añadir este código en nuestro componente de conversión:

```
@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {

    private final ModelMapper modelMapper;


    @PostConstruct
    public void init() {
        modelMapper.addMappings(new PropertyMap<Producto, ProductoDTO>() {

            @Override
            protected void configure() {
                map().setCategoria(source.getCategoria().getNombre());
            }
        });
    }

    public ProductoDTO convertToDto(Producto producto) {
        return modelMapper.map(producto, ProductoDTO.class);

    }

}
```

---

## Referencias

- Para más información sobre la librería: [ModelMapper getting started](http://modelmapper.org/getting-started/).