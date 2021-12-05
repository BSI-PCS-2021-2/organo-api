package bsi.pcs.organo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.FornecedorEntity;
import bsi.pcs.organo.entity.PedidoEntity;
import bsi.pcs.organo.entity.ProdutoEntity;
import bsi.pcs.organo.repository.FornecedorRepository;
import bsi.pcs.organo.repository.PedidoRepository;
import bsi.pcs.organo.repository.ProdutoRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<FornecedorEntity> listFornecedores() {
		return this.fornecedorRepository.findAll();
	}
	
	public void cadastrar(FornecedorEntity fornecedor) {
		this.fornecedorRepository.save(fornecedor);
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

	public boolean autenticar(FornecedorEntity fornecedor) {
		FornecedorEntity fornecedorEncontrado = this.fornecedorRepository.getByEmail(fornecedor.getEmail());
		if(fornecedor.getSenha().equals(fornecedorEncontrado.getSenha())) return true;
		return false;
	}
	
	public List<ProdutoEntity> listarProdutos(String cnpj) {
		List<ProdutoEntity> produtosEncontrados =  this.produtoRepository.findByFornecedorCnpj(cnpj);		
		return produtosEncontrados;
	}
	
	public List<PedidoEntity> listarPedidos(String cnpj) {
		List<PedidoEntity> pedidosEncontrados = this.pedidoRepository.findByFornecedorCnpj(cnpj);
		return pedidosEncontrados;
	}
	

}
