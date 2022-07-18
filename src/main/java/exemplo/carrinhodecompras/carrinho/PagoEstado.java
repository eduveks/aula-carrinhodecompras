package exemplo.carrinhodecompras.carrinho;

import java.util.List;

public class PagoEstado implements Estado {
    @Override
    public boolean proximo(Carrinho carrinho) {
        return false;
    }

    @Override
    public boolean anterior(Carrinho carrinho) {
        carrinho.setEstado(new CarregandoEstado());
        return true;
    }

    @Override
    public List<Tipo> proximos() {
        return List.of();
    }

    @Override
    public List<Tipo> anteriores() {
        return List.of(Tipo.CARREGANDO);
    }

    @Override
    public Tipo getTipo() {
        return Tipo.PAGO;
    }
}
