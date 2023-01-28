# Start MariaDB

docker-compose up -d

# Shell in container

docker exec -it <CONTAINERNAME> bash

# MYSQL Commands:

## Password auth on DB
mysql -p 

## Switch DB
USE <DBNAME>;

## Show all DB
SHOW DATABASES;

