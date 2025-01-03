package br.com.gabrielcaiodev.manytoone_onetomany.model.pedido;

public class PedidoResponse {
    private Long id;
    private String descricao;
    private Long cliente_id;

    public PedidoResponse(Pedido pedido) {
        this.id = pedido.getId();
        this.descricao = pedido.getDescricao();
        this.cliente_id = pedido.getCliente().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }
}
