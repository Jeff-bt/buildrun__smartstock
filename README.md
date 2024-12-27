# Projeto Smart Stock 

Neste projeto, aprendi a:
- Integrar com MongoDB usando Spring Data e Docker
- Ler e processar arquivos CSV com OpenCSV
- Implementar regras de negócio para automação de recompra
- Utilizar OpenFeign para chamadas a APIs e autenticação com reuso inteligente de tokens
- Criar Mock de APIs inteligentes com Mockoon para testes robustos

Foi uma jornada incrível que consolidou meus 
conhecimentos em Spring Boot e desenvolvimento backend 🚀

Agradeço ao Bruno Garcia e a Build & Run pelo apoio! 🙌

## Problema

![img_problem](https://github.com/Jeff-bt/buildrun__smartstock/blob/main/assets/problem_smartStock.png)

## Solução

![img_solution](https://github.com/Jeff-bt/buildrun__smartstock/blob/main/assets/solution_smartStock.png)

## Estrutura

```

        │   ├── resources/
        │   │   └── application.properties
        │   └── java/
        │       └── jeff/
        │           └── dev/
        │               └── smartstock/
        │                   ├── domain/
        │                   │   └── CsvStockItem.java
        │                   ├── exception/
        │                   │   └── SmartStockException.java
        │                   ├── SmartstockApplication.java
        │                   ├── entity/
        │                   │   └── PurchaseRequestEntity.java
        │                   ├── controller/
        │                   │   ├── dto/
        │                   │   │   └── StartDto.java
        │                   │   └── StartController.java
        │                   ├── repository/
        │                   │   └── PurchaseRequestRepository.java
        │                   ├── config/
        │                   │   └── AppConfig.java
        │                   ├── service/
        │                   │   ├── SmartStockService.java
        │                   │   ├── AuthService.java
        │                   │   ├── ReportService.java
        │                   │   └── PurchaseSectorServicer.java
        │                   └── client/
        │                       ├── PurchaseSectorClient.java
        │                       ├── dto/
        │                       │   ├── PurcahseResponse.java
        │                       │   ├── AuthRequest.java
        │                       │   ├── AuthResponse.java
        │                       │   └── PurchaseRequest.java
        │                       └── AuthClient.java

```
