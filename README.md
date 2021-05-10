## First touch
This is my very first somehow-working Java web-project based on raw Servlets & JSPs.

Actually it's not 100% working, some parts were not committed back in 2014 (my first git hands-on as well).
Maybe I will find that fully working version one day, but for now I'll leave it as is.

### Run
- First you need to run MySQL database using docker-compose
```shell
docker-compose up
```
- Then create actual database from `setup.sql`. There are also MWB Schemas for MySQL Workbench
```shell
mysql -h hostname -u user rent_car < database/setup.sql
```
- Compile project & start embedded Tomcat 7 server
```shell
mvn package tomcat7:run
```

Default administrator account: `root/12345`