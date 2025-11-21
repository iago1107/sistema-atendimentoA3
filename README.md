# 🍞 Sistema de Pedidos para Padaria (API Back-end)

Este projeto é um sistema de API REST para o gerenciamento de pedidos de uma padaria, construído com **Java 21** e **Spring Boot 3.x**.

> 🚧 **Status do Projeto:** Em Fase de Teste (API Funcional) 🚧

## 🎓 Contexto Acadêmico

Este projeto foi desenvolvido como atividade para o curso de **Programação de Soluções Computacionais e Modelagem de Software** da **Faculdade da Saúde e Ecologia Humana (FASEH)**, localizada em Vespasiano/MG.

## 🙎🏻‍♂️ Membros da Equipe:
 - Gustavo Moreira 
 - Gabriel Peres 
 - Isaut Van Der Faasen 
 - Matheus Felipe 
 - Estevão Gomes 
 - Iago Gomes 
 - Arthur Streich 
 - Vitor Carlos 

---

## 🎯 O Problema a Ser Resolvido

O projeto nasceu com uma ideia de pegar uma empresa e identificar um tipo de problema que ela tem como um processo , sistema obsoleto , algum setor com má comunicação com cliente,...entre outros.
Nossa análise para os Desenvolvimento começou pela entrevista no Local aonde identificou os principais desafios do atendimento atual da Padaria:

**Entrevista:**

* **Dependência de Atendimento Manual:** 
"Atualmente,Os clientes fazem pedidos pessoalmente na lanchonete ou por telefone".

* **Atendimento Lento:** Um sistema digital foi sugerido:
 "o cliente poderia escolher os produtos sem pressa e sem 'agarrar' no atendimento, já que sempre há clientes que ficam indecisos".

* **Falta de Marketing:** 
"Os clientes só descobrem as novidades e promoções indo ao estabelecimento", pois a padaria não utiliza placas ou redes sociais.

* ** A comunicação entre o balcão de atendimento e a produção é um desafio:** 
"A padaria fica no Pátio e a produção no 4º Andar; a comunicação é feita pelo telefone".

A **solução** proposta é esta API, que serve como base para um futuro aplicativo ou site, digitalizando o cardápio, automatizando pedidos e melhorando a comunicação.

---

## 🚀 Funcionalidades Implementadas (Casos de Uso)

Este projeto foi modelado para cobrir os seguintes casos de uso:

### 👤 Cliente
* **Fazer Cadastro:** Criar uma nova conta de cliente.
* **Fazer Login:** Autenticar-se no sistema.
* **Gerenciar Endereço:** Adicionar ou atualizar seu endereço de entrega.
* **Fazer Pedido:** Montar um carrinho (com verificação de estoque) e submeter um novo pedido, escolhendo a forma de pagamento.
* **Acompanhar Pedido:** Ver o status atual de seus pedidos.

### 🧑‍🍳 Atendente
* **Fazer Login (Funcionário):** Acessar o sistema com credenciais de funcionário.
* **Acompanhar Fila de Pedidos:** Ver todos os pedidos com status `PENDENTE`.
* **Processar Pedido:** Atualizar o status de um pedido (ex: `EM_PREPARO`, `PRONTO_PARA_ENTREGA`).

### 🛠️ Administrador
* **Fazer Login (Admin):** Acessar o sistema com credenciais de administrador.
* **Gerenciar Contas de Funcionários:** Cadastrar novos atendentes ou administradores.
* **Gerenciar Cardápio (Produtos):** Criar, editar e desativar produtos do cardápio.
* **Gerenciar Estoque:** Atualizar a `quantidade_estoque` dos produtos.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.x**
* **Spring Data JPA:** Para persistência de dados.
* **Spring Security:** Para autenticação e autorização (atualmente em modo de teste).
* **MariaDB:** Como banco de dados relacional.
* **Maven:** Para gerenciamento de dependências.
* **Lombok:** Para reduzir código boilerplate.
* **Swagger (OpenAPI 3):** Para documentação e teste interativo da API.
* **Podman (e Podman Compose):** Para conteinirização e orquestração da aplicação e do banco de dados.

---

## 🗃️ Modelo de Dados (Entidades)

O banco de dados foi modelado com as seguintes entidades JPA:

