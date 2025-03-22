package clinic.medvc.controller;

public record CreateUserDto(String name, String cpf, String email, String password) {
}
