
# Documentação: Como Fazer Deploy de Aplicação Java na Render

---

## 1. Introdução

O Render é uma plataforma de hospedagem cloud que permite deploy rápido e simples de aplicações web, APIs e serviços em diversas linguagens, incluindo Java.

Para aplicações Java, especialmente quando você gera um arquivo JAR executável, é importante configurar o projeto e a plataforma para que o deploy e a execução aconteçam corretamente.

---

## 2. Gerando um JAR Executável com Gradle ou Maven

### 2.1. Configurando a Classe Principal (`Main-Class`)

Para que o JAR gerado seja executável via comando `java -jar`, é necessário indicar no arquivo `MANIFEST.MF` qual classe contém o método `main`.

#### Gradle

No arquivo `build.gradle`, configure o bloco da tarefa `jar` para definir a `Main-Class`:

```groovy
tasks.jar {
    manifest {
        attributes["Main-Class"] = "me.dio.Application"
    }
}
```

> **Nota:** Substitua `"me.dio.Application"` pelo caminho completo da sua classe principal.

Se estiver usando Spring Boot com Gradle, utilize o `bootJar` que já faz essa configuração automaticamente:

```groovy
bootJar {
    mainClass = "me.dio.Application"
}
```

---

#### Maven

No arquivo `pom.xml`, configure o plugin `maven-jar-plugin` para definir a classe principal:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-jar-plugin</artifactId>
      <version>3.3.0</version>
      <configuration>
        <archive>
          <manifest>
            <mainClass>me.dio.Application</mainClass>
          </manifest>
        </archive>
      </configuration>
    </plugin>
  </plugins>
</build>
```

Após isso, gere o JAR executável:

```bash
mvn clean package
```

---

## 3. Criando o `Procfile`

### O que é?

O `Procfile` é um arquivo especial que indica à Render **qual comando executar para iniciar sua aplicação**.

### Onde criar?

Coloque o arquivo `Procfile` na raiz do seu projeto (mesma pasta do `build.gradle` ou `pom.xml`).

### Conteúdo do `Procfile`

Exemplo para sua aplicação Java:

```procfile
web: java -jar build/libs/santander-dev-week-2025-0.0.1-SNAPSHOT.jar
```

> **Importante:** Ajuste o caminho e nome do arquivo JAR conforme o seu projeto.

### Por que é necessário?

Sem o `Procfile`, o Render pode não saber qual comando usar para iniciar sua aplicação, causando erros no deploy.

---

## 4. Configurando a Porta da Aplicação

Render atribui dinamicamente uma porta para sua aplicação via variável de ambiente `PORT`. Para sua aplicação funcionar corretamente, ela deve escutar nessa porta.

### Spring Boot

Configure no arquivo `application.properties` ou `application.yml`:

```properties
server.port=${PORT:8080}
```

Isso fará sua aplicação escutar na porta definida pela Render (variável `PORT`) ou 8080 se não definida.

---

## 5. Comandos para Build e Deploy

### Gradle

```bash
./gradlew clean build
```

### Maven

```bash
mvn clean package
```

### Rodando localmente para testar

```bash
java -jar build/libs/seu-projeto.jar
```
ou

```bash
java -jar target/seu-projeto.jar
```

---

## 6. Configurando o Deploy na Render

- Faça o push do seu código para um repositório Git (GitHub, GitLab, etc.).
- Crie um novo serviço web na Render apontando para seu repositório.
- Configure o build command:
  - Gradle: `./gradlew build`
  - Maven: `mvn clean package`
- Configure o start command:
  - `java -jar build/libs/seu-projeto.jar` (Gradle)
  - `java -jar target/seu-projeto.jar` (Maven)
- Certifique-se de que o `Procfile` está na raiz do projeto e contém o comando correto.
- Faça deploy.

---

## 7. Resumo

| Passo                           | O que fazer                                  |
|--------------------------------|---------------------------------------------|
| Gerar JAR executável            | Configurar `Main-Class` no Gradle/Maven     |
| Criar `Procfile`                | Indicar comando de start `java -jar ...`   |
| Configurar porta da aplicação   | `server.port=${PORT:8080}` no Spring Boot   |
| Build da aplicação              | `./gradlew build` ou `mvn clean package`   |
| Configurar deploy no Render     | Build command + Start command + Procfile    |

---

Se precisar, posso ajudar a revisar seus arquivos para garantir que o deploy na Render funcione perfeitamente. Quer?  
