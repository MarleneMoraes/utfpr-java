package br.edu.utfpr.javai.avaliacao.main;

import br.edu.utfpr.javai.avaliacao.entities.PessoaFisica;
import br.edu.utfpr.javai.avaliacao.entities.PessoaJuridica;
import br.edu.utfpr.javai.avaliacao.exception.NumException;
import br.edu.utfpr.javai.avaliacao.utils.Endereco;

public class TstConta {

	public static void main(String[] args) {
		
		// ENTRADA DE DADOS 
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		
		try {
			pessoaJuridica.setNumeroConta(12356);
		} catch (NumException e) {
			e.impMsg();
		}
		
		pessoaJuridica.setCnpj(123456789);
		
		Endereco endereco = new Endereco();
		endereco.setRua("Rua Java");
		endereco.setNum(128);
		
		pessoaJuridica.setEnder(endereco);
		
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setCpf(987654321);
		pessoaFisica.setNome("José Antonio");
		
		pessoaJuridica.setResponsavel(pessoaFisica);
		
		
		// SAIDA DE DADOS
		
		System.out.println(pessoaJuridica.getNumeroConta());
		pessoaJuridica.validar();
		System.out.println(pessoaJuridica.getCnpj());
		System.out.println(pessoaJuridica.getEnder().getRua() + "\n");
		
		
		System.out.println(pessoaFisica.getCpf());
		pessoaFisica.verifDoc();
		System.out.println(pessoaFisica.getNome());
		pessoaJuridica.verifDoc();
	}

}
