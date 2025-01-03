package br.com.gabrielcaiodev.manytoone_onetomany.model.pedido;

import br.com.gabrielcaiodev.manytoone_onetomany.controller.error.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    /** Atualizar um pedido especifico, verificar o efeito em cascata*/
    @Transactional
    public Pedido atualizarPedido(Long pedidoId, Pedido pedidoAtualizado) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        pedido.setDescricao(pedidoAtualizado.getDescricao());
        return pedidoRepository.save(pedido);
    }

    /**Buscar pedido especifico com base no id*/
    @Transactional(readOnly = true)
    public Pedido buscarPedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
    }

    /** Listar os pedidos de um cliente especifico */
    @Transactional(readOnly = true)
    public List<Pedido> listarPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }
}
