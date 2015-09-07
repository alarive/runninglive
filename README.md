# Running Live - Sample CRUD REST API using various spring-boot components.

To run this project:

```
mvn package
java -jar target/runninglive-1.0-SNAPSHOT.jar

```

Then just point your browser to http://localhost:8080 
(use login : aurelien/4ur3l13n).

src/main/resources/import.sql is used to init the database when the application
starts.

# Sample REST calls

curl -u "aurelien:4ur3l13n" http://localhost:8080/

## List users

curl -u "aurelien:4ur3l13n" http://localhost:8080/users

## Get user #1

curl -u "aurelien:4ur3l13n" http://localhost:8080/users/1

## Get user participations to a competition

curl -u "aurelien:4ur3l13n" http://localhost:8080/users/1/participations

## Update user

curl -u "aurelien:4ur3l13n" -H 'Content-Type: application/json' -X PUT -d '{"username": "jean-patrick"}' http://localhost:8080/users/2

