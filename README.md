## Gaussian Solver, REST, MySQL
Implementation of a ParallelGaussianSolver with REST service endpoints and database<br/>

### Technologies
Frontend: REST, Jersey <br />
Calculation: Parallel Gaussian Solver <br />
Database: MySQL <br />



### REST Service
2 Endpoints:  <br />
Send Calculation Request:  <br />
*curl -X POST -d '{"a":[2.0,4.0,5.0,-6.0],"b":[8.0,4.0]}' -H 'Content-Type: application/json' localhost:8080/*

Retrieve all stored requests:  <br />
*curl -X GET localhost:8080 <br />*


### Database Table Implementations
Clients implemented with JDBC driver <br />

MySQLClient_2 (standard): <br />
2 columns: id, JSON





### Steps
##### MYSQL
Start MySQL <br />
create database:  <br />
mysql –uroot –p –e “source src/main/resources/create_database.sql” <br />

Create tables with sql scripts: <br />
mysql –uroot –p –e “source src/main/resources/create_table_client_1.sql” <br />
mysql –uroot –p –e “source src/main/resources/create_table_client_2.sql” <br />

Create tables with Java Test: <br />
run 'createTable()' method via AppTest <br />


##### Run with Maven 
*mvn clean compile exex:java* <br />


##### Build jar, Run jar
*mvn clean compile package* <br />
*java -jar target/jar-name.jar* <br />





