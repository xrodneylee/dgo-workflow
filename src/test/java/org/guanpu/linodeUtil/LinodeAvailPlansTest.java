package org.guanpu.linodeUtil;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.guanpu.core.LinodeCredential;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LinodeAvailPlansTest {

	LinodeCredential credential;
	LinodeAvailPlans plan;
	Response credentialResponse;
	Response planResponse;
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
		
		plan = new LinodeAvailPlans.Builder()
						.setApiKey(apiKey)
						.build();
	
		planResponse = plan.invoke();
		root = new ObjectMapper().readTree(planResponse.readEntity(String.class));
	}

	@After
	public void tearDown() throws Exception {
		credentialResponse.close();
		planResponse.close();
	}

	@Test
	public void testStatus() {
		assertEquals(200, planResponse.getStatus());
	}
	
	@Test
	public void testPlanCount() {
		assertNotEquals(0, root.path("DATA").size());
	}

}
