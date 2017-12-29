package org.tap.vpos.client;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import org.tap.vpos.beans.VPOS;
import org.tap.vpos.util.XmlHelp;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class VposClient {
	
	private static final int TIMEOUT = 600;
	
	public static VposResponse sendXmlMessage(VposRequestConfig config,String xml) {
		VposResponse vposResponse = new VposResponse();
//		VPOS vpos = new VPOS();
		
		Client client = Client.create();
		client.setConnectTimeout(config.getConnectTimeout());
		client.setReadTimeout(config.getReadTimeout());
		
		WebResource webResource = client.resource(config.getUrl());
		ClientResponse clinetResponse = null ;
		
		try {
			 clinetResponse = webResource.accept(
					MediaType.APPLICATION_JSON_TYPE, 
					MediaType.APPLICATION_XML_TYPE).
					// header("X-FOO", "BAR").
					type(MediaType.TEXT_XML_TYPE).
					post(ClientResponse.class, xml);
			vposResponse.setVpos(clinetResponse.getEntity(VPOS.class));
			
		} catch (UniformInterfaceException ue) {
			clinetResponse = ue.getResponse();
		} catch(ClientHandlerException ce) {
			System.out.println(ce.getMessage());
			vposResponse.setResultCode(TIMEOUT);
			vposResponse.setResultCodeMessage(ce.getMessage());
		}
		// 请求超时，连接超时等都不会有clinetResponse对象
		if(clinetResponse != null) {
			vposResponse.setResultCode(clinetResponse.getStatus());
			vposResponse.setResultCodeMessage(clinetResponse.getStatusInfo().toString());
		}
		
		return vposResponse;
	}
	
	public static VposResponse sendJsonMessage(VposRequestConfig config,String json) {
		return null;
	}
	
	public static VposResponse sendVposMessage(VposRequestConfig config,VPOS vpos) {
		String xml = "";
		try {
			xml = XmlHelp.marshaller(vpos);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sendXmlMessage(config,xml);
	}

}
