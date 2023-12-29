# Guia de configurações com Docker para Dashboard de métricas com Spring

> Autor: Carlos Anders

## Passo 1 - Build do Projeto sem o docker no shell:

```bash
# JAVA_HOME paths maquina Anders
# C:\Program Files\Java\jdk1.8.0_301
# C:\dev\java\openjdk-17.0.2_windows-x64_bin\jdk-17.0.2
# C:\Users\anders\scoop\apps\corretto-lts-jdk\current
# verificar versão global do java instalado na máquina
java -version
### no cmd windows - prompt de comando ###
# O comando setx atualiza permanentemente as variáveis de ambiente
# JAVA_HOME
setx /M JAVA_HOME "C:\dev\java\openjdk-17.0.2_windows-x64_bin\jdk-17.0.2"
setx /M JAVA_HOME "C:\Users\anders\scoop\apps\corretto-lts-jdk\current"
setx /M PATH "%PATH%;%JAVA_HOME%\bin";
echo %JAVA_HOME%
###
### no powershell windows - prompt de comando ###
# ver as variáveis de ambiente
$env:Path -split ';' 
#ou
dir env:
# exemplo:
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Users\anders\scoop\apps\corretto-lts-jdk\current")
[Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\dev\java\openjdk-17.0.2_windows-x64_bin\jdk-17.0.2")
# set var no powershell 7
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Users\anders\scoop\apps\corretto-lts-jdk\current", "User")
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Users\anders\scoop\apps\corretto-lts-jdk\current", "Machine")
#ou
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\dev\java\openjdk-17.0.2_windows-x64_bin\jdk-17.0.2", "User")
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\dev\java\openjdk-17.0.2_windows-x64_bin\jdk-17.0.2", "Machine")
$env:JAVA_HOME

# Definindo a variável de ambiente persistentemente
# usa a classe [System.Environment] com o método SetEnvironmentVariable para que 
# a variável de ambiente a defina persistentemente na sessão do usuário
[System.Environment]::SetEnvironmentVariable("PATH", $Env:Path + ";%JAVA_HOME%\bin", "User")
[System.Environment]::SetEnvironmentVariable("PATH", $Env:Path + ";$($Env:JAVA_HOME)\bin", "User")
# a variável de ambiente a defina persistentemente na sessão do sistema
[System.Environment]::SetEnvironmentVariable("PATH", $Env:Path + ";JAVA_HOME\bin", "Machine")
[System.Environment]::SetEnvironmentVariable("PATH", $Env:Path + ";$($Env:JAVA_HOME)\bin", "Machine")
###
# limpar projeto
mvn clean
mvn clean package
# caso queira pular os testes unitários
mvn clean -DskipTests package
# recompilar e executar os testes
mvn package

mvn package -Dp-type=jar -DskipTests
mvn -DskipTests spring-boot:build-image
# esta habilitado para gera o war direto, se precisar gerar o jar basta executar o comando abaixo:
mvn install -Dp-type=jar
# war (opcional)
mvn install -Dp-type=war

# rodando a aplicação criada sem o docker
java -jar .\target\api-siscatbr-singra-prod.jar

# testando conexão bd oracle
# Local
sqlplus fedlogdb/fedlogdb@CATALOP1.PROGNUS.COM.BR
sqlplus SINGRA/singrad4@PORTALP1.MAR.MIL.BR
sqlplus mccprod/marinha23@//localhost:1521/SERVCAT
sqlplus mccprod/marinha23@//10.0.0.2:1521/SERVCAT

# VPN
sqlplus mccprod/mccprod@10.11.12.151:1521/catalop1
sqlplus ANDERS_ORACLE/marinha@10.11.12.151:1521/portalp1.mar.mil.br
```

## Passo 2 - Build do Docker com docker-compose:

```bash
# comandos para remover resquícios anteriores do container do siscat-br
docker-compose down
docker volume prune
docker volume rm servcat-api-data
# certifique-se de remover as images anteriores;
# comando: docker rmi <image_name>:<version_tag>
docker rmi servcat-api:1.0.0

# para inspecionar o arquivo que está na raiz do projeto
# executar na raiz do projeto
docker-compose config
# para subir os containers com visualização de logs
docker-compose up
# para subir os containers em background
docker-compose up -d

# em outro terminal caso a tela esteja travada nos logs
# para VRF containers com formatação
docker ps --format '{{.ID}}\t{{.Names}}\t{{.Status}}'
docker-compose ps --format '{{.ID}}\t{{.Names}}\t{{.Status}}'
# ou
docker-compose ps --format '{{.ID}}\t{{.Names}}\t{{.Status}}'
# para visualizar os logs do container do siscat-br
docker logs --since=1h servcat.api
# por linhas
docker logs --tail 100 servcat.api
# para visualizar os containers de monitoramento:
docker logs --since=1h servcat.api
#docker logs --since=1h grafana.api
#docker logs --since=1h prometheus.api

# Aborting on container exit...
press Ctrl+C

# para remover os containers
docker-compose down
docker volume ls
docker volume rm servcat-api-data

#verificar quanto espaço as imagens do Docker ocupam no pool de armazenamento:
docker system df --format 'table {{.Type}}\t{{.TotalCount}}\t{{.Size}}'

```

## Links de referências

[Dashboard de métricas com Spring Boot Actuator, Prometheus e Grafana](https://www.youtube.com/watch?v=K_EI1SxVQ5Q&list=WL&index=29).
