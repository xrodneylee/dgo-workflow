package org.guanpu.linode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class LinodeCreateVm {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "linode.create";
	private final String dataCenterId;
	private final String planId;
	private final String paymentTerm;
	private final String apiKey;
	
	private LinodeCreateVm(Builder builder) {
		this.dataCenterId = builder.dataCenterId;
		this.planId = builder.planId;
		this.paymentTerm = builder.paymentTerm;
		this.apiKey = builder.apiKey;
	}
	
	public Response invoke(){
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey)
					.queryParam("api_action", ACTION)
					.queryParam("DatacenterID", dataCenterId)
					.queryParam("PlanID", planId)
					.queryParam("PaymentTerm", paymentTerm);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String dataCenterId;
		private String planId;
		private String paymentTerm;
		private String apiKey;
		
		public LinodeCreateVm build() {
			return new LinodeCreateVm(this);
		}
		
		public Builder setDataCenterId(String dataCenterId) {
			this.dataCenterId = dataCenterId;
			return this;
		}
		
		public Builder setPlanId(String planId) {
			this.planId = planId;
			return this;
		}
		
		public Builder setPaymentTerm(String paymentTerm) {
			this.paymentTerm = paymentTerm;
			return this;
		}

		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
	}
}
