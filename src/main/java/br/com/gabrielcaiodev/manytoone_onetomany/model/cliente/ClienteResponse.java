package br.com.gabrielcaiodev.manytoone_onetomany.model.cliente;

import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.PedidoResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteResponse {

    private Long id;

    private String nome;

    private List<PedidoResponse> pedidos = new ArrayList<>();

    public ClienteResponse(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.pedidos = cliente.getPedidos() == null ? null :
                cliente
                        .getPedidos()
                        .stream()
                        .map(PedidoResponse::new)
                        .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PedidoResponse> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoResponse> pedidos) {
        this.pedidos = pedidos;
    }
}
