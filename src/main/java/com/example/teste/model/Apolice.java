package com.example.teste.model;

public class Apolice {
	private Integer sucursal;
	private Integer apolice;
	
	public Apolice(
			final Integer sucursal,
			final Integer apolice) {
		this.sucursal = sucursal;
		this.apolice = apolice;
	}
	
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	
	public Integer getApolice() {
		return apolice;
	}
	public void setApolice(Integer apolice) {
		this.apolice = apolice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apolice == null) ? 0 : apolice.hashCode());
		result = prime * result + ((sucursal == null) ? 0 : sucursal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apolice other = (Apolice) obj;
		if (apolice == null) {
			if (other.apolice != null)
				return false;
		} else if (!apolice.equals(other.apolice))
			return false;
		if (sucursal == null) {
			if (other.sucursal != null)
				return false;
		} else if (!sucursal.equals(other.sucursal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Apolice [sucursal=" + sucursal + ", apolice=" + apolice + "]";
	}
}
