package duoc.fs3.ms_auth.dto.response;

import duoc.fs3.ms_auth.model.UserRole;
import duoc.fs3.ms_auth.service.MessageService;
import lombok.*;

/**
 * DTO para la respuesta de inicio de sesión.
 * 
 * Esta clase contiene la información que se devuelve al usuario
 * después de un inicio de sesión exitoso, incluyendo el token JWT
 * y el rol del usuario.
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
public class LoginResponse {

    /**
     * Token JWT generado para la sesión del usuario.
     */
    private String token;

    /**
     * Tipo de token (siempre Bearer).
     */
    private String tokenType;

    /**
     * Mensaje de estado de la operación.
     */
    private String message;

    /**
     * Rol del usuario en el sistema.
     */
    private UserRole role;

    /**
     * Constructor por defecto que inicializa el tipo de token.
     */
    public LoginResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
        this.message = "Inicio de sesión exitoso";
    }

    /**
     * Constructor que usa MessageService para obtener mensaje personalizado.
     * 
     * @param token Token JWT generado
     * @param messageService Servicio para obtener mensaje de éxito
     */
    public LoginResponse(String token, MessageService messageService) {
        this.token = token;
        this.tokenType = "Bearer";
        this.message = messageService.getMessage("success.login");
    }

    /**
     * Constructor que incluye el rol del usuario.
     * 
     * @param token Token JWT generado
     * @param messageService Servicio para obtener mensaje de éxito
     * @param role Rol del usuario
     */
    public LoginResponse(String token, MessageService messageService, UserRole role) {
        this.token = token;
        this.tokenType = "Bearer";
        this.message = messageService.getMessage("success.login");
        this.role = role;
    }
}
