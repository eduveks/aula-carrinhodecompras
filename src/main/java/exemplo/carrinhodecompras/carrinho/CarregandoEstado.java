package exemplo.carrinhodecompras.carrinho;

import java.util.List;

public class CarregandoEstado implements Estado {
    @Override
    public boolean proximo(Carrinho carrinho) {
        if (carrinho.regrasDeTransicao().test(Tipo.CANCELADO)) {
            carrinho.setEstado(new CanceladoEstado());
            return true;
        } else if (carrinho.regrasDeTransicao().test(Tipo.PAGO)) {
            carrinho.setEstado(new PagoEstado());
            return true;
        }
        return false;
    }

    @Override
    public boolean anterior(Carrinho carrinho) {
        if (carrinho.regrasDeTransicao().test(Tipo.VAZIO)) {
            carrinho.setEstado(new VazioEstado());
            return true;
        }
        return false;
    }

    @Override
    public List<Tipo> proximos() {
        return List.of(Tipo.CANCELADO, Tipo.PAGO);
    }

    @Override
    public List<Tipo> anteriores() {
        return List.of(Tipo.VAZIO);
    }

    @Override
    public Tipo getTipo() {
        return Tipo.CARREGANDO;
    }
}
