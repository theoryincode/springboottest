package com.example.springdemo;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.PathVariable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


@SpringBootApplication
public class SpringdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdemoApplication.class, args);
	}

}

@RestController
class VersionInfoController {
 
    private static final String template = "Hello, %s! this is version 0.0.1";
     
    @RequestMapping(value = "/springdemo")
        public String helloWorld() {
                String response = "Hello user ! " + new Date();
              //  LOG.log(Level.INFO, "/elkdemo - > " + response);

                return response;
        }

    @RequestMapping(value = "/springdemo2")
        public String helloWorld2() {
                String response = "Hello user again ! " + new Date();
              //  LOG.log(Level.INFO, "/elkdemo - > " + response);

                return response;
        }

    @RequestMapping(value = "/add")
        public long add(@RequestParam long a, @RequestParam long b) {
                String response = "Hello user again ! " + new Date();
              //  LOG.log(Level.INFO, "/elkdemo - > " + response);

		long s=a+b;
		if(s<5)return a+b;
		else return s;
        }

}

@RestController
class NewController {  

	@RequestMapping(value = "/calladd")
        public String helloWorld() throws  Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
        	try {
            		HttpGet httpget = new HttpGet("http://localhost:8080/add?a=3&b=5");

            		System.out.println("Executing request " + httpget.getRequestLine());

            		// Create a custom response handler
            		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

			@Override
			public String handleResponse(
				final HttpResponse response) throws ClientProtocolException, IOException {
			    int status = response.getStatusLine().getStatusCode();
			    if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			    } else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			    }
			}

		    };
		    String responseBody = httpclient.execute(httpget, responseHandler);
		    System.out.println("----------------------------------------");
		    System.out.println(responseBody);
		} finally {
		    httpclient.close();
		}
		return "Hello";
	}
}
