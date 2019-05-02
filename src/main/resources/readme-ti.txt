# Pesquisa de usuário por id: gerando exceção ObjectNotFoundException.
# Exclusão de usuário: gerando exceção DataIntegrityException.
# Salvando avaliação de usuário: gerando excecão AuthorizationException. 

# REGRAS DE NEGÓCIO (IMPORTANTES)
# -------------------------------
# SOMENTE ADMINISTRADORES PODEM EXCLUIR USUÁRIOS.
# UM USUÁRIO NÃO PODE AVALIAR ELE MESMO 					- implementado / testado.
# UM USUÁRIO NÃO PODE AVALIAR POR OUTRA PESSOA 				- implementado / testado.
# UM USUÁRIO NÃO PODE CONVIDAR ELE MESMO 					- implementado / testado.
# UM USUÁRIO NÃO PODE CONVIDAR POR OUTRA PESSOA 			- implementado / testado.
# UM USUÁRIO SÓ PODE CADASTRAR JOGOS DELE MESMO. 			- implementado / testado.
# UM USUÁRIO SÓ PODE EDITAR E ALTERAR O CADASTRO DELE MESMO - implementado.
# UM USUÁRIO SÓ PODE RETIRAR SEUS AMIGOS
# UM USUÁRIO SÓ PODE COLOCAR AMIGOS PARA ELE MESMO