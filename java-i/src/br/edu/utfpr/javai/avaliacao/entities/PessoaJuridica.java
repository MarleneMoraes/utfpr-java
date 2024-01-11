package br.edu.utfpr.javai.avaliacao.entities;

public final class PessoaJuridica extends ClienteBanco {
	private int cnpj = 0;
	private PessoaFisica responsavel = new PessoaFisica();

	public int getCnpj() {
		return cnpj;
	}

	public void setCnpj(int cnpj) {
		this.cnpj = cnpj;
	}

	public PessoaFisica getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(PessoaFisica responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public void verifDoc() {
		if (this.responsavel.getNome().length() <= 30) {
			System.out.println("Nome v�lido para Respons�vel");
		} else {
			System.out.println("Nome inv�lido para Respons�vel");
		}
	}
}