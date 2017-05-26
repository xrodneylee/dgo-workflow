package org.guanpu.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.guanpu.core.LinodeCredential;
import org.guanpu.linode.LinodeCreateConfig;
import org.guanpu.linode.LinodeCreateDisk;
import org.guanpu.linode.LinodeCreateVm;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Workflow {
	
	private LinodeCredential credential;
	private LinodeCreateVm vm;
	private LinodeCreateConfig config;
	private String apiKey;
	private int linodeId;
	private List<Integer> disks = new ArrayList<Integer>();
	private int configId;
	
	public void openShopFacade() throws Exception {
		genApiKey();
		createVm();
		createSysDisk();
		createSwapDisk();
		createDataDisk();
		createConfig();
	}

	private void genApiKey() throws Exception {
		credential = new LinodeCredential.Builder()
							.setUsername("username")
							.setPassword("password")
							.build();
		Response response = credential.invoke();
		apiKey = new ObjectMapper().readTree(response.readEntity(String.class)).at("/DATA/API_KEY").asText();
		response.close();
	}
	
	private void createVm() throws Exception {
		vm = new LinodeCreateVm.Builder()
					.setApiKey(apiKey)
					.setDataCenterId(11)
					.setPlanId(1)
					.build();
		Response response = vm.invoke();
		linodeId = new ObjectMapper().readTree(response.readEntity(String.class)).at("/DATA/LinodeID").asInt();
		response.close();
	}
	
	private void createSysDisk() throws Exception {
		LinodeCreateDisk disk = new LinodeCreateDisk.Builder()
										.setApiKey(apiKey)
										.setLinodeId(linodeId)
										.setLabel("vm001 sys")
										.setType("ext4")
										.setSize(10000)
										.build();
		Response response = disk.invoke();
		int diskId = new ObjectMapper().readTree(response.readEntity(String.class)).at("/DATA/DiskID").asInt();
		disks.add(diskId);
		response.close();
	}
	
	private void createSwapDisk() throws Exception {
		LinodeCreateDisk disk = new LinodeCreateDisk.Builder()
										.setApiKey(apiKey)
										.setLinodeId(linodeId)
										.setLabel("vm001 swap")
										.setType("swap")
										.setSize(256)
										.build();
		Response response = disk.invoke();
		int diskId = new ObjectMapper().readTree(response.readEntity(String.class)).at("/DATA/DiskID").asInt();
		disks.add(diskId);
		response.close();
	}
	
	private void createDataDisk() throws Exception {
		LinodeCreateDisk disk = new LinodeCreateDisk.Builder()
										.setApiKey(apiKey)
										.setLinodeId(linodeId)
										.setLabel("vm001 data")
										.setType("ext4")
										.setSize(20464)
										.build();
		Response response = disk.invoke();
		int diskId = new ObjectMapper().readTree(response.readEntity(String.class)).at("/DATA/DiskID").asInt();
		disks.add(diskId);
		response.close();
	}
	
	private void createConfig() throws Exception {
		config = new LinodeCreateConfig.Builder()
						.setApiKey(apiKey)
						.setLinodeId(linodeId)
						.setKernelId(215)
						.setLabel("vm001")
						.setDiskList(disks)
						.build();
		Response response = config.invoke();
		configId = new ObjectMapper().readTree(response.readEntity(String.class)).at("/DATA/ConfigID").asInt();
		response.close();
	}
	
}
