package br.com.kebase.dbUtil;

import br.com.kebase.comercial.cliente.ClienteDAO;
import br.com.kebase.comercial.cliente.ClienteDAOHibernate;
import br.com.kebase.comercial.cliente.salao.SalaoDAO;
import br.com.kebase.comercial.cliente.salao.SalaoDAOHibernate;
import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalaoDAO;
import br.com.kebase.comercial.cliente.salao.enderecoSalao.EnderecoSalaoDAOHibernate;
import br.com.kebase.comercial.setor.SetorDAO;
import br.com.kebase.comercial.setor.SetorDAOHibernate;
import br.com.kebase.comercial.setor.itemSetor.ItemSetorDAO;
import br.com.kebase.comercial.setor.itemSetor.ItemSetorDAOHibernate;
import br.com.kebase.comercial.venda.VendaDAO;
import br.com.kebase.comercial.venda.VendaDAOHibernate;
import br.com.kebase.comercial.venda.itemVenda.ItemVendaDAO;
import br.com.kebase.comercial.venda.itemVenda.ItemVendaDAOHibernate;
import br.com.kebase.comercial.vendedor.VendedorDAO;
import br.com.kebase.comercial.vendedor.VendedorDAOHibernate;
import br.com.kebase.comercial.vendedor.enderecoVendedor.EnderecoVendedorDAO;
import br.com.kebase.comercial.vendedor.enderecoVendedor.EnderecoVendedorDAOHibernate;
import br.com.kebase.endereco.EnderecoDAO;
import br.com.kebase.endereco.EnderecoDAOHibernate;
import br.com.kebase.endereco.bairro.BairroDAO;
import br.com.kebase.endereco.bairro.BairroDAOHibernate;
import br.com.kebase.endereco.cidade.CidadeDAO;
import br.com.kebase.endereco.cidade.CidadeDAOHibernate;
import br.com.kebase.estoque.EstoqueDAO;
import br.com.kebase.estoque.EstoqueDAOHibernate;
import br.com.kebase.estoque.pedidoCompra.PedidoCompraDAO;
import br.com.kebase.estoque.pedidoCompra.PedidoCompraDAOHibernate;
import br.com.kebase.estoque.pedidoCompra.itemCompra.ItemCompraDAO;
import br.com.kebase.estoque.pedidoCompra.itemCompra.ItemCompraDAOHibernate;
import br.com.kebase.estoque.pedidoCompra.statusPedido.StatusPedidoDAO;
import br.com.kebase.estoque.pedidoCompra.statusPedido.StatusPedidoDAOHibernate;
import br.com.kebase.estoque.produto.ProdutoDAO;
import br.com.kebase.estoque.produto.ProdutoDAOHibernate;
import br.com.kebase.estoque.produto.linhaProduto.LinhaProdutoDAO;
import br.com.kebase.estoque.produto.linhaProduto.LinhaProdutoDAOHibernate;
import br.com.kebase.financeiro.categoria.CategoriaDAO;
import br.com.kebase.financeiro.categoria.CategoriaDAOHibernate;
import br.com.kebase.financeiro.categoria.subCategoria.SubCategoriaDAO;
import br.com.kebase.financeiro.categoria.subCategoria.SubCategoriaDAOHibernate;
import br.com.kebase.financeiro.centroCusto.CentroCustoDAO;
import br.com.kebase.financeiro.centroCusto.CentroCustoDAOHibernate;
import br.com.kebase.financeiro.conta.ContaDAO;
import br.com.kebase.financeiro.conta.ContaDAOHibernate;
import br.com.kebase.financeiro.conta.extratoConta.ExtratoContaDAO;
import br.com.kebase.financeiro.conta.extratoConta.ExtratoContaDAOHibernate;
import br.com.kebase.financeiro.conta.tipoConta.TipoContaDAO;
import br.com.kebase.financeiro.conta.tipoConta.TipoContaDAOHibernate;
import br.com.kebase.financeiro.despesa.DespesaDAO;
import br.com.kebase.financeiro.despesa.DespesaDAOHibernate;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioDAO;
import br.com.kebase.financeiro.despesa.beneficiario.BeneficiarioDAOHibernate;
import br.com.kebase.financeiro.despesa.reciboDespesa.ReciboDespesaDAO;
import br.com.kebase.financeiro.despesa.reciboDespesa.ReciboDespesaDAOHibernate;
import br.com.kebase.financeiro.receita.ReceitaDAO;
import br.com.kebase.financeiro.receita.ReceitaDAOHibernate;
import br.com.kebase.financeiro.receita.faturamento.FaturamentoDAO;
import br.com.kebase.financeiro.receita.faturamento.FaturamentoDAOHibernate;
import br.com.kebase.financeiro.receita.reciboReceita.ReciboReceitaDAO;
import br.com.kebase.financeiro.receita.reciboReceita.ReciboReceitaDAOHibernate;
import br.com.kebase.security.usuario.UsuarioDAO;
import br.com.kebase.security.usuario.UsuarioDAOHibernate;

