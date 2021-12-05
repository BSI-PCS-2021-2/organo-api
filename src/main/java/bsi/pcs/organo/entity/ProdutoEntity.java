package bsi.pcs.organo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "produto")
@JsonInclude(Include.NON_NULL)
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id")
	private Long id;
	private String nome;
	private float preco;
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date validade;
	private byte[] foto;
	private String nomeArquivoFoto;
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private FornecedorEntity fornecedor;
	
	public ProdutoEntity() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getPreco() {
		return preco;
	}

	public void setPreco(float preco) {
		this.preco = preco;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public FornecedorEntity getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Long getId() {
		return id;
	}

	public String getNomeArquivoFoto() {
		return nomeArquivoFoto;
	}

	public void setNomeArquivoFoto(String nomeArquivoFoto) {
		this.nomeArquivoFoto = nomeArquivoFoto;
	}
	
}
