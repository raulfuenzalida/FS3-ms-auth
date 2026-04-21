package duoc.fs3.ms_auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}