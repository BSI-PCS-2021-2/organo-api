package bsi.pcs.organo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.FornecedorEntity;
import bsi.pcs.organo.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
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
}
