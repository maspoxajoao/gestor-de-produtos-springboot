# Gestor de Produtos (Spring Boot)

API REST de gestão de produtos com CRUD completo, consultas derivadas do Spring Data JPA e dois clientes de teste (Java e HTML). Projeto de estudo em Java e Spring Boot.

## Tecnologias

- Java 22
- Spring Boot 3.5.5 (Web, Data JPA, Validation, DevTools)
- Hibernate com `hibernate-community-dialects`
- SQLite (arquivo local `meu_banco_de_dados.db`)
- Maven (wrapper incluído)

## Como executar

Pré-requisito: JDK 22 (o `pom.xml` define `java.version` 22).

```bash
git clone https://github.com/maspoxajoao/gestor-de-produtos-springboot.git
cd gestor-de-produtos-springboot
./mvnw spring-boot:run        # no Windows: mvnw.cmd spring-boot:run
```

A API sobe em `http://localhost:8080`. O arquivo do banco é criado na raiz do projeto, e a tabela é criada ou atualizada automaticamente (`ddl-auto=update`).

Ao iniciar, a classe `StartupRestClientRunner` faz uma chamada `GET /produtos` na própria API e imprime os produtos no console.

## Modelo de dados

Entidade `Produto`:

| Campo | Tipo | Observação |
|---|---|---|
| id | long | Gerado automaticamente |
| nome | String | Anotado com `@NotEmpty` |
| quantidade | int | |
| preco | double | |
| status | String | Texto livre (ex.: `ATIVO`, `INATIVO`) |

## Endpoints

Base: `/produtos`

### CRUD

| Método | Rota | Descrição |
|---|---|---|
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca por id |
| POST | `/produtos` | Cria um produto |
| POST | `/produtos/salvarLista` | Cria vários produtos de uma vez |
| PUT | `/produtos/{id}` | Atualiza um produto existente |
| DELETE | `/produtos/{id}` | Remove um produto |

### Consultas

| Método | Rota | Descrição |
|---|---|---|
| GET | `/produtos/buscarPorNome?valor=` | Nome exato |
| GET | `/produtos/buscarPorNomeContendo?valor=` | Nome contém o texto |
| GET | `/produtos/buscarPorNomeComecandoCom?valor=` | Nome começa com |
| GET | `/produtos/buscarPorNomeTerminandoCom?valor=` | Nome termina com |
| GET | `/produtos/buscarPorNomeEStatus?nome=&status=` | Nome e status |
| GET | `/produtos/buscarPorPreco?valor=` | Preço exato |
| GET | `/produtos/buscarPorPrecoMaiorQue?valor=` | Preço maior que |
| GET | `/produtos/buscarPorPrecoMenorQue?valor=` | Preço menor que |
| GET | `/produtos/totalPrecos` | Soma dos preços de todos os produtos |

### Exemplo

```http
POST http://localhost:8080/produtos
Content-Type: application/json

{
  "nome": "Notebook",
  "quantidade": 5,
  "preco": 3500.0,
  "status": "ATIVO"
}
```

```http
GET http://localhost:8080/produtos/buscarPorNomeContendo?valor=note
```

## Clientes de teste

Ficam em `src/main/java/com/produtoapi/client/`:

- `CRUDJavaClient.java`: cliente em Java (RestTemplate) que consome a API
- `client-crud.html`: tela HTML/JavaScript para testar o CRUD pelo navegador
- `client-web-listagem.html`: tela HTML/JavaScript de listagem

Para os clientes HTML, abra o arquivo no navegador com a API rodando (o controller libera CORS para qualquer origem).

## Estrutura

```
src/main/java/com/produtoapi/
├── GestorDeProsutosSpringbootApplication.java
├── controller/ProdutoController.java
├── service/ProdutoService.java
├── repository/ProdutoRepository.java
├── model/Produto.java
├── runner/StartupRestClientRunner.java
└── client/   (clientes de teste)
```

## Limitações conhecidas e próximos passos

- [ ] A validação `@NotEmpty` do nome ainda **não é aplicada**, porque o controller não usa `@Valid`
- [ ] Tratamento de erros: atualizar um id inexistente resulta em erro 500 (deveria ser 404), e `DELETE` de um id inexistente não retorna erro
- [ ] `status` como `enum` (`ATIVO` e `INATIVO`) em vez de texto livre
- [ ] Consulta por faixa de preço e por status
- [ ] Testes automatizados: existe apenas o teste de carregamento do contexto
- [ ] Documentação OpenAPI/Swagger
- [ ] Trocar `CORS *` por origens específicas

## Autor

**João Victor** · [LinkedIn](https://www.linkedin.com/in/jvbraga-dev/)
