package br.com.kebase.relatorios.factory;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.kebase.relatorios.comercial.venda.model.CupomVenda;
import br.com.kebase.relatorios.comercial.venda.model.ItemVenda;

@WebServlet(name="RelatorioServlet", urlPatterns= {"/relatorio"})
public class RelatorioServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String filtro = req.getParameter("filtro");
		processRequets(req, resp, filtro);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		processRequets(req, resp, "");
	}
	
	protected void processRequets(HttpServletRequest req, HttpServletResponse resp, String filtro) throws ServletException, IOException {
		if(!filtro.equals("")) {
			RelatorioFactory relatorioFactory = new RelatorioFactory(resp);
			if(filtro.equals("cupom")) {
				HttpSession session = req.getSession(true);
				CupomVenda venda = (CupomVenda) session.getAttribute("cupomVenda");
				@SuppressWarnings("unchecked")
				List<ItemVenda> listItems = (List<ItemVenda>) session.getAttribute("listItems");
				
				relatorioFactory.gerarRelatorioVenda(venda, listItems);
			}
		}
	}

}
