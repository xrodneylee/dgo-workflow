package org.guanpu.linode;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Preconditions;

public class LinodeCreateConfig {
	private final String ROOT_URL = "https://api.linode.com/";
	private final String ACTION = "linode.config.create";
	private final String apiKey;
	private final int linodeId;
	private final int kernelId;
	private final String label;
	private final String comments;
	private final int ramLimit;
	private final List<String> diskList;
	private final String virtMode;
	private final String runLevel;
	private final int rootDeviceNum;
	private final String rootDeviceCustom;
	private final boolean rootDeviceRo;
	private final boolean helperDisableUpdateDd;
	private final boolean helperDistro;
	private final boolean helperXen;
	private final boolean helperDepmod;
	private final boolean helperNetwork;
	private final boolean devtmpfsAutomount;
	
	private LinodeCreateConfig(Builder builder) {
		this.apiKey = builder.apiKey;
		this.linodeId = builder.linodeId;
		this.kernelId = builder.kernelId;
		this.label = builder.label;
		this.comments = builder.comments;
		this.ramLimit = builder.ramLimit;
		this.diskList = builder.diskList;
		this.virtMode = builder.virtMode;
		this.runLevel = builder.runLevel;
		this.rootDeviceNum = builder.rootDeviceNum;
		this.rootDeviceCustom = builder.rootDeviceCustom;
		this.rootDeviceRo = builder.rootDeviceRo;
		this.helperDisableUpdateDd = builder.helperDisableUpdateDd;
		this.helperDistro = builder.helperDistro;
		this.helperXen = builder.helperXen;
		this.helperDepmod = builder.helperDepmod;
		this.helperNetwork = builder.helperNetwork;
		this.devtmpfsAutomount = builder.devtmpfsAutomount;
	}
	
	public Response invoke(){
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(ROOT_URL)
					.queryParam("api_key", apiKey).queryParam("api_action", ACTION)
					.queryParam("LinodeID", linodeId).queryParam("KernelID ", kernelId)
					.queryParam("Label", label).queryParam("Comments", comments)
					.queryParam("RAMLimit", ramLimit).queryParam("DiskList", diskList)
					.queryParam("virt_mode", virtMode).queryParam("RunLevel", runLevel)
					.queryParam("RootDeviceNum", rootDeviceNum).queryParam("RootDeviceCustom", rootDeviceCustom)
					.queryParam("RootDeviceRO", rootDeviceRo).queryParam("helper_disableUpdateDB", helperDisableUpdateDd)
					.queryParam("helper_distro", helperDistro).queryParam("helper_xen", helperXen)
					.queryParam("helper_depmod", helperDepmod).queryParam("helper_network", helperNetwork)
					.queryParam("devtmpfs_automount", devtmpfsAutomount);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		return response;
	}
	
	public static final class Builder {
		private String apiKey;
		private int linodeId;
		private int kernelId;
		private String label;
		private String comments;
		private int ramLimit;
		private List<String> diskList;
		private String virtMode;
		private String runLevel;
		private int rootDeviceNum;
		private String rootDeviceCustom;
		private boolean rootDeviceRo;
		private boolean helperDisableUpdateDd;
		private boolean helperDistro;
		private boolean helperXen;
		private boolean helperDepmod;
		private boolean helperNetwork;
		private boolean devtmpfsAutomount;
		
		public LinodeCreateConfig build(){
			Preconditions.checkNotNull(linodeId, "linodeId can't be null");
			Preconditions.checkNotNull(kernelId, "kernelId can't be null");
			Preconditions.checkNotNull(label, "label can't be null");
			Preconditions.checkNotNull(diskList, "diskList can't be null");
			Preconditions.checkNotNull(apiKey, "apiKey can't be null");
			return new LinodeCreateConfig(this);
		}
		
		public Builder setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
		
		public Builder setLinodeId(int linodeId) {
			this.linodeId = linodeId;
			return this;
		}
		
		public Builder setKernelId(int kernelId) {
			this.kernelId = kernelId;
			return this;
		}
		
		public Builder setLabel(String label) {
			this.label = label;
			return this;
		}
		
		public Builder setComments(String comments) {
			this.comments = comments;
			return this;
		}
		
		public Builder setRamLimit(int ramLimit) {
			this.ramLimit = ramLimit;
			return this;
		}
		
		public Builder setDiskList(List<String> diskList) {
			this.diskList = diskList;
			return this;
		}
		
		public Builder setVirtMode(String virtMode) {
			this.virtMode = virtMode;
			return this;
		}
		
		public Builder setRunLevel(String runLevel) {
			this.runLevel = runLevel;
			return this;
		}
		
		public Builder setRootDeviceNum(int rootDeviceNum) {
			this.rootDeviceNum = rootDeviceNum;
			return this;
		}
		
		public Builder setRootDeviceCustom(String rootDeviceCustom) {
			this.rootDeviceCustom = rootDeviceCustom;
			return this;
		}
		
		public Builder setRootDeviceRo(boolean rootDeviceRo) {
			this.rootDeviceRo = rootDeviceRo;
			return this;
		}
		
		public Builder setHelperDisableUpdateDd(boolean helperDisableUpdateDd) {
			this.helperDisableUpdateDd = helperDisableUpdateDd;
			return this;
		}
		
		public Builder setHelperDistro(boolean helperDistro) {
			this.helperDistro = helperDistro;
			return this;
		}
		
		public Builder setHelperXen(boolean helperXen) {
			this.helperXen = helperXen;
			return this;
		}
		
		public Builder setHelperDepmod(boolean helperDepmod) {
			this.helperDepmod = helperDepmod;
			return this;
		}
		
		public Builder setHelperNetwork(boolean helperNetwork) {
			this.helperNetwork = helperNetwork;
			return this;
		}
		
		public Builder setDevtmpfsAutomount(boolean devtmpfsAutomount) {
			this.devtmpfsAutomount = devtmpfsAutomount;
			return this;
		}
		
	}
	
}
