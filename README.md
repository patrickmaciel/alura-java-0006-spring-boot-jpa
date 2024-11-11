
![Programação-Formação Java](https://github.com/iasminaraujoc/3355-java-screenmatch-com-jpa/assets/84939115/3c51e000-962d-4dc9-97fc-1d384e2511a2)

# Java: persistência de dados e consultas com Spring Data JPA

Projeto desenvolvido no segundo curso da formação Avançando com Java da Alura

## 🔨 Objetivos do projeto

- [x] Evoluir no projeto Screenmatch, iniciado no primeiro curso da formação, criando um menu com várias opções;
- [x] Modelar as abstrações da aplicação através de classes, enums, atributos e métodos;
- [x] Consumir a API do ChatGPT;
- [x] Utilizar o Spring Data JPA para persistir dados no banco;
- [ ] Conhecer vários tipos de banco de dados e utilizar o PostgreSQL;
- [ ] Trabalhar com vários tipos de consultas ao banco de dados;
- [ ] Aprofundar na interface JPARepository

## Como rodar o projeto?

- Duplique o arquivo `config.properties.example` para `config.properties`
- Coloque sua API KEY da OpenAi nele
- Desce o beti!

## Melhorias

### É possível obter os dados de acessos, senhas, e keys diretamente das variaveis de ambiente do sistema

Não foi feito neste projeto.

Em contra partida você também pode buscar estes dados do config.properties.

```
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME} 
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}  
spring.datasource.driver-class-name=org.postgresql.Driver  
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect  
#spring.jpa.show-sql=true  
spring.jpa.hibernate.ddl-auto=update
```

```
System.getenv("OPENAI_APIKEY")
```