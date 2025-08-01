# Etapa 1: build com Gradle
FROM gradle:8.7.0-jdk17 AS builder
# Usa uma imagem oficial com Gradle e JDK 17 pré-instalados.
# Essa imagem é usada apenas para compilar o projeto.

WORKDIR /app
# Define o diretório de trabalho dentro do container como /app.

COPY --chown=gradle:gradle . .
# Copia todos os arquivos do diretório atual (projeto local) para o container,
# garantindo que o usuário e grupo 'gradle' seja o dono dos arquivos (evita problemas de permissão).

RUN gradle build -x test
# Executa o build do projeto, mas pula os testes (-x test).
# O resultado será um `.jar` dentro de build/libs/.

# Etapa 2: imagem leve apenas com o jar
FROM eclipse-temurin:17-jdk-alpine
# Usa uma imagem leve do Eclipse Temurin com JDK 17 e baseada em Alpine Linux.
# Ideal para produção, pois é mais enxuta.

WORKDIR /app
# Define o diretório de trabalho como /app também nessa etapa.

COPY --from=builder build/libs/santander-dev-week-2025.jar /app/app.jar
# Copia o `.jar` gerado na etapa de build do primeiro estágio para o segundo estágio (imagem final).

EXPOSE 8080
# Expõe a porta 8080 (a porta que a aplicação irá escutar).
# Essa instrução é apenas informativa para quem for rodar o container.

ENTRYPOINT ["java", "-jar", "app.jar"]
# Define o ponto de entrada: inicia a aplicação executando o `jar`.
# Como o WORKDIR é /app, podemos usar "app.jar" sem o caminho completo.