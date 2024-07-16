# Usa a imagem base do OpenJDK do Eclipse Temurin com Alpine Linux 21
FROM eclipse-temurin:21-alpine

# Define o diretório /tmp como um volume dentro do contêiner
VOLUME /tmp

# Expõe a porta 8080 no contêiner
EXPOSE 8080

# Define a variável de argumento JAR_FILE com o caminho para o arquivo JAR
ARG JAR_FILE=target/gestao-residuos-0.0.1-SNAPSHOT.jar

# Copia o arquivo JAR especificado pelo ARG JAR_FILE para o contêiner com o nome app.jar
ADD ${JAR_FILE} app.jar

# Define o comando de entrada para executar o aplicativo Spring Boot
ENTRYPOINT ["java","-jar","/app.jar"]


# O comando docker build -t gestao_residuos:v1 . serve para construir uma nova imagem Docker a partir de um Dockerfile
# presente no diretório atual (.) e atribuir a essa imagem o nome gestao_residuos com a tag v1.

# Abra o terminal e rode o comando o comando da aplicação para criar uma image do docker
#   docker build -t gestao_residuos:v1 .

# Vamos executar essa aplicação a partir da imagem docker que criamos que e a gestao_residuos:v1 - abra o terminal prompt ou pwershell

# Listar todas as imagens Docker disponíveis localmente
# docker image ls

# Listar todos os containers Docker, incluindo os parados
# docker container ls -a

# Parando o container
# docker container ls

# Executar o container 'meu-container' a partir da imagem 'gestao_residuos:v1' em segundo plano (-d), mapeando a porta 8080
# do host para a porta 8080 do contêiner (-p 8080:8080) e nomeando-o como 'meu-container'
# docker container run -d -p 8080:8080 --name meu-container gestao_residuos:v1

# Listar todos os containers em execução
# docker container ls

# Agora vamos testar uma requisição: endpoint para ver se o container esta rodando