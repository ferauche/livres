# API LIVRES BAIXADA SANTISTA

## OBJETIVO

Criar métodos de cadastro e consulta através de requisições HTTP utilizando-se de Spring Boot e JPA.

## CAMINHOS

### GET

- api/consumidor

  - retorna todos os comsumidores cadastrados.

- api/consumidor/_cpf_

  - retorna o consumidor com o CPF especificado.

- api/precomunidade

  - retornar todas as pré-comunidade cadastradas.

- api/precomunidade/_id_

  - retornar a pré-comunidade com o id especificado.

- produtos/categorias

  - retornar todas as categorias de produto cadastradas.

### POST

- api/precomunidade

  - adiciona uma pré-comunidade;

  - parametros do body:

    - _nome - string_.

- api/consumidor

  - adiciona um consumidor;

  - parametros do body:

    - _cpf - string_;
    - _nome - string_;
    - _sobrenome - string_;
    - _senha - string_;
    - _precomunidade - long_;

  - _precomunidade tem que existir no banco_.

### PUT

- AQUI

### DELETE

- api/consumidor/_cpf_

  - Remove o registro do comsumidor especificado.
