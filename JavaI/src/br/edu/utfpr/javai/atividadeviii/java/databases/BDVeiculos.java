package br.edu.utfpr.javai.atividadeviii.java.databases;

import br.edu.utfpr.javai.atividadeviii.java.entities.Carga;
import br.edu.utfpr.javai.atividadeviii.java.entities.Passeio;

import java.util.List;
import java.util.ArrayList;

public final class BDVeiculos {
	private List<Passeio> listaPasseio;
	private List<Carga> listaCarga;

	public BDVeiculos() { 
		this.listaPasseio = new ArrayList<>();
		this.listaCarga = new ArrayList<>();
	}

	public List<Passeio> getListaPasseio() {
		return listaPasseio;
	}

	public void setListaPasseio(List<Passeio> listaPasseio) {
		this.listaPasseio = listaPasseio;
	}

	public List<Carga> getListaCarga() {
		return listaCarga;
	}

	public void setListaCarga(List<Carga> listaCarga) {
		this.listaCarga = listaCarga;
	}
}

