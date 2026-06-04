package duoc.fs3.ms_auth.controller;

import duoc.fs3.ms_auth.dto.request.UpdateUserRequest;
import duoc.fs3.ms_auth.dto.response.UserListResponse;
import duoc.fs3.ms_auth.dto.response.UserResponse;
import duoc.fs3.ms_auth.model.User;
import duoc.fs3.ms_auth.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para operaciones administrativas.
 * 
 * Esta clase proporciona endpoints exclusivos para administradores
 * para gestionar usuarios del sistema. Todos los endpoints requieren
 * autenticación JWT y rol ADMIN.
 * 
 * @author Duoc UC Fullstack III
 * @version 1.0
 * @since 2026
 */
@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200", "http://localhost:8081", "http://localhost:8084"})
@Tag(name = "Administración", description = "API para gestión de usuarios (solo administradores)")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final UserRepository userRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     * 
     * @param userRepository Repositorio de usuarios
     */
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Obtiene todos los usuarios del sistema.
     * 
     * @return Lista de todos los usuarios
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios del sistema (solo ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado (solo ADMIN)")
    })
    public ResponseEntity<UserListResponse> getAllUsers() {
        logger.info("Solicitud para listar todos los usuarios");
        
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = users.stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
        
        UserListResponse response = UserListResponse.builder()
                .users(userResponses)
                .total(userResponses.size())
                .build();
        
        logger.info("Retornando {} usuarios", userResponses.size());
        return ResponseEntity.ok(response);
    }

    /**
     * Actualiza la información de un usuario.
     * 
     * @param id Identificador del usuario
     * @param request Datos actualizados del usuario
     * @return Usuario actualizado
     */
    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario (solo ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado (solo ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        
        logger.info("Solicitud para actualizar usuario con ID: {}", id);
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        
        User updatedUser = userRepository.save(user);
        logger.info("Usuario actualizado exitosamente: {}", updatedUser.getUsername());
        
        return ResponseEntity.ok(mapToUserResponse(updatedUser));
    }

    /**
     * Elimina un usuario del sistema.
     * 
     * @param id Identificador del usuario
     * @return Respuesta de confirmación
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema (solo ADMIN)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Acceso denegado (solo ADMIN)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        logger.info("Solicitud para eliminar usuario con ID: {}", id);
        
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        
        userRepository.deleteById(id);
        logger.info("Usuario eliminado exitosamente con ID: {}", id);
        
        return ResponseEntity.ok().build();
    }

    /**
     * Mapea una entidad User a un UserResponse.
     * 
     * @param user Entidad de usuario
     * @return DTO de respuesta de usuario
     */
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getIdUser())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
