# SPRING-BOOT + HAZELCAST CLIENTE [spring-boot-hazelcast-client]

Aplicação feita em Spring Boot com intuito de elucidar a configuração de uma aplicação cliente para acesso a um cluster ou nó do hazelcast em outra maquina ou desvinculado ao projeto além do uso de *Predicates* na query de consulta.

            [projeto cliente - maquina 1]
                      ||   /\
                      \/   ||
     [instancia cluster hazelcast - maquina n(s)]


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

```sh
export CLASSPATH="$HAZELCAST_HOME/lib/hazelcast-all-3.8.3.jar:*$HAZELCAST_HOME/path-do-seu-jar/seus-models.jar"
```

Após feito a adição pode se iniciar o servidor.

```
./start.sh
```

E logo após o servidor ter iniciado, deve se atentar para o ip em que o nó foi configurado (será mostrado no console em que se executou o comando), para alterar no arquivo hazelcast-client.xml localizado na pasta de resources do projeto.

```xml
<address>ip-hazelcast-server:5701</address>
```

Após adicionar o ip do hazelcast, pode-se então subir nossa aplicação através do seguinte comando na pasta do projeto aonde está localizado o pom.xml:

```
mvn clean package spring-boot:run
```

# Recursos

**POST /teste-spring-hazelcast/cidades**

Recurso utilizado para popular o cache enviando um JSON da cidade no body, e recebendo um 201 Created como response.

Exemplo JSON:

```json
{
  "nome":"rio de janeiro",
  "populacao": 15989929
}
```

**GET /teste-spring-hazelcast/cidades**

Recurso utilizado para consultar as cidades no cache, recebendo um __200 Ok__ e um JSON com os resultados da consulta ou um __404 Not Found__ quando não retorna ninguem da consulta.

Os predicates devem ser observados na classe CidadeServiceImpl. A query retorna cidades com população inferior a 200.


# Log4J

O Log4j foi adicionado ao projeto para gravar os logs das requisições na aplicaço, por isso deve se levar em conta a permissão ao diretório configurado no arquivo *log4j2-spring.xml* na linha:

```xml
<Property name="log-path">/var/log/teste-spring-hazelcast</Property>
```

E para alteraço do nivel de log da aplicação, deve se alterar as linhas:

```xml
<Logger name="br.org.teste.spring.hazelcast" level="debug">
	 <AppenderRef ref="File-Appender" level="debug"/>
</Logger>
```

att.
