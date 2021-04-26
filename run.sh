mvn clean package
docker build -t linxue/homework-docker:v1 .
docker build -t linxue/nginx:v1 nginx/.
docker build -t linxue/redis:v1 redis/.
docker-compose down --remove-orphans
docker-compose up