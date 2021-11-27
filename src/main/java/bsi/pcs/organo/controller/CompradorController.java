package bsi.pcs.organo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bsi.pcs.organo.entity.CompradorEntity;
import bsi.pcs.organo.service.CompradorService;

@RestController
@RequestMapping("/comprador")
public class CompradorController {
	
	@Autowired
	private CompradorService compradorService;

	@PostMapping("/cadastrar")
	public ResponseEntity<?> create(@RequestBody(required = true) CompradorEntity comprador) {
		if(comprador == null) {
			return ResponseEntity.badRequest().body("Por favor informar todos os dados do usuário.");
		}
		
		this.compradorService.cadastrar(comprador);
		return ResponseEntity.status(HttpStatus.CREATED).build(); 


		
	}
}
