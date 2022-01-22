package bsi.pcs.organo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import bsi.pcs.organo.util.InfoHorarios;

@Entity
@Table(name = "horario")
public class HorarioEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "horario_id")
	private Long id;
	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private FornecedorEntity fornecedor;
	@NotNull
	@Column(name = "horario_selecionado")
	private InfoHorarios horarioSelecionado;
	
	public Long getId() {
		return id;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}
	public InfoHorarios getHorarioSelecionado() {
		return horarioSelecionado;
	}
	public void setHorarioSelecionado(InfoHorarios horarioSelecionado) {
		this.horarioSelecionado = horarioSelecionado;
	}
	
	
}
