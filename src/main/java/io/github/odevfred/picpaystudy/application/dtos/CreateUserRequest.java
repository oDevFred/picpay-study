package io.github.odevfred.picpaystudy.application.dtos;

import io.github.odevfred.picpaystudy.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Nome completo é obrigatório")
    private String fullName;

    @CPF(message = "CPF inválido")
    private String cpf;

    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    private String password;

    @NotNull(message = "Tipo de usuário é obrigatório")
    private User.UserType userType;

    private BigDecimal initialBalance = BigDecimal.ZERO;
}
