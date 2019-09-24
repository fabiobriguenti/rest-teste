package com.example.teste.service;

import java.time.LocalDate;

import com.example.teste.model.Apolice;

public interface ClienteService {
	LocalDate porCPF(final String cpf);
	LocalDate porApolice(final Apolice apolice);
	LocalDate porPlaca(final String placa);
}