package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;

import br.com.fiap.postech.gestaoservicos.core.domain.servico.ServicoEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.*;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.AgendamentoMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.AgendamentoHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toMap;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;


@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgendamentoDbRepositoryIT {

    @Autowired
    private AgendamentoDbRepository agendamentoDbRepository;
    
    @Autowired
    private AgendamentoMapper agendamentoMapper;

    @Test
    void deveSalvarAgendamento() {
        UUID id = UUID.randomUUID();

        AgendamentoDb agendamentoDb = this.agendamentoMapper.toAgendamentoDb(
                AgendamentoHelper.getAgendamento()
        );
        agendamentoDb.setId(id);

        AgendamentoDb agendamentoDbSalvo = this.agendamentoDbRepository.save(agendamentoDb);

        assertThat(agendamentoDbSalvo)
                .isNotNull();

        assertThat(agendamentoDbSalvo)
                .isNotNull()
                .extracting(AgendamentoDb::getId)
                .isEqualTo(id);
        
        assertThat(agendamentoDbSalvo)
                .isNotNull()
                .extracting(AgendamentoDb::getDataHora)
                .isEqualTo(agendamentoDb.getDataHora());
        assertThat(agendamentoDbSalvo)
                .extracting(AgendamentoDb::getDuracao)
                .isEqualTo(agendamentoDb.getDuracao());

        assertThat(agendamentoDbSalvo.getServico())
                .extracting(ServicoDbEntity::getCliente)
                .isEqualTo(agendamentoDb.getServico().getCliente());

        assertThat(agendamentoDbSalvo.getServico())
                .extracting(ServicoDbEntity::getEspecialidade)
                .isEqualTo(agendamentoDb.getServico().getEspecialidade());

        assertThat(agendamentoDbSalvo.getServico())
                .extracting(ServicoDbEntity::getEstabelecimento)
                .isEqualTo(agendamentoDb.getServico().getEstabelecimento());

        assertThat(agendamentoDbSalvo.getServico())
                .extracting(ServicoDbEntity::getProfissional)
                .isEqualTo(agendamentoDb.getServico().getProfissional());
        
    }

    @Test
    void deveBuscarAgendamentoPorId() {

        AgendamentoDb agendamentoDbSalvo = this.registrarAgendamento();
        UUID id = agendamentoDbSalvo.getId();

        Optional<AgendamentoDb> agendamentoDbBuscado =
                this.agendamentoDbRepository.findById(id);

        agendamentoDbBuscado.ifPresent(agendamentoDb -> {
            assertThat(agendamentoDbSalvo)
                    .isNotNull()
                    .extracting(AgendamentoDb::getId)
                    .isEqualTo(id);
            assertThat(agendamentoDbSalvo)
                    .isNotNull()
                    .extracting(AgendamentoDb::getDataHora)
                    .isEqualTo(agendamentoDb.getDataHora());
            assertThat(agendamentoDbSalvo)
                    .extracting(AgendamentoDb::getDuracao)
                    .isEqualTo(agendamentoDb.getDuracao());

            assertThat(agendamentoDbSalvo.getServico())
                    .extracting(ServicoDbEntity::getCliente)
                    .isEqualTo(agendamentoDb.getServico().getCliente());

            assertThat(agendamentoDbSalvo.getServico())
                    .extracting(ServicoDbEntity::getEspecialidade)
                    .isEqualTo(agendamentoDb.getServico().getEspecialidade());

            assertThat(agendamentoDbSalvo.getServico())
                    .extracting(ServicoDbEntity::getEstabelecimento)
                    .isEqualTo(agendamentoDb.getServico().getEstabelecimento());

            assertThat(agendamentoDbSalvo.getServico())
                    .extracting(ServicoDbEntity::getProfissional)
                    .isEqualTo(agendamentoDb.getServico().getProfissional());
        });
    }

    void deveBuscarAgendamentoPorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    @Test
    void deveDeletarAgendamento() {
        AgendamentoDb agendamentoDbSalvo = this.registrarAgendamento();
        UUID id = agendamentoDbSalvo.getId();

        this.agendamentoDbRepository.deleteById(id);
        Optional<AgendamentoDb> agendamentoBuscado = this.agendamentoDbRepository.findById(id);

        assertThat(agendamentoBuscado)
                .isEmpty();
    }

    private AgendamentoDb registrarAgendamento() {
        UUID id = UUID.randomUUID();

        AgendamentoDb agendamentoDb = this.agendamentoMapper.toAgendamentoDb(
                AgendamentoHelper.getAgendamento()
        );
        agendamentoDb.setId(id);

        return this.agendamentoDbRepository.save(agendamentoDb);

    }
}
