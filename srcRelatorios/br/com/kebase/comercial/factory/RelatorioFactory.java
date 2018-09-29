package br.com.kebase.comercial.factory;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import br.com.kebase.comercial.venda.model.CupomVenda;
import br.com.kebase.comercial.venda.model.ItemVenda;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class RelatorioFactory {
	
	private FacesContext context;
	private InputStream stream;
	private ByteArrayOutputStream outputStream;
	private HttpServletResponse response;
	
	public RelatorioFactory() {
		this.context = FacesContext.getCurrentInstance();
		this.response = (HttpServletResponse) context.getExternalContext().getResponse();
	}

	public void gerarRelatorioVenda(CupomVenda venda, List<ItemVenda> listItems) {
		this.stream = this.getClass().getResourceAsStream("/br/com/kebase/comercial/venda/jasper/Invoice.jasper");
		this.outputStream = new ByteArrayOutputStream();
		
		try {
	        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listItems);
	
	        Map<String, Object> parameters = new HashMap<String, Object>();
	        parameters.put("venda", venda);
	        parameters.put("ItemDataSource", itemsJRBean);
	        
	        JasperReport report = (JasperReport) JRLoader.loadObject(stream);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	        
	        this.response.reset();
	        this.response.setContentType("application/pdf");
	        this.response.setContentLength(outputStream.size());
	        this.response.setHeader("Content-disposition", "inline; filename=relatorio.pdf");
	        this.response.getOutputStream().write(outputStream.toByteArray());
	        this.response.getOutputStream().flush();
	        this.response.getOutputStream().close();
	        
	        this.context.responseComplete();

	        System.out.println("File Generated");
	        
		} catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}

	}

}
