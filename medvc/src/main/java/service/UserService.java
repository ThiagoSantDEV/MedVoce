package service;

import controller.CreateUserDto;
import model.User;
import org.springframework.stereotype.Service;
import repository.UserRepository;

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
                UUID.randomUUID(),
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
