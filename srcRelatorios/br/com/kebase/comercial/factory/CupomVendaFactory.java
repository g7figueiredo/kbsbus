package br.com.kebase.comercial.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.kebase.comercial.venda.model.CupomVenda;
import br.com.kebase.comercial.venda.model.ItemVenda;

public class CupomVendaFactory {
	
	public static void main(String[] args) {
	            double total = 0;
	            double desconto = 10;
	            
	            /* List to hold Items */
	            List<ItemVenda> listItems = new ArrayList<ItemVenda>();

	            /* Create Items */
	            ItemVenda item1 = new ItemVenda();
	            item1.setDescricaoProduto("Shampoo Purificante 1000ml");
	            item1.setValorUnitario(75.00);
	            item1.setValorQuantidade(5);
	            item1.setSubTotal(item1.getValorUnitario()*item1.getValorQuantidade());
	            total += item1.getSubTotal();
	            
	            ItemVenda item2 = new ItemVenda();
	            item2.setDescricaoProduto("Máscara hidratante 1000g");
	            item2.setValorUnitario(95.00);
	            item2.setValorQuantidade(2);
	            item2.setSubTotal(item2.getValorUnitario()*item2.getValorQuantidade());
	            total += item2.getSubTotal();
	            
	            ItemVenda item3 = new ItemVenda();
	            item3.setDescricaoProduto("Defrizante 1000ml");
	            item3.setValorUnitario(90.00);
	            item3.setValorQuantidade(3);
	            item3.setSubTotal(item3.getValorUnitario()*item3.getValorQuantidade());
	            total += item3.getSubTotal();

	            /* Add Items to List */
	            listItems.add(item1);
	            listItems.add(item2);
	            listItems.add(item3);
	            
	            CupomVenda venda = new CupomVenda();
	            venda.setIdVenda(575187);
	            venda.setCliente("Carlos de Baitolas Sauro");
	            venda.setDataVenda(new Date());
	            venda.setVendedor("Giovani Monteiro");
	            venda.setValorTotal(total);
	            venda.setValorDesconto(desconto);
	            venda.setValorFinal(total-desconto);
	            venda.setDocumento("22.458.587/0001-82");
	            venda.setSalao("Studio Prime");
	            venda.setEndereco("Rua da Prata, 558, Barueri-SP");
	            venda.setRegional("Barueri-SP");
	            
	            String observacoes = "Condições de Pagamento: \n"
	            		+ "1º R$ 165,00 - 01/10/2018 (Boleto)\n"
	            		+ "2º R$ 165,00 - 01/11/2018 (Boleto)\n"
	            		+ "3º R$ 165,00 - 01/12/2018 (Boleto)\n"
	            		+ "4º R$ 165,00 - 01/01/2019 (Boleto)\n"
	            		+ "5º R$ 165,00 - 01/02/2019 (Boleto)\n"
	            		+ "\n O atraso de pagamento das parcelas implicam acréscimo de juros diário de 0,5% + multa de 7%.";
	            venda.setObservacoes(observacoes);
	            
	            new RelatorioFactory().gerarRelatorioVenda(venda, listItems);
	}
}
