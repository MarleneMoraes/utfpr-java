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
      name: "Recursos Humanos"
    },
    {
      id: 2,
      name: "Financeiro"
    },
    {
      id: 3,
      name: "TI"
    },
    {
      id: 4,
      name: "Marketing"
    },
    {
      id: 5,
      name: "Vendas"
    },
    {
      id: 6,
      name: "Produção"
    },
    {
      id: 7,
      name: "Logística"
    },
    {
      id: 8,
      name: "Compras"
    },
    {
      id: 9,
      name: "Qualidade"
    },
    {
      id: 10,
      name: "Pesquisa e Desenvolvimento"
    },
    {
      id: 11,
      name: "Suporte ao Cliente"
    }
  ]
}
