package org.guanpu.linodeUtil;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Preconditions;

public class LinodeAvailDatacenters {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "avail.datacenters";
	private final String apiKey;
	
	private LinodeAvailDatacenters(Builder builder) {
		this.apiKey = builder.apiKey;
	}
	
	public Response invoke() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey)
					.queryParam("api_action", ACTION);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String apiKey;

		public LinodeAvailDatacenters build() {
			Preconditions.checkNotNull(apiKey, "apiKey can't be null");
			return new LinodeAvailDatacenters(this);
		}
		
		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
	}
}
