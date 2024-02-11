# Repositórios (Parte 1)

## 1. Repositórios
O Spring Data como já abordado, fornece três interfaces principais que são: `Repository`, `CrudRepository` e `PagingAndSortingRepository`.

Estas interfaces são a base para o projeto Spring Data JPA, sendo assim, é importante conhecê-las um pouco mais a fundo.

Nesta relação de interfaces, Repository é tida como o ponto inicial, ou a interface base que tem o objetivo de formar a infraestrutura dos repositórios no Spring Data. Já CrudRepository, que estende Repository, surge com uma lista de assinaturas de métodos.

Nesta lista, existem operações básicas para CRUD, aquelas mais comuns existentes na camada de persistência de um projeto com acesso a banco de dados. Vejamos quais são essas operações:

- `save(S)` - método para salvar uma entidade na base de dados;
- `save(Iterable<S>)` - este método insere uma lista de entidades no banco de dados, onde cada entidade desta lista é inserida por uma diferente operação de inserção;
- `findById(ID)` - método de consulta que recebe um parâmetro ID (identificador) e retorna uma única entidade conforme a igualdade com o parâmetro;
- `exists(ID)` - pesquisa no banco de dados, via parâmetro ID, se a entidade existe ou não e retorna um booleano;
- `findAll( )` - retorna todas as entidades. Consulta idêntica a um: 
`SELECT * FROM`;
- `findAll(Iterable<ID>)` - recebe um a lista de parâmetros do tipo ID e retorna todas as entidades referentes aos parâmetros; 
- `count( )` - retorna a quantidade de entidades em uma tabela; 
- `delete(ID)` - exclui uma entidade a partir do identificador;
- `delete(T)` - exclui uma entidade na tabela. O critério é um objeto referente à classe de entidade da tabela que sofrerá a ação;
- `delete(Iterable<? extends T>)` - recebe como critério uma lista de entidades e exclui as entidades referentes no banco de dados;
- `deleteAll( )` - realiza uma exclusão em massa sem qualquer critério. Este método vai remover todas as entidades de uma tabela.
 
 A última interface da relação é a PagingAndSortingRepository, a qual tem como função oferecer consultas para ordenação e paginação de dados:

- `findAll(Sort)` - este método retorna todas as entidades de forma ordenada por ascendência ou descendência. O critério Sort é o objeto que vai conter a ordenação desejada;
- `findAll(Pageable)` - a consulta retorna todas as entidades conforme o critério Pageable, um objeto que tem como finalidade paginar os dados de retorno.

Até aqui foram abordadas as interfaces do Spring Data, as quais são utilizadas como base para o projeto Spring Data JPA.