import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  nomeCantor = '';
  numero = 0;

  adicionar() {
    this.numero++;
    this.nomeCantor = '';
  }
}
