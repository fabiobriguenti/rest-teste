package com.example.teste.util;

import java.time.LocalDate;
import java.util.LinkedList;

public final class Validades {
	private static final LinkedList<LocalDate> VALIDADES_GERENCIADAS = new LinkedList<>();
	
	private final static int CAPACIDADE = 35;
	
	static {
		for (int i = -1; i < CAPACIDADE; i++) {
			VALIDADES_GERENCIADAS.add(LocalDate.now().plusDays(i));
		}
	}
	
	private Validades() {
		
	}
	
	public static LocalDate para(final int dia, final int mes, final int ano) {
		
		if (VALIDADES_GERENCIADAS.peekFirst().isBefore(LocalDate.now().plusDays(-1))) {
			VALIDADES_GERENCIADAS.pollFirst();
			VALIDADES_GERENCIADAS.add(VALIDADES_GERENCIADAS.peekLast().plusDays(1));
		}
		
		
		LocalDate dataSolicitada = LocalDate.of(ano, mes, dia);
		
		if (dataSolicitada.isBefore(VALIDADES_GERENCIADAS.peekFirst())) {
			dataSolicitada = VALIDADES_GERENCIADAS.peekFirst();
		} else if (dataSolicitada.isAfter(VALIDADES_GERENCIADAS.peekLast())) {
			dataSolicitada = VALIDADES_GERENCIADAS.peekLast();
		} else {
			for (final LocalDate vigenciaGerenciada : VALIDADES_GERENCIADAS) {
				if (dataSolicitada.equals(vigenciaGerenciada)) {
					dataSolicitada = vigenciaGerenciada;
					break;
				}
			}
		}
		return dataSolicitada;
	}
}