# Spring Data JPA
## Introdução ao Spring Data JPA

### 1. Introdução
O Spring Framework é um dos mais populares e poderosos frameworks dentro da linguagem Java.  

Ele não se limita a um único recurso, mas sim, a uma larga variedade de funcionalidades que fazem parte do dia a dia de um programador Java no desenvolvimento de aplicações.

 Suas funcionalidades mais famosas talvez sejam o seu módulo Web, conhecido por Spring MVC e o núcleo principal do Spring que provê o conceito de injeção de dependências e inversão de controle.

Programar utilizando o Spring Framework não se limita apenas a usar um ou mais de seus recursos, mas também fazer uso das boas práticas e padrões de projetos, os quais aumentam a produtividade, facilitam a manutenção e atualização do código bem como o reuso, princípio básico da orientação a objetos.

Definir o Spring Framework sempre é uma tarefa difícil, pois ele cobre tantos conceitos que é quase impossível dizer especificamente o que ele faz. Você pode definir o Hibernate como um framework ORM e explicar exatamente sua função, ou pode conceituar o Apache Struts 2 com relação ao padrão MVC. Mas definir o Spring Framework realmente não é fácil.

Na documentação, o Spring é citado como um framework leve, projetado para a construção de aplicativos Java. Ser considerado leve tem o sentido referente a você realizar apenas algumas poucas alterações no código de sua aplicação para obter seus benefícios.

Uma característica marcante do Spring Framework é a possibilidade de integração com bibliotecas, especificações Java e diversos frameworks como o Hibernate, Apache Struts, JasperReports, Dandelion, Direct Web Remoting (DWR), Java Server Faces (JSF 2), entre outros.

Um conceito importante sobre o Spring Framework está diretamente ligado à injeção de dependências e inversão de controle. Cada classe que será gerenciada por esse sistema recebe o nome de bean. A partir de beans você usa diversas classes e interfaces do Spring e também pode criar seus próprios beans para serem gerenciados pelo framework e assim, não precisa mais se preocupar em criar a instância de seus objetos sempre que precisar deles. O Spring fará isso por você.

## Configuração do Spring Data JPA

### 1. Configuração do Spring Data JPA
O Spring Data JPA é um recurso que precisa ser configurado junto ao Spring framework para que possa ser usado em um projeto. Essa configuração não é complicada, mas necessária, porque a partir dela são adicionadas as informações de conexão com o banco de dados, qual pool de conexão vai ser usado, entre algumas mais que veremos a frente.

Outra parte importante são as bibliotecas que o Spring Data precisa para trabalhar. Existem algumas formas diferentes de adicionar estas bibliotecas em um projeto Java. Eu particularmente prefiro usar um gerenciador de dependências como o Maven e, será desta forma que vou apresentá-las no decorrer deste curso.

#### Spring Boot

Para herdar as configurações do spring boot devemos ter no arquivo pom.xml do projeto a seguinte instrução:
 

A instrução `<parent>` no arquivo pom.xml de um projeto Maven indica que o projeto atual herda as configurações de outro projeto (o "parent" ou pai). Nesse caso específico, a instrução indica que o nosso projeto está herdando as configurações do Spring Boot Starter Parent.

A instrução `<groupId>` especifica o ID do grupo (ou organização) do projeto pai. No caso do Spring Boot, o grupo é `org.springframework.boot`.

A instrução `<artifactId>` especifica o ID do artefato (ou nome do projeto) do projeto pai. Neste caso, o artefato é `spring-boot-starter-parent`.

A instrução `<version>` especifica a versão do projeto pai que deve ser usada. Nós usaremos uma versão que seja compatível com o JDK 17 como por exemplo a versão "3.2.1". Isso significa que o nosso projeto usará as configurações e dependências do Spring Boot na versão 3.2.1. Se você utilizasse o JDK 11 por exemplo, precisaria usar a versão "2.5.3" para conseguir rodar corretamente o projeto.

Ao herdar as configurações do Spring Boot Starter Parent, o nosso projeto obtém automaticamente várias configurações e dependências padrões do Spring Boot. Isso inclui a definição das versões das dependências do Spring Boot e de outras configurações relacionadas ao desenvolvimento de aplicativos Spring. Além disso, o Spring Boot Starter Parent facilita a gestão de dependências e plugins do Maven, fornecendo configurações padronizadas.

Essa abordagem ajuda a simplificar a configuração e a garantir a consistência entre os projetos Spring Boot, permitindo que os desenvolvedores se concentrem mais na lógica do aplicativo em si.

Portanto o Spring Boot é um projeto que faz parte do ecossistema do SPRING. Ele nos permite definir qual projeto spring vamos usar em nossa aplicação de forma simplificada, como por exemplo o Spring Data JPA e configura automaticamente para nós. Ele também contribui para a organização do arquivo pom.xml de nosso projeto evitando que coloquemos diversas dependências, pois ele nos fornece o uso dos starters. Os starters são dependências que agrupam outras dependências, encapsulando um conjunto de outras dependências. 

