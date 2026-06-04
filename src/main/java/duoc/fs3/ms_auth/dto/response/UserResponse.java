package duoc.fs3.ms_auth.dto.response;

import duoc.fs3.ms_auth.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para la respuesta de información de usuario.
 * 
 * Esta clase contiene la información pública de un usuario
 * que puede ser devuelta en las respuestas de la API.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre de usuario.
     */
    private String username;

    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Rol del usuario en el sistema.
     */
    private UserRole role;

    /**
     * Indica si el usuario está habilitado.
     */
    private Boolean enabled;

    /**
     * Fecha y hora de creación del usuario.
     */
    private LocalDateTime createdAt;

    /**
     * Fecha y hora de última actualización.
     */
    private LocalDateTime updatedAt;
}
