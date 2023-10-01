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