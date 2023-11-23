package br.edu.utfpr.javai.atividadevi.java.databases;

import br.edu.utfpr.javai.atividadeiv.java.entities.Carga;
import br.edu.utfpr.javai.atividadeiv.java.entities.Passeio;

public final class BDVeiculos {
	private Passeio[] listaPasseio;
	private Carga[] listaCarga;

	public BDVeiculos() { 
		this.listaPasseio = new Passeio[5];
		this.listaCarga = new Carga[5];
	}

	public Passeio[] getListaPasseio() {
		return listaPasseio;
	}

	public void setListaPasseio(Passeio[] listaPasseio) {
		this.listaPasseio = listaPasseio;
	}

	public Carga[] getListaCarga() {
		return listaCarga;
	}

	public void setListaCarga(Carga[] listaCarga) {
		this.listaCarga = listaCarga;
	}
}
