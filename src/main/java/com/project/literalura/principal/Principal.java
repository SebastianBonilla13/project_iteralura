package com.project.literalura.principal;

import com.project.literalura.model.DatosAutor;
import com.project.literalura.model.DatosConsulta;
import com.project.literalura.model.DatosLibro;
import com.project.literalura.model.Libro;
import com.project.literalura.repository.LibroRepository;
import com.project.literalura.service.ConsumoAPI;
import com.project.literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    ConsumoAPI api = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();

    final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository libroRepositorio;

    public Principal(LibroRepository libroRepositorio) {
        this.libroRepositorio = libroRepositorio;
    }

    public void mostrarMenu(){
        Scanner input = new Scanner(System.in);
        var opc = -1;
        String menu = """
                    Elegir una opción de acuerdo a su número:
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    
                    1-VALIDAR SI EL AUTOR YA EXISTE EN LA BD, PARA ADD LIBRO
                   
                    0- Salir
                    """;

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

                    break;

                case 5:

                    break;

                case 0:
                    System.out.println("¡Gracias por visitarnos!");
            }
        }
    }

    private void listarAutores() {

        List<Libro> libros = libroRepositorio.findAll();

        // Extrae los autores únicos
        var autores = libros.stream()
                .map(Libro::getAutor) // Obtiene el autor de cada libro
                .collect(Collectors.toSet()); // Asegura que los autores sean únicos

//        autores.forEach(a -> System.out.println("Autor: " + a.getName() +
//                "\nFecha de nacimiento: " + a.getFecha_nacimiento() +
//                "\nFecha de fallecimiento: " + a.getFecha_fallecimiento() +
//                "\nLibros: [" + a.getLibros().stream()
//                    .map(Libro::getTitulo)
//                    .collect(Collectors.joining(", ")) + "] \n" ));

        autores.forEach(a -> System.out.println(a.toString()));
    }

    private void listarLibros() {
        List<Libro> librosRegistrados = libroRepositorio.findAll();
        librosRegistrados.forEach(l -> System.out.println(l.toString()));
    }

    private void buscarLibroAPI(){
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el libro a buscar: ");
        var busquedaLibro = input.nextLine();

        System.out.println("Buscando... " + busquedaLibro);

        var json = api.obtenerDatos(URL_BASE+busquedaLibro.replace(" ","%20"));

        var datos = convierteDatos.obtenerDatos(json, DatosConsulta.class);

        if(datos.resultadosLibros().size() > 0){
            var libro = datos.resultadosLibros().get(0); // obteniendo primer resultado

            Libro libroEncontrado = new Libro(libro);

            System.out.println(libroEncontrado);

            //System.out.println("Libro Encontrado: " + libroEncontrado);

            //System.out.println("LIBROS AUTOR" + libroEncontrado.getAutor().getLibros());

            // ARREGLAR! Consultar si ya existe en la BD Cómo???
            try{
                //libroEncontrado.setAutor();
                libroRepositorio.save(libroEncontrado);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Este libro ya se encuentra registrado");
            }

        }else{
            System.out.println("Este libro no fué encontrado");
        }






        //List<DatosLibro> libros = datos.resultadosLibros();
        //System.out.println("LIBROSSS: " + libros);
        //DatosAutor autorX = libros.get(0).autores().get(0);

        //List<DatosAutor> autoresLibro = libro.autores();
        //System.out.println("autores: " + autoresLibro);

    }

}
