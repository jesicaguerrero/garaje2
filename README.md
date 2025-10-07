# garaje2
VehicleAPP2 

Aplicación web desarrollada en Jakarta EE 10 con arquitectura en capas, que permite gestionar vehículos mediante Servlets, JSP y componentes de persistencia.


## 1. Arquitectura del Proyecto

El sistema sigue una arquitectura en capas:

Modelo (`com.garaje.model`)
  - Contiene las entidades principales de negocio.
  - Ejemplo: `Vehiculo.java`.

DAO – Data Access Object (`com.garaje.persistence`)
  - Se encarga de la comunicación con la base de datos.
  - Ejemplo: `VehiculoDAO.java`.

Fachada (`com.garaje.facade`)
  - Expone reglas de negocio y valida restricciones antes de acceder a la capa DAO.
  - Ejemplo: `VehiculoFacade.java`, `BusinessException.java`.

Presentación
  - **Servlets (`com.garaje.vehicleapp2`)**: controladores que reciben y responden peticiones.
    - Ejemplo: `VehicleServlet.java`.
  - **Vistas (`src/main/webapp`)**: páginas estáticas/dinámicas.
    - `index.html` (welcome file).
    - `vehicles.jsp` (vista principal de vehículos).

Servicios REST (`com.uts.garaje.vehicleapp2.resources`)**
  - Se expone un recurso REST configurado con `JakartaRestConfiguration.java` y `JakartaEE10Resource.java`.

Archivos de configuración**
  - `web.xml` → mapea servlets y define welcome file.
  - `glassfish-web.xml` → define el context-root (`/VehicleAPP2`).
  - `beans.xml` → activa CDI (Context and Dependency Injection).


## 2. Convenciones de Nombres

- Paquetes: en minúscula, con prefijo `com.garaje` para clases de negocio y `com.uts.garaje` para recursos REST.
- Clases: en PascalCase (`Vehiculo`, `VehiculoDAO`, `VehiculoFacade`).
- Métodos: en camelCase (`agregarVehiculo`, `buscarPorPlaca`).
- Atributos: en camelCase (`placa`, `color`, `modelo`).
- Archivos JSP/HTML: en minúscula (`index.html`, `vehicles.jsp`).


## 3. Uso de Git

Flujo recomendado:
  - Rama principal: `main` o `master`.
  - Ramas de trabajo:
    - `feature/nueva_funcionalidad`
    - `bugfix/correccion_error`
  - Pull Requests revisados por otro integrante antes de hacer merge.

Convenciones de commits
  - `feat:` para nuevas funcionalidades → `feat: agregar validación de placa duplicada`.
  - `fix:` para correcciones → `fix: corregir error en búsqueda de vehículos`.
  - `docs:` para documentación → `docs: actualizar README.md`.



## 4. Cómo Ejecutar el Sistema

1.Requisitos previos  
   - Java JDK 11 o superior  
   - Apache Maven  
   - Servidor GlassFish 6 o superior  
   - NetBeans o IDE compatible  

2.Clonar el repositorio
   ```bash
   git clone https://github.com/jesicaguerrero/garaje2
   cd VehicleAPP2