#### Starter do Spring Data JPA

Para adicionar o Spring Data JPA em nosso projeto, podemos incluir o starter dele no pom.xml, conforme descrito a seguir:
```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!-- Starter do Spring Data JPA -->
```

A dependência acima vai fornecer todas as classes e interfaces que são necessárias para lidar com o Spring Data JPA. 

#### Starter do Spring BootDevTools

Para adicionar o Spring BootDevTools em nosso projeto, inclua o starter dele no pom.xml, coforme descrito a seguir:

```xml
<!-- Starter do Spring BootDevTools -->
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-devtools</artifactId>
   <scope>runtime</scope>
   <optional>true</optional>
</dependency>
```

Ao incluir essa dependência podemos notar duas instruções a mais, a `<scope>` e a `<optional>`. 
A instrução <scope> especifica o escopo da dependência. Neste caso, o escopo é "runtime", o que significa que a dependência é necessária apenas em tempo de execução, não em tempo de compilação ou teste. 
A instrução `<optional>` indica que a dependência é opcional. Isso significa que ela não é necessária para o funcionamento básico do projeto, mas pode ser útil em determinados cenários.

O Spring BootDevTools é uma dependência que auxilia muito o desenvolvimento, pois oferece como recurso a reinicialização automática do projeto após cada alteração que fazemos. O interessante é que ele não faz um restart completo da aplicação, ele possui como recurso a divisão em dois ClassLoaders, sendo que o ClassLoader é uma classe responsável por ler as estruturas das classes do nosso projeto e armazenar na memória para quando precisar usar determinada classe. Dessa forma o BootDevTools cria um ClassLoader chamado de base e outro de restart.

**ClassLoader base:** engloba tudo que não muda, como por exemplo as bibliotecas, frameworks que usamos são adicionados nele.
ClassLoader restart: contém tudo que estamos desenvolvendo, então quando alteramos algum código na implementação de nosso projeto, esse classLoader reinicia apenas essa parte de nossa aplicação, tornando assim mais rápido a atualização de tudo que fazemos na aplicação que está rodando.

#### Pool de conexão HikariCP
Neste ponto, você pode incluir o pool de conexões e nesse caso usaremos o HikariCP. O HikariCP é um pool de conexões de alto desempenho para Java. Ele fornece recursos avançados de gerenciamento de conexões com o banco de dados, como pooling de conexões, configuração flexível e monitoramento.

A principal função de um pool de conexão, como o HikariCP, é gerenciar e reutilizar conexões com o banco de dados. Isso ajuda a reduzir a sobrecarga de criação e destruição de conexões, o que pode ser caro em termos de desempenho e recursos. O HikariCP mantém um conjunto de conexões pré-criadas e permite que os aplicativos obtenham e liberem conexões conforme necessário, garantindo que elas estejam sempre disponíveis quando necessário.

Essa dependência é útil para gerenciar conexões com o banco de dados. Com isso poderemos usar as classes e recursos do HikariCP para criar e gerenciar pools de conexões com eficiência, o que pode melhorar o desempenho e a escalabilidade do seu aplicativo.

```xml
<!-- HikariCP -->
<dependency>
   <groupId>com.zaxxer</groupId>
   <artifactId>HikariCP</artifactId>
</dependency>
```

#### MariaDB (Banco de dados)
Para se conectar e interagir com um banco de dados MariaDB, precisamos incluir sua dependência no pom.xml também. Essa biblioteca fornece a implementação do driver JDBC para o banco de dados MariaDB, permitindo que seja possível se conectar ao banco de dados e executar operações nele.
```xml
<!-- MariaDB -->
<dependency>
   <groupId>org.mariadb.jdbc</groupId>
   <artifactId>mariadb-java-client</artifactId>
   <scope>runtime</scope>
</dependency>
```

#### H2 (Banco de dados)
Outra opção que vocês podem usar para testar o projeto é o banco de dados H2. O H2 é um banco de dados relacional escrito em Java que é leve, rápido e pode ser executado em modo de memória ou em disco. Ele é amplamente utilizado para fins de desenvolvimento e teste, especialmente em aplicativos Java. Essa dependência é especialmente útil quando você deseja desenvolver e testar aplicativos que requerem um banco de dados relacional, mas não deseja configurar um banco de dados mais robusto.

```xml
<!-- H2 -->
<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
   <scope>runtime</scope>
</dependency>
```

Com as dependências listadas até o momento já é possível colocar o SpringData JPA para funcionar em um projeto. Em caso de utilizar outro banco de dados, basta pesquisar a dependência dele na documentação e incluir no arquivo pom.xml do projeto. Ao criar um projeto spring boot, pode-se observar no pom.xml a inclusão de outras dependências auxiliares não descritas aqui como por exemplo o Spring Web, Lombok, Spring Boot Starter Test, dentre outras que podem ser definidas para o projeto.