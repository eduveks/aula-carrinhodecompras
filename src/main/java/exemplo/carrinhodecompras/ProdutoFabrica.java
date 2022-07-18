package exemplo.carrinhodecompras;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ProdutoFabrica {
    private static Random random = new Random();

    private static List<InformacaoBase> informacoesBases = List.of(
            InformacaoBase.builder()
                    .codigo("violao")
                    .nome("Violão")
                    .preco(100)
                    .build(),
            InformacaoBase.builder()
                    .codigo("bateria")
                    .nome("Bateria")
                    .preco(200)
                    .build(),
            InformacaoBase.builder()
                    .codigo("tambor")
                    .nome("Tambor")
                    .preco(50)
                    .build(),
            InformacaoBase.builder()
                    .codigo("teclado")
                    .nome("Teclado")
                    .preco(200)
                    .build(),
            InformacaoBase.builder()
                    .codigo("guitarra")
                    .nome("Guitarra")
                    .preco(300)
                    .build()
    );

    public static Supplier<List<Produto>> lista(int numero) {
        return () -> new ArrayList<>() {{
            IntStream.range(0, numero).forEach((i) -> {
                var informacaoBase = getInformacaoBase();
                add(
                        Produto.builder()
                                .nome(informacaoBase.getNome() +" "+ (i + 1))
                                .codigo(informacaoBase.getCodigo() +"-"+ (i + 1))
                                .quantidade(random.nextInt(1, 100))
                                .preco(informacaoBase.getPreco())
                                .build()
                );
            });
        }};
    }

    private static InformacaoBase getInformacaoBase() {
        return informacoesBases.get(random.nextInt(0, informacoesBases.size()));
    }

    @Data
    @Builder
    private static class InformacaoBase {
        private String codigo;
        private String nome;
        private float preco;
    }
}
