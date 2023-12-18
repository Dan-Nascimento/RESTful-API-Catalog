# SERVCAT API

## Propósito:

O Webservice...

## Informações Técnicas

- **JAVA 17+**
- **Maven 3.9+**
- **Arquitetura: REST** - (Representational State Transfer)
- **Formato de Serialização de Dados: JSON** (JavaScript Object Notation)

```bash
# Clonando o projeto do site
git clone https://singra.prognus.com.br/catalogacao/servcat-api.git
# ou se desejar baixar para outra pasta
git clone https://singra.prognus.com.br/catalogacao/servcat-api.git webservice-servcat
```

##### Configurar o Git:

```bash
# configurar nome e email no nível global do sistema para identificar no commit
git config --global user.name "Seu Nome"
git config --global user.email "email@provedor.com"

# Para conferir execute
git config user.name
git config user.email

# Para listar todas as configurações
git config --list
```

##### Criando um chave SSH Para criar uma chave privada/publica no git:

```bash
ssh-keygen -t rsa -C "seuemails@provedor.com"
# Para listar a chave pública
cat ~/.ssh/id_rsa.pub
# ou se preferir pelo editor de texto padrão ou cacadastrado
subl ~/.ssh/id_rsa.pub
```

##### Iniciando Push an existing Git no projeto:

```bash
# verficar os branches trazidos do repositório com os commits
git remote rename origin old-origin
git remote add origin https://singra.prognus.com.br/catalogacao/servcat-api.git
git push -u origin --all
git push -u origin --tags
```

### Executando a Aplicação:

```bash
# Dentro da raiz do projeto, como por exemplo em:
cd /d ...\web_services\servcat-api
# execute o comando:
mvn spring-boot:run
# E para cancelar a execução use CTRL+C

# caso desejar rodar pela IDE
# Isso pode ser feito usando Run da IDE em:
servcat-api\src\main\java\mb\dabm\servcatapi\ServcatApiApplication.java
```

#### Gerando o WAR:

Certifique-se de o arquivo `pom.xml` está configurado com a tag `packaging` para o valor: `war`, se estiver com o valor `jar` o arquivo gerado será um arquivo compactado para ser executado.

```bash
# Para gerar o war, pelo terminal vá na na raiz do projeto, como por exemplo em:
../web_services/servcat-api
# E digite os camandos:
mvn clean package
mvn package
# sem testes
mvn package -Dmaven.test.skip
# ou
mvn clean -DskipTests package

# se gerou o arquivo .jar para executar será o comando:
java -jar target/servcat-api.jar
# ou caso queira alterar algum parametro do resource
# ex.: alterar usuario/senha banco
java -jar servcat-api.jar --spring.datasource.username=root --spring.datasource.password=root
# para alterar ambiente de execucao:
java -jar -Dspring.profiles.active=dev servcat-api.jar
# ou
java -jar -Dspring.profiles.active=prod servcat-api.jar

mvn package -Dp-type=jar

mvn -DskipTests spring-boot:build-image
# esta habilitado para gera o war direto, se precisar gerar o jar basta executar o comando abaixo:
mvn install -Dp-type=jar
# war (opcional)
mvn install -Dp-type=war
```

#### caso desejar altera o nome do arquivo 'war' ou 'jar' na complicação

Alterar a tag `finalName` dentro do arquivo `pom.xml`

```xml
<!-- exemplo: -->
<build>
    <finalName>servcat-api</finalName>
    <plugins>
        ...
    </plugins>
</build>
```
