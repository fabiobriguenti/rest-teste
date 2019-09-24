package com.example.teste.util;

import java.security.SecureRandom;

import com.example.teste.model.Cliente;
import com.example.teste.repository.ClienteRepository;

public class RandomUtil {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    
    private static SecureRandom random = new SecureRandom();
    
    public static String alpha(int length) {
    	return randomString(length, CHAR_UPPER);
    }
    
    public static String numeric(int length) {
    	return randomString(length, NUMBER);
    }
    
    public static String alphaNumeric(int length) {
    	return randomString(length, NUMBER+CHAR_UPPER);
    }
    
    private static String randomString(int length, String data) {
        if (length < 1) throw new IllegalArgumentException();
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
        	
            int rndCharAt = random.nextInt(data.length());
            char rndChar = data.charAt(rndCharAt);
            
            sb.append(rndChar);
        }
        
        return sb.toString();
    }
    
    public static int range(int min, int max) {
    	return (int) (min + (Math.random() * (max - min)));
    }
    
    public static Cliente cliente() {
    	int i = 0;
    	int random = range(0, ClienteRepository.clientes.size());
    	for (Cliente cliente : ClienteRepository.clientes) {
			if (i == random) return cliente;
			i++;
		}
    	return null;
    }
    
    public static Cliente criaCliente() {
    	Cliente cliente = new Cliente(
				RandomUtil.numeric(11),
				Integer.valueOf(RandomUtil.numeric(2)),
				Integer.valueOf(RandomUtil.numeric(7)),
				RandomUtil.alphaNumeric(7),
				Validades.para(RandomUtil.range(20, 30), 9, 2019));
    	
    	ClienteRepository.clientes.add(cliente);
    	return cliente;
    }
}