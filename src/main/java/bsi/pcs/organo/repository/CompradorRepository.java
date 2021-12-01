package bsi.pcs.organo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bsi.pcs.organo.entity.CompradorEntity;

@Repository
public interface CompradorRepository extends JpaRepository<CompradorEntity, Long> {

	public CompradorEntity getByCpf(String cpf);
	
	public CompradorEntity getByEmail(String email);
	
}
