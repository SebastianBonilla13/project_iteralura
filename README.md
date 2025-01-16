# Proyecto Literalura
Desarrollo de un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando X opciones de interacción. Los libros se buscarán a través de API "Gutendex". Framework SpringBoot y PostgreSQL.

## Funcionalidades  
- Menú interactivo y dinámico.  
- Buscar, listar libros/autores, listar por criterio de autor vivo y por idioma.  
- Herramientas utilizadas:
  - **Framework Spring Boot**
  - **Spring Data JPA**
  - **PostgreSQL**
  - **servicio API https://gutendex.com/**  
  - **Java**  
  - **HttpClient/HttpRequest**  
  - **JSON**  
  - **Jackson**  
  - **Streams**
  - **Lambdas**

## Requisitos Previos  

### 1. Instalar Java Development Kit (JDK)  
- Descargar JDK desde la página oficial:  
  [Descargar JDK](https://www.oracle.com/java/technologies/javase-downloads.html)  
- Asegúrate de tener la versión 8 o superior instalada.  

### 2. Descargar y Configurar PostGresSQL 
- Descargar e instalar PostGresSQL, en donde se creará una base de datos relacional,
- para pa ersistencia de los datos. Guardando y consultando los datos en la aplicación.
- Página oficial: https://www.postgresql.org/download/
- puerto por defecto, puerto: 5432
- Crear la base de datos: db_libros

### 4. Conexión a Internet  
- Es necesario tener una conexión activa a Internet para el consumo de la API (la API no requiere KEY).  

## Ejemplo de Uso  

```plaintext
    Bienvenido a Literalura 

Elegir una opción de acuerdo a su número:
1- Buscar libro por titulo
2- Listar libros registrados
3- Listar autores registrados
4- Listar autores vivos en un determinado año
5- Listar libros por idioma
0- Salir

opción: 1
Ingrese el libro a buscar: Emma
Buscando... Emma

----- LIBRO ----- 
Título: Emma
Autor: Austen, Jane
Idioma: en
Numero de descargas: 7974
----------------

¡Libro guardado!

(Ingresar libros con mismo autor: "Emm"a, "Lady Susan", "Oliver Twist", que son de la autora "Austen, Jane")
(Ingresar libros diferentes: "Oliver Twist", "Moby", "La Odisea")

----------------------------------------

opción: 4
Ingrese el año vivo de autor(es) que desea buscar: 1850
Autor vivo en 1850: Dickens, Charles
Autor vivo en 1850: Melville, Herman

----------------------------------------

opción: 5
Ingresa el número de idioma para buscar los libros
1) es - Español
2) en - Inglés
3) fr - Francés
4) pt - Portugués

2
----- LIBRO ----- 
Título: Mansfield Park
Autor: Austen, Jane
Idioma: en
Numero de descargas: 3499
----------------

----- LIBRO ----- 
Título: Lady Susan
Autor: Austen, Jane
Idioma: en
Numero de descargas: 870

.
.
.

