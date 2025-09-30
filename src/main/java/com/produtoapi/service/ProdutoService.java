package com.produtoapi.service;

import com.produtoapi.model.Produto;
import com.produtoapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Indica que esta classe é um "Service" do Spring (camada de regras de negócio)
@Service
public class ProdutoService {

    // Injeta automaticamente o repositório (camada de acesso ao banco)
    @Autowired
    private ProdutoRepository produtoRepository;

    // Retorna todos os produtos do banco (SELECT * FROM produto)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Salva um novo produto no banco ou atualiza se já existir (INSERT ou UPDATE)
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Remove um produto do banco pelo ID (DELETE FROM produto WHERE id = ?)
    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }


    // Atualiza os dados de um produto existente
    // 1. Verifica se o produto existe pelo ID
    // 2. Se existir, define o ID no objeto recebido e salva (faz um UPDATE)
    // 3. Se não existir, lança uma exceção
    public Produto atualizar(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        } else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    // Busca um produto específico pelo ID (SELECT * FROM produto WHERE id = ?)
    // Retorna Optional -> pode ter produto ou pode estar vazio
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    // Salva uma lista de produtos no banco (INSERT em lote)
    // Retorna a lista de produtos salvos (com IDs gerados)
    public List<Produto> salvarLista(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    // Busca produtos pelo nome (SELECT * FROM produto WHERE nome = ?)
    public List<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    // Busca produtos cujo nome contém a string fornecida (SELECT * FROM produto WHERE nome LIKE %?%)
    public List<Produto> findByNomeContaining(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    // Busca produtos pelo nome e status (SELECT * FROM produto WHERE nome = ? AND status = ?)
    public List<Produto> findByNomeAndStatus(String nome, String status) {
        return produtoRepository.findByNomeAndStatus(nome, status);
    }

    // Busca produtos cujo nome começa com o prefixo fornecido (SELECT * FROM produto WHERE nome LIKE ?%)
    public List<Produto> findByNomeStartingWith(String prefix) {
        return produtoRepository.findByNomeStartingWith(prefix);
    }

    // Busca produtos cujo nome termina com o sufixo fornecido (SELECT * FROM produto WHERE nome LIKE %?)
    public List<Produto> findByNomeEndingWith(String suffix) {
        return produtoRepository.findByNomeEndingWith(suffix);
    }

    // Busca produtos pelo preço (SELECT * FROM produto WHERE preco = ?)
    public List<Produto> findByPreco(Double preco) {
        return produtoRepository.findByPreco(preco);
    }

    // Busca produtos com preço maior que o valor fornecido (SELECT * FROM produto WHERE preco > ?)
    public List<Produto> findByPrecoGreaterThan(Double preco) {
        return produtoRepository.findByPrecoGreaterThan(preco);
    }
    // Busca produtos com preço menor que o valor fornecido (SELECT * FROM produto WHERE preco < ?)
    public List<Produto> findByPrecoLessThan(Double preco) {
        return produtoRepository.findByPrecoLessThan(preco);
    }

    // Obtém a soma total dos preços de todos os produtos (SELECT SUM(preco) FROM produto)
    public Double findTotalPreco() {
        return produtoRepository.findTotalPreco();
    }
}