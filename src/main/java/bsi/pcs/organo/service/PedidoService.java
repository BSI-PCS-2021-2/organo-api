package bsi.pcs.organo.service;

import java.io.IOException;
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
	
	@Autowired
	private EmailService emailService;
	

	public Object retornarById(Long pedidoId) {
		return this.pedidoRepository.findById(pedidoId);
	}

	public void atualizarStatus(Long pedidoId, String status) {
		Optional<PedidoEntity> pedidoEncontrado = this.pedidoRepository.findById(pedidoId);
		Status stat = Status.valueOf(status);
		pedidoEncontrado.get().setStatus(stat);
		this.pedidoRepository.save(pedidoEncontrado.get());
	}

	public Object retornar(Date dataEntrega, String fornecedorCnpj) {
		return this.pedidoRepository.findByDataEntregaAndFornecedorCnpj(dataEntrega, fornecedorCnpj);
	}

	public void registrar(PedidoEntity pedido, String compradorCpf, String fornecedorCnpj) throws IOException {
		CompradorEntity compradorEncontrado = this.compradorRepository.getByCpf(compradorCpf);
		compradorEncontrado.addPedido(pedido);
		FornecedorEntity fornecedorEncontrado = this.fornecedorRepository.getByCnpj(fornecedorCnpj);
		EnderecoEntity enderecoEncontrado = this.enderecoRepository.findByCepAndCompradorCpf(pedido.getEndereco().getCep(), compradorCpf);
		pedido.setEndereco(enderecoEncontrado);
		pedido.setCompradorAssociado(compradorEncontrado);
		pedido.setFornecedorAssociado(fornecedorEncontrado);
		
		for(ItemEntity item : pedido.getItens()) {
			ProdutoEntity produtoEncontrado = this.produtoRepository.findByFornecedorCnpjAndNome(fornecedorCnpj, item.getProduto().getNome());
			produtoEncontrado.setQuantidade(produtoEncontrado.getQuantidade() - item.getQuantidade());
			this.produtoRepository.save(produtoEncontrado);
			item.setProduto(produtoEncontrado);
			this.itemRepository.save(item);
		}
		
		this.pedidoRepository.save(pedido);
		this.enviarEmail(compradorEncontrado.getEmail(), fornecedorEncontrado.getEmail());
	}
	
	private void enviarEmail(String emailComprador, String emailFornecedor) throws IOException {
		String contentComprador = "Seu pedido acaba de ser criado. Acompanhe o status do seu pedido na sua página de perfil.";
		String assuntoComprador = "Recebemos seu pedido!";
		String contentFornecedor = "Um pedido acabou de chegar para você. Para mais detalhes, verifique seus pedidos em sua página de perfil.";
		String assuntoFornecedor = "Um pedido chegou para você!";
		String emailFrom = "organomercadodeorganicos@gmail.com";
		this.emailService.sendEmail(emailFrom, emailComprador, contentComprador, assuntoComprador);
		this.emailService.sendEmail(emailFrom, emailFornecedor, contentFornecedor, assuntoFornecedor);
	}

}
