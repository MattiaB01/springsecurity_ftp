package com.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Uuid {
	
	public static String getUuid() throws IOException {
	
	String command2 = "wmic csproduct get UUID";
	
	 
    Process process = Runtime.getRuntime().exec(command2);
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

    String stampa = "";
    String UUID="";
    int i=0;
    while((stampa=reader.readLine())!=null)
     {
    	if (i==2) UUID=stampa;
    	i++;
    	
     }
    	System.out.println(UUID);
    	
    	
    return (UUID);
}
	
}