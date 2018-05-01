package com.jrafael.mc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jrafael.mc.domain.ItemPedido;
import com.jrafael.mc.domain.PagamentoComBoleto;
import com.jrafael.mc.domain.Pedido;
import com.jrafael.mc.domain.enums.EstadoPagamento;
import com.jrafael.mc.repositories.ItemPedidoRepository;
import com.jrafael.mc.repositories.PagamentoRepository;
import com.jrafael.mc.repositories.PedidoRepository;
import com.jrafael.mc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	public PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	@Autowired 
	private ProductoService productoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found with id = " + id
				+ ", Type = " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido pedido) {
		//usar productoService.find() ao inves de producto repository
		
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto,pedido.getInstante());
		}
		pedido = repo.save(pedido);
		pagamentoRepo.save(pedido.getPagamento());
		
		for(ItemPedido ip: pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProducto(productoService.find(ip.getProducto().getId()));
			ip.setPreco(productoService.find(ip.getProducto().getId()).getPreco());
//			ip.setPreco(productoRepo.findById(ip.getProducto().getId()).get().getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		
		emailService.sendOrderConfirmationEmail(pedido);
		
		return pedido;
	}
}
