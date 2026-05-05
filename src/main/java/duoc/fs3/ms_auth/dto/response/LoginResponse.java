package duoc.fs3.ms_auth.dto.response;

import duoc.fs3.ms_auth.service.MessageService;
import lombok.*;

/**
 * DTO para la respuesta de inicio de sesión.
 * 
 * Esta clase contiene la información que se devuelve al usuario
 * después de un inicio de sesión exitoso, incluyendo el token JWT.
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
}
