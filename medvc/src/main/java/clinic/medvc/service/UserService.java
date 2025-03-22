package clinic.medvc.service;

import clinic.medvc.controller.User.CreateUserDto;
import clinic.medvc.model.User;
import org.springframework.stereotype.Service;
import clinic.medvc.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var entity = new User(
                null, // Remova a geração manual do UUID
                createUserDto.name(),
                createUserDto.cpf(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null
        );

        var userSaved = userRepository.save(entity);
        return userSaved.getIdUser();
    }
}
