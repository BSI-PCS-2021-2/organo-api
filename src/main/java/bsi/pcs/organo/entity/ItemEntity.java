package bsi.pcs.organo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "item")
@JsonInclude(Include.NON_NULL)
public class ItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private ProdutoEntity produto;
	private int quantidade;
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private PedidoEntity pedido;
	public ItemEntity() {}

	public ProdutoEntity getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public PedidoEntity getPedido() {
		return null;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}
}
