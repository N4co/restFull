# full-api
# api-rest-full-dadostrafegados

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Aplicação sendo iniciada na versão Java 18 
Spring Framework na versão 3.0 (RC1)
POM.XML (MAVEM)

Aplicação simples que retorna um mock para guardar o estado dos dados.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Classe Model

A Responsabilidade da classe Person é criar um objeto Model e setar valores ao seus atributos e variáveis,
informar um construtor default, getter e setters e o hashCode. No contexto desse aplicativo de testes a motivação dessa classe é criar um objeto pessoa e trafegar e
enviar dados via webServices n formato JSON.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Classe Service

A responsabilidade da classe PersonService incluir a anotação @Service, é reconhecer classe Person e instanciar o objeto Person para setar os valores e a obedecer a 
regra de negócio, o objeto muda de estado para uma lista e  criando um ServiçoMock(serviço alternativo temporário para suprir necessidades primárias) serviço que seta 
uma lista de usuários "mocKados" interessante foi que o ID do Person é  com a classe incrementAndGet().  logo após chamar a condição ternária FOR que 
seta int id List<Person>  .    

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Classe Controller

A responsabilidade da classe PersonController com anotação @RestController é controlar as requisições feita pelo client fornecendo os producers e consumes gerados com 
end-point, essa classe sabe lidar com a injeção de dependências do @Service e identifica qual caminho vai ser fornecido e qual operação do CRUD(Create, Update, Delete)
está sendo solicitado.   
