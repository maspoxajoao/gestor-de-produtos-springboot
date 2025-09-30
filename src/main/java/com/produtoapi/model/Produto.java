package com.produtoapi.model;

import jakarta.persistence.Entity; // marca como um entidade persistente
import jakarta.persistence.GeneratedValue; // serve para especificar o valor da chave primaria
import jakarta.persistence.GenerationType; // define a estrategia de geracao do valor da chave primaria
import jakarta.persistence.Id; // marca o atributo como chave primaria
import jakarta.validation.constraints.NotEmpty; // valida se o campo nao esta vazio

// Define que esta classe é uma entidade JPA -> será uma tabela no banco de dados
@Entity
public class Produto {
    // Chave primária da tabela (ID do produto)
    @Id
    // O valor do ID será gerado automaticamente pelo banco/hibernate
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Campo "nome" do produto
    // Não pode ser vazio (validação automática pelo Bean Validation)
    @NotEmpty(message = "O nome do produto nao pode ser vazio")
    private String nome;
    private int quantidade;
    private double preco;
    private String status;

    // Construtor com parâmetros (facilita a criação de objetos Produto já preenchidos)
    public Produto(String nome, int quantidade, double preco, String status) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
        this.status = status;
    }
    // Construtor vazio (obrigatório para o JPA conseguir instanciar a entidade)
    public Produto() {
        // construtor vazio
    }

    // Métodos getters e setters -> permitem acessar e alterar os atributos da classe
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
