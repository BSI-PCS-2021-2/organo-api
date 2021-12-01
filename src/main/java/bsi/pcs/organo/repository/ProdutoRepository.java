package bsi.pcs.organo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.entity.ProdutoEntity;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

	@Query(value = "SELECT * FROM produto p INNER JOIN fornecedor f on (f.cnpj = ?1 and p.nome = ?2)", 
			  nativeQuery = true)
	public ProdutoEntity findByFornecedorCnpjAndNome(String fornecedorCnpj, String nomeProduto);
	
	public Optional<ProdutoEntity> findById(Long id);
}
