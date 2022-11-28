package br.com.laticinioapi.entity;

public class ResourceDto {
	
	String fileName;

	byte[] bytes;

	public ResourceDto(String fileName, byte[] bytes) {
		super();
		this.fileName = fileName;
		this.bytes = bytes;
	}

	public String getFileName() {
		return fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

}
