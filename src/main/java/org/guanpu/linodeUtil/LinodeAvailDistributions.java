package org.guanpu.linodeUtil;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Preconditions;

public class LinodeAvailDistributions {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "avail.distributions";
	private final String apiKey;
	private final int distributionId;
	
	private LinodeAvailDistributions(Builder builder) {
		this.apiKey = builder.apiKey;
		this.distributionId = builder.distributionId;
	}
	
	public Response invoke() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey)
					.queryParam("api_action", ACTION)
					.queryParam("DistributionID", distributionId);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String apiKey;
		private int distributionId;
		
		public LinodeAvailDistributions builder() {
			Preconditions.checkNotNull(apiKey, "apiKey can't be null");
			Preconditions.checkNotNull(distributionId, "distributionId can't be null");
			return new LinodeAvailDistributions(this);
		}
		
		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		public Builder setDistributionId(int distributionId) {
			this.distributionId = distributionId;
			return this;
		}
		
	}
}
