package br.com.gabrielcaiodev.manytoone_onetomany.model.cliente;

import br.com.gabrielcaiodev.manytoone_onetomany.controller.error.ResourceNotFoundException;
import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.Pedido;
import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /** Salvar um Cliente e os Pedido(s) de uma so vez*/
    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        // Consistência: Atualize ambos os lados do relacionamento em associações bidirecionais
        cliente.getPedidos().forEach(pedido -> pedido.setCliente(cliente));
        return clienteRepository.save(cliente);
    }

    /** Adicionar um novo pedido ao cliente*/
    @Transactional
    public Cliente atualizarPedidos(Long clienteId, Pedido novoPedido) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        cliente.adicionarPedido(novoPedido);
        return clienteRepository.save(cliente);
    }

    /** Remover o pedido de um cliente especifico */
    @Transactional
    public void removerPedido(Long clienteId, Long pedidoId) {
        // Busca o cliente pelo ID
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        // Verifica se o pedido pertence ao cliente
        Pedido pedido =
                cliente.getPedidos().stream()
                .filter(p -> p.getId().equals(pedidoId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado ou não pertence ao cliente"));

        // Remove o pedido do cliente
        cliente.removerPedido(pedido);
        clienteRepository.save(cliente); // Salva o cliente sem o pedido
    }


    /** Listar todos os clientes com seus respectivos pedidos */
    @Transactional(readOnly = true)
    public List<Cliente> listarClientesComPedidos() {
        return clienteRepository.findAll();
    }
}

