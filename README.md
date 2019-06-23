# polyglot-dictionary


## Installation

poliglot-dictionary application uses MySql database. Database with a name language has 
to be created: \
'''
**mysql -u root -e "create database language"** 
'''

Next step is to create database tables, for this  we can use DDL queries which can be found
in the file create-ddl-and-one_dump.sql Thus we can issue the following command: \
**mysql -u root -p language < create-ddl-and-one_dump.sql** \
create-ddl-and-one-dump.sql contains besides ddl statements insert queries to populate
database with some simple data.

By default MySQL root password is empty string. It can be changed to another password 
("sa"** according to the password specified in polyglot-dictionary/src/main/resources/application.properties
This can be done with the following command:\
'''
**mysqladmin -u root password sa**
'''

If MySQL version is different from the version of the corrector, you should change
MySqlConnector version in the pom.xml file.

### Parameters


In the file polyglot-dictionary/src/main/resources/application.properties 
we can change access parameters for the database.  For example jdbc.username = username and 
jdbc.password =password according to your database data. 

### Start the application 
First time when we want to run application we can have to type in terminal`:`\
**mvn install** \
Jetty is used as a testing server program and can be run issuing commands: \
**mvn jetty:run** 

