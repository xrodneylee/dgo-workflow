package org.guanpu.linode;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.guanpu.core.LinodeCredential;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LinodeCreateDiskTest {

	LinodeCredential credential;
	LinodeCreateDisk disk;
	Response credentialResponse;
	Response diskResponse;
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
		
		disk = new LinodeCreateDisk.Builder()
			.setApiKey(apiKey)
			.setLinodeId(0)
			.setLabel("label")
			.setType("type")
			.setReadOnly(false)
			.setSize(0)
			.build();
		
		diskResponse = disk.invoke();
		root = new ObjectMapper().readTree(diskResponse.readEntity(String.class));
	}

	@After
	public void tearDown() throws Exception {
		credentialResponse.close();
		diskResponse.close();
	}

	@Test
	public void testStatus() {
		assertEquals(200, diskResponse.getStatus());
	}
	
	@Test
	public void testDiskId() {
		assertNotEquals("", root.at("/DATA/DiskID").asText());
	}

}
