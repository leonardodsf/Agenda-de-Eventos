# Aplicativo Agenda de Eventos

Aplicativo feito para agendamentos de eventos. Onde pode-se criar um evento profissional, workshops, eventos locais entre outros.

## Desenvolvimento

**Início**

Este aplicativo inicia em uma Splash(tela de abertura), logo após com uma Intent ele vai para tela de login, onde se tem as opções de "realizar o login", "Cadastrar-se" e "Esqueceu a senha". 

![Splash, Login, Register](https://user-images.githubusercontent.com/54339869/71788454-1d20a280-3001-11ea-8081-75b78e53754e.png)

Caso esqueça a senha, funciona da seguinte forma:

Coloca um e-mail que já exista no banco de dados, ou seja, um e-mail cadastrado. Então clica no esqueceu senha, ele irá encaminhar um e-mail automático do Firebase direto para sua caixa de entrada. Neste e-mail é fornecido um link para realizar a troca de senha no seu navegador.

**Após login**

Existem 3 opções de tela pro usuário clicar.

**Tela "Home"->** Quando efetuar o login, entra na tela de início do aplicativo, onde se tem um ArrayList, com todos os eventos criados que estão cadastrados no Firebase(Realtime Database).

![ScreenHome](https://user-images.githubusercontent.com/54339869/71791211-e5baf180-3012-11ea-9acb-461a9b5701fb.png)



**Tela "Add"->** Podendo adicionar um evento, cadastrando em tempo real no banco de dados. Mas só pode adicionar o evento se preencher todos os dados(Foi realizado uma verificação).  

![ScreenAdd](https://user-images.githubusercontent.com/54339869/71791254-169b2680-3013-11ea-9819-a2bf1a989463.png)


**Tela "Account" ->** Aparece o e-mail do usuário que está "logado" ou "conectado". E também a opção de "Logoff", que é basicamente sair da conta atual e voltar para tela de login.

![ScreenAccount](https://user-images.githubusercontent.com/54339869/71791270-316d9b00-3013-11ea-9d72-31c33fa39c84.png)



### Feito com

**Programa usado:**

- Android Studio 3.5.

**Linguagem usada:**

- Java nativo, XML Layout. 

**Banco de Dados Usado:**

- Firebase Realtime Database.

#### Autores

- Leonardo Flores - *Trabalho completo*.

##### Licença

Este projeto está licenciado sob a licença MIT - consulte o arquivo [License](https://github.com/mecnosh/Agenda-de-Eventos/blob/master/LICENSE) para obter detalhes

