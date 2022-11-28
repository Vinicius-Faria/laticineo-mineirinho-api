package br.com.laticinioapi.util;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import br.com.laticinioapi.entity.JasperDto;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Relatorio {
	public static ByteArrayOutputStream gerarRelatorio(List<JasperDto> jasperDtoList, String filename, Map<String, Object> parameters) throws Exception {

		var resource = new ClassPathResource(filename);

		var jasperPrint = JasperFillManager.fillReport(resource.getInputStream(), parameters, (jasperDtoList.isEmpty()) ? new JREmptyDataSource() : new JRBeanCollectionDataSource(jasperDtoList));

		var baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		return baos;
	}
}