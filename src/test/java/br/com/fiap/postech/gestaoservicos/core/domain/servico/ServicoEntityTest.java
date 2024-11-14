package br.com.fiap.postech.gestaoservicos.core.domain.servico;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.cliente.exception.ClienteNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.EstabelecimentoEntityTest;
import br.com.fiap.postech.gestaoservicos.core.domain.estabelecimento.exception.EstabelecimentoNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.Especialidade;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.EspecialidadeTest;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.ProfissionalEntityTest;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.EspecialidadeNaoPodeSerNulaException;
import br.com.fiap.postech.gestaoservicos.core.domain.profissional.exception.ProfissionalNaoPodeSerNuloException;
import br.com.fiap.postech.gestaoservicos.utils.entity.ClienteHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EspecialidadeHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.EstabelecimentoHelper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ProfissionalHelper;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

public class ServicoEntityTest {

    @Test
    void deveCriarServico() {

        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        ClienteEntity cliente = ClienteHelper.getCliente();
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();

        ServicoEntity servico = new ServicoEntity(
                estabelecimento,
                profissional,
                cliente,
                especialidade
        );

        assertThat(servico.getEstabelecimento()).isEqualTo(estabelecimento);
        assertThat(servico.getProfissional()).isEqualTo(profissional);
        assertThat(servico.getCliente()).isEqualTo(cliente);
        assertThat(servico.getEspecialidade()).isEqualTo(especialidade);
    }

    @Test
    void deveCriarServico_GeraExcessaoSeEstabelecimentoNulo() {
        EstabelecimentoEntity estabelecimento = null;
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        ClienteEntity cliente = ClienteHelper.getCliente();
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();

        assertThatThrownBy(() ->
                new ServicoEntity(
                        estabelecimento,
                        profissional,
                        cliente,
                        especialidade)
        ).isInstanceOf(EstabelecimentoNaoPodeSerNuloException.class);

    }
    @Test
    void deveCriarServico_GeraExcessaoSeProfissionalNulo() {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        ProfissionalEntity profissional = null;
        ClienteEntity cliente = ClienteHelper.getCliente();
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();

        assertThatThrownBy(() ->
            new ServicoEntity(
                estabelecimento,
                profissional,
                cliente,
                especialidade)
        ).isInstanceOf(ProfissionalNaoPodeSerNuloException.class);

    }
    @Test
    void deveCriarServico_GeraExcessaoSeClienteNulo() {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        ClienteEntity cliente = null;
        Especialidade especialidade = EspecialidadeHelper.getEspecialidade();

        assertThatThrownBy(() ->
                new ServicoEntity(
                        estabelecimento,
                        profissional,
                        cliente,
                        especialidade)
        ).isInstanceOf(ClienteNaoPodeSerNuloException.class);
    }
    @Test
    void deveCriarServico_GeraExcessaoSeEspecialidadeNula() {
        EstabelecimentoEntity estabelecimento = EstabelecimentoHelper.getEstabelecimento();
        ProfissionalEntity profissional = ProfissionalHelper.getProfissional();
        ClienteEntity cliente = ClienteHelper.getCliente();
        Especialidade especialidade = null;

        assertThatThrownBy(() ->
                new ServicoEntity(
                        estabelecimento,
                        profissional,
                        cliente,
                        especialidade)
        ).isInstanceOf(EspecialidadeNaoPodeSerNulaException.class);
    }

}
