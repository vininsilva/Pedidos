package br.com.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedidos.entity.Pedido;
import br.com.pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	public PedidoService pedidoService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> receberPedidos(@RequestBody List<Pedido> pedido) {
		return pedidoService.receberPedidos(pedido);
	}

	@GetMapping
	public ResponseEntity<List<Pedido>> consultarPedidos(
			@RequestParam(required = false) String numeroPedido,
			@RequestParam(required = false) String dataCadastro) {
		return pedidoService.consultarPedidos(numeroPedido, dataCadastro);
	}

}