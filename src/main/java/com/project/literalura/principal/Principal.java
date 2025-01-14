package com.project.literalura.principal;

import com.project.literalura.model.DatosConsulta;
import com.project.literalura.service.ConsumoAPI;
import com.project.literalura.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {

    ConsumoAPI api = new ConsumoAPI();
    ConvierteDatos convierteDatos = new ConvierteDatos();

    Scanner input = new Scanner(System.in);

    final String URL_BASE = "https://gutendex.com/books/?search=";


    public void mostrarMenu(){

        var opc = -1;
        String menu = """
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    /n
                    0- Salir
                    """;

        while(opc != 0){
            System.out.println(menu);
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

        //var busquedaLibro = input.nextLine();
        var busquedaLibro = "zodiac";

        var json = api.obtenerDatos(URL_BASE+busquedaLibro);

        var datos = convierteDatos.obtenerDatos(json, DatosConsulta.class);

        var libros = datos.resultadosLibros();

        System.out.println("LIBROSSS: " + libros);

    }

}
