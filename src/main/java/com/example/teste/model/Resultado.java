package com.example.teste.model;

public class Resultado {
	public static Resultado CLIENTE = new Resultado(true);
	public static Resultado NAO_CLIENTE = new Resultado(false);
	
	private boolean cliente;
	
	private Resultado(final boolean cliente) {
		this.cliente = cliente;
	}

	public boolean isCliente() {
		return cliente;
	}

	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}
}
