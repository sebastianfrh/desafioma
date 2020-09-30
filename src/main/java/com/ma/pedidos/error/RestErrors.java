package com.ma.pedidos.error;

public class RestErrors {

	private String error;
	
	public RestErrors(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
