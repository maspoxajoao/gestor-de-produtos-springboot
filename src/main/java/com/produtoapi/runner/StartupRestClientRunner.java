package com.produtoapi.runner;

import org.springframework.boot.CommandLineRunner; // importa a interface CommandLineRunner para executar código na inicialização
import org.springframework.stereotype.Component; // importa a anotação Component para registrar a classe como um bean gerenciado pelo Spring
import org.springframework.web.client.RestTemplate; // importa a classe RestTemplate para fazer requisições HTTP

import com.produtoapi.model.Produto;

import java.util.Arrays;
import java.util.List;

@Component // Registra esta classe como um componente Spring
public class StartupRestClientRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception{
        System.out.println("RestClient sendo executado");

            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/produtos"; // URL do endpoint da API REST que retorna a lista de produtos

            Produto[] produtosArray = restTemplate.getForObject(url, Produto[].class);
        assert produtosArray != null; // Verifica se o array não é nulo
        List<Produto> produtos = Arrays.asList(produtosArray);

            produtos.forEach(produto -> System.out.println("ID:" + produto.getId() + " " + produto.getNome() + " : " + produto.getPreco() ));
    }
}
