package exemplo.carrinhodecompras;

import exemplo.carrinhodecompras.carrinho.Carrinho;

import java.util.Optional;
import java.util.function.Function;

public class ValorFinal {
    public static Function<Carrinho, Optional<Float>> total() {
        return (c) ->
                c.getProdutos().stream()
                        .map(Produto::getPreco)
                        .reduce((preco1, preco2) -> preco1 + preco2);
    }
}
