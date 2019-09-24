package com.example.teste;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.teste.controller.ClienteController;
import com.example.teste.model.Cliente;
import com.example.teste.util.RandomUtil;

@SpringBootApplication
public class RestTesteApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestTesteApplication.class);
	
	@Autowired
	ClienteController clienteController;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(RestTesteApplication.class, args);
		
		context.getBean(RestTesteApplication.class).initialize();
	}
	
	public void initialize() {		
		for (int i = 0; i < 10; i++) {
			testa();
		}
	}
	
	public synchronized void testa() {
		final Cliente cliente = RandomUtil.criaCliente();
		final int random = RandomUtil.range(0, 3);
		
		String retorno = "";
		if (random == 0) {
			LOGGER.info("Busca cpf: " + cliente.getCpf());
			retorno = clienteController.isCliente(cliente.getCpf(), null, null, null).getBody() + cliente.getVigenciaFinal();
		} else if (random == 1) {
			LOGGER.info("Busca apolice: " + cliente.getApolice().getSucursal() + "-" + cliente.getApolice().getApolice());
			retorno = clienteController.isCliente(null, cliente.getApolice().getSucursal(), cliente.getApolice().getApolice(), null).getBody() + cliente.getVigenciaFinal();
		} else if (random == 2) {
			LOGGER.info("Busca placa: " + cliente.getPlaca());
			retorno = clienteController.isCliente(null, null, null, cliente.getPlaca()).getBody() + cliente.getVigenciaFinal();
		}
		
		LOGGER.info("Retorno: " + retorno);
	}
}