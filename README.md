# polyglot-dictionary


After downloading of the project to directory you have to install MySql
and to run DDL queries which can be found in file mysqlscript-create.sql
or together with data in create-ddl-and-one-dump.sql in the root directory of the program.

Additionally, parameters
jdbc.username = username and jdbc.password =password in the
file polyglot-dictionary/src/main/resources/application.properties according to
your database credentials. Because jetty is used as testing server program can
be run issuing commands: 
mvn install 
mvn jetty:run

