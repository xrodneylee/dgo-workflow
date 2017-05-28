package org.guanpu.linodeUtil;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Preconditions;

public class LinodeAvailKernels {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "avail.kernels";
	private final String apiKey;
	private final boolean isXen;
	private final boolean isKvm;
	
	private LinodeAvailKernels(Builder builder) {
		this.apiKey = builder.apiKey;
		this.isXen = builder.isXen;
		this.isKvm = builder.isKvm;
	}
	
	public Response invoke() {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey)
					.queryParam("api_action", ACTION)
					.queryParam("isXen", isXen)
					.queryParam("isKVM", isKvm);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String apiKey;
		private boolean isXen;
		private boolean isKvm;
		
		public LinodeAvailKernels build() {
			Preconditions.checkNotNull(apiKey, "apiKey can't be null");
			return new LinodeAvailKernels(this);
		}
		
		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
		public Builder setXen(boolean isXen) {
			this.isXen = isXen;
			return this;
		}
		
		public Builder setKvm(boolean isKvm) {
			this.isKvm = isKvm;
			return this;
		}
		
	}
}
