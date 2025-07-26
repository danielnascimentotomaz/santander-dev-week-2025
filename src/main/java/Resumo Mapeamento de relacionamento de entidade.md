
# Boas Práticas em Relacionamentos JPA (@OneToMany, @OneToOne)

Este guia documenta boas práticas adotadas pelo mercado para mapeamento de relacionamentos entre entidades no JPA/Hibernate, com foco em performance e manutenção.

---

## 🔄 Relacionamentos @OneToMany

### 📌 Exemplo com entidade `User`:

```java
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Feature> features = new ArrayList<>();
}
```

### 📌 Exemplo na entidade `Feature` (lado "muitos"):

```java
@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
```

### ✅ Boas práticas:
- Use `mappedBy` no lado "um" para evitar criação de tabelas intermediárias desnecessárias.
- Use `orphanRemoval = true` para deletar filhos órfãos automaticamente.
- Prefira `FetchType.LAZY` para coleções. Evita carregar todos os dados desnecessariamente.
- Inicialize listas para evitar `NullPointerException`.

---

## 🔗 Relacionamentos @OneToOne

### 📌 Exemplo com entidade `User` e `Account`:

```java
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;
}
```

```java
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
}
```

### ✅ Boas práticas:
- Sempre defina `@JoinColumn` para controlar nome da coluna de ligação.
- Em relacionamentos bidirecionais, use `mappedBy` no lado não proprietário.
- Use `FetchType.LAZY` para evitar sobrecarga, a menos que seja necessário imediato.

---

## 🚫 O que evitar:
- Evite `FetchType.EAGER` em coleções (`@OneToMany`). Pode causar **problemas de performance** e **N+1 select**.
- Evite deixar coleções nulas. Sempre inicialize com `new ArrayList<>()`.
- Evite deixar `mappedBy` de fora — isso cria **tabelas intermediárias indesejadas**.

---

## 🧠 Dicas Finais
- Utilize DTOs para expor dados no controller e evitar vazamento de entidades.
- Use `@Transactional` com cuidado ao manipular entidades com cascata.
- Analise com o time de backend/DBA o volume de dados para definir se `@OneToMany` deve existir ou ser tratado por consulta separada.

---

Desenvolvido com ❤️ para devs que se preocupam com a performance e manutenibilidade do sistema.
