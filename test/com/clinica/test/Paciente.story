Narrativa:
Como uma Atendente
Desejo cadastrar no sistema um Paciente
De modo a tornar mais agil o acesso aos dados do cliente
					 
Scenario:  Cadastro de Paciente
Given que eu acesse o cadastrar atendente
When eu entrar com nome Tacyanne
And o rg 30738130
And o sexo Feminino
And o telefone 32246327
And o cpf 02986105580
And o endereco Avenida Pedro Calazans
And a data de nascimento 31121988
And o plano Particular
And o email tacyannebernadete.pimentel@souunit.com
Then o sistema responde Paciente Tacyanne criado com sucesso


