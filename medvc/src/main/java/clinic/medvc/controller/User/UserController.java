package clinic.medvc.controller.User;

import clinic.medvc.model.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import clinic.medvc.service.UserService;

import java.net.URI;
import java.util.List;


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

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        var user = userService.getUserById(userId);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
                                               @RequestBody UpdateUserDto updateUserDto ) {
        userService.updateUser(userId, updateUserDto);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUser();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> DeleteById(@PathVariable("userId") String userId) {
    userService.deleteUser(userId);

    return ResponseEntity.noContent().build();
    }

}
