# 🔐 MS-AUTH - Microservicio de Autenticación

Microservicio desarrollado en Spring Boot encargado de la autenticación de usuarios mediante JWT.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot 3.5.13
- Spring Security
- Spring Data JPA
- MySQL (Laragon)
- JWT (jjwt)
- Maven

---

## ⚙️ Configuración

### 📌 Base de datos

Configurar en `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_bd
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Ejecución

```bash
mvn spring-boot:run
```

El servicio quedará disponible en:

```
http://localhost:8080
```

---

## 🔐 Endpoints

### 📌 Registro

**POST** `/auth/register`

```json
{
  "email": "test@mail.com",
  "password": "123456"
}
```

✔ Registra un usuario  
✔ Contraseña encriptada con BCrypt  

---

### 📌 Login

**POST** `/auth/login`

```json
{
  "email": "test@mail.com",
  "password": "123456"
}
```

✔ Valida credenciales  
✔ Retorna JWT  

**Respuesta:**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

---

### 📌 Endpoint protegido

**GET** `/auth/test`

**Headers:**

```
Authorization: eyJhbGciOiJIUzI1NiIs...
```

✔ Requiere token válido  
✔ Respuesta:

```
Acceso permitido
```

---

## 🔑 Autenticación

- El token se genera en el login  
- Se envía en el header `Authorization`  
- Es validado por `JwtFilter`  

---

## 🛡️ Seguridad

- Contraseñas encriptadas con BCrypt  
- Validación de token en endpoints protegidos  
- `/auth/**` es público  

---

## 📂 Estructura del proyecto

```
ms-auth
│── config
│   └── SecurityConfig.java
│
│── controller
│   ├── AuthController.java
│   └── TestController.java
│
│── dto
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   └── AuthResponse.java
│
│── model
│   └── User.java
│
│── repository
│   └── UserRepository.java
│
│── security
│   ├── JwtService.java
│   └── JwtFilter.java
│
│── service
│   └── AuthService.java
```

---

## 📌 Estado del proyecto

✔ Registro funcionando  
✔ Login funcionando  
✔ JWT operativo  
✔ Endpoint protegido funcionando  

---

## 🚀 Próximas mejoras
 
- Manejo global de errores  
- Refresh tokens  
- Integración con otros microservicios  

---

## 🧪 Pruebas

Se recomienda usar Postman para probar los endpoints.
