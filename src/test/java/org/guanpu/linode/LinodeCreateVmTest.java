package org.guanpu.linode;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.guanpu.core.LinodeCredential;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LinodeCreateVmTest {

	LinodeCredential credential;
	LinodeCreateVm vm;
	Response credentialResponse;
	Response vmResponse;
	String apiKey;
	JsonNode root;
	
	@Before
	public void setUp() throws Exception {
		credential = new LinodeCredential.Builder()
							.setUsername("username")
							.setPassword("password")
							.build();
		
		credentialResponse = credential.invoke();
		apiKey = new ObjectMapper().readTree(credentialResponse.readEntity(String.class)).at("/DATA/API_KEY").asText();
		
		vm = new LinodeCreateVm.Builder()
					.setApiKey(apiKey)
					.setDataCenterId("dataCenterId")
					.setPlanId("planId")
					.setPaymentTerm("paymentTerm")
					.build();
		
		vmResponse = vm.invoke();
		root = new ObjectMapper().readTree(vmResponse.readEntity(String.class));
	}

	@After
	public void tearDown() throws Exception {
		credentialResponse.close();
		vmResponse.close();
	}

	@Test
	public void testStatus() {
		assertEquals(200, vmResponse.getStatus());
	}
	
	@Test
	public void testLinodeId() {
		assertNotEquals("", root.at("/DATA/LinodeID").asText());
	}

}
