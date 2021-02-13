# consumo-api-externo-spring-boot
Se tiene que consumir información del siguiente servicio externo que cuenta con los datos de usuarios, sus álbumes y sus fotos; además de sus posts y 
los comentarios de otros usuarios sobre ellos: https://jsonplaceholder.typicode.com/ De la información del servicio se tiene que poder acceder a través de nuestra API a:
1. Los usuarios.
2. Las fotos.
3. Los álbumes del sistema y de cada usuario.

Este API permite:
1. Registrar un álbum compartido con un usuario y sus permisos.
2. Cambiar los permisos de un usuario para un álbum determinado.
3. Traer todos los usuarios que tienen un permiso determinado respecto a un
álbum específico.

Health check
<br>
http://localhost:8081/wolox-api/actuator/health

Documentación y test de endpoints
<br>
http://localhost:8081/wolox-api/swagger-ui.html

Base de datos
El archivo para crear la bd en postgres se encuentra en los recuersos del proyecto (bbdd/*.sql).

Modificar el application.properties con las accesos y/o credenciales correctos para la base de datos

host base de base datos -> spring.datasource.url
<br>
usuario   de base datos -> spring.datasource.username
<br>
password  de base datos -> spring.datasource.password
<br>

Componentes usados durante el desarrollo:
<h4> Docker image postgres:12.2 </h4>
<h4> Spring tool Suite 4 </h4>
