package br.com.laticinioapi.dto;

import java.util.ArrayList;

public class GraficoEntradaSaidaDto {
	
	private ArrayList<String> data;
	
	private ArrayList<Double> quantidade;

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	public ArrayList<Double> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(ArrayList<Double> quantidade) {
		this.quantidade = quantidade;
	}

}
