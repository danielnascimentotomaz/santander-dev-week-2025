
# 🧪 Santander Dev Week 2025 - API Java

Este projeto foi criado como base para a **Santander Dev Week 2025**, usando Java moderno e Spring Boot 3.5 com as dependências essenciais para criar uma API RESTful profissional.

---

## ⚙️ Configurações do Projeto

| Campo           | Valor                                |
|----------------|--------------------------------------|
| **Group**       | `me.dio`                              |
| **Artifact**    | `santander-dev-week-2025`             |
| **Name**        | `santander-dev-week-2025`             |
| **Descrição**   | `Java RESTful API criada para a Santander Dev Week 2023` |
| **Package base**| `me.dio.santander-dev-week-2025`      |
| **Packaging**   | `Jar`                                 |
| **Java version**| `17`                                  |
| **Spring Boot** | `3.5.3` *(ou a versão que desejar)*   |

---

## 📦 Dependências Incluídas

- ✅ **Spring Web** → Estrutura para criar APIs REST com Spring MVC.
- ✅ **Spring Data JPA** → Persistência de dados com Hibernate e JPA.
- ✅ **PostgreSQL Driver** → Comunicação com banco de dados PostgreSQL.
- ✅ **H2 Database** → Banco de dados em memória para testes locais.

---

## 🏁 Como iniciar

```bash
# Clone o projeto
git clone https://github.com/seu-usuario/santander-dev-week-2025.git

# Acesse o diretório
cd santander-dev-week-2025

# Execute o projeto (Maven Wrapper)
./mvnw spring-boot:run
```

---

## 🧪 Banco de Dados H2

Você pode acessar o console do H2 em:

```
http://localhost:8080/h2-console
```

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(em branco)*

---

## 📝 Notas

- Este projeto está preparado para usar **PostgreSQL em produção** e **H2 em desenvolvimento/testes**.
- O uso do **Spring Boot 3.5.x** garante compatibilidade com Jakarta EE 10.

---

## 🤝 Licença

Distribuído sob a licença MIT. Sinta-se à vontade para usar, estudar e contribuir!

---

## 🧑‍💻 Autor

Feito com 💻 por [Seu Nome](https://github.com/seu-usuario)
