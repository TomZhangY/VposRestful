package org.tap.vpos.client;

import org.tap.vpos.beans.VPOS;

public class VposResponse {
	private VPOS vpos;
	private int resultCode;
	private String resultCodeMessage;

	public VPOS getVpos() {
		return vpos;
	}

	public void setVpos(VPOS vpos) {
		this.vpos = vpos;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultCodeMessage() {
		return resultCodeMessage;
	}

	public void setResultCodeMessage(String resultCodeMessage) {
		this.resultCodeMessage = resultCodeMessage;
	}
}
