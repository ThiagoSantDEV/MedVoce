package clinic.medvc.controller.User;

import clinic.medvc.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import clinic.medvc.service.UserService;

import java.net.URI;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userRegistration")
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
    }
}
