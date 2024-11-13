# language: pt

Funcionalidade: Agendamento

  Cenário: Realizar Agendamento
    Dado que o agentamento tenha um cliente registrado
    E o agentamento tenha que um especialidade esteja registrada
    E o agentamento tenha que um profissional esteja registrado
    E o agentamento tenha que um estabelecimento está registrado
    Quando submeter um novo agendamento
    Então o agendamento é criado com sucesso