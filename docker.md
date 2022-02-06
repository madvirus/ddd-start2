```
docker create --name mysql8 -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -v c:\work\mysqldata:/var/lib/mysql mysql:8.0.27
```

docker start mysql8

docker stop mysql8