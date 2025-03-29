package clinic.medvc.controller.User;

import clinic.medvc.model.Enum.UserRole;

public record CreateUserDto(String name, String cpf, String email, String password, UserRole role) {
}
