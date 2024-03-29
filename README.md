# italy-municipalities-rest

REST service for retrieving municipalities of Italy. Data provided by ISTAT

### Resources

[CSV file](https://www.istat.it/storage/codici-unita-amministrative/Elenco-comuni-italiani.csv)

## Prerequisites

- jdk 11.0.4 or higher
- maven 3.6.1 or higher

## To build

```shell
$ mvn clean package
```

## To start the app

```shell
 $ cd italy-municipalities-rest
 $ mvn spring-boot:run

```

## Swagger

http://localhost:8080/swagger-ui/index.html

## Services

- full list of municipalities: http://localhost:8080/municipality
- list with pagination: http://localhost:8080/municipality?page={page}&size={size}
- single municipality: http://localhost:8080/municipality/{code}
## Docker
Building the image
```shell
$ docker build -t italy-municipalities-rest .
```
Running docker container
```shell
$ docker run -p 8080:8080 italy-municipalities-rest
```
If pulled from the docker hub:
```shell
$ docker run -p 8080:8080 xyz/italy-municipalities-rest:0.0.3
```

### Pushing to docker hub
Assuming _xyz_ is your docker hub username.
```shell
 $ docker login --username=xyz
 $ docker tag italy-municipalities-rest xyz/italy-municipalities-rest:0.0.3
 $ docker push xyz/italy-municipalities-rest:0.0.3

```
