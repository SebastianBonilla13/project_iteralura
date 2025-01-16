package com.project.literalura.principal;

import com.project.literalura.model.*;
import com.project.literalura.repository.AutorRepository;
import com.project.literalura.repository.LibroRepository;
import com.project.literalura.service.ConsumoAPI;
import com.project.literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;

public class Principal {

    ConsumoAPI api = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();

    final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository libroRepositorio;
    private AutorRepository autorRepositorio;
    private Map<Integer,String> mapIdiomas = new HashMap<>();

    public Principal(LibroRepository libroRepositorio, AutorRepository autorRepositorio) {
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    public void mostrarMenu(){
        Scanner input = new Scanner(System.in);
        var opc = -1;
        String menu = """
                    \nElegir una opción de acuerdo a su número:
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0- Salir
                    """;
        System.out.println("\n    Bienvenido a Literalura");
        try{
            while(opc != 0){
                System.out.println(menu);
                System.out.print("opción: ");
                opc = input.nextInt();

                switch (opc) {
                    case 1:
                        buscarLibroAPI();
                        break;

                    case 2:
                        listarLibros();
                        break;

                    case 3:
                        listarAutores();
                        break;

                    case 4:
                        listarAutoresVivosEn();
                        break;

                    case 5:
                        buscarPorIdioma();
                        break;

                    case 0:
                        System.out.println("¡Gracias por visitarnos!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Ingresa una opción válida");
            mostrarMenu();
        }
    }

    private void listarLibros() {
        List<Libro> librosRegistrados = libroRepositorio.findAll();
        librosRegistrados.forEach(l -> System.out.println(l.toString()));
    }

    private void listarAutores() {
        List<Autor> autores = autorRepositorio.findAll();
        autores.forEach(a -> System.out.println(a.toString()));
    }

    private void listarAutoresVivosEn() {

        Scanner input = new Scanner(System.in);
        System.out.print("Ingrese el año vivo de autor(es) que desea buscar: ");
        var anioBuscar = input.nextInt();

        List<Autor> autores = autorRepositorio.findAll();
        autores.stream()
                .filter(autor -> {
                    int anioNacimiento = Integer.parseInt(autor.getFecha_nacimiento());
                    int anioFallecimiento = autor.getFecha_fallecimiento() != null
                            ? Integer.parseInt(autor.getFecha_fallecimiento())
                            : Integer.MAX_VALUE; // Autor no ha fallecido, se considera vivo
                    return anioBuscar >= anioNacimiento && anioBuscar <= anioFallecimiento;
                })
                .forEach(autor -> System.out.println("Autor vivo en " + anioBuscar + ": " + autor.getName()));
    }

    private void buscarPorIdioma() {
        mapIdiomas.put(1, "es");
        mapIdiomas.put(2, "en");
        mapIdiomas.put(3, "fr");
        mapIdiomas.put(4, "pt");

        String strIdiomas = """
                1) es - Español
                2) en - Inglés
                3) fr - Francés
                4) pt - Portugués
                """;

        Scanner input = new Scanner(System.in);
        System.out.println("Ingresa el número de idioma para buscar los libros");
        System.out.println(strIdiomas);
        var opc = input.nextInt();
        var busquedaIdioma = mapIdiomas.get(opc); // obtener clave

        if(busquedaIdioma != null){
            List<Libro> libroExiste = libroRepositorio.findByIdioma(busquedaIdioma);
            if(libroExiste.size() > 0){ // hay libros en tal idioma
                libroExiste.stream()
                        .forEach(l -> System.out.println(l.toString()));
            }else{
                System.out.println("No hay libros registrados en '" + busquedaIdioma + "' :(");
            }
        }else{
            System.out.println("Ingresa una opción correcta");
        }
    }

    public Autor guardarOActualizarAutor(Libro libroEncontrado) {
        Autor nuevoAutor = libroEncontrado.getAutor();
        // Validar, buscar el autor por nombre
        Optional<Autor> autorExistente = autorRepositorio.findByName(nuevoAutor.getName());

        if (autorExistente.isPresent()) { // si autor existe
            // Actualizar datos del autor existente
            Autor autor = autorExistente.get();
            autor.setFecha_nacimiento(nuevoAutor.getFecha_nacimiento());
            autor.setFecha_fallecimiento(nuevoAutor.getFecha_fallecimiento());

            List<Libro> nuevaListaLibros = autorExistente.get().getLibros();
            nuevaListaLibros.add(libroEncontrado); // Libros existentes + nuevo libro

            autor.setLibros(nuevaListaLibros);

            return autorRepositorio.save(autor); // Actualizar en la bd

        } else {
            // Guardar un nuevo autor
            return autorRepositorio.save(nuevoAutor);
        }
    }

    private void buscarLibroAPI() {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el libro a buscar: ");
        var busquedaLibro = input.nextLine();

        System.out.println("Buscando... " + busquedaLibro + "\n");

        var json = api.obtenerDatos(URL_BASE + busquedaLibro.replace(" ", "%20")); // "%20"->"espacio" en API
        var datos = convierteDatos.obtenerDatos(json, DatosConsulta.class); // json to class

        if (datos.resultadosLibros().size() > 0) { // Sí! hay información
            var libro = datos.resultadosLibros().get(0); // obteniendo primer resultado
            Libro libroEncontrado = new Libro(libro); // class to Libro

            System.out.println(libroEncontrado);

            try {
                // Guardar/actualizar autor antes de guardar libro
                Autor autorPersistido = guardarOActualizarAutor(libroEncontrado);

                libroEncontrado.setAutor(autorPersistido); // Persistiendo autor

                libroRepositorio.save(libroEncontrado);
                System.out.println("¡Libro guardado!");
            } catch (DataIntegrityViolationException e) {
                System.out.println("Este libro ya se encuentra registrado");
            }
        } else {
            System.out.println("Este libro no fue encontrado");
        }
    }

}