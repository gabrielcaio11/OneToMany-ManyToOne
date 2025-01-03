package br.com.gabrielcaiodev.manytoone_onetomany.controller;

import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.Pedido;
import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.PedidoResponse;
import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<PedidoResponse> atualizarPedido(@PathVariable Long pedidoId, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.atualizarPedido(pedidoId, pedidoAtualizado);
        PedidoResponse pedidoResponse = new PedidoResponse(pedido);
        return ResponseEntity.ok(pedidoResponse);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoResponse> buscarPedidoPorId(@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
        PedidoResponse pedidoResponse = new PedidoResponse(pedido);
        return ResponseEntity.ok(pedidoResponse);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponse>> listarPedidosPorCliente(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.listarPedidosPorCliente(clienteId);
        List<PedidoResponse> pedidosResponses = pedidos.stream().map(PedidoResponse::new).toList();
        return ResponseEntity.ok(pedidosResponses);
    }
}

