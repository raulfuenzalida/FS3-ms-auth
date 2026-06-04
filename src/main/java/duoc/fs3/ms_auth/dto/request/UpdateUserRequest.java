package duoc.fs3.ms_auth.dto.request;

import duoc.fs3.ms_auth.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO para la solicitud de actualización de usuario.
 * 
 * Esta clase contiene los datos necesarios para actualizar la información
 * de un usuario existente en el sistema. Solo los administradores pueden
 * realizar esta operación.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@Getter
@Setter
public class UpdateUserRequest {

    /**
     * Nuevo nombre de usuario.
     * Debe tener entre 4 y 30 caracteres.
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 4, max = 30, message = "El nombre debe tener entre 4 y 30 caracteres")
    private String username;

    /**
     * Nuevo correo electrónico del usuario.
     * Debe ser un correo válido.
     */
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe ser válido")
    private String email;

    /**
     * Nuevo rol del usuario.
     * Puede ser USER o ADMIN.
     */
    private UserRole role;
}
