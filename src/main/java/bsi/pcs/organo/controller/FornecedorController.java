package bsi.pcs.organo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsi.pcs.organo.entity.FornecedorEntity;
import bsi.pcs.organo.service.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

	@Autowired
	private FornecedorService fornecedorService;

	@GetMapping("/listar")
	public ResponseEntity<?> list() {	
		return ResponseEntity.status(HttpStatus.OK).body(this.fornecedorService.listFornecedores()); 
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> create(@RequestBody(required = true) FornecedorEntity fornecedor) {
		
		if(this.fornecedorService.retornar(fornecedor.getCnpj()) != null) {
			return ResponseEntity.badRequest().body("Fornecedor já está cadastrado");
		}
		
		this.fornecedorService.cadastrar(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body("Fornecedor cadastrado com sucesso."); 
		
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<?> update(@RequestBody(required = true) FornecedorEntity fornecedor) {
		
		if(this.fornecedorService.retornar(fornecedor.getCnpj()) == null) {
			return ResponseEntity.badRequest().body("Fornecedor informado não existe");
		}
		
		this.fornecedorService.atualizar(fornecedor);
		return ResponseEntity.status(HttpStatus.OK).body("Fornecedor atualizado com sucesso."); 
		
	}
	
	@GetMapping("/{cnpjFornecedor}")
	public ResponseEntity<?> getFornecedor(@PathVariable(required = true) String cnpjFornecedor) {
		
		FornecedorEntity fornecedor = this.fornecedorService.retornar(cnpjFornecedor);
		if(fornecedor == null) return ResponseEntity.badRequest().body("CNPJ informado não está associado a nenhum fornecedor.");
		
		return ResponseEntity.status(HttpStatus.OK).body(fornecedor);
	}
	
	@PostMapping("/autenticar")
	public ResponseEntity<?> autenticar(@RequestBody(required = true) FornecedorEntity fornecedor) {
		
		if(this.fornecedorService.autenticar(fornecedor)) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Fornecedor autenticado com sucesso");
		}
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Não foi possível autenticar fornecedor.");
	}
	
	@GetMapping("/{cnpjFornecedor}/listarProdutos")
	public ResponseEntity<?> listProdutos(@PathVariable(required = true) String cnpjFornecedor) {	
		return ResponseEntity.status(HttpStatus.OK).body(this.fornecedorService.listarProdutos(cnpjFornecedor)); 
	}
	
	@GetMapping("/{cnpjFornecedor}/listarPedidos")
	public ResponseEntity<?> listPedidos(@PathVariable(required = true) String cnpjFornecedor) {	
		return ResponseEntity.status(HttpStatus.OK).body(this.fornecedorService.listarPedidos(cnpjFornecedor)); 
	}
	
	
	
}
