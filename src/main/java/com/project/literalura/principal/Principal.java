package com.project.literalura.principal;

import com.project.literalura.model.DatosAutor;
import com.project.literalura.model.DatosConsulta;
import com.project.literalura.model.DatosLibro;
import com.project.literalura.model.Libro;
import com.project.literalura.repository.LibroRepository;
import com.project.literalura.service.ConsumoAPI;
import com.project.literalura.service.ConvierteDatos;

import java.util.Scanner;

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
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    
                    0- Salir
                    """;

        while(opc != 0){
            System.out.println(menu);
            System.out.print("Ingresa una opción: ");
            opc = input.nextInt();

            switch (opc) {
                case 1:
                    buscarLibroAPI();
                    break;

                case 2:

                    break;

                case 3:

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

    private void buscarLibroAPI(){
        Scanner input = new Scanner(System.in);

        System.out.print("Ingrese el libro a buscar: ");
        var busquedaLibro = input.nextLine();

        System.out.println("B = " + busquedaLibro);

        var json = api.obtenerDatos(URL_BASE+busquedaLibro);

        var datos = convierteDatos.obtenerDatos(json, DatosConsulta.class);

        var libro = datos.resultadosLibros().get(0); // obteniendo primer resultado

        Libro libroEncontrado = new Libro(libro);

        System.out.println("Libro Encontrado: " + libroEncontrado);


        libroRepositorio.save(libroEncontrado);

        //List<DatosLibro> libros = datos.resultadosLibros();
        //System.out.println("LIBROSSS: " + libros);
        //DatosAutor autorX = libros.get(0).autores().get(0);


        //List<DatosAutor> autoresLibro = libro.autores();
        //System.out.println("autores: " + autoresLibro);




    }

}
