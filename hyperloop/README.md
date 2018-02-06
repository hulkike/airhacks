# Build
mvn clean package && docker build -t com.airhacks/hyperloop .

# RUN

docker rm -f hyperloop || true && docker run -d -p 8080:8080 -p 4848:4848 --name hyperloop com.airhacks/hyperloop 