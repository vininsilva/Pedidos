package br.com.pedidos.util;

public class PedidoException extends RuntimeException {
	
	private static final long serialVersionUID = 3007694268102038811L;

	public PedidoException(String message) {
        super(message);
    }
}