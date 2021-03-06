package com.jrafael.mc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.domain.Cidade;
import com.jrafael.mc.domain.Cliente;
import com.jrafael.mc.domain.Endereco;
import com.jrafael.mc.domain.Estado;
import com.jrafael.mc.domain.ItemPedido;
import com.jrafael.mc.domain.Pagamento;
import com.jrafael.mc.domain.PagamentoComBoleto;
import com.jrafael.mc.domain.PagamentoComCartao;
import com.jrafael.mc.domain.Pedido;
import com.jrafael.mc.domain.Producto;
import com.jrafael.mc.domain.enums.EstadoPagamento;
import com.jrafael.mc.domain.enums.TipoCliente;
import com.jrafael.mc.repositories.CategoriaRepository;
import com.jrafael.mc.repositories.CidadeRepository;
import com.jrafael.mc.repositories.ClienteRepository;
import com.jrafael.mc.repositories.EnderecoRepository;
import com.jrafael.mc.repositories.EstadoRepository;
import com.jrafael.mc.repositories.ItemPedidoRepository;
import com.jrafael.mc.repositories.PagamentoRepository;
import com.jrafael.mc.repositories.PedidoRepository;
import com.jrafael.mc.repositories.ProductoRepository;

@Service
public class DBService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@Autowired
	PagamentoRepository pagamentoRepository;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");
		Categoria cat3 = new Categoria(null,"a1");
		Categoria cat4 = new Categoria(null,"a2");
		Categoria cat5 = new Categoria(null,"a3");
		Categoria cat6 = new Categoria(null,"a4");
		Categoria cat7 = new Categoria(null,"a5");
		Categoria cat8 = new Categoria(null,"a6");

		
		
		Producto p1 = new Producto(null, "Computador", 2000.00);
		Producto p2 = new Producto(null, "Impressora", 800.00);
		Producto p3 = new Producto(null, "Mouse", 80.00);
		Producto p4 = new Producto(null, "Mesa de escritorio", 300.00);
		Producto p5 = new Producto(null, "Toalha", 50.00);
		Producto p6 = new Producto(null, "Colcha", 200.00);
		Producto p7 = new Producto(null, "TV", 1200.00);
		Producto p8 = new Producto(null, "Roçadeira", 800.00);
		Producto p9 = new Producto(null, "Abajour", 100.00);
		Producto p10 = new Producto(null, "Pendente", 180.00);
		Producto p11 = new Producto(null, "Shampo", 90.00);
		
		cat1.getProductos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProductos().addAll(Arrays.asList(p2,p4));
		cat3.getProductos().addAll(Arrays.asList(p5,p6));
		cat4.getProductos().addAll(Arrays.asList(p1,p2,p3, p7));
		cat5.getProductos().addAll(Arrays.asList(p8));
		cat6.getProductos().addAll(Arrays.asList(p9,p10));
		cat7.getProductos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8));
		productoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria.silva@gmail.com", "2135597213", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("21982313","21331123"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 300", "Jardim", "23131", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua Carpacio", "1200", "Apto 100", "Rotunda", "244", cli1, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, e2);
		
		Pagamento pagTo1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagTo1);
		
		Pagamento pagTo2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, 
				sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagTo2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagTo1,pagTo2));
		
		//Pedido pedido, Producto producto, Double desconto, Integer quantidade, Double preco
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);;
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
