package br.com.fiap.postech.gestaoservicos.core.usecase;

import br.com.fiap.postech.gestaoservicos.core.domain.cliente.ClienteEntity;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.Pessoa;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaFisica;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.PessoaJuridica;
import br.com.fiap.postech.gestaoservicos.core.domain.pessoa.documento.TipoDocumento;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.ClienteRepository;

import java.time.LocalDate;

public class CadastrarClienteUseCaseImpl implements CadastrarClienteUseCase {

    private final ClienteRepository clienteRepository;

    public CadastrarClienteUseCaseImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteEntity executa(String nome,
                                 String email,
                                 String numeroDocumento,
                                 TipoDocumento tipoDocumento,
                                 LocalDate dataNascimento) {
        Pessoa pessoa = switch(tipoDocumento) {
            case CPF -> PessoaFisica.criar(nome, email, numeroDocumento, dataNascimento);
            case CNPJ -> PessoaJuridica.criar(nome,email, numeroDocumento, dataNascimento);
        };

        ClienteEntity cliente = new ClienteEntity(pessoa);
        return this.clienteRepository.salvarCliente(cliente);
    }

}
