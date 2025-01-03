package br.com.gabrielcaiodev.manytoone_onetomany.model.cliente;

import br.com.gabrielcaiodev.manytoone_onetomany.model.pedido.Pedido;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public void adicionarPedido(Pedido pedido) {
        pedido.setCliente(this);
        pedidos.add(pedido);
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setCliente(null);
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

