package com.produtoapi.repository;

import com.produtoapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// Interface responsável pelo acesso ao banco de dados da entidade Produto
// JpaRepository já fornece vários métodos prontos para CRUD (Create, Read, Update, Delete)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    /*
        // Não precisa implementar nada aqui, o JpaRepository já oferece:
        // - save() -> insere ou atualiza um produto
        // - findById() -> busca produto pelo ID
        // - findAll() -> lista todos os produtos
        // - deleteById() -> remove produto pelo ID
        // - existsById() -> verifica se existe produto com determinado ID
        //
        // Podemos adicionar consultas personalizadas aqui, se necessário, por exemplo:
        // List<Produto> findByStatus(String status);

     */

    // Consultas personalizadas usando a convenção de nomenclatura do Spring Data JPA
    List<Produto> findByNome(String nome); //Busca por nome
    List<Produto> findByNomeContaining(String nome); //Busca por nome contendo
    List<Produto> findByNomeAndStatus(String nome, String status); //Busca por nome e status
    List<Produto> findByNomeStartingWith(String prefix); //Busca por nome começando com prefixo
    List<Produto> findByNomeEndingWith(String suffix); //Busca por nome terminando com sufixo

    List<Produto> findByPreco(Double preco); //Busca por preço
    List<Produto> findByPrecoGreaterThan(Double preco); //Busca por preço maior que
    List<Produto> findByPrecoLessThan(Double preco); //Busca por preço menor que


    //usando annotations @Query para obter total de preços
    @Query("SELECT SUM(p.preco) FROM Produto p")
    Double findTotalPreco();
}
