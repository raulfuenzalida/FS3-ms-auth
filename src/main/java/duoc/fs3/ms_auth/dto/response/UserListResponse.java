package duoc.fs3.ms_auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO para la respuesta de lista de usuarios.
 * 
 * Esta clase contiene la lista de usuarios del sistema
 * que puede ser devuelta en las respuestas de la API para administradores.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListResponse {

    /**
     * Lista de usuarios del sistema.
     */
    private List<UserResponse> users;

    /**
     * Cantidad total de usuarios.
     */
    private Integer total;
}
