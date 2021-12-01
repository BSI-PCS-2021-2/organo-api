package bsi.pcs.organo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bsi.pcs.organo.entity.FornecedorEntity;
import bsi.pcs.organo.entity.ProdutoEntity;
import bsi.pcs.organo.service.FornecedorService;
import bsi.pcs.organo.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;

	@PostMapping("/{cnpjFornecedor}/cadastrar")
	public ResponseEntity<?> create(@RequestBody(required = true) ProdutoEntity produto,
									@PathVariable String cnpjFornecedor) {
		if(produto == null) return ResponseEntity.badRequest().body("Por favor informar todos os dados do produto.");
		
		FornecedorEntity fornecedorEncontrado = this.fornecedorService.retornar(cnpjFornecedor);
		produto.setFornecedor(fornecedorEncontrado);
		
		if(this.produtoService.retornar(produto.getFornecedor().getCnpj(), produto.getNome()) != null) {
			return ResponseEntity.badRequest().body("Produto já está cadastrado");
		}
		
		this.produtoService.cadastrar(produto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso."); 
		
	}
	
	@PutMapping("/cadastrarFoto/{produtoId}")
	public ResponseEntity<?> cadastrarFoto(@RequestParam("foto") MultipartFile foto, @PathVariable Long produtoId) throws IOException {
		if(produtoId == null) return ResponseEntity.badRequest().body("Por favor informar id do produto.");
		
		if(foto == null) return ResponseEntity.badRequest().body("Foto vazia.");
		
		ProdutoEntity produtoEncontrado = this.produtoService.retornarById(produtoId);
		
		if(produtoEncontrado == null) {
			return ResponseEntity.badRequest().body("Produto não existe");
		}
		
		this.produtoService.cadastrarFoto(produtoEncontrado, foto);
		return ResponseEntity.status(HttpStatus.OK).body("Foto cadastrada com sucesso."); 
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<?> update(@RequestBody(required = true) ProdutoEntity produto) {
		if(produto == null) return ResponseEntity.badRequest().body("Por favor informar os dados do produto.");
		
		if(this.produtoService.retornar(produto.getFornecedor().getCnpj(), produto.getNome()) == null) {
			return ResponseEntity.badRequest().body("Produto informado não existe");
		}
		
		this.produtoService.atualizar(produto);
		return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso."); 
		
	}
	
	@GetMapping("/{cnpjFornecedor}/retornar/{nomeProduto}")
	public ResponseEntity<?> getProduto(@PathVariable String cnpjFornecedor, @PathVariable String nomeProduto) {
		if(cnpjFornecedor == null) return ResponseEntity.badRequest().body("Por favor informar o CNPJ do fornecedor.");
		if(nomeProduto == null) return ResponseEntity.badRequest().body("Por favor informar o nome do produto.");
		
		ProdutoEntity produto = this.produtoService.retornar(cnpjFornecedor, nomeProduto);
		if(produto == null) return ResponseEntity.badRequest().body("Os dados informados não estão associados a nenhum produto.");
		
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
}
