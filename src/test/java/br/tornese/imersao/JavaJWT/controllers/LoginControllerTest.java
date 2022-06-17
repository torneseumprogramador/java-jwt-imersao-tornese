package br.tornese.imersao.JavaJWT.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import br.tornese.imersao.JavaJWT.infraestrutura.entidades.Cliente;
import br.tornese.imersao.JavaJWT.models.AdmToken;

@SpringBootTest
class LoginControllerTest {

	private AdmToken getTokenLogin() throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("http://localhost:8080/login");
		httppost.setHeader("Accept", "application/json");
		httppost.setHeader("Content-type", "application/json");

		String json = "{\r\n" + 
		"  \"email\": \"danilo@torneseumprogramador.com.br\",\r\n" + 
		"  \"senha\": \"123456\"\r\n" + 
		"}";
		StringEntity stringEntity = new StringEntity(json);
		httppost.setEntity(stringEntity);

		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		};
		
		String responseBody = httpclient.execute(httppost, responseHandler);
		AdmToken admToken = new Gson().fromJson(responseBody, AdmToken.class);
		return admToken;
	}

	@Test
	void login() throws ClientProtocolException, IOException {
		AdmToken admToken = getTokenLogin();
		assertNotNull(admToken.getToken());
	}

	@Test
	void usuarios() throws ClientProtocolException, IOException {
		AdmToken admToken = getTokenLogin();

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://localhost:8080/clientes");
		httpget.setHeader("Accept", "application/json");
		httpget.setHeader("Content-type", "application/json");
		httpget.setHeader("Authorization", admToken.getToken());

		ResponseHandler<String> responseHandler = response -> {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status < 300) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			} else {
				return null;
			}
		};
		
		String responseBody = httpclient.execute(httpget, responseHandler);
		List<Cliente> clientes = new Gson().fromJson(responseBody, new TypeToken<List<Cliente>>(){}.getType());
		
		assertTrue(clientes.size() > 0);
	}

}
