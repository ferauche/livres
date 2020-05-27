package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumidorDTO {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private Long precomunidade;
    
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Long getPrecomunidade() {
		return precomunidade;
	}
	public void setPrecomunidade(Long precomunidade) {
		this.precomunidade = precomunidade;
	}
    
    
}
