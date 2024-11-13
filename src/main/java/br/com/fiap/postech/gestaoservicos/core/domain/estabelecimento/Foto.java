package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import lombok.Data;

@Data
public class Foto {

    private String url;

    public Foto() {
    }

    public Foto(String url) {
        this.url = url;
    }
}
