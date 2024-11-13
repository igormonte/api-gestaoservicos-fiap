package br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository;

import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ProfissionalDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.mapper.ClienteMapper;
import br.com.fiap.postech.gestaoservicos.utils.entity.ClienteHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteDbRepositoryIT {

    @Autowired
    private ClienteDbRepository clienteDbRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Test
    void deveSalvarCliente() {
        UUID id = UUID.randomUUID();

        ClienteDbEntity clienteDb = this.clienteMapper.toClienteDbEntity(
                ClienteHelper.getCliente()
        );
        clienteDb.setId(id);

        ClienteDbEntity clienteDbSalvo = this.clienteDbRepository.save(clienteDb);

        assertThat(clienteDbSalvo)
                .isNotNull()
                .extracting(ClienteDbEntity::getId)
                .isEqualTo(id);

        assertThat(clienteDbSalvo)
                .isNotNull()
                .extracting(ClienteDbEntity::getNome)
                .isEqualTo(clienteDb.getNome());

        assertThat(clienteDbSalvo)
                .isNotNull()
                .extracting(ClienteDbEntity::getEmail)
                .isEqualTo(clienteDb.getEmail());

        assertThat(clienteDbSalvo)
                .isNotNull()
                .extracting(ClienteDbEntity::getTipoDocumento)
                .isEqualTo(clienteDb.getTipoDocumento());

        assertThat(clienteDbSalvo)
                .isNotNull()
                .extracting(ClienteDbEntity::getDataNascimento)
                .isEqualTo(clienteDb.getDataNascimento());

        assertThat(clienteDbSalvo)
                .isNotNull()
                .extracting(ClienteDbEntity::getNumeroDocumento)
                .isEqualTo(clienteDb.getNumeroDocumento());
    }

    @Test
    void deveBuscarClientePorId() {


        ClienteDbEntity clienteDbSalvo = this.registrarCliente();
        UUID id = clienteDbSalvo.getId();

        Optional<ClienteDbEntity> clienteDbBuscado =
                this.clienteDbRepository.findById(id);

        clienteDbBuscado.ifPresent(clienteDb -> {
            assertThat(clienteDb)
                    .isNotNull()
                    .extracting(ClienteDbEntity::getId)
                    .isEqualTo(id);

            assertThat(clienteDb)
                    .isNotNull()
                    .extracting(ClienteDbEntity::getNome)
                    .isEqualTo(clienteDbSalvo.getNome());

            assertThat(clienteDb)
                    .isNotNull()
                    .extracting(ClienteDbEntity::getEmail)
                    .isEqualTo(clienteDbSalvo.getEmail());

            assertThat(clienteDb)
                    .isNotNull()
                    .extracting(ClienteDbEntity::getTipoDocumento)
                    .isEqualTo(clienteDbSalvo.getTipoDocumento());

            assertThat(clienteDb)
                    .isNotNull()
                    .extracting(ClienteDbEntity::getDataNascimento)
                    .isEqualTo(clienteDbSalvo.getDataNascimento());

            assertThat(clienteDb)
                    .isNotNull()
                    .extracting(ClienteDbEntity::getNumeroDocumento)
                    .isEqualTo(clienteDbSalvo.getNumeroDocumento());
        });
    }

    void deveBuscarClientePorNumeroDocumento() {
        fail("Teste não implementado");}

    void deveListarProfissionais() {
        fail("Teste não implementado");
    }

    void deveAtualizarCliente() {
        fail("Teste não implementado");
    }

    @Test
    void deveDeletarCliente() {

        ClienteDbEntity clienteDbSalvo = this.registrarCliente();
        UUID id = clienteDbSalvo.getId();

        this.clienteDbRepository.deleteById(id);
        Optional<ClienteDbEntity> clienteBuscado = this.clienteDbRepository.findById(id);

        assertThat(clienteBuscado)
                .isEmpty();
    }

    private ClienteDbEntity registrarCliente(){

        UUID id = UUID.randomUUID();

        ClienteDbEntity clienteDb = this.clienteMapper.toClienteDbEntity(
                ClienteHelper.getCliente()
        );
        clienteDb.setId(id);

        return this.clienteDbRepository.save(clienteDb);
        
    }

}
