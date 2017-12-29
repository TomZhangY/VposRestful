package org.tap.vpos.client;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tap.vpos.beans.VPOS;

public class VposClientTest {

	@Test
	public void testSendXmlMessage() {
		String name ="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<VPOS>\r\n" + 
				"	<Ticket Action=\"Request\" StoreID=\"02134\" Description=\"Request\" TxnNo=\"01201712120001\" TxnTime=\"2016-11-29 20:49:01\" TotalAmount=\"50.00\" DiscountAmount=\"0\" NetTotalAmount=\"50.00\">\r\n" + 
				"	  <LoyaltyInfo LoyaltyNumber=\"991701100000499\" CustomerName=\"FirstName LastName\" OriginalPoint=\"6000\"  EarnedPoint =\"200\"  BalancePoint=\"6200\"  OriginalStamp=\"400\"  EarnedStamp=\"100\"  BalanceStamp=\"500\">\r\n" + 
				"	  </LoyaltyInfo>\r\n" + 
				"	  <Items>\r\n" + 
				"        <Item Code=\"100646803\" Barcode=\"\" Price=\"12.00\" Quanlity=\"2\" Amount=\"24.00\" NetAmount=\"22.00\" Description=\"悠哈CORORO软糖52克(\"/>\r\n" + 
				"		<Item Code=\"100646804\" Barcode=\"\" Price=\"15.00\" Quanlity=\"2\" Amount=\"30.00\" NetAmount=\"28.00\" Description=\"软糖52克(\"/>        \r\n" + 
				"      </Items>\r\n" + 
				"      <Tenders>\r\n" + 
				"        <Tender Code=\"1\" Amount=\"25.00\" Description=\"支付宝在线\"/>\r\n" + 
				"        <Tender Code=\"2\" Amount=\"25.00\" Description=\"微信在线\"/>\r\n" + 
				"      </Tenders>\r\n" + 
				"	</Ticket>\r\n" + 
				"</VPOS>";
		VposRequestConfig config = new VposRequestConfig();
		config.setConnectTimeout(3000);
		config.setReadTimeout(3000);
		config.setUrl("http://localhost:8080/vpos-assembly/rest/VPOS/sendkey");
		VposResponse vposr = VposClient.sendXmlMessage(config,name);
		System.out.println(vposr.getResultCode());
		System.out.println(vposr.getResultCodeMessage());
		System.out.println(vposr.getVpos());
	}

	
	
	
	@Test
	public void testSendVposMessage() {
		VPOS vpos = new VPOS();
		vpos.setPOSTxnNo("5000");;
		VposRequestConfig config = new VposRequestConfig();
		config.setConnectTimeout(3000);
		config.setReadTimeout(3000);
		config.setUrl("http://localhost:8080/vpos-assembly/rest/VPOS/sendkey");
		VposResponse vposr = VposClient.sendVposMessage(config,vpos);
		System.out.println(vposr.getResultCode());
		System.out.println(vposr.getResultCodeMessage());
		System.out.println(vposr.getVpos().getPOSTxnNo());
	}

}
