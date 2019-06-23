# polyglot-dictionary

Poliglog-dictionary is an application which can be used as a dictionary in which
we can enter words or sayings and explain their meanings in different languages.
To relate specific words in different languages we can use links between
meanings of words. 

The core of the application is implemented in Java using Spring framework to 
implement MVC architecture. Hibernate is used as an ORM tool.

Second part of the application is using SPARQL query with ontop platform 
to get information of 
a an arbitrarily inserted word. For this part of the applicaton we use Angular
 platform while in the backend we still use Java.


## Installation

poliglot-dictionary application uses MySql database. Database with a name **language** has
to be created. It can be done using the follwing command in terminal: 

```
mysql -u root -e "create database language"
```
Next step is to create database tables, for this  we can use DDL queries which can be found
in the file **create-ddl-and-one_dump.sql** Thus we can issue the following command: 

```
mysql -u root -p language < create-ddl-and-one_dump.sql 
```
create-ddl-and-one-dump.sql contains besides ddl statements insert queries to populate
database with some simple data.

By default MySQL root password is an empty string. It can be changed to another password 
("sa" according to the password specified in polyglot-dictionary/src/main/resources/application.properties)
This can be done with using the following command:
```
mysqladmin -u root password sa
```

If MySQL RDBMS version on your computer is not compatible to the MySQL from the 
version of the **mysql-connector-java** in pom.xml then you should change
the version in the pom.xml file or update your MySQL.

### Parameters


In the file polyglot-dictionary/src/main/resources/application.properties 
we can change access parameters for the database.  For example jdbc.username = username and 
jdbc.password =password according to your database data. 

### Start the application 
First time when we want to run application we can have to type in terminal`:`
```
mvn install 
```
Jetty is used as a testing server program and can be run issuing commands: 
```
mvn jetty:run 
```

