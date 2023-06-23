package br.com.pedidos.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "numero_controle", nullable = false, unique = true)
	private String numeroControle;
	
	@Column(name = "data_cadastro")
    private Date dataCadastro;
	
	@Column(nullable = false)
    private String nome;
	
	@Column(nullable = false)
    private double valor;
	
	@Column(nullable = false)
    private int quantidade;
	
	@Column(name = "codigo_cliente", nullable = false)
    private int codigoCliente;
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public String getNumeroControle() {
        return numeroControle;
    }

    public void setNumeroControle(String numeroControle) {
        this.numeroControle = numeroControle;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    public Pedido() {
        
    }
    
    public Pedido(String numeroControle, Date dataCadastro, String nome, double valor, int quantidade, int codigoCliente) {
        this.numeroControle = numeroControle;
        this.dataCadastro = dataCadastro;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.codigoCliente = codigoCliente;
    }
}