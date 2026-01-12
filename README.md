# TeamSoft - Backend

API REST para TeamSoft, un software inteligente para la formación óptima de equipos de trabajo utilizando algoritmos metaheurísticos.

## 📋 Descripción

TeamSoft es una aplicación backend desarrollada en Spring Boot que implementa algoritmos de optimización multiobjetivo para la formación automática de equipos de trabajo. El sistema considera múltiples factores como competencias técnicas, compatibilidad personal, diversidad demográfica y restricciones organizacionales para crear equipos balanceados y eficientes.

## 🚀 Tecnologías Utilizadas

### Framework Principal
- **Spring Boot 3.5.6** - Framework principal de Java
- **Java 21** - Versión del lenguaje de programación

### Base de Datos
- **PostgreSQL** - Sistema de gestión de base de datos
- **Spring Data JPA** - Abstracción de persistencia
- **Hibernate** - ORM (Object-Relational Mapping)

### Documentación API
- **Knife4j OpenAPI 3** (v4.4.0) - Documentación interactiva de la API
- **Swagger UI** - Interfaz web para probar endpoints

### Herramientas de Desarrollo
- **Lombok** - Reducción de código boilerplate
- **ModelMapper** (v3.2.4) - Mapeo entre DTOs y entidades
- **Spring Boot Validation** - Validación de datos
- **Maven** - Gestión de dependencias y construcción

### Algoritmos Metaheurísticos
- **BiCIAM-MultObj.jar** - Librería personalizada para algoritmos de optimización multiobjetivo

## 🏗️ Arquitectura del Proyecto

```
src/main/java/com/tesis/teamsoft/
├── metaheuristics/          # Algoritmos de optimización
│   ├── objectives/          # Funciones objetivo
│   ├── operator/           # Operadores genéticos
│   ├── restrictions/       # Restricciones del problema
│   ├── test/              # Clases de prueba
│   └── util/              # Utilidades
├── persistence/            # Capa de persistencia
│   ├── entity/            # Entidades JPA
│   └── repository/        # Repositorios Spring Data
├── pojos/                 # Objetos de transferencia de datos
├── presentation/          # Capa de presentación
│   ├── controller/        # Controladores REST
│   └── dto/              # Data Transfer Objects
├── service/               # Capa de servicios
│   ├── implementation/    # Implementaciones de servicios
│   └── interfaces/        # Interfaces de servicios
└── TeamSoftApplication.java # Clase principal
```

## 🔧 Funcionalidades Principales

### Gestión de Datos
- **Personas**: Gestión de perfiles de trabajadores con competencias y características personales
- **Proyectos**: Administración de proyectos con roles y requisitos específicos
- **Competencias**: Sistema de competencias técnicas con niveles e importancia
- **Roles**: Definición de roles de proyecto con cargas de trabajo

### Algoritmos de Optimización
- **Funciones Objetivo Múltiples**:
  - Maximización de competencias técnicas
  - Balance de diversidad demográfica (edad, género, nacionalidad, religión)
  - Minimización de incompatibilidades personales
  - Optimización de cargas de trabajo
  - Balance de intereses en proyectos

### API REST Endpoints
- `/teamFormation/getTeams` - Generación de equipos optimizados
- Endpoints CRUD para todas las entidades del sistema
- Documentación interactiva disponible en `/swagger-ui.html`

## 📋 Prerrequisitos

- **Java 21** o superior
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Git**

## 🛠️ Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/tu-usuario/TeamSoft-BackEnd.git
cd TeamSoft-BackEnd
```

### 2. Configurar Base de Datos PostgreSQL

#### Instalar PostgreSQL
- Descargar e instalar PostgreSQL desde [postgresql.org](https://www.postgresql.org/download/)
- Crear una base de datos llamada `TeamSoft-DB`

#### Configurar la Base de Datos
```sql
-- Conectarse a PostgreSQL como superusuario
CREATE DATABASE "TeamSoft-DB";
CREATE USER postgres WITH PASSWORD 'tu_password';
GRANT ALL PRIVILEGES ON DATABASE "TeamSoft-DB" TO postgres;
```

### 3. Configurar Variables de Entorno

Editar el archivo `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/TeamSoft-DB
    username: postgres
    password: tu_password_aqui
