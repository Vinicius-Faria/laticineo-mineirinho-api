package br.com.laticinioapi.entity;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

public class JasperDto {

	private JRDataSource subJasper;

	private String subJasperCaminho;

	private Map<String, Object> subJasperParametros;

	public JasperDto(JRDataSource subJasper, String subJasperCaminho, Map<String, Object> subJasperParametros) {
		this.subJasper = subJasper;
		this.subJasperCaminho = subJasperCaminho;
		this.subJasperParametros = subJasperParametros;
	}

	public JRDataSource getSubJasper() {
		return subJasper;
	}

	public String getSubJasperCaminho() {
		return subJasperCaminho;
	}

	public Map<String, Object> getSubJasperParametros() {
		return subJasperParametros;
	}



}
