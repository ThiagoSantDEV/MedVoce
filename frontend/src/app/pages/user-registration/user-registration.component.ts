import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';


@Component({
  selector: 'app-user-registration',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css'],
  
})
export class UserRegistrationComponent {
  user = { name: '',cpf:'', email: '', password: '' }; // Modelo de dados do formulário

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('/v1/users/userRegistration', this.user, { observe: 'response' }).subscribe(
      (response) => {
        console.log('Usuário criado:', response);
        alert('Usuário registrado com sucesso!');
        this.router.navigate(['/']); // Redireciona para a página inicial após o registro
      },
      (error) => {
        console.error('Erro ao registrar usuário:', error);
        alert('Erro ao registrar usuário.');
      }
    );
  }
}