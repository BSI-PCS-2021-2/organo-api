package bsi.pcs.organo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.EnderecoEntity;
import bsi.pcs.organo.entity.FornecedorEntity;
import bsi.pcs.organo.entity.HorarioEntity;
import bsi.pcs.organo.entity.ItemEntity;
import bsi.pcs.organo.entity.PedidoEntity;
import bsi.pcs.organo.entity.ProdutoEntity;
import bsi.pcs.organo.repository.EnderecoRepository;
import bsi.pcs.organo.repository.FornecedorRepository;
import bsi.pcs.organo.repository.HorarioRepository;
import bsi.pcs.organo.repository.ItemRepository;
import bsi.pcs.organo.repository.PedidoRepository;
import bsi.pcs.organo.repository.ProdutoRepository;
import bsi.pcs.organo.util.RelatorioDeVendas;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private HorarioRepository horarioRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public List<FornecedorEntity> listFornecedores() {
		return this.fornecedorRepository.findAll();
	}
	
	public void cadastrar(FornecedorEntity fornecedor) {		
		this.fornecedorRepository.save(fornecedor);
		
		if(!fornecedor.getEnderecos().isEmpty()) {
			for(EnderecoEntity endereco : fornecedor.getEnderecos()) {
				endereco.setFornecedor(fornecedor);
				this.enderecoRepository.save(endereco);
			}
		}
		
		if(!fornecedor.getHorarios().isEmpty()) {
			for(HorarioEntity horario : fornecedor.getHorarios()) {
				horario.setFornecedor(fornecedor);
				this.horarioRepository.save(horario);
			}
		}
	}
	
	public FornecedorEntity retornar(String cnpj) {
		FornecedorEntity fe = this.fornecedorRepository.getByCnpj(cnpj);
		return fe;
	}
	
	public void atualizar(FornecedorEntity fornecedor) {
		FornecedorEntity fornecedorEncontrado = this.fornecedorRepository.getByCnpj(fornecedor.getCnpj());
		fornecedorEncontrado.setEmail(fornecedor.getEmail());
		fornecedorEncontrado.setNomeFantasia(fornecedor.getNomeFantasia());
		fornecedorEncontrado.setSenha(fornecedor.getSenha());
		this.fornecedorRepository.save(fornecedorEncontrado);
	}

	public FornecedorEntity autenticar(FornecedorEntity fornecedor) {
		FornecedorEntity fornecedorEncontrado = this.fornecedorRepository.getByEmail(fornecedor.getEmail());
		if(fornecedor.getSenha().equals(fornecedorEncontrado.getSenha())) return fornecedorEncontrado;
		return null;
	}
	
	public List<ProdutoEntity> listarProdutos(String cnpj) {
		List<ProdutoEntity> produtosEncontrados =  this.produtoRepository.findByFornecedorCnpj(cnpj);		
		return produtosEncontrados;
	}
	
	public List<PedidoEntity> listarPedidos(String cnpj) {
		List<PedidoEntity> pedidosEncontrados = this.pedidoRepository.findByFornecedorCnpj(cnpj);
		return pedidosEncontrados;
	}
	
	public RelatorioDeVendas gerarRelatorioDeVendas(FornecedorEntity fornecedor) {
		RelatorioDeVendas relatorio = new RelatorioDeVendas();
		String cnpj = fornecedor.getCnpj();
		
		List<PedidoEntity> pedidos = this.listarPedidos(cnpj);
		List<ProdutoEntity> produtos = this.listarProdutos(cnpj);
		List<ItemEntity> itens = this.itemRepository.findByFornecedorCnpj(cnpj);
		
		float totalGanhoPedidos = 0;
		
		for(PedidoEntity pedido : pedidos) {
			totalGanhoPedidos += pedido.getValor();
		}
		
		relatorio.setTotalDePedidos(pedidos.size());
		relatorio.setTotalItensVendidos(itens.size());
		relatorio.setTotalProdutosCadastrados(produtos.size());
		relatorio.setGanhoTotalDePedidos(totalGanhoPedidos);
		relatorio.setFornecedor(fornecedor.getNomeFantasia());
		
		return relatorio;
	}
}
