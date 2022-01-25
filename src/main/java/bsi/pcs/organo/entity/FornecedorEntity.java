package bsi.pcs.organo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import bsi.pcs.organo.util.InfoEntrega;

@Entity
@Table(name = "fornecedor")
@JsonInclude(Include.NON_NULL)
public class FornecedorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fornecedor_id")
	private Long id;
	private String nomeFantasia;
	@NotNull
	private String cnpj;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String senha;
	@Column(name = "info_entrega")
	private InfoEntrega infoEntrega;
	@Column(name = "telefone_movel")
	private String telefoneMovel;
	@OneToMany(mappedBy = "fornecedor")
	private List<HorarioEntity> horarios;
	@OneToMany(mappedBy = "fornecedor")
	private List<EnderecoEntity> enderecos;

	public FornecedorEntity() {}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Long getId() {
		return id;
	}

	public InfoEntrega getInfoEntrega() {
		return infoEntrega;
	}

	public void setInfoEntrega(InfoEntrega infoEntrega) {
		this.infoEntrega = infoEntrega;
	}
	
	public String getTelefoneMovel() {
		return telefoneMovel;
	}

	public void setTelefoneMovel(String telefoneMovel) {
		this.telefoneMovel = telefoneMovel;
	}
	
	public List<HorarioEntity> getHorarios() {
		return horarios;
	}
	
	public List<String> getHorariosValores() {
		List<String> h = new ArrayList<>();
		for(HorarioEntity he : horarios) {
			h.add(he.getHorarioSelecionado().getInfoHorarios());
		}
		
		return h;
	}

	public List<EnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

	public void setHorarios(List<HorarioEntity> horarios) {
		this.horarios = horarios;
	}
	
	
}
