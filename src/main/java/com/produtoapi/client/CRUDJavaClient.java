package com.produtoapi.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.produtoapi.model.Produto;

import java.util.Arrays;
import java.util.List;



public class CRUDJavaClient {

    private static final String BASE_URL = "http://localhost:8080/produtos";
    private RestTemplate restTemplate;

    public CRUDJavaClient(){
        this.restTemplate = new RestTemplate();
    }


    public  void listarTodos(){
        ResponseEntity<Produto[]> response = restTemplate.getForEntity(BASE_URL, Produto[].class);
        List<Produto> produtos = Arrays.asList(response.getBody());
        produtos.forEach( produto -> {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: " + produto.getPreco());
            System.out.println("Quantidade: " + produto.getQuantidade());
            System.out.println("Status: " + produto.getStatus());
            System.out.println("---------------------------");
        });
    }
    // metodo para salvar
    public Produto salvar(Produto produto){
        HttpEntity<Produto> request = new HttpEntity<>(produto);
        return restTemplate.postForObject(BASE_URL, request, Produto.class);
    }

    //metodo para deletar pelo id
    public  void deletar(long id){
        restTemplate.delete(BASE_URL + "/" + id);
    }

    //metodo para atualizar o produto
    public Produto atualizar (Long id, Produto produto){
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Produto> resquest = new HttpEntity<>(produto, headers);
        ResponseEntity<Produto> response = restTemplate.exchange(BASE_URL + "/" + id, HttpMethod.PUT,resquest, Produto.class);
        return response.getBody();
    }

    // metodo para buscar produto por id

    public Produto findById(long id){
        ResponseEntity<Produto> response = restTemplate.getForEntity(BASE_URL + "/" + id, Produto.class);
        return response.getBody();
    }

    public static void main(String[] args){
        CRUDJavaClient client = new CRUDJavaClient();

        /*
        // cria novo produto
        Produto novoProduto = new Produto();
        novoProduto.setNome("Skate p");
        novoProduto.setPreco(300.0);
        novoProduto.setQuantidade(200);
        novoProduto.setStatus("Disponivel");

        System.out.println("Criar um produto novo");
        client.salvar(novoProduto);
        client.listarTodos();
        */


        /*

        //atualizar o produto
        Produto atualizarProduto = client.findById(152);
        atualizarProduto.setNome("Skate p2");
        atualizarProduto.setPreco(320.0);
        atualizarProduto.setQuantidade(100);
        atualizarProduto.setStatus("Disponivel");

        System.out.println("Atualizar produto");
        client.atualizar(atualizarProduto.getId(), atualizarProduto);

        client.listarTodos();
        */


        //Deletar o produto
        System.out.println("Deletar Produto");
        client.deletar(152);

        //listar todos produtos
        client.listarTodos();

    }
}

