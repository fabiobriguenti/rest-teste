package com.example.teste.model;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.teste.RestTesteApplication;

public class Cliente {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestTesteApplication.class);
	
	public String cpf;
	private Apolice apolice;
	private String placa;
	private LocalDate vigenciaFinal;
	
	public Cliente(
			final String cpf, 
			final Integer sucursal, 
			final Integer apolice, 
			final String placa, 
			final LocalDate vigenciaFinal) {
		this.cpf = cpf;
		this.placa = placa;
		this.apolice = new Apolice(sucursal, apolice);
		this.vigenciaFinal = vigenciaFinal;
		LOGGER.info(this.toString());
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Apolice getApolice() {
		return apolice;
	}
	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	public LocalDate getVigenciaFinal() {
		return vigenciaFinal;
	}
	public void setVigenciaFinal(LocalDate vigenciaFinal) {
		this.vigenciaFinal = vigenciaFinal;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", apolice=" + apolice + ", placa=" + placa + ", vigenciaFinal=" + vigenciaFinal + "]";
	}
	
}