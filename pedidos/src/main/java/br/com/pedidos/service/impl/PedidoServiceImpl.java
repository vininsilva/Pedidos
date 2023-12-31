package br.com.pedidos.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pedidos.entity.Pedido;
import br.com.pedidos.repository.PedidoRepository;
import br.com.pedidos.service.PedidoService;
import br.com.pedidos.util.CalcularDesconto;
import br.com.pedidos.util.PedidoException;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	private List<Pedido> pedidos = new ArrayList<>();

	public ResponseEntity<String> receberPedidos(List<Pedido> pedidos) {
		List<StringBuilder> responseList = new ArrayList<StringBuilder>();
		
		for (Pedido pedido : pedidos) {
			StringBuilder responseBuilder = new StringBuilder();
			StringBuilder errorBuilder = new StringBuilder();
			
			try {

				Optional<Pedido> pedidoExistente = pedidoRepository.findByNumeroControle(pedido.getNumeroControle());

				if (pedidoExistente.isPresent()) {
					errorBuilder.append("Número de controle já cadastrado para o pedido: ");
					throw new PedidoException(errorBuilder.toString());
				} else {
					if (pedido.getDataCadastro() == null) {
						pedido.setDataCadastro(new Date());
					}

					if (pedido.getQuantidade() > 10) {
						errorBuilder.append("Quantidade excede o limite máximo para o pedido: ");
						throw new PedidoException(errorBuilder.toString());
					} else if (pedido.getQuantidade() == 0) {
						pedido.setQuantidade(1);
					}

					CalcularDesconto valorTotal = new CalcularDesconto();
					pedido.setValor(valorTotal.calcularValorTotal(pedido.getValor(), pedido.getQuantidade()));

					pedidoRepository.save(pedido);

					responseBuilder.append("Pedido recebido. Número de controle: ")
					.append(pedido.getNumeroControle())
					.append("\n");
					responseList.add(responseBuilder);
				}

			} catch (Exception e) {
				responseBuilder.append("Detalhes do erro: ")
				.append(e.getMessage())
				.append(pedido.getNumeroControle())
				.append("\n");
				responseList.add(responseBuilder);
			}
		} 

		return ResponseEntity.status(HttpStatus.OK).body(responseList.toString());

	}

	public ResponseEntity<List<Pedido>> consultarPedidos(String numeroPedido, String dataCadastro) {

		if (numeroPedido != null) {
			List<Pedido> resultado = pedidoRepository.findAll();
			resultado = resultado.stream()
					.filter(p -> p.getNumeroControle().equals(numeroPedido))
					.collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(resultado);
		}

		if (dataCadastro != null) {
			Date data;
			try {
				data = new SimpleDateFormat("yyyy-MM-dd").parse(dataCadastro);
			} catch (ParseException e) {
				throw new PedidoException("Data inválida");
			}
			List<Pedido> pedidos = pedidoRepository.findByDataCadastro(data);
			return ResponseEntity.status(HttpStatus.OK).body(pedidos);
		}

		if (numeroPedido == null && dataCadastro == null) 
			for (Pedido pedido : pedidoRepository.findAll()) {
				pedidos.add(pedido);
			}

		return ResponseEntity.status(HttpStatus.OK).body(pedidos);

	}

}
