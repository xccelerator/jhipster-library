# Create database
```shell
docker run -d --name librarydb \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-p 5432:5432 postgres:latest
```
