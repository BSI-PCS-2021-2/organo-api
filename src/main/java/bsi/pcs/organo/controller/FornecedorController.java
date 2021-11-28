package bsi.pcs.organo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
		if(fornecedor == null) return ResponseEntity.badRequest().body("Por favor informar todos os dados do fornecedor.");
		
		if(this.fornecedorService.retornar(fornecedor.getCnpj()) != null) {
			return ResponseEntity.badRequest().body("Fornecedor já está cadastrado");
		}
		
		this.fornecedorService.cadastrar(fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body("Fornecedor cadastrado com sucesso."); 
		
	}
}