public class DAOFactory {

	public static ClienteDAO criarClienteDAO() {
		ClienteDAOHibernate clienteDAOHibernate = new ClienteDAOHibernate();
		clienteDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return clienteDAOHibernate;
	}

	public static SalaoDAO criarSalaoDAO() {
		SalaoDAOHibernate salaoDAOHibernate = new SalaoDAOHibernate();
		salaoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return salaoDAOHibernate;
	}

	public static EnderecoDAO criarEnderecoDAO() {
		EnderecoDAOHibernate enderecoDAOHibernate = new EnderecoDAOHibernate();
		enderecoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return enderecoDAOHibernate;
	}

	public static BairroDAO criarBairroDAO() {
		BairroDAOHibernate bairroDAOHibernate = new BairroDAOHibernate();
		bairroDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return bairroDAOHibernate;
	}

	public static CidadeDAO criarCidadeDAO() {
		CidadeDAOHibernate cidadeDAOHibernate = new CidadeDAOHibernate();
		cidadeDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return cidadeDAOHibernate;
	}

	public static EnderecoSalaoDAO criarEnderecoSalaoDAO() {
		EnderecoSalaoDAOHibernate enderecoSalaoDAOHibernate = new EnderecoSalaoDAOHibernate();
		enderecoSalaoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return enderecoSalaoDAOHibernate;
	}

	public static VendedorDAO criarVendedorDAO() {
		VendedorDAOHibernate vendedorDAOHibernate = new VendedorDAOHibernate();
		vendedorDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return vendedorDAOHibernate;
	}

	public static SetorDAO criarSetorDAO() {
		SetorDAOHibernate setorDAOHibernate = new SetorDAOHibernate();
		setorDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return setorDAOHibernate;
	}

	public static EnderecoVendedorDAO criarEnderecoVendedorDAO() {
		EnderecoVendedorDAOHibernate enderecoVendedorDAOHibernate = new EnderecoVendedorDAOHibernate();
		enderecoVendedorDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return enderecoVendedorDAOHibernate;
	}

	public static ProdutoDAO criarProdutoDAO() {
		ProdutoDAOHibernate produtoDAOHibernate = new ProdutoDAOHibernate();
		produtoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return produtoDAOHibernate;
	}

	public static VendaDAO criarVendaDAO() {
		VendaDAOHibernate vendaDAOHibernate = new VendaDAOHibernate();
		vendaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return vendaDAOHibernate;
	}

	public static ItemVendaDAO criarItemVendaDAO() {
		ItemVendaDAOHibernate itemVendaDAOHibernate = new ItemVendaDAOHibernate();
		itemVendaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return itemVendaDAOHibernate;
	}

	public static FaturamentoDAO criarFaturamentoDAO() {
		FaturamentoDAOHibernate faturamentoDAOHibernate = new FaturamentoDAOHibernate();
		faturamentoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return faturamentoDAOHibernate;
	}

	public static PedidoCompraDAO criarPedidoCompraDAO() {
		PedidoCompraDAOHibernate pedidoCompraDAOHibernate = new PedidoCompraDAOHibernate();
		pedidoCompraDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return pedidoCompraDAOHibernate;
	}

