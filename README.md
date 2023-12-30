# CRIANDO A API DE TESTE

## TESTANDO A API COM O BANCO pgAdmin

![select do administrador](https://github.com/Git-LeAmaral/gerenciador-tarefas/assets/101153930/0fa8fc2b-a5f7-4779-827b-8a87492c0e30)

### Criando usuário admistrador: 
    Aqui como podemos ver estamos fazendo um select do usuario criado como administrador, onde ele possui o login de usuário e admin.

## Realizando Login e gerando token de autorização

### ![Login no postman](https://github.com/Git-LeAmaral/gerenciador-tarefas/assets/101153930/741ba9a0-8e7a-4572-b4b4-b2946aeb3ae4)

    Nesta imagem realizamos o login com o usuário admin e assim podemos ver o estatus 200 que o login foi efetuado com sucesso.

  ![Token de autorização](https://github.com/Git-LeAmaral/gerenciador-tarefas/assets/101153930/02416005-1b0b-4902-9b3d-692d8ba23ad6)

  Aqui podemos ver que assim que logamos foi gerado um token de autorização ter o acesso a determinadas tarefas da aplicação como,
  criar um usuario novo, atualizar, e excluir.

  ![Criando usuario](https://github.com/Git-LeAmaral/gerenciador-tarefas/assets/101153930/e677d6c8-fd95-401d-8535-122836c6c37a)

  Aqui podemos ver o body onde inserimos as informações do usuário como login, senha e o tipo da regra que é Usuario, ou seja,
  esse perfil não tem toda autorização que o admin tem.

  ![retorno no banco](https://github.com/Git-LeAmaral/gerenciador-tarefas/assets/101153930/e9257003-8529-4537-bd9d-7f7978236ac2)

  Podemos ver essa atualização no banco que agora existem dois perfis, e o interessante que quando criamos no postman podemos ver
  a senha que criamos junto do usuário, quando vamos para o banco esta informação já esta criptografada.

