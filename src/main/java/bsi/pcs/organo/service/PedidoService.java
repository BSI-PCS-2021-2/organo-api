package bsi.pcs.organo.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsi.pcs.organo.entity.CompradorEntity;
import bsi.pcs.organo.entity.EnderecoEntity;
import bsi.pcs.organo.entity.FornecedorEntity;
import bsi.pcs.organo.entity.ItemEntity;
import bsi.pcs.organo.entity.PedidoEntity;
import bsi.pcs.organo.entity.ProdutoEntity;
import bsi.pcs.organo.repository.CompradorRepository;
import bsi.pcs.organo.repository.EnderecoRepository;
import bsi.pcs.organo.repository.FornecedorRepository;
import bsi.pcs.organo.repository.ItemRepository;
import bsi.pcs.organo.repository.PedidoRepository;
import bsi.pcs.organo.repository.ProdutoRepository;
import bsi.pcs.organo.util.Status;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private CompradorRepository compradorRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public Object retornarById(Long pedidoId) {
		return this.pedidoRepository.findById(pedidoId);
	}

	public void atualizarStatus(Long pedidoId, String status) {
		Optional<PedidoEntity> pedidoEncontrado = this.pedidoRepository.findById(pedidoId);
		Status stat = Status.valueOf(status);
		pedidoEncontrado.get().setStatus(stat);
	}

	public Object retornar(Date dataEntrega, String fornecedorCnpj) {
		return this.pedidoRepository.findByDataEntregaAndFornecedorCnpj(dataEntrega, fornecedorCnpj);
	}

	public void registrar(PedidoEntity pedido, String compradorCpf, String fornecedorCnpj) {
		CompradorEntity compradorEncontrado = this.compradorRepository.getByCpf(compradorCpf);
		compradorEncontrado.addPedido(pedido);
		FornecedorEntity fornecedorEncontrado = this.fornecedorRepository.getByCnpj(fornecedorCnpj);
		EnderecoEntity enderecoEncontrado = this.enderecoRepository.findByCepAndCompradorCpf(pedido.getEndereco().getCep(), compradorCpf);
		pedido.setEndereco(enderecoEncontrado);
		pedido.setCompradorAssociado(compradorEncontrado);
		pedido.setFornecedorAssociado(fornecedorEncontrado);
		
		for(ItemEntity item : pedido.getItens()) {
			ProdutoEntity produtoEncontrado = this.produtoRepository.findByFornecedorCnpjAndNome(fornecedorCnpj, item.getProduto().getNome());
			item.setProduto(produtoEncontrado);
			this.itemRepository.save(item);
		}
		
		this.pedidoRepository.save(pedido);
	}

}
