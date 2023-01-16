# ALL CRUD JAVA

Código adaptádo para operação CRUD em diversos bancos de dados relacionais e não relacionais na linguagem JAVA

#

* Relacionais
     * MySqlWorkBench [Repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/src/jmysql)
     * PostgreSQL [Repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/src/jpostgresql)
     * SQLite [Repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/src/jsqlite)
* Não Relacionais
     * MongoDB [Repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/src/jmongodb)
     * CouchDB [Repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/src/jcouchdb)
     * Redis [repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/src/jredis)

* Pacotes .Jar
     * [Repositório](https://github.com/Brunonavarrooficial/CRUD_Databases_MySQL_NoSQL/tree/main/CRUD_All_JAVA/lib)
     

##

## CRUD MySQL WorkBench Java

Realizada operação CRUD com bando de dados MySQL WorkBench em linguagem JAVA

#

### Skills:

* Classes
* Bibliotecas
    * import java.sql.Connection;
    * import java.sql.DriverManager;
    * import java.sql.ResultSet;
    * import java.sql.PreparedStatement;
    * import java.sql.SQLException;
    * import java.util.Locale;
    * import java.util.Scanner;
* Jar
   * MySQL-Connector-j-8.0.31.jar 

##
## CRUD PostgreSQL in JAVA
Realizada operação CRUD com banco de dados PostgreSQL em Linguagem JAVA

#

### Skills:

* Classes
* Bibliotécas
    * import java.sql.Connection;
    * import java.sql.DriverManager;
    * import java.sql.PreparedStatement;
    * import java.sql.ResultSet;
    * import java.sql.SQLException;
    * import java.util.Properties;
    * import java.util.Scanner;
* Jar
   * postgresql-42.3.7.jar
   
##

## CRUD SQLite in JAVA
Realizada operação CRUD com banco de dados SQLite em linguagem JAVA

#

### Skills:

* Classes
* Repositório sqlite criado na raiz do projeto
   * jsqlite3.geek
* Bibliotécas
    * import java.sql.Connection;
    * import java.sql.DriverManager;
    * import java.sql.ResultSet;
    * import java.sql.PreparedStatement;
    * import java.sql.SQLException;
    * import java.util.Locale;
    * import java.util.Scanner;
* Jar
   * sqlite-jdc-3.40.0.0.jar
   
#

## CRUD mongoDB in JAVA

Operação Crud com banco de dados MongoDB em linguagem JAVA
#

### Skills

* Classes
* Bibliotécas
   * import java.util.Arrays;
   * import java.util.Locale;
   * import java.util.Scanner;
   * import org.bson.Document;
   * import org.bson.conversions.Bson;
   * import org.bson.types.ObjectId;
   * import org.json.JSONObject;
   * import com.mongodb.MongoClientSettings;
   * import com.mongodb.ServerAddress;
   * import com.mongodb.client.MongoClients;
   * import com.mongodb.client.MongoCollection;
   * import com.mongodb.client.MongoCursor;
   * import com.mongodb.client.MongoDatabase;
   * import com.mongodb.client.result.DeleteResult;
   * import com.mongodb.client.result.UpdateResult;
   * import static com.mongodb.client.model.Updates.combine;
   * import static com.mongodb.client.model.Updates.set;
* Jar
   *  json-20220924.jar
   * mongo-java-driver-3.12.11.jar
#

## CRUD Redis in JAVA

Código CRUD em linguagem JAVA para banco de dados Redis
#

* Classes
* bibliotécas
   * import java.util.Arrays;
   * import java.util.HashMap;
   * import java.util.Locale;
   * import java.util.Map;
   * import java.util.Scanner;
   * import java.util.Set;
   * import redis.clients.jedis.Jedis;
   * import redis.clients.jedis.exceptions.JedisConnectionException;
* Jar
   * jedis-4.3.1.jar

#

## CRUD CouchDB in JAVA
Código Crud na linguagem Java em conexão com banco de dados CouchDB
#

### Skills:

* Classes
* Bibliotécas
   * import java.io.IOException;
   * import java.net.URI;
   * import java.net.http.HttpClient;
   * import java.net.http.HttpRequest;
   * import java.net.http.HttpResponse;
   * import java.net.http.HttpRequest.BodyPublishers;
   * import java.net.http.HttpResponse.BodyHandlers;
   * import java.util.Scanner;
   * import org.json.JSONArray;
   * import org.json.JSONObject;
* Jar
   * #### Não foi necessário
#

## CRUD Firebase in JAVA
código CRUD na linguagem JAVA em conexão com banco de dados Firebase
#

### Skills:

* Classes
* Bibliotécas
   * import java.io.IOException;
   * import java.net.URI;
   * import java.net.http.HttpClient;
   * import java.net.http.HttpRequest;
   * import java.net.http.HttpResponse;
   * import java.net.http.HttpRequest.BodyPublishers;
   * import java.net.http.HttpResponse.BodyHandlers;
   * import java.util.Scanner;
   * import org.json.JSONObject;
* Jar
   * #### Não foi necessário

#
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
