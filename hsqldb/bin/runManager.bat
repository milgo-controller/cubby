cd ..\data
@java -classpath ..\lib\hsqldb.jar org.hsqldb.util.DatabaseManagerSwing --driver org.hsqldb.jdbcDriver --url jdbc:hsqldb:hsql://localhost/testdb --user "SA" --password ""
