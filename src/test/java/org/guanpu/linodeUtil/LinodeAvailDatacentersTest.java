package org.guanpu.linodeUtil;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.guanpu.core.LinodeCredential;
import org.guanpu.linode.LinodeCreateVm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LinodeAvailDatacentersTest {

	private static final int LINODE_DATACENTER_AMOUNT = 9;
	LinodeCredential credential;
	LinodeAvailDatacenters datacenter;
	Response credentialResponse;
	Response datacenterResponse;
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
		
		datacenter = new LinodeAvailDatacenters.Builder()
			.setApiKey(apiKey)
			.build();
		
		datacenterResponse = datacenter.invoke();
		root = new ObjectMapper().readTree(datacenterResponse.readEntity(String.class));
	}

	@After
	public void tearDown() throws Exception {
		credentialResponse.close();
		datacenterResponse.close();
	}

	@Test
	public void testStatus() {
		assertEquals(200, datacenterResponse.getStatus());
	}
	
	@Test
	public void testDatacenterCount() {
		assertEquals(LINODE_DATACENTER_AMOUNT, root.path("DATA").size());
	}

}
