import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';

export const routes: Routes = [
  { path: '', component: AppComponent }, // Rota raiz
  { path: 'user-registration', component: UserRegistrationComponent }, // Rota do formulário
];