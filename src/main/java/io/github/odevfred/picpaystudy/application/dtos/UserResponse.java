package io.github.odevfred.picpaystudy.application.dtos;

import io.github.odevfred.picpaystudy.domain.entities.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserResponse {
    private Long id;
    private String fullName;
    private String cpf;
    private String email;
    private User.UserType userType;
    private BigDecimal balance;

    public static UserResponse fromEntity(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setCpf(user.getCpf());
        response.setUserType(user.getUserType());
        response.setBalance(user.getBalance());
        return response;
    }
}
