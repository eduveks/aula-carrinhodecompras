package exemplo.carrinhodecompras.carrinho;

import exemplo.carrinhodecompras.Cliente;
import exemplo.carrinhodecompras.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Carrinho {
    private Cliente cliente;
    private List<Produto> produtos = new ArrayList<>();
    private Runnable finalizou;
    private Estado estado = new VazioEstado();
    private boolean cancelado = false;
    private boolean finalizado = false;

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean avancaEstado() {
        return estado.proximo(this);
    }

    public boolean voltaEstado() {
        boolean resultado = estado.anterior(this);
        if (!resultado) {
            System.out.println("!!! Não foi possível voltar." + getEstado().getTipo());
        } else {
            System.out.println("!!! Foi possível voltar." + getEstado().getTipo());
        }
        return resultado;
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public void add(Produto produto) {
        System.out.printf("+ %-20s%.2f%n", produto.getNome(), produto.getPreco());
        produtos.add(produto);

        if (estado.getTipo() == Estado.Tipo.VAZIO) {
            avancaEstado();
        }
    }

    public void quandoFinaliza(Runnable finalizou) {
        this.finalizou = finalizou;
    }

    public void cancela() {
        System.out.println("-- CANCELA");
        cancelado = true;
        avancaEstado();
    }

    public void finaliza() {
        finalizado = true;
        if (avancaEstado()) {
            if (estado.getTipo() == Estado.Tipo.PAGO) {
                System.out.println("@@@ Finalizado e PAGO!!!");
                finalizou.run();
            } else if (estado.getTipo() == Estado.Tipo.CANCELADO) {
                System.out.println("@@@ Finalizado mas CANCELADO!!!");
            } else if (estado.getTipo() == Estado.Tipo.CARREGANDO) {
                System.out.println("@@@ Finalizado a partir do VAZIO!");
            }
        }
    }

    protected void setEstado(Estado estado) {
        System.out.println("NOVO ESTADO :: "+ estado.getTipo().name());
        this.estado = estado;
    }

    public Estado getEstado() {
        return estado;
    }

    public Predicate<Estado.Tipo> regrasDeTransicao() {
        Predicate<Estado.Tipo> validacao = (proximoEstadoTipo) -> {
            System.out.println("CANCELADO proximoEstadoTipo == "+ proximoEstadoTipo);
            System.out.println("CANCELADO estadoAtualTipo == "+ estado.getTipo());
            return estado.getTipo() == Estado.Tipo.CARREGANDO
                    && cancelado
                    && proximoEstadoTipo == Estado.Tipo.CANCELADO;
        };
        validacao = validacao.or(
                (proximoEstadoTipo) -> {
                    System.out.println("FINALIZADO proximoEstadoTipo == "+ proximoEstadoTipo);
                    System.out.println("FINALIZADO estadoAtualTipo == "+ estado.getTipo());
                    return estado.getTipo() == Estado.Tipo.CARREGANDO
                            && finalizado
                            && proximoEstadoTipo == Estado.Tipo.PAGO;
                });
        validacao = validacao.or(
                (proximoEstadoTipo) -> {
                    return estado.getTipo() == Estado.Tipo.CARREGANDO
                            && proximoEstadoTipo == Estado.Tipo.VAZIO
                            && !produtos.isEmpty();
                }
        );
        return validacao;
    }
}
