# language: pt

Funcionalidade: Estabelecimento

  Cenário: Buscar Estabelecimento
    Dado que um estabelecimento está registrado
    Quando ao buscar um estabelecimento
    Então o estabelecimento é apresentado

  Cenário: Buscar Estabelecimento Dinâmico
    Dado que um estabelecimento está registrado
    Quando ao buscar um estabelecimentos por filtro
    Então estabelecimentos são apresentados

  Cenário: Cadastrar Estabelecimento
    Quando submeter um novo estabelecimento
    Então o estabelecimento é criado com sucesso