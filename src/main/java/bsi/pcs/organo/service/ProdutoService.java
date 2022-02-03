package bsi.pcs.organo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.ProdutoEntity;
import bsi.pcs.organo.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public void cadastrar(ProdutoEntity produto) {
		this.produtoRepository.save(produto);
	}
	
	public ProdutoEntity retornar(String cnpjFornecedor, String nomeProduto) {
		ProdutoEntity pe = this.produtoRepository.findByFornecedorCnpjAndNome(cnpjFornecedor, nomeProduto);
		return pe;
	}
	
	public ProdutoEntity retornarById(Long id) {
		Optional<ProdutoEntity> pe = this.produtoRepository.findById(id);
		return pe.get();
	}
	
	public ProdutoEntity atualizar(ProdutoEntity produto) {
		ProdutoEntity produtoEncontrado = this.produtoRepository.findByFornecedorCnpjAndNome(produto.getFornecedor().getCnpj(), produto.getNome());
		produtoEncontrado.setNome(produto.getNome());
		produtoEncontrado.setFotoUrl(produto.getFotoUrl());
		produtoEncontrado.setPreco(produto.getPreco());
		produtoEncontrado.setValidade(produto.getValidade());
		produtoEncontrado.setQuantidade(produto.getQuantidade());
		this.produtoRepository.save(produtoEncontrado);
		return produtoEncontrado;
	}

	public void deletarProduto(Long produtoId) {
		Optional<ProdutoEntity> pe = this.produtoRepository.findById(produtoId);
		ProdutoEntity produtoEncontrado = pe.get();
		produtoEncontrado.setDeleted(true);
		this.produtoRepository.save(produtoEncontrado);
	}
}
