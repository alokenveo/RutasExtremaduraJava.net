# RutasExtremadura.net

Una aplicación web Java EE para la gestión y reserva de rutas turísticas en Extremadura.

![Java](https://img.shields.io/badge/Java-8-orange)
![Jakarta EE](https://img.shields.io/badge/Jakarta%20EE-5.0-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Tomcat](https://img.shields.io/badge/Tomcat-11.0-yellow)

## 📋 Descripción

RutasExtremadura.net es una plataforma web muy simple desarrollada para la universidad, que permite a los usuarios explorar, valorar y reservar rutas turísticas por la región de Extremadura. El proyecto incluye funcionalidades de gestión para administradores y un sistema completo de reservas para usuarios registrados.

## ✨ Características

### Para Usuarios
- 🔍 Exploración de rutas con filtros avanzados
- ⭐ Sistema de valoraciones (1-5 estrellas)
- 📅 Reserva de rutas con selección de fecha, horario y servicios extras
- 👤 Perfil personal con historial de reservas
- 📱 Interfaz responsive

### Para Administradores
- 👥 Gestión completa de usuarios
- 🗺️ Administración de rutas (crear, editar, eliminar)
- 📊 Gestión de reservas
- 🖼️ Subida múltiple de imágenes para rutas

### Características Generales
- 🏠 Página de inicio con carrusel de imágenes
- 📈 Ranking de rutas por dificultad, distancia y fecha
- 💬 Sistema de comentarios y valoraciones
- 🔐 Autenticación y autorización mediante filtros

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 8**: Lenguaje principal
- **Jakarta EE 5.0**: Servlets, JSP, JSTL
- **MySQL 8.0**: Base de datos
- **Apache Tomcat 11.0**: Servidor de aplicaciones
- **JDBC**: Conexión a base de datos mediante DataSource

### Frontend
- **HTML5/CSS3**: Estructura y estilos
- **JavaScript**: Interactividad
- **Bootstrap Icons**: Iconografía

### Arquitectura
- **Patrón MVC**: Separación de responsabilidades
- **DAO Pattern**: Acceso a datos
- **Service Layer**: Lógica de negocio

## 📁 Estructura del Proyecto
```
RutasExtremadura/
├── src/main/
│   ├── java/
│   │   └── es/unex/cum/tw/rutas/
│   │       ├── conexion/          # Gestión de conexiones BD
│   │       ├── controller/        # Controladores (Servlets)
│   │       ├── filter/           # Filtros de seguridad
│   │       ├── model/            # Entidades del dominio
│   │       └── service/          # Capa de servicios
│   └── webapp/
│       ├── css/                  # Hojas de estilo
│       ├── img/                  # Recursos multimedia
│       ├── js/                   # Scripts JavaScript
│       ├── WEB-INF/
│       │   ├── conf/            # Scripts SQL
│       │   ├── web.xml          # Descriptor de despliegue
│       │   └── context.xml      # Configuración JNDI
│       └── *.jsp                # Páginas JSP
└── build/                        # Archivos compilados
```

## 🗄️ Modelo de Datos

### Principales Entidades

- **Usuario**: Gestión de usuarios del sistema
- **Ruta**: Información de rutas turísticas
- **Reserva**: Reservas realizadas por usuarios
- **Valoracion**: Sistema de puntuación de rutas
- **FotosRuta**: Imágenes asociadas a cada ruta

### Diagrama ER
El esquema completo se encuentra en `src/main/webapp/WEB-INF/conf/RutasExtremaduraBD.sql`

## 🚀 Instalación y Configuración

### Prerequisitos

- JDK 8 o superior
- Apache Tomcat 11.0
- MySQL 8.0
- Maven (opcional)

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/alokenveo/RutasExtremaduraJava.net.git
cd RutasExtremaduraJava.net
```

2. **Configurar la base de datos**
```bash
mysql -u root -p < src/main/webapp/WEB-INF/conf/RutasExtremaduraBD.sql
mysql -u root -p < src/main/webapp/WEB-INF/conf/InsertDatos.sql
```

3. **Configurar el contexto de Tomcat**
Editar `src/main/webapp/META-INF/context.xml` con tus credenciales:
```xml
<Resource name="jdbc/testdb" 
          auth="Container"
          type="javax.sql.DataSource" 
          maxTotal="10" 
          maxIdle="5" 
          maxWait="-1"
          driverClassName="com.mysql.cj.jdbc.Driver"
          url="jdbc:mysql://localhost:3306/tw?useSSL=false&amp;allowPublicKeyRetrieval=true"
          username="tu_usuario" 
          password="tu_contraseña" />
```

4. **Desplegar en Tomcat**
- Copiar el proyecto al directorio `webapps` de Tomcat
- O importar en Eclipse/IntelliJ y ejecutar en servidor

5. **Acceder a la aplicación**
```
http://localhost:8080/RutasExtremadura
```

## 👤 Usuarios de Prueba

### Administrador
- **Usuario**: `admin`
- **Contraseña**: `admin`

<!--

## 📸 Capturas de Pantalla

### Página Principal
![Inicio](docs/screenshots/inicio.png)

### Vista de Rutas
![Rutas](docs/screenshots/rutas.png)

### Panel Administrador
![Admin](docs/screenshots/admin.png)

-->

## 🔧 Funcionalidades Principales

### Sistema de Autenticación
- Login/Registro de usuarios
- Filtro de seguridad para rutas protegidas
- Sesiones de usuario

### Gestión de Rutas
```java
// Ejemplo de creación de ruta
RutaService rutaService = new RutaServiceBD();
Ruta nuevaRuta = new Ruta(nombre, descripcion, enlace, 
                          maxUsuarios, dificultad, metros, fotos);
rutaService.crearRuta(nuevaRuta);
```

### Sistema de Valoraciones
```javascript
// Cliente: Envío de valoración
fetch('/ValoracionController', {
    method: 'POST',
    body: new FormData(formulario)
})
.then(response => response.json())
.then(data => actualizarInterfaz(data));
```

## 🔐 Seguridad

- **LoginFilter**: Protege rutas que requieren autenticación
- **Validación de entrada**: Sanitización de datos en formularios
- **Prepared Statements**: Prevención de SQL Injection
- **Control de sesiones**: Gestión segura de sesiones de usuario

## 📝 API Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/RutaController?action=VerRutas` | Lista todas las rutas |
| GET | `/RutaController?action=VerRuta&idRuta={id}` | Detalle de una ruta |
| POST | `/ReservaController?action=CrearReserva` | Crear reserva |
| POST | `/ValoracionController?action=CrearOActualizarValoracion` | Valorar ruta |
| POST | `/UsuarioController?action=UsuarioLogin` | Login |
| GET | `/UsuarioController?action=UsuarioLogout` | Logout |

## 🤝 Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu característica (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## ✉️ Contacto

**Alfredo Mituy Okenve**
- Email: alokenveo@alumnos.unex.es
- GitHub: [@alokenveo](https://github.com/alokenveo)

## 🙏 Agradecimientos

- Universidad de Extremadura
- Comunidad de desarrolladores Java EE
- Bootstrap Icons por la iconografía

---

⭐ Si este proyecto te ha sido útil, considera darle una estrella en GitHub!
