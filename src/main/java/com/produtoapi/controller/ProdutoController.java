package com.produtoapi.controller;

import com.produtoapi.model.Produto; // importa a classe Produto do pacote model
import com.produtoapi.service.ProdutoService; // importa a classe ProdutoService do pacote service
import org.springframework.beans.factory.annotation.Autowired; // importa a anotação Autowired para injeção de dependência
import org.springframework.web.bind.annotation.*; // importa anotações para definir controladores REST e mapear endpoints

import java.util.List;
import java.util.Optional;

// Permite requisições de qualquer origem (CORS)
@CrossOrigin(origins = "*")
// Define que esta classe é um controlador REST (vai expor endpoints HTTP)
@RestController
// Define a rota base da API -> todos os endpoints começam com /produtos
@RequestMapping("/produtos")
public class ProdutoController {

    // Injeta (autowired) o serviço que contém a lógica de negócio dos produtos
    @Autowired
    private ProdutoService produtoService;

    // Endpoint GET /produtos
    // Lista todos os produtos cadastrados
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    // Endpoint POST /produtos
    // Recebe um produto no corpo da requisição e salva no banco
    @PostMapping
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    // Endpoint PUT /produtos/{id}
    // Atualiza os dados de um produto já existente pelo ID
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }

    // Endpoint DELETE /produtos/{id}
    // Remove um produto do banco de dados pelo ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoService.deletar(id);
    }

    // Endpoint GET /produtos/{id}
    // Busca um produto específico pelo ID
    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable Long id) { return produtoService.findById(id);}

    // Endpoint POST /produtos/lista
    // Recebe uma lista de produtos no corpo da requisição e salva todos no banco
    @PostMapping("/salvarLista")
    public List<Produto> salvarLista(@RequestBody List<Produto> produtos) {
        return produtoService.salvarLista(produtos);
    }

    // Endpoint GET /produtos/buscarPorNome
    // Busca produtos pelo nome exato informado
    @GetMapping("/buscarPorNome")
    public List<Produto> findByNome(@RequestParam String valor) {
        return produtoService.findByNome(valor);
    }

    // Endpoint GET /produtos/buscarPorNomeContendo
    // Busca produtos cujo nome contenha o valor informado (parcial)
    @GetMapping("/buscarPorNomeContendo")
    public List<Produto> findByNomeContendo(@RequestParam String valor) {
        return produtoService.findByNomeContaining(valor);
    }

    // Endpoint GET /produtos/buscarPorNomeEStatus
    // Busca produtos pelo nome e status informados
    @GetMapping("/buscarPorNomeEStatus")
    public List<Produto> findByNomeEStatus(@RequestParam String nome, @RequestParam String status) {
        return produtoService.findByNomeAndStatus(nome, status);
    }

    // Endpoint GET /produtos/buscarPorNomeComecandoCom
    // Busca produtos cujo nome comece com o valor informado
    @GetMapping("/buscarPorNomeComecandoCom")
    public List<Produto> findByNomeComecandoCom(@RequestParam String valor) {
        return produtoService.findByNomeStartingWith(valor
        );
    }

    // Endpoint GET /produtos/buscarPorNomeTerminandoCom
    // Busca produtos cujo nome termine com o valor informado
    @GetMapping("/buscarPorNomeTerminandoCom")
    public List<Produto> findByNomeTerminandoCom(@RequestParam String valor) {
        return produtoService.findByNomeEndingWith(valor);
    }

    // Endpoint GET /produtos/buscarPorPreco
    // Busca produtos pelo preço exato informado
    @GetMapping("/buscarPorPreco")
    public List<Produto> findByPreco(@RequestParam Double valor) {
        return produtoService.findByPreco(valor);
    }

    // Endpoint GET /produtos/buscarPorPrecoMaiorQue
    // Busca produtos com preço maior que o valor informado
    @GetMapping("/buscarPorPrecoMaiorQue")
    public List<Produto> findByPrecoMaiorQue(@RequestParam Double valor) {
        return produtoService.findByPrecoGreaterThan(valor);
    }

    // Endpoint GET /produtos/buscarPorPrecoMenorQue
    // Busca produtos com preço menor que o valor informado
    @GetMapping("/buscarPorPrecoMenorQue")
    public List<Produto> findByPrecoMenorQue(@RequestParam Double valor) {
        return produtoService.findByPrecoLessThan(valor);
    }

    // Endpoint GET /produtos/totalPrecos
    // Retorna a soma total dos preços de todos os produtos
    @GetMapping("/totalPrecos")
    public Double findTotalPrecos() {
        return produtoService.findTotalPreco();
    }
}