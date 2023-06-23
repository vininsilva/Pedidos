package br.com.pedidos.util;

public class CalcularDesconto {
	
	public double calcularValorTotal(double valorUnitario, int quantidade) {
        double valorTotal = valorUnitario * quantidade;

        if (quantidade > 5 && quantidade < 10) {
            valorTotal -= valorTotal * 0.05; // Aplicar desconto de 5%
        } else if (quantidade >= 10) {
            valorTotal -= valorTotal * 0.1; // Aplicar desconto de 10%
        }

        return valorTotal;
    }

}
