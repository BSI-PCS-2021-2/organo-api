package bsi.pcs.organo.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public void cadastrarFoto(ProdutoEntity produto, MultipartFile foto) throws IOException {
		produto.setFoto(foto.getBytes());
		this.produtoRepository.save(produto);
	}
	
	public void atualizar(ProdutoEntity produto) {
		ProdutoEntity produtoEncontrado = this.produtoRepository.findByFornecedorCnpjAndNome(produto.getFornecedor().getCnpj(), produto.getNome());
		produtoEncontrado.setNome(produto.getNome());
		produtoEncontrado.setFoto(produto.getFoto());
		produtoEncontrado.setPreco(produto.getPreco());
		produtoEncontrado.setValidade(produto.getValidade());
		this.produtoRepository.save(produtoEncontrado);
	}
}
