package com.example.teste.repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.stereotype.Repository;

import com.example.teste.RestTesteApplication;
import com.example.teste.model.Apolice;
import com.example.teste.model.Cliente;

@Repository
public class ClienteRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestTesteApplication.class);
	
	public volatile static Set<Cliente> clientes = new HashSet<>();
	
	@Autowired
	JCacheCacheManager cacheManager;
	
	public Cliente buscaClientePorCPF(final String cpf) {
		for (Cliente cliente : clientes) {
			if (cliente.getCpf().equals(cpf)) {
				return cliente;
			}
		}
		return null;
	}
	
	public Cliente buscaClientePorApolice(final Apolice apolice) {
		for (Cliente cliente : clientes) {
			if (cliente.getApolice().equals(apolice)) {
				return cliente;
			}
		}
		return null;
	}
	
	public Cliente buscaClientePorPlaca(String placa) {
		for (Cliente cliente : clientes) {
			if (cliente.getPlaca().equals(placa)) {
				return cliente;
			}
		}
		return null;
	}
	
	@Caching(put = {
			@CachePut(value="cacheClientesPorCPF", key = "#cliente.cpf.hashCode()", condition="#cliente.cpf != null", unless="#result == null"),
	        @CachePut(value="cacheClientesPorApolice", key = "#cliente.apolice.hashCode()", condition="#cliente.apolice != null", unless="#result == null"),
	        @CachePut(value="cacheClientesPorPlaca", key = "#cliente.placa.hashCode()", condition="#cliente.placa != null", unless="#result == null")
			})
	public LocalDate buscaValidadeCliente(final Cliente cliente) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		LOGGER.info(cliente.toString());
		return cliente.getVigenciaFinal();
	}
}