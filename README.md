# SPRING-BOOT + HAZELCAST CLIENTE [spring-boot-hazelcast-client]

Aplicação feita em Spring Boot com intuito de elucidar a configuração do cliente para acesso ao hazelcast server separadamente, e com uso de *Predicates* na query de consulta.

# PRÉ-REQUISITOS

* Java 8
* Maven
* Hazelcast IMDG

# Java 8

O Java é a linguagem de programação utilizada por esta aplicação; para que a aplicação funcione, é necessário realizar a instalação do mesmo em todos os ambientes computacionais em que se pretende utilizar a aplicação.

O passo-a-passo da instalação do Java 8 de acordo com o sistema operacional pode ser obtido no site 'https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html'.

# Maven

O Maven é o mecanismo de build de aplicações requisitado por esta aplicação, para poder utilizá-lo, é necessário realizar a instalação do mesmo no computador em que será gerado o artefato da aplicação.

O passo-a-passo da instalação deste mecanismo de acordo com o sistema operacional pode ser obtido no site 'https://maven.apache.org/install.html'. 

# Hazelcast IMDG

O Hazelcast é um cache robusto que pode ser usado em aplicações com o intuito de performance. O download do arquivo que será utilizado para inicialização de um nó servidor se encontra em: https://hazelcast.org/download/

Após o download, o arquivo de start.sh (ambientes com kernel unix) ou start.bat (ambientes com windows), que se encontra dentro da pasta *bin*, deverá ser utilizado para subir nosso nó.

**Build da Aplicação**

Para o uso dos Predicates do Hazelcast funcionar corretamente é preciso colocar o caminho aonde se encontra o jar que contem os models do seu projeto que serão utilizados na desserialização para query, neste caso o jar do próprio projeto.

Para gerar o jar, basta executar este comando no projeto aonde se localiza o arquivo pom.xml:

```
mvn clean package install
```

Para incluir o caminho do jar no hazelcast basta alterar o arquivo de start do hazelcast na seguinte linha:

```
export CLASSPATH="$HAZELCAST_HOME/lib/hazelcast-all-3.8.3.jar:*$HAZELCAST_HOME/path-do-seu-jar/seus-models.jar"
```

Após feito a adição pode se iniciar o servidor.

```
./start.sh
```

E logo após o servidor ter iniciado, deve se atentar para o ip em que o nó foi configurado (será mostrado no console em que se executou o comando), para alterar no arquivo hazelcast-client.xml localizado na pasta de resources do projeto.

```
<address>ip-hazelcast-server:5701</address>
```

Após adicionar o ip do hazelcast, pode-se então subir nossa aplicação através do seguinte comando na pasta do projeto aonde está localizado o pom.xml:

```
mvn clean package spring-boot:run
```
