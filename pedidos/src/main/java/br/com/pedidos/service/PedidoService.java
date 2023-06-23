package br.com.pedidos.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.pedidos.entity.Pedido;

public interface PedidoService {
	
	public ResponseEntity<String> receberPedidos(List<Pedido> pedido);
	
	public ResponseEntity<List<Pedido>> consultarPedidos(String numeroPedido, String dataCadastro);

}
