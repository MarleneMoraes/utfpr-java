import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';

class Departamento {
  nome: string = '';
}

@Component({
  selector: 'app-departamento-cadastro',
  templateUrl: './departamento-cadastro.component.html',
  styleUrl: './departamento-cadastro.component.css'
})

export class DepartamentoCadastroComponent {

  departamento = new Departamento();

  salvar(departamentoForm: NgForm) {
    console.log(departamentoForm);
    departamentoForm.reset({ departamento: '' });
  }
}
