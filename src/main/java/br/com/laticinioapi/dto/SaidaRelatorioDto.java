package br.com.laticinioapi.dto;

public class SaidaRelatorioDto {
	
	private String produto;
	
	private String quantidade;
	
	private String preco;
	
	private String valor;
	
	private String somaTotal;

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getSomaTotal() {
		return somaTotal;
	}

	public void setSomaTotal(String somaTotal) {
		this.somaTotal = somaTotal;
	}

}
