# 🛒 Gestor de Produtos - Spring Boot

![Java](https://img.shields.io/badge/Java-17+-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![SQLite](https://img.shields.io/badge/SQLite-Database-lightgrey)
![License](https://img.shields.io/badge/License-MIT-yellow)

API RESTful para **gestão de produtos** desenvolvida com **Spring Boot**. Permite operações **CRUD**, consultas avançadas e inclui clientes de teste em **Java** e **HTML/JavaScript**. Idealizada para demonstração de habilidades em backend, APIs REST, Spring Boot e integração com bancos de dados.

---

## 🎯 Visão Geral

O **Gestor de Produtos** foi desenvolvido para facilitar o gerenciamento de produtos em aplicações corporativas é um estudo acadêmicos.  
Ele suporta:

- Criação, leitura, atualização e exclusão de produtos.
- Consultas avançadas com filtros dinâmicos por nome, status e preço.
- Integração com banco **SQLite** para persistência leve.
- Testes automatizados e clientes de teste em Java e HTML/JS.

---

## 🔥 Funcionalidades

- **CRUD Completo**: criar, listar, atualizar e remover produtos.
- **Consultas Avançadas**:
  - Buscar por nome exato, parcial, prefixo ou sufixo.
  - Filtrar por nome e status.
  - Consultar por preço exato, maior, menor ou entre valores.
  - Obter soma total dos preços.
- **Clientes de Teste**:
  - Cliente Java (`CRUDJavaClient.java`)
  - Cliente Web (`client-crud.html`)
- **Banco de Dados**: SQLite com configuração automática
- **Testes Automatizados**: `GestorProdutosApplicationTests.java`

---

## 🛠️ Tecnologias

- **Backend**: Java 17+, Spring Boot, JPA/Hibernate
- **Banco de Dados**: SQLite
- **Build & Dependências**: Maven
- **Frontend/Testes**: HTML, JavaScript
- **Testes**: JUnit, Spring Boot Test

---

## 📡 Estrutura do Projeto

```
gestor-de-produtos-springboot/
│
├── src/
│   ├── main/java/com/example/gestorprodutos/
│   │   ├── GestorProdutosApplication.java    # Classe principal
│   │   ├── controller/ProdutoController.java
│   │   ├── model/Produto.java
│   │   ├── model/Status.java
│   │   └── repository/ProdutoRepository.java
│   ├── main/resources/
│   │   └── application.properties
│   └── test/java/...          # Testes automatizados
├── clients/
│   ├── client-crud.html       # Cliente de teste web
│   └── CRUDJavaClient.java    # Cliente de teste Java
├── pom.xml                    # Configurações Maven
└── README.md
```

---

## 📊 Modelo de Dados

### Entidade Produto
| Campo   | Tipo     | Descrição               |
|---------|----------|-------------------------|
| id      | Long     | Identificador único     |
| nome    | String   | Nome do produto         |
| preco   | Double   | Preço do produto        |
| status  | Status   | Status (ATIVO/INATIVO)  |

### Enum Status
```java
public enum Status {
    ATIVO, INATIVO
}
```

---

## 🗃️ Configuração do Banco de Dados

O projeto utiliza **SQLite** com configuração automática. O arquivo do banco é criado automaticamente na primeira execução.

**Propriedades principais:**
```properties
spring.datasource.url=jdbc:sqlite:meu_banco_de_dados.db
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.community.dialect.SQLiteDialect
```

---

## 📡 Endpoints da API

### Produtos

| Método | Endpoint                  | Descrição                       |
|--------|---------------------------|---------------------------------|
| GET    | `/produtos`               | Lista todos os produtos         |
| POST   | `/produtos`               | Cria um novo produto            |
| PUT    | `/produtos/{id}`          | Atualiza produto existente      |
| DELETE | `/produtos/{id}`          | Remove um produto               |
| GET    | `/produtos/{id}`          | Busca produto por ID            |

### Consultas Avançadas

| Método | Endpoint                                             | Descrição                                |
|--------|------------------------------------------------------|------------------------------------------|
| GET    | `/produtos/buscarPorNome?valor=...`                  | Busca por nome exato                     |
| GET    | `/produtos/buscarPorNomeContendo?valor=...`          | Busca por nome contendo valor            |
| GET    | `/produtos/buscarPorNomeEStatus?nome=...&status=...` | Busca por nome e status                  |
| GET    | `/produtos/buscarPorNomeComecandoCom?valor=...`      | Busca por prefixo                        |
| GET    | `/produtos/buscarPorNomeTerminandoCom?valor=...`     | Busca por sufixo                         |
| GET    | `/produtos/buscarPorPreco?valor=...`                 | Busca por preço exato                    |
| GET    | `/produtos/buscarPorPrecoMaiorQue?valor=...`         | Busca por preço maior que valor          |
| GET    | `/produtos/buscarPorPrecoMenorQue?valor=...`         | Busca por preço menor que valor          |
| GET    | `/produtos/buscarPorPrecoEntre?min=...&max=...`      | Busca por preço entre valores            |
| GET    | `/produtos/buscarPorStatus?valor=...`                | Busca por status (ATIVO/INATIVO)         |
| GET    | `/produtos/totalPreco`                               | Retorna soma total dos preços            |

---

## ⚡ Exemplos de Uso

### Criar Produto
```http
POST http://localhost:8080/produtos
Content-Type: application/json

{
  "nome": "Notebook",
  "preco": 3500.0,
  "status": "ATIVO"
}
```

### Listar Produtos
```http
GET http://localhost:8080/produtos
```

### Consultar Produtos por Nome Contendo
```http
GET http://localhost:8080/produtos/buscarPorNomeContendo?valor=nome
```

### Buscar Produtos com Preço Entre Valores
```http
GET http://localhost:8080/produtos/buscarPorPrecoEntre?min=100&max=1000
```

### Buscar Produtos por Status
```http
GET http://localhost:8080/produtos/buscarPorStatus?valor=INATIVO
```

### Somar Preços
```http
GET http://localhost:8080/produtos/totalPreco
```

---

## ⚠️ Tratamento de Erros

A API retorna respostas padronizadas para erros:

**Produto não encontrado (404):**
```json
{
    "timestamp": "2023-10-01T10:00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Produto não encontrado"
}
```

**Dados inválidos (400):**
```json
{
    "timestamp": "2023-10-01T10:00:00",
    "status": 400,
    "error": "Bad Request",
    "message": "Dados de entrada inválidos"
}
```

---

## 🧪 Clientes de Teste

### 🌐 Cliente Web (clients/client-crud.html)
- Interface gráfica para testar todos os endpoints
- Desenvolvido em HTML/JavaScript puro
- Permite executar operações CRUD visualmente

### ☕ Cliente Java (clients/CRUDJavaClient.java)
- Cliente em Java para testes automatizados
- Demonstra consumo da API programaticamente
- Ideal para integração com outros sistemas Java

---

## 🏃 Como Executar

### Pré-requisitos
* Java 17+
* Maven

### Passos
1. Clone o repositório:
```bash
git clone https://github.com/maspoxajoao/gestor-de-produtos-springboot.git
cd gestor-de-produtos-springboot
```

2. Compile e execute:
```bash
mvn clean install
mvn spring-boot:run
```

3. Acesse a API: [http://localhost:8080/produtos](http://localhost:8080/produtos)

4. Teste com os clientes:
   - Web: Abra `clients/client-crud.html` no navegador
   - Java: Execute `clients/CRUDJavaClient.java`

---

## ✅ Testes

* **Automatizados**: `GestorProdutosApplicationTests.java`
* **Manuais**: 
  - Cliente Web: `clients/client-crud.html`
  - Cliente Java: `clients/CRUDJavaClient.java`

---

## 📝 Logs e Monitoramento

A aplicação inclui logging detalhado:
- SQL gerado pelo Hibernate
- Endpoints acessados
- Operações de banco de dados
- Tratamento de exceções

Para ver os logs em tempo real:
```bash
mvn spring-boot:run
```

---

## 🔒 Considerações de Segurança

**Para ambiente de produção considere:**
- Adicionar autenticação JWT
- Implementar rate limiting
- Validar entrada de dados
- Usar HTTPS
- Configurar CORS adequadamente

*Nota: Esta versão é para fins educacionais*

---

## 💡 Aprendizados Técnicos

Este projeto demonstra:
- **Spring Boot**: Configuração automática, starters
- **JPA/Hibernate**: ORM, consultas derivadas, queries customizadas
- **REST**: Design de APIs, métodos HTTP, status codes
- **SQLite**: Configuração, operações CRUD
- **Testes**: JUnit, testes de integração
- **Maven**: Gerenciamento de dependências, build

---

## ✅ Checklist de Implementação

- [x] CRUD completo de produtos
- [x] Consultas por nome (exato, contendo, prefixo, sufixo)
- [x] Consultas por preço (exato, maior, menor, entre)
- [x] Consultas por status
- [x] Cálculo de soma total de preços
- [x] Clientes de teste (Java e Web)
- [x] Banco SQLite integrado
- [x] Testes automatizados
- [ ] Documentação Swagger/OpenAPI
- [ ] Deploy em nuvem

---

## 🔗 Links Úteis

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [Documentação SQLite JDBC](https://github.com/xerial/sqlite-jdbc)
- [Padrões REST](https://restfulapi.net/)

---

## 👨‍💻 Sobre o Autor

**João Victor Braga Soares** – estudante de Engenharia de Software, com experiência em desenvolvimento web, APIs REST e bancos de dados.

[Linkedin](https://www.linkedin.com/in/jvbraga-dev/) 
[Email](mailto:j0407victor@gmail.com)

---

## 📄 Licença
Este projeto está sob a licença MIT.


