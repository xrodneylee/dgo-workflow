package org.guanpu.linode;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Preconditions;

public class LinodeCreateVm {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "linode.create";
	private final int dataCenterId;
	private final int planId;
	private final int paymentTerm;
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
		private int dataCenterId;
		private int planId;
		private int paymentTerm;
		private String apiKey;
		
		public LinodeCreateVm build() {
			Preconditions.checkNotNull(dataCenterId, "dataCenterId can't be null");
			Preconditions.checkNotNull(planId, "planId can't be null");
			Preconditions.checkNotNull(apiKey, "apiKey can't be null");
			return new LinodeCreateVm(this);
		}
		
		public Builder setDataCenterId(int dataCenterId) {
			this.dataCenterId = dataCenterId;
			return this;
		}
		
		public Builder setPlanId(int planId) {
			this.planId = planId;
			return this;
		}
		
		public Builder setPaymentTerm(int paymentTerm) {
			this.paymentTerm = paymentTerm;
			return this;
		}

		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
	}
}
