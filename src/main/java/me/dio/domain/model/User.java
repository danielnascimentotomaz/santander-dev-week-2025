package me.dio.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;


@Entity(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @OneToOne(cascade = CascadeType.ALL)  // Quando um usuário for deletado a sua conta será deletado
    private Account account;


     // Define um relacionamento obrigatório de um-para-muitos com a entidade News.
    // Todas as operações (salvar, atualizar, remover) serão aplicadas também à lista de Feature (CascadeType.ALL).
   // As fetures associadas serão carregadas imediatamente com a entidade principal (FetchType.EAGER).
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Feature> features;


    @OneToOne(cascade = CascadeType.ALL)  // Quando um usuário for deletado a seu cartão  será deletado
    private Card card;

    @Column(nullable = false)
     // Define um relacionamento obrigatório de um-para-muitos com a entidade News.
    // Todas as operações (salvar, atualizar, remover) serão aplicadas também à lista de News (CascadeType.ALL).
   // As notícias associadas serão carregadas imediatamente com a entidade principal (FetchType.EAGER).
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<News> news;

    public User() {
    }

    public User(Long id, String name, Account account, List<Feature> features, Card card, List<News> news) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.features = features;
        this.card = card;
        this.news = news;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }
}
