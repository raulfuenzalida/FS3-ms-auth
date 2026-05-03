package duoc.fs3.ms_auth.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias para RegisterResponse.
 * 
 * Esta clase contiene las pruebas unitarias para el DTO de respuesta
 * de registro de usuarios utilizando JUnit 5.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
class RegisterResponseTest {

    private RegisterResponse successResponse;
    private RegisterResponse errorResponse;

    /**
     * Configuración inicial para cada prueba.
     */
    @BeforeEach
    void setUp() {
        successResponse = new RegisterResponse("testuser", "test@example.com");
        errorResponse = new RegisterResponse("Error message", false);
    }

    /**
     * Prueba la creación de una respuesta exitosa.
     */
    @Test
    @DisplayName("Debería crear respuesta exitosa correctamente")
    void testCreateSuccessResponse() {
        assertEquals("testuser", successResponse.getUsername());
        assertEquals("test@example.com", successResponse.getEmail());
        assertEquals("Usuario registrado correctamente", successResponse.getMessage());
        assertTrue(successResponse.isSuccess());
        assertNotNull(successResponse.getTimestamp());
    }

    /**
     * Prueba la creación de una respuesta de error.
     */
    @Test
    @DisplayName("Debería crear respuesta de error correctamente")
    void testCreateErrorResponse() {
        assertEquals("Error message", errorResponse.getMessage());
        assertFalse(errorResponse.isSuccess());
        assertNull(errorResponse.getUsername());
        assertNull(errorResponse.getEmail());
        assertNotNull(errorResponse.getTimestamp());
    }

    /**
     * Prueba el constructor con todos los parámetros.
     */
    @Test
    @DisplayName("Debería crear respuesta con constructor completo")
    void testCreateFullResponse() {
        LocalDateTime now = LocalDateTime.now();
        RegisterResponse response = new RegisterResponse(
                "Custom message", "fulluser", "full@example.com", now, true
        );

        assertEquals("Custom message", response.getMessage());
        assertEquals("fulluser", response.getUsername());
        assertEquals("full@example.com", response.getEmail());
        assertEquals(now, response.getTimestamp());
        assertTrue(response.isSuccess());
    }

    /**
     * Prueba el patrón builder.
     */
    @Test
    @DisplayName("Debería crear respuesta usando builder")
    void testCreateWithBuilder() {
        LocalDateTime now = LocalDateTime.now();
        RegisterResponse response = RegisterResponse.builder()
                .message("Builder message")
                .username("builderuser")
                .email("builder@example.com")
                .timestamp(now)
                .success(true)
                .build();

        assertEquals("Builder message", response.getMessage());
        assertEquals("builderuser", response.getUsername());
        assertEquals("builder@example.com", response.getEmail());
        assertEquals(now, response.getTimestamp());
        assertTrue(response.isSuccess());
    }

    /**
     * Prueba los setters y getters.
     */
    @Test
    @DisplayName("Debería funcionar setters y getters")
    void testSettersAndGetters() {
        RegisterResponse response = new RegisterResponse();
        LocalDateTime now = LocalDateTime.now();

        response.setMessage("Updated message");
        response.setUsername("updateduser");
        response.setEmail("updated@example.com");
        response.setTimestamp(now);
        response.setSuccess(false);

        assertEquals("Updated message", response.getMessage());
        assertEquals("updateduser", response.getUsername());
        assertEquals("updated@example.com", response.getEmail());
        assertEquals(now, response.getTimestamp());
        assertFalse(response.isSuccess());
    }
}
