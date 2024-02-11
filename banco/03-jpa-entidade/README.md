# JPA
## Lidando com Entidades

O conceito de entidades é bem simples de ser compreendido e nasceu nos bancos de dados relacionais. Todas as bases de dados são formadas por tabelas e estas, por linhas e colunas, onde cada linha é considerada uma entidade.

Em uma aplicação Java, preparada para trabalhar com um framework ORM, é preciso realizar o mapeamento objeto relacional. Este mapeamento é a forma como o framework vai referenciar uma tabela no banco de dados com a classe que a representa na aplicação.

E cada coluna da tabela vai ter uma referência na classe que será um atributo (variável de instância). Assim, cada classe da aplicação que referencia uma tabela no banco de dados é chamada de classe de entidade. Isto porque, uma classe vai ser a instância de um objeto que será persistido na tabela como uma linha, ou seja, uma entidade.

Os mapeamentos podem ser feitos de duas formas, uma delas, por meio de arquivo XML. Com o lançamento da versão 5 do Java anos atrás, a linguagem passou a oferecer o uso de anotações e elas foram adicionadas como opção de mapeamento pelos frameworks ORM e também pela especificação JPA.

Por exemplo, o Hibernate tem suas próprias anotações de mapeamento, se elas forem usadas, apenas aplicações que trabalham com o Hibernate farão uso deste mapeamento.

Já, se o mapeamento for realizado com as anotações oferecidas pela especificação JPA, qualquer framework ORM poderá fazer uso deste mapeamento. Sendo assim, é sempre mais apropriado usar o sistema de mapeamento da especificação.