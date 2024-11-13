package br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento;

import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EnderecoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.NomeInvalidoException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.funcionamento.Funcionamento;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class EstabelecimentoEntity {
    private UUID id;
    private String nome;
    private Endereco endereco;
    private List<ProfissionalEntity> profissional;
    private List<Funcionamento> funcionamento;
    private List<Foto> foto;

    public EstabelecimentoEntity(
        String nome,
        Endereco endereco,
        List<ProfissionalEntity> profissional,
        List<Funcionamento> funcionamento,
        List<Foto> foto
    ) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }

        if(endereco == null) {
            throw new EnderecoNaoPodeSerNuloException();
        }

        this.nome = nome;
        this.endereco = endereco;
        this.profissional = profissional;
        this.funcionamento = funcionamento;
        this.foto = foto;
    }

    public Boolean isDataHoraDentroPeriodoFuncionamento(LocalDateTime dataHora) {

        Optional<Funcionamento> periodoFuncionamento =
                this.funcionamento
                    .stream()
                    .filter(f->
                        f.getDiaSemana().equals(DiaSemana.deDayOfWeek(dataHora.getDayOfWeek()))
                    ).findAny();

        return periodoFuncionamento.map(funcionamento -> funcionamento.getPeriodoFuncionamento()
                .stream()
                .anyMatch(periodo -> {
                    return (dataHora.toLocalTime().isAfter(periodo.getHoraInicial())
                            || dataHora.toLocalTime().equals(periodo.getHoraInicial())
                    ) && dataHora.toLocalTime().isBefore(periodo.getHoraFinal());
                })).orElse(false);

    }

    public void adicionarProfissional(ProfissionalEntity profissional) {

        if(profissional == null) {
            throw new IllegalArgumentException("Profissional não pode ser nulo");
        }

        if(this.profissional == null) {
            this.profissional = List.of(profissional);
            return;
        }

        this.profissional.add(profissional);

    }
}
