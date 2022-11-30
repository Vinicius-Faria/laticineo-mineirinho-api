package br.com.laticinioapi.exception;

@SuppressWarnings("serial")
public class InvalidOpertaionException extends RuntimeException {
	
	public InvalidOpertaionException(String errorMessage) {
		super(errorMessage);
	}

}
