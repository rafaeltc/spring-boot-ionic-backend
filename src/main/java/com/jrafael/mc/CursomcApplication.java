package com.jrafael.mc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jrafael.mc.domain.Categoria;
import com.jrafael.mc.domain.Cidade;
import com.jrafael.mc.domain.Cliente;
import com.jrafael.mc.domain.Endereco;
import com.jrafael.mc.domain.Estado;
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
import com.jrafael.mc.repositories.PagamentoRepository;
import com.jrafael.mc.repositories.PedidoRepository;
import com.jrafael.mc.repositories.ProductoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null,"escritorio");

		Producto p1 = new Producto(null, "Computador", 2000.00);
		Producto p2 = new Producto(null, "Impressora", 800.00);
		Producto p3 = new Producto(null, "Mouse", 80.00);
		
		cat1.getProductos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProductos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		productoRepository.save(Arrays.asList(p1,p2,p3));
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(cid1,cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria.silva@gmail.com", "2135597213", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("21982313","21331123"));
		
		Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 300", "Jardim", "23131", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua Carpacio", "1200", "Apto 100", "Rotunda", "244", cli1, cid2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));

		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1, e2);
		
		Pagamento pagTo1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagTo1);
		
		Pagamento pagTo2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, 
				sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagTo2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pagTo1,pagTo2));
	}
}
