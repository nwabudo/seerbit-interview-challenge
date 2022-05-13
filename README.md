# seerbit-interview-challenge

## How to

Clone the project from this repository

    git clone https://github.com/nwabudo/seerbit-interview-challenge.git

Clean, Run and Package a new Jar file using

    mvn clean package

Start up the docker container using

    docker-compose up -d

Explore the App via HTTP Rest Calls using Postman or other HTTP Client simulator 

```
POST http://localhost:8084/transaction
GET http://localhost:8084/transaction/statistics
DELETE http://localhost:8084/transaction
```

Lastly, stop up the docker container using

    docker-compose down