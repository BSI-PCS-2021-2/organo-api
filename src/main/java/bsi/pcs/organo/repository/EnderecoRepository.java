package bsi.pcs.organo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.entity.PedidoEntity;

@Repository
public interface EnderecoRepository extends JpaRepository<PedidoEntity, Long> {

}
