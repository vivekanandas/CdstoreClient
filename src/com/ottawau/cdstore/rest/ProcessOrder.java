package com.ottawau.cdstore.rest;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
 
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

 
/**
 * @author Crunchify.com
 * 
 */
 
public class ProcessOrder {
	public String submitProcess2(){
		
		return "Order status";
	}
	// Rest Tutorial http://java.dzone.com/articles/restful-web-services-java
	public String submitProcess3(String clientName){
		final String BASE_URI="http://localhost:8080/CDOrderProcessing";
		String PATH_NAME = "/OPService/users/saveNewPo/";
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URI);
		WebResource nameResource = resource.path(PATH_NAME+clientName);
		System.out.println("Client Response \n"+ getClientResponse(nameResource));
		System.out.println("Response \n" + getResponse(nameResource) + "\n\n");
		
		return getResponse(nameResource);
	}
	private static String getClientResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
	}
	
	private static String getResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(String.class);
	}
		
	/*
	public String submitProcess(){
		 try {
             URL url = new URL("http://localhost:8080/CdStoreProcessOrder/OPService/testConnection/saveNewPo");
             URLConnection connection = url.openConnection();
             connection.setDoOutput(true);
             connection.setRequestProperty("Content-Type", "application/json");
             connection.setConnectTimeout(5000);
             connection.setReadTimeout(5000);
             OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
             out.write("Call from rest Test");
             out.close();

             BufferedReader in = new BufferedReader(new InputStreamReader(
                     connection.getInputStream()));

             while (in.readLine() != null) {
             }
             System.out.println("\nREST Service Invoked Successfully..");
             in.close();
         } catch (Exception e) {
             System.out.println("\nError while calling REST Service");
             System.out.println(e);
         }
		 return "Order status";
	}*/
    public  void test() {
        String string = "";
        try {
        	
        	/*
            // Step1: Let's 1st read file from fileSystem
            InputStream crunchifyInputStream = new FileInputStream(
                    "/Users/<username>/Documents/crunchify-git/JSONFile.txt");
            InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
            BufferedReader br = new BufferedReader(crunchifyReader);
            String line;
            while ((line = br.readLine()) != null) {
                string += line + "\n";
            }
 
            JSONObject jsonObject = new JSONObject(string);
            System.out.println(jsonObject);
            
            // Step2: Now pass JSON File Data to REST Service
            */
            try {
                URL url = new URL("http://localhost:8080/CdStoreProcessOrder/OPService/testConnection/saveNewPo");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write("Call from rest Test");
                out.close();
 
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
 
                while (in.readLine() != null) {
                }
                System.out.println("\nREST Service Invoked Successfully..");
                in.close();
            } catch (Exception e) {
                System.out.println("\nError while calling REST Service");
                System.out.println(e);
            }
 
          //  br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
