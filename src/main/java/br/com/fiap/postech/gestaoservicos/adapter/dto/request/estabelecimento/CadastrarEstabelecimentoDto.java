package br.com.fiap.postech.gestaoservicos.adapter.dto.request.estabelecimento;

import java.util.List;

public record CadastrarEstabelecimentoDto (
    String nome,
    EnderecoDto endereco,
    List<ProfissionalDto> profissional,
    List<FuncionamentoDto> funcionamento,
    List<String> foto
){



}
