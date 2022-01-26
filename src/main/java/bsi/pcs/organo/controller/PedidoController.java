package bsi.pcs.organo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsi.pcs.organo.entity.PedidoEntity;
import bsi.pcs.organo.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping("/{fornecedorCnpj}/registrar/{compradorCpf}")
	public ResponseEntity<?> register(@RequestBody(required = true) PedidoEntity pedido,
									  @PathVariable(required = true) String fornecedorCnpj,
									  @PathVariable(required = true) String compradorCpf) throws IOException {

		this.pedidoService.registrar(pedido, compradorCpf, fornecedorCnpj);
		return ResponseEntity.status(HttpStatus.CREATED).body("Pedido registrado com sucesso."); 
	
	}
	
	@PutMapping("/atualizarStatus/{pedidoId}")
	public ResponseEntity<?> atualizarStatus(@PathVariable(required = true) Long pedidoId, @RequestParam("status") String status) {
		
		if(this.pedidoService.retornarById(pedidoId) == null) {
			return ResponseEntity.badRequest().body("Pedido não existe.");
		}
		
		this.pedidoService.atualizarStatus(pedidoId, status);
		return ResponseEntity.status(HttpStatus.CREATED).body("Status do pedido atualizado com sucesso."); 
		
	}
}
