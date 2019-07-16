1. Acrescentar as duas dependências no pom.xml.

2. Criar a classe SecurityConfig
	- verificar anotações (as duas primeiras)
	- verificar a herança (extends)
	- sobrescrever o método configure, pois nele implementaremos:
		- a autorização e o bloqueio dos endpoints;
		- autorização do cors e ataques csrf.
	- criar o @Bean para o cors, que será usado no método acima.
	- criar um método para encriptar uma senha.
		bCryptPasswordEncoder
3. Criar o enum Perfil.
	
4. Criar a classe UserSS
	- essa classe representará o usuário logado.
	- verificar implements
	- criar atributos conforme negócio (id, email, senha e perfis de acesso)
	
5. Criar a classe UserDetailServiceImpl 
	- responsável por buscar o usuário por email (identificador na tela de login)
	- verificar implements
	- o método deve retornar o UserSS.
	
6. Criar o método de sobrecarga (configure) dentro do SecurityConfig.
	- configure(AuthenticationManagerBuilder auth)
		- informar quem é o service: UserDetailService
		- informar quem é o encoder: bCryptPasswordEncoder
		
7. Criar a classe CredenciaisDTO

8. Definir as propriedades do TOKEN no application.properties.
	- jwt.secret - qualquer palavra
	- jwt.expiration - numero em milissegundos
	
9. Criar a classe JWTUtil
	- responsável por gerar um TOKEN a partir do email.
	
10. Criar a classe JWTAuthenticationFilter
	- responsável por verificar se o USUÁRIO é válido, conforme contratos do SpringSecurity.
	- utilizar o filtro no método configure da classe SecurityConfig.
	
11. Criar a classe JWTAuthorizationFilter
	- responsável por verificar se o TOKEN é válido.
	- utilizar o filtro no método configure da classe SecurityConfig.
	
12. Criar a classe AuthenticationService
	- responsável por retornar o usuário logado.
	- com este método, poderá realizar validações nas classes de serviços ou em qualquer ponto do sitema conforme regra de negócio.
	
	