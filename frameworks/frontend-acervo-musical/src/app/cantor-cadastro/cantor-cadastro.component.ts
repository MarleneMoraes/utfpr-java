import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

class Cantor {
  nome: string = '';
  pais: string = '';
}


@Component({
  selector: 'app-cantor-cadastro',
  templateUrl: './cantor-cadastro.component.html',
  styleUrls: ['./cantor-cadastro.component.css']
})
export class CantorCadastroComponent {

  paises = ['Brasil', 'Estados Unidos', 'Inglaterra', 'Irlanda', 'Itália', 'Portugal']

  cantor = new Cantor();


  salvar(cantorForm: NgForm) {
    console.log(cantorForm)
    cantorForm.reset({ pais: '' });
  }
}
