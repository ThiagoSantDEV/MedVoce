import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // Importe isto

@Component({
  selector: 'app-user-registration',
  standalone: true,
  imports: [FormsModule, CommonModule], 
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css'],
})
export class UserRegistrationComponent {


  roles = [
    { value: 'ROLE_USER', display: 'Usuário Padrão' },
    { value: 'ROLE_ADMIN', display: 'Administrador' }
  ];
  
  user = { 
    name: '', 
    cpf: '', 
    email: '', 
    password: '', 
    role: 'ROLE_USER' 
  };

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('/v1/users/userRegistration', this.user, { observe: 'response' }).subscribe(
      (response) => {
        alert('Usuário registrado com sucesso!');
        this.router.navigate(['/']);
      },
      (error) => {
        alert('Erro ao registrar usuário: ' + (error.error?.message || error.message));
      }
    );
  }
}