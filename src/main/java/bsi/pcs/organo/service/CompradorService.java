package bsi.pcs.organo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.CompradorEntity;
import bsi.pcs.organo.entity.EnderecoEntity;
import bsi.pcs.organo.repository.CompradorRepository;
import bsi.pcs.organo.repository.EnderecoRepository;

@Service
public class CompradorService {
	
	@Autowired
	private CompradorRepository compradorRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
		
	public void cadastrar(CompradorEntity comprador) {
		this.compradorRepository.save(comprador);
		
		if(!comprador.getEnderecos().isEmpty()) {
			for(EnderecoEntity endereco : comprador.getEnderecos()) {
				endereco.setComprador(comprador);
				this.enderecoRepository.save(endereco);
			}
		}
	}
	
	public CompradorEntity retornar(String cpf) {
		CompradorEntity ce = this.compradorRepository.getByCpf(cpf);
		return ce;
	}
	
	public void atualizar(CompradorEntity comprador) {
		CompradorEntity compradorEncontrado = this.compradorRepository.getByCpf(comprador.getCpf());
		compradorEncontrado.setEmail(comprador.getEmail());
		compradorEncontrado.setNome(comprador.getNome());
		compradorEncontrado.setSobrenome(comprador.getSobrenome());
		compradorEncontrado.setSenha(comprador.getSenha());
		this.compradorRepository.save(compradorEncontrado);
		
		if(!comprador.getEnderecos().isEmpty()) {
			for(EnderecoEntity ee : comprador.getEnderecos()) {
				EnderecoEntity enderecoEncontrado = this.enderecoRepository.findByCepRuaNumeroComplementoCompradorId(ee.getCep(), ee.getRua(), ee.getNumero(), ee.getComplemento(), compradorEncontrado.getId());
				if(enderecoEncontrado == null) {
					ee.setComprador(compradorEncontrado);
					this.enderecoRepository.save(ee);
				}
			}
		}
	}
	
	public boolean autenticar(CompradorEntity comprador) {
		CompradorEntity compradorEncontrado = this.compradorRepository.getByEmail(comprador.getEmail());
		if(comprador.getSenha().equals(compradorEncontrado.getSenha())) return true;
		return false;
	}
}
