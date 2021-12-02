package bsi.pcs.organo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.entity.EnderecoEntity;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

	@Query(value = "SELECT * FROM endereco e where (e.cep = ?1 and e.rua = ?2 and e.numero = ?3 and e.complemento = ?4 and e.comprador_id = ?5)", 
	  nativeQuery = true)
	public EnderecoEntity findByCepRuaNumeroComplementoCompradorId(String cep, String rua, int numero, String complemento, Long compradorId);
	
	@Query(value = "SELECT * FROM endereco e INNER JOIN comprador c on (e.cep = ?1 and e.comprador_id = c.comprador_id and c.cpf = ?2)", 
			  nativeQuery = true)
	public EnderecoEntity findByCepAndCompradorCpf(String cep, String compradorCpf);
}
