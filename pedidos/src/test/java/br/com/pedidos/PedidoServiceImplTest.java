package br.com.pedidos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.pedidos.entity.Pedido;
import br.com.pedidos.repository.PedidoRepository;
import br.com.pedidos.service.impl.PedidoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;
    
    @Test
    public void testReceberPedidos() {
        Pedido pedido1 = new Pedido();
        pedido1.setNumeroControle("123");
        pedido1.setNome("Produto A");
        pedido1.setDataCadastro(null);
        pedido1.setQuantidade(2);
        pedido1.setValor(10.0);
        pedido1.setCodigoCliente(1);

        Pedido pedido2 = new Pedido();
        pedido2.setNumeroControle("456");
        pedido2.setNome("Produto B");
        pedido2.setDataCadastro(new Date());
        pedido2.setQuantidade(0);
        pedido2.setValor(5.0);
        pedido2.setCodigoCliente(2);
        
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);

        when(pedidoRepository.findByNumeroControle("123")).thenReturn(Optional.empty());
        when(pedidoRepository.findByNumeroControle("456")).thenReturn(Optional.empty());

        ResponseEntity<String> response = pedidoService.receberPedidos(pedidos);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[Pedido recebido. Número de controle: 123\n, Pedido recebido. Número de controle: 456\n]" , response.getBody());

        verify(pedidoRepository).save(pedido1);
        verify(pedidoRepository).save(pedido2);
    }
    
    @Test
    public void testReceberPedidosQuantidadeErrada() { 
        Pedido pedido3 = new Pedido();
        pedido3.setNumeroControle("789");
        pedido3.setNome("Produto C");
        pedido3.setDataCadastro(new Date());
        pedido3.setQuantidade(12);
        pedido3.setValor(7.0);
        pedido3.setCodigoCliente(3);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido3);

        when(pedidoRepository.findByNumeroControle("789")).thenReturn(Optional.empty());

        ResponseEntity<String> response = pedidoService.receberPedidos(pedidos);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[Detalhes do erro: Quantidade excede o limite máximo para o pedido: 789\n]" , response.getBody());

        verify(pedidoRepository, never()).save(pedido3);
    }
    
    @Test
    public void testReceberNumeroControleIgual() { 
        Pedido pedido3 = new Pedido();
        pedido3.setNumeroControle("789");
        pedido3.setNome("Produto C");
        pedido3.setDataCadastro(new Date());
        pedido3.setQuantidade(10);
        pedido3.setValor(7.0);
        pedido3.setCodigoCliente(3);
        
        Pedido pedido4 = new Pedido();
        pedido4.setNumeroControle("789");
        pedido4.setNome("Produto D");
        pedido4.setDataCadastro(new Date());
        pedido4.setQuantidade(5);
        pedido4.setValor(10.0);
        pedido4.setCodigoCliente(4);

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido3);
        pedidos.add(pedido4);

        when(pedidoRepository.findByNumeroControle("789")).thenReturn(Optional.empty())
        												  .thenReturn(Optional.of(pedido3));

        ResponseEntity<String> response = pedidoService.receberPedidos(pedidos);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("[Pedido recebido. Número de controle: 789\n, Detalhes do erro: Número de controle já cadastrado para o pedido: 789\n]" , response.getBody());

        verify(pedidoRepository).save(pedido3);
        verify(pedidoRepository, never()).save(pedido4);
        
    }

    @Test
    public void testConsultarPedidosPorNumeroPedido() {
        String numeroPedido = "123";

        List<Pedido> pedidosMock = new ArrayList<>();
        Pedido pedidoMock = new Pedido();
        pedidoMock.setNumeroControle(numeroPedido);
        pedidosMock.add(pedidoMock);

        when(pedidoRepository.findAll()).thenReturn(pedidosMock);

        ResponseEntity<List<Pedido>> response = pedidoService.consultarPedidos(numeroPedido, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidosMock, response.getBody());
    }

    @Test
    public void testConsultarPedidosPorDataCadastro() throws ParseException {
        String dataCadastro = "2023-06-22";
        Date dataMock = new SimpleDateFormat("yyyy-MM-dd").parse(dataCadastro);

        List<Pedido> pedidosMock = new ArrayList<>();
        Pedido pedidoMock = new Pedido();
        pedidoMock.setDataCadastro(dataMock);
        pedidosMock.add(pedidoMock);

        when(pedidoRepository.findByDataCadastro(dataMock)).thenReturn(pedidosMock);

        ResponseEntity<List<Pedido>> response = pedidoService.consultarPedidos(null, dataCadastro);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedidosMock, response.getBody());
    }
}