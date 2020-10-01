package com.ma.pedidos.error;

public class RestControllerError {

	private String error;
	
	public RestControllerError(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
