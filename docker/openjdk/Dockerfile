FROM openjdk:17-alpine

# Expose Web Port
#EXPOSE 8443
EXPOSE 8080

# maintainer details
LABEL maintainer="anders@marinha.mil.br"
COPY ../../target/servcat-api.jar servcat-api.jar
ENTRYPOINT ["java","-jar","/servcat-api.jar"]
