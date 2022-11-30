package br.com.laticinioapi.service;

import java.io.ByteArrayInputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import br.com.laticinioapi.dto.EntradaRelatorioDto;
import br.com.laticinioapi.entity.Entrada;
import br.com.laticinioapi.entity.JasperDto;
import br.com.laticinioapi.entity.ResourceDto;
import br.com.laticinioapi.util.Relatorio;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioEntradaService {
	
	public ResourceDto gera(List<Entrada> entrada)  throws Exception {
		var jasperDtoList = new ArrayList<JasperDto>();
		jasperDtoList.add(geraRelatorioCorpo(entrada));
		return relatorio(jasperDtoList);
	}
	
	public ResourceDto relatorio(ArrayList<JasperDto> jasperDtoList) throws Exception {
		var parameters = new HashMap<String, Object>();
		var inputStreamLogo = new ClassPathResource("images/Logo_desenho.jpg").getInputStream();

		var logoBase64 = "";
		if (logoBase64 != null && !logoBase64.isBlank()) {
			inputStreamLogo = new ByteArrayInputStream(
					Base64.getDecoder().decode(logoBase64.split("[,]")[1].getBytes()));
		}

		parameters.put("logo", inputStreamLogo);
		parameters.put("empresa", "Laticínio Mineirinho");
		parameters.put("telefone", "035 3651-1792");
		parameters.put("endereco", "Paraisópolis, Centro, Rua Ana Simões de Almeida, 272");

		String name = "Laticínio";
		String fileName = name + "_" + System.currentTimeMillis() + ".pdf";

		var baos = Relatorio.gerarRelatorio(jasperDtoList, "reports/laticinio_template.jasper", parameters);
		return new ResourceDto(fileName, baos.toByteArray());
	}
	
	private JasperDto geraRelatorioCorpo(List<Entrada> entrada) {
		var parameters = new HashMap<String, Object>();
		var list = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		
		for (Entrada listEntrada : entrada) {
			var entradaRelatorio = new EntradaRelatorioDto();
			entradaRelatorio.setProduto(listEntrada.getProduto());
			entradaRelatorio.setQuantidade(df.format(Double.valueOf(listEntrada.getQuantidade().replace(",", "."))));
			entradaRelatorio.setPreco(df.format(Double.valueOf(listEntrada.getValor())));
			
			list.add(entradaRelatorio);
		}
		var subJasper = new JRBeanCollectionDataSource(list);
		var subJasperCaminho = new ClassPathResource("reports/entrada.jasper").getPath();

		return new JasperDto(subJasper, subJasperCaminho, parameters);
	}

}
