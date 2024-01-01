FROM tomcat:10-jdk17-corretto
#FROM tomcat:9-jdk17-corretto
#FROM tomcat:jdk17-openjdk
#FROM tomcat:8.5-jdk17-corretto
LABEL maintainer="anders@marinha.mil.br"
EXPOSE 8080
EXPOSE 8443
## para o projeto ficar como path: /servcat-api/
#  para copiar pagina inicial tomcat descomente a linha abaixo
RUN cp -R /usr/local/tomcat/webapps.dist/*   /usr/local/tomcat/webapps/
## copiando o projeto direto descomente a linha abaixo
# foi comentado pq colocamos no docker-compose.yml
# COPY  ../../target/servcat-api.war /usr/local/tomcat/webapps/servcat-api.war

## para o projeto ficar como root no tomcat
#RUN rm -rf /usr/local/tomcat/webapps/*
# foi comentado pq colocamos no docker-compose.yml
#ADD ../../target/servcat-api.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
