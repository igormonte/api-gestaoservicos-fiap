package br.com.fiap.postech.gestaoservicos.infrastructure.config;

import br.com.fiap.postech.gestaoservicos.core.usecase.*;
import br.com.fiap.postech.gestaoservicos.core.usecase.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UsecaseConfig {

    @Bean
    BuscarEstabelecimentoUseCase getBuscarEstabelecimentoUseCase(
            EstabelecimentoRepository estabelecimentoRepository){
        return new BuscarEstabelecimentoUseCaseImpl(estabelecimentoRepository);

    }
    @Bean
    CadastrarClienteUseCase getCadastrarClienteUseCase(
            ClienteRepository clienteRepository){
        return new CadastrarClienteUseCaseImpl(clienteRepository);

    }
    @Bean
    CadastrarEstabelecimentoUseCase getCadastrarEstabelecimentoUseCase(
            EstabelecimentoRepository estabelecimentoRepository,
            ProfissionalRepository profissionalRepository){
        return new CadastrarEstabelecimentoUseCaseImpl(
                estabelecimentoRepository, profissionalRepository);

    }
    @Bean
    CadastrarPerfilProfissionalUseCase getCadastrarPerfilProfissionalUseCase(
            ProfissionalRepository profissionalRepository,
            EspecialidadeRepository especialidadeRepository
    ){
        return new CadastrarPerfilProfissionalUseCaseImpl(profissionalRepository, especialidadeRepository);

    }

    @Bean
    RealizarAgendamentoUseCase getRealizarAgendamentoUseCase(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository,
            EstabelecimentoRepository estabelecimentoRepository
    ) {
        return new RealizarAgendamentoUseCaseImpl(
                agendamentoRepository,
                clienteRepository,
                estabelecimentoRepository);
    }

    @Bean
    CadastrarEspecialidadeUseCase getCadastrarEspecialidadeUseCase(
            EspecialidadeRepository especialidadeRepository
    ) {
        return new CadastrarEspecialidadeUseCaseImpl(especialidadeRepository);
    }

    @Bean
    AssociarEspecialidadeProfissionalUseCase getAssociarEspecialidadeProfissionalUseCase(
            EspecialidadeRepository especialidadeRepository,
            ProfissionalRepository profissionalRepository
    ) {
        return new AssociarEspecialidadeProfissionalUseCaseImpl(
                especialidadeRepository,
                profissionalRepository);
    }

    @Bean
    BuscarClienteUseCase getBuscarClienteUseCase(
            ClienteRepository clienteRepository
    ) {
        return new BuscarClienteUseCaseImpl(clienteRepository);
    }

    @Bean
    AdicionarProfissionalEstabelecimentoUseCase getAdicionarProfissionalEstabelecimentoUseCase(
            EstabelecimentoRepository estabelecimentoRepository,
            ProfissionalRepository profissionalRepository
    ) {
        return new AdicionarProfissionalEstabelecimentoUseCaseImpl(
                estabelecimentoRepository,
                profissionalRepository);
    }

    @Bean
    BuscaAgendamentoUseCase getBuscarAgendamentoUseCase(
            AgendamentoRepository agendamentoRepository
    ) {
        return new BuscaAgendamentoUseCaseImpl(agendamentoRepository);
    }

    @Bean
    BuscarProfissionalUseCase getBuscarProfissionalUseCase(
            ProfissionalRepository profissionalRepository
    ) {
        return new BuscarProfissionalUseCaseImpl(profissionalRepository);
    }

    @Bean
    RealizarAvaliacaoUseCase getRealizarAvaliacaoUseCase(
            AgendamentoRepository agendamentoRepository,
            ClienteRepository clienteRepository
    ) {
        return new RealizarAvaliacaoUseCaseImpl(agendamentoRepository, clienteRepository);
    }

}
