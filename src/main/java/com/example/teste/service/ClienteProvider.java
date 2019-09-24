package com.example.teste.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.teste.model.Apolice;
import com.example.teste.repository.ClienteRepository;

@Service
public class ClienteProvider implements ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	@Cacheable(value="cacheClientesPorCPF", key = "#cpf.hashCode()", unless="true")
	public LocalDate porCPF(String cpf) {
		return clienteRepository.buscaValidadeCliente(clienteRepository.buscaClientePorCPF(cpf));
	}
	
	@Override
	@Cacheable(value="cacheClientesPorApolice", key = "#apolice.hashCode()", unless="true")
	public LocalDate porApolice(Apolice apolice) {
		return clienteRepository.buscaValidadeCliente(clienteRepository.buscaClientePorApolice(apolice));
	}
	
	@Override
	@Cacheable(value="cacheClientesPorPlaca", key = "#placa.hashCode()", unless="true")
	public LocalDate porPlaca(String placa) {
		return clienteRepository.buscaValidadeCliente(clienteRepository.buscaClientePorPlaca(placa));
	}
}