import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ButtonModule } from 'primeng/button'
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { CantoresPesquisaComponent } from './cantores-pesquisa/cantores-pesquisa.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CantorCadastroComponent } from './cantor-cadastro/cantor-cadastro.component';

@NgModule({
  declarations: [
    AppComponent,
    CantoresPesquisaComponent,
    NavbarComponent,
    CantorCadastroComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
    InputTextModule,
    TableModule,
    TooltipModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
