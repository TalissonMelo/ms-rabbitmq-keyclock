# Spring Boot Microservices, RabbitMQ e Keycloak.

### Descrição do Projeto

Esse projeto tem como objetivo inicial demonstrar o uso das tecnologias Spring Boot, Spring Cloud, Docker, RabbitMQ, Eureka Discovery, Zuul Gateway, Spring Security, OAuth2, Keyclock, Spring Web, MySQL, Lombok, e Maven.

## Informações relacionadas a IDE utilizada.
Neste projeto usei a IDE "IntelliJ" Versão: IntelliJ IDEA Community Edition 2022.1

## Como replicar o projeto em meu ambiente de execução?
Faça o download do projeto e abra os microserviços na IDE "IntelliJ IDEA"


## API Gateway 

E uma aplicação que se registra no service registry, ela recebe uma notificação do cliente como por exemplo fazer um cadastro ou buscar um de cliente.
A mesma verifica em qual microservice ela tem que ir atraves do service registry que vai passar o servidor, porta, caminho e o endereço IP do microservice desejado, e encaminha a requisição ao microservice de endpoint desejado.

API GATEWAY faz com que o cliente nao consiga acessar diretamente nenhum microservice. 

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/api-gateway.jpg)

## Service Discovery

O Service Discovery é um dos principais princípios da arquitetura baseada em microservices. Imagine que temos muitos serviços dinamicamente distribuídos na rede. Onde as instâncias de serviços mudam dinamicamente devido a escala automática, falhas, atualizações e não temos controle de endereços IP e nem o nome da instância.

O ideal nessa situação seria que o serviço comunica-se ao servidor ou até mesmo a algum serviço que poderia chamá-lo que está disponível para ser requisitado.

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/service-registry.jpg)

## Service Broker 

O Service Broker fornece enfileiramento e mensagens confiáveis para as requisições e respostas a mesma ajuda os desenvolvedores a compor aplicativos de componentes independentes denominados serviços. Os aplicativos que exigem a funcionalidade exposta nesses serviços usam mensagens para interagir com os serviços.

O Service Broker usa TCP/IP para trocar mensagens entre instâncias. O Service Broker contém recursos para ajudar a impedir o acesso não autorizado da rede e para criptografar mensagens enviadas pela rede usa a comunicação assíncrona você envia a mensagem, caso o destinaria esteja indisponível o RabbitMQ guarda a mensagem e quando o service estiver disponível ele entrega a mensagem como no exemplo abaixo.

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/service-broker.jpg)

## RabbitMQ

O RabbitMQ é um software de enfileiramento de mensagens também conhecido como intermediário de mensagens ou gerenciador de filas.

Quando o usuário inserir informações do usuário na interface da web, o aplicativo da web criará uma mensagem que inclui todas as informações importantes que o usuário precisa em uma mensagem e a colocará em uma fila definida no RabbitMQ.

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/exemplo.jpg)

<b>Publisher</b> -> Publica a mensagem.

<b>Exchange</b> -> Pega a mensagem, processa e encaminha para fila.

<b>Queue</b> -> Fila 

<b>Consumer</b> ->  Receptor da mensagem.

## Fluxo de mensagens no RabbitMQ

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/rabbitmq.png)

1 - O usuário envia uma solicitação de criação de mensagem (JSON) para o aplicativo da web.

2 - O aplicativo da web (o produtor) envia uma mensagem ao RabbitMQ que inclui dados da solicitação, como nome e e-mail.

3 - Uma troca aceita as mensagens do produtor e as encaminha para as filas de mensagens corretas para a entrega da mensagem.

4 - O consumidor recebe a mensagem e inicia o processamento da mesma.

## Exchanges

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/exchanges-bidings-routing-keys.png)

1- O produtor publica uma mensagem para uma troca. Ao criar uma troca, o tipo deve ser especificado.

2 - A exchange recebe a mensagem e agora é responsável pelo roteamento da mensagem. A troca leva em consideração diferentes atributos de mensagem, como a chave de roteamento, dependendo do tipo de troca.

3 - As ligações devem ser criadas da troca para as filas. Nesse caso, há duas ligações para duas filas diferentes da troca. A troca roteia a mensagem para as filas dependendo dos atributos da mensagem.

4 - As mensagens permanecem na fila até serem tratadas por um consumidor

5 - O consumidor manipula a mensagem.

## Tipos de exchanges

![exemplo](https://github.com/TalissonMelo/ms-rabbitmq-keyclock/blob/main/ms-imagem/exchanges-topic-fanout-direct.png)

 <b> Direct: </b> A mensagem é roteada para as filas cuja chave de ligação corresponde exatamente à chave de roteamento da mensagem. 
 
 <b> Fanout: </b> Uma troca de fanout roteia mensagens para todas as filas vinculadas a ela.
 
 <b> Topic:</b> A troca de topic faz uma correspondência curinga entre a chave de roteamento e o padrão de roteamento especificado na ligação. 



