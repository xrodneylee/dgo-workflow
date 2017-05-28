package org.guanpu.linodeUtil;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Preconditions;

public class LinodeAvailPlans {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "avail.linodeplans";
	private final String apiKey;
	private final int planId;
	
	private LinodeAvailPlans(Builder builder) {
		this.apiKey = builder.apiKey;
		this.planId = builder.planId;
	}
	
	public Response invoke() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey)
					.queryParam("api_action", ACTION)
					.queryParam("PlanID", planId);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String apiKey;
		private int planId;

		public LinodeAvailPlans build() {
			Preconditions.checkNotNull(apiKey, "apiKey can't be null");
			return new LinodeAvailPlans(this);
		}
		
		public Builder setPlanId(int planId) {
			this.planId = planId;
			return this;
		}
		
		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
	}
}
