package com.hatherly.pocketmoneytracker.model;

public class Image {
	private String data;
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	private String mimeType;
	
	public Image(String data, String mimeType) {
		super();
		if (data.startsWith("data")) {
			int idx = data.indexOf(',');
			this.data = data.substring(idx+1);
		} else {
			this.data = data;
		}
		this.mimeType = mimeType;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
