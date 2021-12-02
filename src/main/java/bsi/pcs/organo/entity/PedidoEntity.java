package bsi.pcs.organo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import bsi.pcs.organo.util.MetodoPagamento;
import bsi.pcs.organo.util.Status;

@Entity
@Table(name = "pedido")
@JsonInclude(Include.NON_NULL)
public class PedidoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id")
	private Long id;
	private float valor;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "comprador_id")
	private CompradorEntity compradorAssociado;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private FornecedorEntity fornecedorAssociado;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataEntrega;
	@Column(name = "status_pedido")
	private Status status;
	private MetodoPagamento metodoPagamento;
	@OneToMany
	@JoinColumn(name = "item_id")
	private List<ItemEntity> itens;
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private EnderecoEntity endereco;
	
	public PedidoEntity() {}
	
	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public MetodoPagamento getMetodoPagamento() {
		return metodoPagamento;
	}

	public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
		this.metodoPagamento = metodoPagamento;
	}

	public CompradorEntity getCompradorAssociado() {
		return compradorAssociado;
	}

	public void setCompradorAssociado(CompradorEntity compradorAssociado) {
		this.compradorAssociado = compradorAssociado;
	}

	public FornecedorEntity getFornecedorAssociado() {
		return fornecedorAssociado;
	}

	public void setFornecedorAssociado(FornecedorEntity fornecedorAssociado) {
		this.fornecedorAssociado = fornecedorAssociado;
	}

	public List<ItemEntity> getItens() {
		return itens;
	}

	public void setItens(List<ItemEntity> itens) {
		this.itens = itens;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
		this.endereco = endereco;
	}	
	
	public Long getId() {
		return id;
	}
	
}
