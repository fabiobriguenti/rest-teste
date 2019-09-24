package com.example.teste.controller;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.teste.model.Apolice;
import com.example.teste.model.Resultado;
import com.example.teste.service.ClienteService;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<String> isCliente(
    		@RequestParam(value = "cpf", required = false) final String cpf,
    		@RequestParam(value = "sucursal", required = false) final Integer sucursal,
    		@RequestParam(value = "apolice", required = false) final Integer apolice,
    		@RequestParam(value = "placa", required = false) final String placa) {
		LocalDate validadeCliente = null;
		if (cpf != null) {
			validadeCliente = clienteService.porCPF(cpf);
		} else if (sucursal != null && apolice != null) {
			validadeCliente = clienteService.porApolice(new Apolice(sucursal, apolice));
		} else if (placa != null) {
			validadeCliente =  clienteService.porPlaca(placa);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
					.body(null);
		}
		
		if (!LocalDate.now().isAfter(validadeCliente)) {
			return ResponseEntity.status(HttpStatus.OK)
					.cacheControl(CacheControl
							.maxAge(60, TimeUnit.SECONDS)
							.sMaxAge(60, TimeUnit.SECONDS))
					.body(JSONObject.wrap(Resultado.CLIENTE).toString());
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.cacheControl(CacheControl
						.maxAge(60, TimeUnit.SECONDS)
						.sMaxAge(60, TimeUnit.SECONDS))
				.body(JSONObject.wrap(Resultado.NAO_CLIENTE).toString());
    }
}
