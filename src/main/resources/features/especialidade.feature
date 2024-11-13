# language: pt

Funcionalidade: Especialidade

  Cenário: Cadastrar Especialidade
    Quando submeter uma nova especialidade
    Então a especialidade é criada com sucesso

  Cenário: Associar Especialidade
    Dado que uma especialidade está registrada
    E que um profissional está registrado
    Quando submeter a associacao da especialidade ao profissional
    Então a especialidade é associada com sucesso