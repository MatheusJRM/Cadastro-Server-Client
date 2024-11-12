# Projeto Cliente-Servidor em Java com Sockets e Threads

## Descrição
Este projeto implementa uma aplicação cliente-servidor em Java com o uso de Sockets para comunicação, Threads para execução de processos paralelos, e acesso a banco de dados via JPA. A aplicação é composta por um servidor que gerencia conexões e processos simultâneos para atender múltiplos clientes.

## Estrutura do Projeto

### Servidor
- **Servidor com Sockets**: Implementação de um servidor Java que escuta em uma porta específica e aceita conexões de clientes.
- **Gerenciamento de múltiplos clientes**: Cada cliente conectado ao servidor é tratado em uma Thread independente, garantindo que múltiplas conexões possam ser atendidas simultaneamente.
- **Acesso ao Banco de Dados**: O servidor possui integração com um banco de dados usando JPA (Java Persistence API) para gerenciar e persistir informações.

### Cliente
- **Cliente Síncrono e Assíncrono**: O projeto implementa tanto um cliente síncrono, onde o cliente aguarda pela resposta do servidor antes de prosseguir, quanto um cliente assíncrono, onde o cliente utiliza uma Thread para receber respostas sem bloquear outras operações.
- **Interface Gráfica (JFrame)**: O cliente exibe as mensagens e informações recebidas do servidor em uma interface gráfica Swing (`JFrame`), enquanto as entradas de escolha são feitas pelo terminal.

## Tecnologias Utilizadas
- **Java Sockets** para comunicação de rede.
- **JPA (Java Persistence API)** para acesso ao banco de dados.
- **Swing** para a interface gráfica do cliente.
- **Threads** para permitir execução paralela no servidor e no cliente.
- **Banco SQL SERVER** para consulta e manipulação de dados.

## Como Executar

1. **Servidor**: Inicie o servidor que escutará na porta 4321 e gerenciará o banco de dados via JPA.
2. **Cliente**: Execute o projeto CadastroClientV2, que terá o console para gerenciar os comandos e JFrame para exibição de dados.
