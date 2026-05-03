package duoc.fs3.ms_auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para verificar la configuración HS256 y JWT typ en JwtService.
 * 
 * Esta clase verifica que los tokens JWT se generen con el algoritmo HS256
 * y el header typ=JWT para compatibilidad con JWT debugger.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
class JwtServiceHS256Test {

    private JwtService jwtService;
    private String testSecret;

    /**
     * Configuración inicial para cada prueba.
     */
    @BeforeEach
    void setUp() {
        // Usar una clave secreta de prueba en Base64
        SecretKey key = Keys.hmacShaKeyFor("test-secret-key-for-hs256-algorithm-256-bits".getBytes());
        testSecret = Base64.getEncoder().encodeToString(key.getEncoded());
        
        jwtService = new JwtService();
        // Usar reflection para establecer el secret (ya que es @Value)
        try {
            var field = JwtService.class.getDeclaredField("jwtSecret");
            field.setAccessible(true);
            field.set(jwtService, testSecret);
        } catch (Exception e) {
            fail("No se pudo configurar el secret para prueba: " + e.getMessage());
        }
    }

    /**
     * Prueba que el token generado contiene el header typ=JWT.
     */
    @Test
    @DisplayName("Debería generar token con header typ=JWT")
    void testGenerateTokenWithJwtTyp() {
        // When
        String token = jwtService.generateToken("testuser");
        
        // Then
        assertNotNull(token);
        
        // Parsear el token para verificar el header
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "Token debe tener 3 partes (header.payload.signature)");
        
        // Decodificar el header
        String headerJson = new String(Base64.getDecoder().decode(parts[0]));
        assertTrue(headerJson.contains("\"typ\":\"JWT\""), "Header debe contener typ=JWT");
        assertTrue(headerJson.contains("\"alg\":\"HS256\""), "Header debe contener alg=HS256");
    }

    /**
     * Prueba que el token generado usa el algoritmo HS256.
     */
    @Test
    @DisplayName("Debería generar token con algoritmo HS256")
    void testGenerateTokenWithHS256() {
        // When
        String token = jwtService.generateToken("testuser");
        
        // Then
        assertNotNull(token);
        
        // Verificar que el token puede ser parseado con la misma clave
        assertDoesNotThrow(() -> {
            String username = jwtService.extractUsername(token);
            assertEquals("testuser", username);
        });
    }

    /**
     * Prueba que el token contiene el subject correcto.
     */
    @Test
    @DisplayName("Debería contener subject correcto en el token")
    void testTokenContainsCorrectSubject() {
        // When
        String token = jwtService.generateToken("testuser123");
        
        // Then
        String username = jwtService.extractUsername(token);
        assertEquals("testuser123", username);
    }

    /**
     * Prueba la estructura completa del token.
     */
    @Test
    @DisplayName("Debería tener estructura JWT válida")
    void testTokenStructure() {
        // When
        String token = jwtService.generateToken("testuser");
        
        // Then
        assertNotNull(token);
        
        // Verificar formato JWT (3 partes separadas por puntos)
        String[] parts = token.split("\\.");
        assertEquals(3, parts.length, "Token JWT debe tener 3 partes");
        
        // Verificar que cada parte no esté vacía
        assertTrue(parts[0].length() > 0, "Header no debe estar vacío");
        assertTrue(parts[1].length() > 0, "Payload no debe estar vacío");
        assertTrue(parts[2].length() > 0, "Signature no debe estar vacía");
        
        // Verificar que las partes están en Base64 válido
        assertDoesNotThrow(() -> Base64.getDecoder().decode(parts[0]));
        assertDoesNotThrow(() -> Base64.getDecoder().decode(parts[1]));
    }
}
