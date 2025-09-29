# 🧠 MindTek API – Backend

Este repositório contém o código-fonte da **API RESTful** que serve como backend para o aplicativo de bem-estar emocional **MindTek**.  
Desenvolvida com **Kotlin** e **Spring Boot**, esta API é responsável por processar, armazenar e analisar os dados de check-in dos usuários de forma **segura e anônima**.

---

## 🚀 Tecnologias Utilizadas
- **Linguagem:** Kotlin  
- **Framework:** Spring Boot  
- **Banco de Dados:** MongoDB (NoSQL), escolhido pela flexibilidade para armazenar os documentos de check-in.  

### Módulos Principais
- **Spring Web** → construção dos endpoints RESTful.  
- **Spring Data MongoDB** → integração nativa com o banco de dados.  
- **Spring Security** → controle de acesso e proteção dos endpoints *(não implementado nesta versão)*.  

---

## 💡 Como Executar o Projeto Localmente

### ✅ Pré-requisitos
- IntelliJ IDEA (Community ou Ultimate)  
- JDK 17 ou superior  
- Docker Desktop instalado e em execução  

---

### 🔹 1. Inicie o Banco de Dados com Docker
O projeto está configurado para se conectar a uma instância do **MongoDB**.  
A forma mais fácil de iniciar uma é usando o **Docker**:

```bash
docker run --name mindtek-mongo -p 27017:27017 \
-e MONGO_INITDB_ROOT_USERNAME=admin \
-e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo
```
Este comando irá baixar e iniciar um container **MongoDB**, que estará acessível em `localhost:27017`.

---

### 🔹 2. Execute a Aplicação
1. Clone este repositório.  
2. Abra o projeto na **IntelliJ IDEA**.  
3. Aguarde o **Gradle** sincronizar todas as dependências.  
4. Encontre e execute o arquivo:  
   ```bash
   MindtekApiApplication.kt
   ```
   O servidor iniciará e estará acessível em:  
[http://localhost:8080](http://localhost:8080)  

---

## 📄 Documentação da API
A API segue os padrões **REST** e está versionada.  

**URL Base:** `/v1`

| Endpoint                     | Método | Descrição                            | Autenticação |
|-------------------------------|--------|--------------------------------------|--------------|
| `/check-ins`                  | POST   | Registra um novo check-in emocional  | Nenhuma      |
| `/check-ins`                  | GET    | Busca o histórico do usuário         | Nenhuma      |
| `/support/channels`           | GET    | Lista os canais de apoio             | Nenhuma      |
| `/reports/psychosocial-risks` | GET    | Gera um relatório agregado de riscos | Nenhuma      |

---

### 📌 Exemplo de Requisição: **POST /v1/check-ins**

**Header:**
```http
X-User-ID: id-unico-do-usuario
```
**Body (JSON):**
```json
{
  "moodScore": 8,
  "emoji": "😃",
  "comment": "Tive um dia produtivo!",
  "extraAnswers": {
    "esgotado": "Não",
    "eficiencia": "Sim"
  }
}
```

## 🔒 Privacidade e Segurança
- **Anonimato do Usuário:** A identificação é feita via header `X-User-ID`, garantindo que nenhum dado pessoal seja armazenado.  
- **Dados Agregados:** O endpoint de relatórios trabalha apenas com dados estatísticos, impedindo a identificação de respostas individuais, em conformidade com a **NR-1**.  

---

🔗 Backend desenvolvido para a solução **MindTek**.  

