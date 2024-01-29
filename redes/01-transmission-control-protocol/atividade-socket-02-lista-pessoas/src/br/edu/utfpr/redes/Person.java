package br.edu.utfpr.redes;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String cpf;
	private String address;
	private String telephone;
	private String email;
	private long age;
	
	public Person() { }

	public Person(String name, String cpf, String address, String telephone, String email, long age) {
		this.name = name;
		this.cpf = cpf;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "\nNome: " + name + "\nCPF: " + cpf + "\nEndereco: " + address + "\nTelefone: " + telephone
				+ "\nE-mail: " + email + "\nIdade: " + age + "\n";
	}
}
