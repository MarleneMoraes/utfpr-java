import { Component } from '@angular/core';

@Component({
  selector: 'app-cantores-pesquisa',
  templateUrl: './cantores-pesquisa.component.html',
  styleUrls: ['./cantores-pesquisa.component.css']
})
export class CantoresPesquisaComponent {
  cantores = [
    { id: 1, nome: 'Marisa Monte', pais: 'Brasil' },
    { id: 2, nome: 'Coldplay', pais: 'Inglaterra' },
    { id: 3, nome: 'U2', pais: 'Irlanda' },
    { id: 4, nome: 'Djavan', pais: 'Brasil' },
    { id: 5, nome: 'Laura Pausini', pais: 'Italia' },
    { id: 6, nome: 'Roberto Leal', pais: 'Portugal' },
    { id: 7, nome: 'The Corrs', pais: 'Estados Unidos' },
    { id: 8, nome: 'Legiao Urbana', pais: 'Brasil' },
    { id: 9, nome: 'Cazuza', pais: 'Brasil' },
    { id: 10, nome: 'Tom Jobim', pais: 'Brasil' },
    { id: 11, nome: 'Frank Sinatra', pais: 'Estados Unidos' },
  ];
}
