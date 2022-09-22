# cd eureka-service/
# mvn clean && mvn package -DskipTests
# docker build . -t eureka-service
# cd ..

# cd config-service/
# mvn clean && mvn package -DskipTests
# docker build . -t config-service
# cd ..

# cd movie-service/
# mvn clean && mvn package -DskipTests
# docker build . -t movie-service
# cd ..

# cd serie-service/
# mvn clean && mvn package -DskipTests
# docker build . -t serie-service
# cd ..

cd catalog-service/
mvn clean && mvn package -DskipTests
docker build . -t catalog-service
cd ..

# cd gateway-service/
# mvn clean && mvn package -DskipTests
# docker build . -t gateway-service
# cd ..

docker-compose up