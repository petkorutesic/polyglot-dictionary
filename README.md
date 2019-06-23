# polyglot-dictionary

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

