import { ApplicationConfig } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes'; 
import { provideZoneChangeDetection } from '@angular/core';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(), // Configura o HttpClient
    provideZoneChangeDetection({ eventCoalescing: true }), // Configura o Zone.js
    provideRouter(routes), // Configura o roteamento
  ]
};  