package br.com.leonardoangelo.app.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
		
	public static String decodePrarm(String s) throws UnsupportedEncodingException {
		try {
		return URLDecoder.decode(s,"UTF-8");
	}catch(UnsupportedOperationException e) {
		return "";
	}
	}
	
	 public static List<Integer>decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer>list = new ArrayList<>();
		for(int i= 0; i<vet.length;i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		//cOM JAVA 8
		//return Arrays.asList(s.slipt(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
