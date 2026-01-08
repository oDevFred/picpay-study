package io.github.odevfred.picpaystudy.application.service;

import io.github.odevfred.picpaystudy.application.dtos.CreateUserRequest;
import io.github.odevfred.picpaystudy.application.dtos.UserResponse;
import io.github.odevfred.picpaystudy.domain.entities.User;
import io.github.odevfred.picpaystudy.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        // Valida se o CPF existe
        if (userRepository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        // Valida se o email existe
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        // Cria entidade
        User user = new User();
        user.setFullName(request.getFullName());
        user.setCpf(request.getCpf());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserType(request.getUserType());
        user.setBalance(request.getInitialBalance());

        // Salva no banco
        User savedUser = userRepository.save(user);

        // Retorna o DTO
        return UserResponse.fromEntity(savedUser);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        return UserResponse.fromEntity(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }
}
