import { Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './login/dashboard/dashboard.component';


export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'user-registration', component: UserRegistrationComponent },// Rota do formulário
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];