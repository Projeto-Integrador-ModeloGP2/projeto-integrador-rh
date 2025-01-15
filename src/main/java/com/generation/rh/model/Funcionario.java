package com.generation.rh.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é Obrigatório")
	@Size(min = 3, max = 100, message = "O atributo nome deve conter no mínimo 03 e no máximo 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo e-mail é Obrigatório")
	@Size(min = 10, max = 100, message = "O atributo e-mail deve conter no mínimo 10 e no máximo 100 caracteres")
	private String email;
	
	@NotBlank(message = "O atributo cargo é Obrigatório")
	@Size(min = 3, max = 100, message = "O atributo cargo deve conter no mínimo 03 e no máximo 100 caracteres")
	private String cargo;
	
	@NotBlank(message = "O atributo CPF é Obrigatório")
	@Size(min = 11 , max = 11, message = "O atributo CPF deve conter 11 caracteres")
	private String cpf;
	
	@UpdateTimestamp
	private LocalDateTime data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}
