package exemplo.carrinhodecompras;

import exemplo.carrinhodecompras.carrinho.Carrinho;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Compras {

    public static Consumer<Carrinho> simula(Supplier<List<Produto>> produtos) {
        Random random = new Random();

        int max = random.nextInt(10, 20);

        Consumer<Carrinho> listaDeCompras = (carrinho) -> {};
        for (int i = 0; i < max; i++) {
            System.out.println("Construindo Lista de Compras..."+ i);
            listaDeCompras = listaDeCompras.andThen(c -> {
                System.out.println("Adicionando produto...");
                c.add(produtos.get().get(random.nextInt(0, produtos.get().size())));
            });
        }

        return listaDeCompras;
    }

}
