package duoc.fs3.ms_auth.dto.response;

import duoc.fs3.ms_auth.service.MessageService;
import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO para la respuesta de registro de usuario.
 * 
 * Esta clase contiene la información que se devuelve al usuario
 * después de un registro exitoso, incluyendo detalles del usuario
 * registrado y un mensaje de confirmación.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    /**
     * Mensaje de estado de la operación de registro.
     */
    private String message;

    /**
     * Nombre de usuario del usuario registrado.
     */
    private String username;

    /**
     * Correo electrónico del usuario registrado.
     */
    private String email;

    /**
     * Timestamp del registro del usuario.
     */
    private LocalDateTime timestamp;

    /**
     * Indica si el registro fue exitoso.
     */
    private boolean success;

    /**
     * Constructor por defecto que inicializa los valores básicos.
     * 
     * @param username Nombre de usuario registrado
     * @param email Correo electrónico registrado
     */
    public RegisterResponse(String username, String email) {
        this.username = username;
        this.email = email;
        this.message = "Usuario registrado correctamente";
        this.success = true;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor que usa MessageService para obtener mensaje personalizado.
     * 
     * @param username Nombre de usuario registrado
     * @param email Correo electrónico registrado
     * @param messageService Servicio para obtener mensaje de éxito
     */
    public RegisterResponse(String username, String email, MessageService messageService) {
        this.username = username;
        this.email = email;
        this.message = messageService.getMessage("success.user.registered");
        this.success = true;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor para respuestas de error.
     * 
     * @param message Mensaje de error
     * @param success Estado de éxito (false para errores)
     */
    public RegisterResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
        this.timestamp = LocalDateTime.now();
    }
}
