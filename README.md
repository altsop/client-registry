# Client Registry 

This application allows to register clients using REST API.

Flow: 
1. HTTP request comes to controller
2. Controller invokes service
3. Service invokes Kafka producer
4. Producer writes message to the topic
5. Kafka listener receives message and invokes handler
6. Handler processes message and writes data to DB

## Local setup 

1. Lombok: enable annotation processor in Intellij
3. Run from the project root: docker-compose up -d zookeeper broker mysql
4. Run application with 'local' profile

## Swagger

Url: http://localhost:8099/swagger-ui/index.html#

## Deployment

1. run docker build -t app .
2. run docker-compose up -d


## Plans for future 

1. Configure CI/CD
   1. Build mechanism (e.g. Jenkinsfile)
   2. Deployment mechanism (e.g. K8S deployment files)
   3. Configure repository 
      1. Static analysis (e.g. SonarQube)
      2. Merge Checks (e.g. mandatory pull-request, successful build)
   4. Configure monitoring
      1. Logs (e.g. kibana)
      2. Performance (e.g. elastic APM)
2. Deep dive into Kafka
   1. CAP optimization
   2. Deployment
3. Auditing
   1. MySQL (e.g. created_at/by)
   2. User Context (general info about request)
4. Security
   1. OAuth2 (resource server)
5. API
   1. RestControllerAdvice (better handling of exceptions)
   2. Endpoints' descriptions (OpenApi)

