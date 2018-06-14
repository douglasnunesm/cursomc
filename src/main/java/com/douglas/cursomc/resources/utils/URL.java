package com.douglas.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static String decodeString(String string)  {
		try {
			return URLDecoder.decode(string, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeToListInteger(String string){

		return Arrays.asList(string.split(",")).stream().map(x -> Integer.valueOf(x)).collect(Collectors.toList());
	}
}