```

### 4. Instalar Dependencias
```bash
mvn clean install
```

## 🚀 Ejecución del Proyecto

### Opción 1: Usando Maven
```bash
mvn spring-boot:run
```

### Opción 2: Usando el JAR compilado
```bash
mvn clean package
java -jar target/TeamSoft-0.0.1-SNAPSHOT.jar
```

### Opción 3: Desde el IDE
Ejecutar la clase principal `TeamSoftApplication.java`

## 🌐 Acceso a la Aplicación

Una vez iniciada la aplicación:

- **API Base URL**: `http://localhost:8081`
- **Documentación Swagger**: `http://localhost:8081/swagger-ui.html`
- **API Docs JSON**: `http://localhost:8081/v3/api-docs`

## 📊 Configuración de Base de Datos

La aplicación está configurada para:
- **Puerto**: 5434 (configurable en application.yml)
- **Auto-creación de tablas**: Habilitada (ddl-auto: update)
- **Pool de conexiones**: HikariCP con configuración optimizada
- **Logs SQL**: Habilitados para desarrollo

## 🧪 Testing

### Ejecutar Tests
```bash
mvn test
```

### Cobertura de Tests
```bash
mvn jacoco:report
```

## 📚 Documentación de la API

### Swagger UI
Accede a `http://localhost:8081/swagger-ui.html` para:
- Explorar todos los endpoints disponibles
- Probar la API directamente desde el navegador
- Ver esquemas de datos y ejemplos

### Endpoints Principales

#### Formación de Equipos
- `GET /teamFormation/getTeams` - Generar equipos optimizados

#### Gestión de Entidades
- `GET|POST|PUT|DELETE /persons` - Gestión de personas
- `GET|POST|PUT|DELETE /projects` - Gestión de proyectos
- `GET|POST|PUT|DELETE /roles` - Gestión de roles
- `GET|POST|PUT|DELETE /competences` - Gestión de competencias

## 🔧 Configuración Avanzada

### Perfiles de Entorno
```bash
# Desarrollo
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Producción
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### Variables de Entorno
```bash
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=TeamSoft-DB
export DB_USER=postgres
export DB_PASSWORD=tu_password
```

## 🐳 Docker (Opcional)

### Crear imagen Docker
```bash
docker build -t teamsoft-backend .
```

### Ejecutar con Docker Compose
```bash
docker-compose up -d
```

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📝 Estructura de Commits

```
feat: nueva funcionalidad
fix: corrección de bugs
docs: documentación
style: formato de código
refactor: refactorización
test: tests
chore: tareas de mantenimiento
```

## 🐛 Solución de Problemas

### Error de Conexión a Base de Datos
```bash
# Verificar que PostgreSQL esté ejecutándose
sudo systemctl status postgresql

# Verificar conectividad
psql -h localhost -p 5432 -U postgres -d TeamSoft-DB
```

### Error de Puerto en Uso
```bash
# Cambiar puerto en application.yml
server:
  port: 8082
```

### Problemas con Dependencias
```bash
mvn clean install -U
```

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para detalles.

## 👥 Autores

- **Tu Nombre** - *Desarrollo inicial* - [tu-github](https://github.com/tu-usuario)

## 🙏 Agradecimientos

- Universidad por el apoyo en la investigación
- Comunidad Spring Boot por la documentación
- Contribuidores del proyecto BiCIAM

## 📞 Contacto

- Email: tu-email@universidad.edu
- LinkedIn: [tu-perfil](https://linkedin.com/in/tu-perfil)
- Proyecto: [https://github.com/tu-usuario/TeamSoft-BackEnd](https://github.com/tu-usuario/TeamSoft-BackEnd)

---

⭐ Si este proyecto te ha sido útil, ¡no olvides darle una estrella!
