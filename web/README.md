# CETEJ35 - Java Web - JAVA_XXIX (2023_03)

## Corpo Docente
Professor: Dr. Gabriel Costa Silva

Tutor: Arthur Gregório

[Contrato Pedagógico](https://github.com/MarleneMoraes/utfpr-java/blob/main/web/WEB_contrato-pedagogico.md)

## Conteúdo
### 01 | Gerenciamento de Cidades

> Um CRUD é um aplicativo que permite criar (create), ler (read), alterar (update) e excluir (delete) dados. Como referência, vamos usar os dados de cidades. Uma cidade está associada a um único estado. Portanto, para criar uma cidade precisamos informar o nome da cidade e o estado onde ela se encontra.

#### Material Complementar
- [TIOBE index](https://www.tiobe.com/tiobe-index/)
- [StackOverflow Survey](https://insights.stackoverflow.com/survey/2020/)
- [CodinGame Survey](https://www.codingame.com/work/codingame-developer-survey-2021/)
- [OutSystems Trends Survey](https://www.outsystems.com/1/state-app-development-trends/)
- [Java Ecosystem Survey (Snyk)](https://snyk.io/pt-BR/blog/jvm-ecosystem-report-2020/)
- [VS Code versão Java](https://code.visualstudio.com/docs/languages/java/)

### 02 | Página Dinâmica

> Nesta aula, nós transformamos a página estática em uma página dinâmica. Isso é necessário porque queremos que a tabela de cidades seja atualizada à medida que novas cidades são inseridas. Para isso, vamos precisar de mais uma tecnologia - o Freemarker. Em seguida, mudamos a página existente para uma nova pasta. Assim, o Spring Boot reconhece a página como uma página dinâmica. Também alteramos a extensão da página. O próximo passo é colocar o código dinâmico na página, usando a sintaxe do Freemarker. Também fazemos os ajustes necessários para implementar o padrão MVC no projeto.

#### Material Complementar
- [Freemarker](https://freemarker.apache.org/)

### 03 | WebConf I

### 04 | Criando, Alterando e Excluindo

> Nesta aula nós finalizamos a implementação das quatro operações CRUD. Isso significa que nosso usuário será capaz de criar, alterar, excluir e listar as cidades em uma base de dados. Observe que ainda estamos usando uma base local, baseada em uma lista em memória. Nós vamos evoluir esse projeto até integrarmos essa base com um banco de dados.

### 05 | Validação de Dados

> Nesta aula, vamos ver como usar recursos do *Bean Validation Framework* juntamente com o Spring Boot, Freemarker e Bootstrap para garantir que o usuário consiga visualizar os erros e corrigir os dados sempre que necessário.

### 06 | Integração

> Normalmente, um aplicativo real vai precisar mais do que salvar dados em memória - ele vai precisar de persistência em banco! É aí que entra o conteúdo dessa aula.

### 07 | API Reativa

> Nesta aula vamos construir uma nova aplicação que funciona como uma API, recebendo dados usando a arquitetura REST. Para fazer isso, vamos usar uma nova tecnologia do Spring - o Spring WebFlux.