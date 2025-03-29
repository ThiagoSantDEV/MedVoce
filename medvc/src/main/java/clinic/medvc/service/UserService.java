package clinic.medvc.service;

import clinic.medvc.controller.User.CreateUserDto;
import clinic.medvc.controller.User.UpdateUserDto;
import clinic.medvc.model.User;
import org.springframework.stereotype.Service;
import clinic.medvc.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var entity = new User(
                null,
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

    public Optional<User>  getUserById(String userId){
        return userRepository.findById(UUID.fromString(userId));
    }
    public Optional<User> getUserByEmail(String email){return userRepository.findById(UUID.fromString(email));}

    public List<User> listUser(){
        return userRepository.findAll();
    }

    public void updateUser(String userId,
                           UpdateUserDto updateUserDto){

        var id  = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            var user =  userEntity.get();

            if(updateUserDto.name() != null){
                user.setName(updateUserDto.name());

            }

            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }
            userRepository.save(user);

        }
    }


        public void deleteUser(String userId){
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if(userExists){
            userRepository.deleteById(id);
        }



    }
}
