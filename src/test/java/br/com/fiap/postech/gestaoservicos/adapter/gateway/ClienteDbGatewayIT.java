package br.com.fiap.postech.gestaoservicos.adapter.gateway;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.Documento;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.entity.ClienteDbEntity;
import br.com.fiap.postech.gestaoservicos.infrastructure.db.mongodb.repository.ClienteDbRepository;
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

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteDbGatewayIT {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void deveCriarCliente() {
        ClienteEntity cliente = ClienteHelper.getCliente();
        cliente.setId(UUID.randomUUID());

        ClienteEntity clienteEntitySalvo = this.clienteRepository.salvarCliente(cliente);

        assertThat(clienteEntitySalvo)
                .isNotNull()
                .isInstanceOf(ClienteEntity.class);
        assertThat(clienteEntitySalvo)
                .extracting(ClienteEntity::getId)
                .isNotNull()
                .isEqualTo(cliente.getId());
        assertThat(clienteEntitySalvo.getPessoa())
                .extracting(Pessoa::getNome)
                .isNotNull()
                .isEqualTo(cliente.getPessoa().getNome());
        assertThat(clienteEntitySalvo.getPessoa())
                .extracting(Pessoa::getDataNascimento)
                .isNotNull()
                .isEqualTo(cliente.getPessoa().getDataNascimento());
        assertThat(clienteEntitySalvo.getPessoa().getDocumento())
                .extracting(Documento::getTipoDocumento)
                .isNotNull()
                .isEqualTo(cliente.getPessoa().getDocumento().getTipoDocumento());
        assertThat(clienteEntitySalvo.getPessoa().getDocumento())
                .extracting(Documento::getNumeroDocumento)
                .isNotNull()
                .isEqualTo(cliente.getPessoa().getDocumento().getNumeroDocumento());

    }

    void deveExecutar_GeraExcessaoSe() {}

}
