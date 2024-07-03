import { Component } from '@angular/core';

@Component({
  selector: 'app-departamentos-pesquisa',
  templateUrl: './departamentos-pesquisa.component.html',
  styleUrls: ['./departamentos-pesquisa.component.css']
})
export class DepartamentosPesquisaComponent {
  departamentos = [
    {
      id: 1,
      nome: "Recursos Humanos"
    },
    {
      id: 2,
      nome: "Financeiro"
    },
    {
      id: 3,
      nome: "TI"
    },
    {
      id: 4,
      nome: "Marketing"
    },
    {
      id: 5,
      nome: "Vendas"
    },
    {
      id: 6,
      nome: "Produção"
    },
    {
      id: 7,
      nome: "Logística"
    },
    {
      id: 8,
      nome: "Compras"
    },
    {
      id: 9,
      nome: "Qualidade"
    },
    {
      id: 10,
      nome: "Pesquisa e Desenvolvimento"
    },
    {
      id: 11,
      nome: "Suporte ao Cliente"
    }
  ]
}
