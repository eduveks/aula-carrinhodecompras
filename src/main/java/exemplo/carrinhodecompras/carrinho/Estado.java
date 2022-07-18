package exemplo.carrinhodecompras.carrinho;

import java.util.List;

public interface Estado {
    enum Tipo {
        VAZIO,
        CARREGANDO,
        PAGO,
        CANCELADO;
    }

    boolean proximo(Carrinho carrinho);
    boolean anterior(Carrinho carrinho);

    List<Tipo> proximos();
    List<Tipo> anteriores();

    Tipo getTipo();
}
