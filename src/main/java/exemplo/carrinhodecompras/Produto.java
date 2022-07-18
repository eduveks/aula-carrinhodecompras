package exemplo.carrinhodecompras;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produto {
    private String codigo;
    private String nome;
    private float preco;
    private int quantidade;
}
