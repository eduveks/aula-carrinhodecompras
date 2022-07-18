package exemplo.carrinhodecompras.carrinho;

import java.util.List;

public class VazioEstado implements Estado {
    @Override
    public boolean proximo(Carrinho carrinho) {
        carrinho.setEstado(new CarregandoEstado());
        return true;
    }

    @Override
    public boolean anterior(Carrinho carrinho) {
        return false;
    }

    @Override
    public List<Tipo> proximos() {
        return List.of(Tipo.CARREGANDO);
    }

    @Override
    public List<Tipo> anteriores() {
        return List.of();
    }

    @Override
    public Tipo getTipo() {
        return Tipo.VAZIO;
    }
}
