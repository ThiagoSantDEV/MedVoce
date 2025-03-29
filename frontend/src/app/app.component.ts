import { Component } from '@angular/core';
import { RouterModule } from '@angular/router'; // Importe o RouterModule

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule], // Adicione o RouterModule aqui
  template: `
    <router-outlet></router-outlet>
  `,
})
export class AppComponent {
  title(title: any) {
    throw new Error('Method not implemented.');
  }
}