* **`Cliente`**: Armazena os dados pessoais do cliente (`@OneToOne` com `Endereco`).
* **`Endereco`**: Armazena o endereço de entrega, ligado a um `Cliente`.
* **`Funcionario`**: Classe base abstrata (`@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`) para:
    * **`Atendente`**
    * **`Administrador`**
* **`Produto`**: Define um item do cardápio, incluindo `preco`, `CategoriaProduto` (Enum) e `quantidade_estoque`.
* **`Pedido`**: A entidade central, ligada ao `Cliente` e `EnderecoEntrega`, contendo `StatusPedido` (Enum), `FormaDePagamento` (Enum) e `valorTotal`.
* **`ItemPedido`**: A "linha" do pedido, ligando um `Pedido` a um `Produto` e armazenando `quantidade` e `precoUnitario`.

---

## 🏁 Como Rodar o Projeto (Tutorial para Grupo)

Este guia explica como configurar seu computador Windows do zero para rodar a API e o Banco de Dados juntos usando `podman-compose`.

### 1. Requisitos de Instalação (Fazer só uma vez)

Você precisa instalar 5 programas e configurar 1 regra de firewall.

#### A. Ativar Recursos de Virtualização do Windows (WSL)
1.  Pressione a tecla **Windows**, digite `Ativar ou desativar recursos do Windows` e abra.
2.  Na lista, **MARQUE** estas duas caixas:
    * `Plataforma de Máquina Virtual`
    * `Subsistema do Windows para Linux`
3.  Clique em **OK** e **REINICIE O COMPUTADOR**.

#### B. Instalar o WSL (O "Motor" do Linux)
1.  Abra o **PowerShell como Administrador** (Menu Iniciar > digite `PowerShell` > clique com o botão direito > "Executar como administrador").
2.  Execute o comando:
    ```powershell
    wsl --install
    ```
3.  **REINICIE O COMPUTADOR** novamente.

#### C. Instalar o Git
O Podman precisa do `ssh-keygen` (que vem com o Git) para criar a máquina virtual.
1.  Baixe e instale o **Git for Windows**: [https://git-scm.com/download/win](https://git-scm.com/download/win)
2.  Pode aceitar todas as opções padrão durante a instalação.

#### D. Instalar o Podman Desktop
1.  Baixe e instale o **Podman Desktop**: [https://podman-desktop.io/](https://podman-desktop.io/)
2.  Após instalar, abra o Podman Desktop e deixe-o iniciar. Ele deve detectar o WSL automaticamente (o ícone no canto inferior esquerdo deve ficar verde).

#### E. Instalar Python e `podman-compose`
1.  Baixe e instale o **Python**: [https://www.python.org/downloads/windows/](https://www.python.org/downloads/windows/)
2.  **IMPORTANTE:** Na primeira tela do instalador, **MARQUE A CAIXINHA** que diz **`Add Python.exe to PATH`**.
3.  Após instalar, **feche e abra um novo terminal** (CMD ou PowerShell).
4.  Instale o `podman-compose` usando o `pip`:
    ```cmd
    pip install podman-compose
    ```

#### F. Configurar o Firewall do Windows
O container da API precisa de permissão para falar com o container do Banco de Dados na porta 3306.
1.  Abra o **"Firewall do Windows com Segurança Avançada"** (pelo Menu Iniciar).
2.  Clique em **"Regras de Entrada"** (esquerda) > **"Nova Regra..."** (direita).
3.  **Tipo de Regra:** Selecione **Porta** > Avançar.
4.  **Protocolo e Portas:** Selecione **TCP**. Em **Portas locais específicas:**, digite `3306` > Avançar.
5.  **Ação:** Selecione **Permitir a conexão** > Avançar.
6.  **Perfil:** Deixe as três caixas marcadas > Avançar.
7.  **Nome:** Dê um nome (ex: `Liberar MariaDB (Podman)`) > Concluir.

**Setup Concluído!**

---

### 2. Passo a Passo para Executar o Projeto

Agora, toda vez que você quiser rodar o projeto, o processo é este:

**1. Abra o Terminal (Admin):**
Abra o **PowerShell (ou CMD) como Administrador**.

**2. Ligue o "Motor" do Podman:**
```bash
podman machine start
