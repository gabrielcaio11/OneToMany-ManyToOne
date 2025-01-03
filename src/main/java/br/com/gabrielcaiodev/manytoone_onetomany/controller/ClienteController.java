package br.com.gabrielcaiodev.manytoone_onetomany.controller;

import br.com.gabrielcaiodev.manytoone_onetomany.model.cliente.Cliente;
import br.com.gabrielcaiodev.manytoone_onetomany.model.cliente.ClienteResponse;
import br.com.gabrielcaiodev.manytoone_onetomany.model.cliente.ClienteService;
import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.Pedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.cadastrarCliente(cliente);
        var ClienteResponse  = new ClienteResponse(clienteSalvo);
        return ResponseEntity.ok(ClienteResponse);
    }

    @PutMapping("/{clienteId}/pedidos")
    public ResponseEntity<ClienteResponse> atualizarPedidos(@PathVariable Long clienteId, @RequestBody Pedido novoPedido) {
        Cliente clienteAtualizado = clienteService.atualizarPedidos(clienteId, novoPedido);
        var clienteResponse  = new ClienteResponse(clienteAtualizado);
        return ResponseEntity.ok(clienteResponse);
    }

    @DeleteMapping("/{clienteId}/pedidos/{pedidoId}")
    public ResponseEntity<Void> removerPedido(@PathVariable Long clienteId, @PathVariable Long pedidoId) {
        clienteService.removerPedido(clienteId, pedidoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientesComPedidos() {
        List<Cliente> clientes = clienteService.listarClientesComPedidos();
        List<ClienteResponse> clienteResponses = clientes.stream().map(ClienteResponse::new).toList();
        return ResponseEntity.ok(clienteResponses);
    }
}
