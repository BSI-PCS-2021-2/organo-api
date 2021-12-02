package bsi.pcs.organo.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

	@Query(value = "SELECT * FROM pedido p INNER JOIN fornecedor f on (f.cnpj = ?2 and p.data_entrega = ?1)", 
			  nativeQuery = true)
	public PedidoEntity findByDataEntregaAndFornecedorCnpj(Date dataEntrega, String fornecedorCnpj);
}
