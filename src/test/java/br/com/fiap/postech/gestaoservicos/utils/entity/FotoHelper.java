package br.com.fiap.postech.gestaoservicos.utils.entity;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.Foto;

public class FotoHelper {
    public static Foto getFoto() {
        return new Foto("https://example.com/foto.jpg");
    }
}