	public static ItemCompraDAO criarItemCompraDAO() {
		ItemCompraDAOHibernate itemCompraDAOHibernate = new ItemCompraDAOHibernate();
		itemCompraDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return itemCompraDAOHibernate;
	}

	public static DespesaDAO criarDespesaDAO() {
		DespesaDAOHibernate despesaDAOHibernate = new DespesaDAOHibernate();
		despesaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return despesaDAOHibernate;
	}

	public static EstoqueDAO criarEstoqueDAO() {
		EstoqueDAOHibernate estoqueDAOHibernate = new EstoqueDAOHibernate();
		estoqueDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return estoqueDAOHibernate;
	}

	public static StatusPedidoDAO criarStatusPedidoDAO() {
		StatusPedidoDAOHibernate statusPedidoDAOHibernate = new StatusPedidoDAOHibernate();
		statusPedidoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return statusPedidoDAOHibernate;
	}

	public static ItemSetorDAO criarItemSetorDAO() {
		ItemSetorDAOHibernate itemSetorDAOHibernate = new ItemSetorDAOHibernate();
		itemSetorDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return itemSetorDAOHibernate;
	}

	public static CategoriaDAO criarCategoriaDAO() {
		CategoriaDAOHibernate categoriaDAOHibernate = new CategoriaDAOHibernate();
		categoriaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return categoriaDAOHibernate;
	}

	public static SubCategoriaDAO criarSubCategoriaDAO() {
		SubCategoriaDAOHibernate subCategoriaDAOHibernate = new SubCategoriaDAOHibernate();
		subCategoriaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return subCategoriaDAOHibernate;
	}

	public static CentroCustoDAO criarCentroCustoDAO() {
		CentroCustoDAOHibernate centroCustoDAOHibernate = new CentroCustoDAOHibernate();
		centroCustoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return centroCustoDAOHibernate;
	}

	public static BeneficiarioDAO criarBeneficiarioDAO() {
		BeneficiarioDAOHibernate beneficiarioDAOHibernate = new BeneficiarioDAOHibernate();
		beneficiarioDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return beneficiarioDAOHibernate;
	}

	public static ReciboDespesaDAO criarReciboDespesaDAO() {
		ReciboDespesaDAOHibernate reciboDespesaDAOHibernate = new ReciboDespesaDAOHibernate();
		reciboDespesaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return reciboDespesaDAOHibernate;
	}

	public static TipoContaDAO criarTipoContaDAO() {
		TipoContaDAOHibernate tipoContaDAOHibernate = new TipoContaDAOHibernate();
		tipoContaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return tipoContaDAOHibernate;
	}

	public static ContaDAO criarContaDAO() {
		ContaDAOHibernate contaDAOHibernate = new ContaDAOHibernate();
		contaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return contaDAOHibernate;
	}

	public static ReceitaDAO criarReceitaDAO() {
		ReceitaDAOHibernate receitaDAOHibernate = new ReceitaDAOHibernate();
		receitaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return receitaDAOHibernate;
	}

	public static ReciboReceitaDAO criarReciboReceitaDAO() {
		ReciboReceitaDAOHibernate reciboReceitaDAOHibernate = new ReciboReceitaDAOHibernate();
		reciboReceitaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return reciboReceitaDAOHibernate;
	}

	public static ExtratoContaDAO criarExtratoContaDAO() {
		ExtratoContaDAOHibernate extratoContaDAOHibernate = new ExtratoContaDAOHibernate();
		extratoContaDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return extratoContaDAOHibernate;
	}

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAOHibernate = new UsuarioDAOHibernate();
		usuarioDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return usuarioDAOHibernate;
	}

	public static LinhaProdutoDAO criarLinhaProdutoDAO() {
		LinhaProdutoDAOHibernate linhaProdutoDAOHibernate = new LinhaProdutoDAOHibernate();
		linhaProdutoDAOHibernate.setSession(HibernateUtil.getSession().getCurrentSession());
		
		return linhaProdutoDAOHibernate;
	}
	

}
