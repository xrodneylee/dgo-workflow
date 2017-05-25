package org.guanpu.linode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LinodeCreateDisk {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "linode.disk.create";
	private final String linodeId;
	private final String label;
	private final String type;
	private final boolean isReadOnly;
	private final int size;
	private final String apiKey;
	
	private LinodeCreateDisk(Builder builder) {
		this.apiKey = builder.apiKey;
		this.linodeId = builder.linodeId;
		this.label = builder.label;
		this.type = builder.type;
		this.isReadOnly = builder.isReadOnly;
		this.size = builder.size;
	}
	
	public Response invoke(){
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey)
					.queryParam("api_action", ACTION)
					.queryParam("LinodeID", linodeId)
					.queryParam("Label", label)
					.queryParam("Type", type)
					.queryParam("isReadOnly", isReadOnly)
					.queryParam("Size", size);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String linodeId;
		private String label;
		private String type;
		private boolean isReadOnly;
		private int size;
		private String apiKey;
		
		public LinodeCreateDisk build(){
			return new LinodeCreateDisk(this);
		}
		
		public Builder setLinodeId(String linodeId) {
			this.linodeId = linodeId;
			return this;
		}
		
		public Builder setLabel(String label) {
			this.label = label;
			return this;
		}
		
		public Builder setType(String type) {
			this.type = type;
			return this;
		}
		
		public Builder setReadOnly(boolean isReadOnly) {
			this.isReadOnly = isReadOnly;
			return this;
		}
		
		public Builder setSize(int size) {
			this.size = size;
			return this;
		}
		
		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
	}
}
