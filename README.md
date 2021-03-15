# SistemadeCadastro
Este projeto foi desenvolvido com JSF primefaces, integrado com o banco de dados PGAdmin(Postgresql), e com o Maven. 
O funcional deste projeto consiste em uma tela de login, uma de cadastro de usuário e uma de de consulta, 
na tela de consulta é possível além de ver todos os usuários cadastrados, tem as opções de editar e deletar um determinado usuário por vez.

É necessário ir no arquivo de persistence.xml e trocar as credenciais do banco de dados, caso necessário, 
ou acessar o PGAdmin com:
host: localhost; 
username: postgres; 
password: 1234; 
port: 5432; 
Database name: lportal;

Para acessar o login inicialmente ultilize o login: admin e a senha: 1234 
Após isso, poderá cadastrar os usuários um por vez e para testar o login com o usuário cadastrado, 
clique em logout no canto superior direito da tela e faça o login novamente usando o email e senha que foram cadastrados.


