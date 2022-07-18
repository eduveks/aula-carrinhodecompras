package exemplo.carrinhodecompras;

import exemplo.carrinhodecompras.carrinho.Carrinho;

public class App {

    public static void main(String[] args) {
        var produtos = ProdutoFabrica.lista(10);

        var carrinho = new Carrinho(
                Cliente.builder()
                        .nome("Nome do Cliente...")
                        .build()
        );

        carrinho.quandoFinaliza(() ->
            ValorFinal.total().apply(carrinho)
                    .ifPresent((f) -> System.out.printf("Valor Total: %.2f %n", f))
        );

        Compras.simula(produtos).accept(carrinho);

        carrinho.cancela();

        carrinho.voltaEstado();

        carrinho.voltaEstado();

        //carrinho.cancela();

        //carrinho.finaliza();
    }
}
