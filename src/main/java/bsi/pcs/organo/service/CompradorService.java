package bsi.pcs.organo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.CompradorEntity;
import bsi.pcs.organo.entity.EnderecoEntity;
import bsi.pcs.organo.repository.CompradorRepository;

@Service
public class CompradorService {
	
	@Autowired
	private CompradorRepository compradorRepository;
	
	public void cadastrar(CompradorEntity comprador) {
		if(!comprador.getEnderecos().isEmpty()) {
			for(EnderecoEntity endereco : comprador.getEnderecos()) {
				endereco.setComprador(comprador);
			}
		}
		this.compradorRepository.save(comprador);
	}
}
