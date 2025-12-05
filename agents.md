# Guia para Agentes - API CNPJ e Leis

## 1. Introdução

Esta API fornece dois serviços principais:

1. **Consulta de CNPJ**: Permite consultar dados cadastrais de empresas brasileiras através do número do CNPJ.
2. **Consulta de Leis Ambientais**: Permite acessar uma base de dados com legislação ambiental brasileira, incluindo resoluções CONAMA, decretos, leis e instruções normativas.

A API foi desenvolvida em Java utilizando Spring Boot e está configurada para alta disponibilidade com cache e circuit breaker.

---

## 2. Endpoints Disponíveis

### 2.1 Consulta de CNPJ

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET    | `/`      | Retorna dados cadastrais de uma empresa pelo CNPJ |

### 2.2 Consulta de Leis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET    | `/leis`  | Retorna lista completa de leis ambientais disponíveis |

---

## 3. Formato de Requisições

### 3.1 Consulta de CNPJ

**Método**: `GET`

**URL**: `/{base_url}/?cnpj={cnpj}`

**Parâmetros de Query**:

| Parâmetro | Tipo   | Obrigatório | Descrição |
|-----------|--------|-------------|-----------|
| cnpj      | String | Sim         | Número do CNPJ (com ou sem formatação) |

**Headers Necessários**:

```http
Content-Type: application/json
Accept: application/json
```

**Formatos de CNPJ Aceitos**:
- Com máscara: `12.345.678/0001-90`
- Sem máscara: `12345678000190`

### 3.2 Consulta de Leis

**Método**: `GET`

**URL**: `/{base_url}/leis`

**Parâmetros**: Nenhum parâmetro necessário.

**Headers Necessários**:

```http
Accept: application/json
```

---

## 4. Formato de Respostas

### 4.1 Resposta de Consulta de CNPJ

**Estrutura da Resposta (JSON)**:

```json
{
  "status": "true",
  "result": {
    "nome": "RAZÃO SOCIAL DA EMPRESA",
    "fantasia": "NOME FANTASIA",
    "cep": "12345-678",
    "email": "contato@empresa.com.br",
    "telefone": "(11) 1234-5678",
    "atividade": {
      "text": "Descrição da atividade econômica principal"
    }
  }
}
```

**Campos da Resposta**:

| Campo               | Tipo   | Descrição |
|---------------------|--------|-----------|
| status              | String | Status da consulta ("true" para sucesso) |
| result.nome         | String | Razão social da empresa |
| result.fantasia     | String | Nome fantasia da empresa |
| result.cep          | String | CEP do endereço da empresa |
| result.email        | String | Email de contato da empresa |
| result.telefone     | String | Telefone de contato |
| result.atividade.text | String | Descrição da atividade econômica principal |

### 4.2 Resposta de Consulta de Leis

**Estrutura da Resposta (JSON Array)**:

```json
[
  {
    "ano": 2024,
    "documento": "RESOLUÇÃO CONAMA",
    "numero": "504",
    "ato": "Resolução CONAMA nº 504, de 8 de setembro de 2023",
    "ementa": "Art. 1º Fica revogada a Resolução CONAMA nº 502...",
    "area": "DCONAMA",
    "assunto": "CONAMA",
    "link": "https://pesquisa.in.gov.br/..."
  }
]
```

**Campos da Resposta**:

| Campo     | Tipo    | Descrição |
|-----------|---------|-----------|
| ano       | Integer | Ano de publicação da lei |
| documento | String  | Tipo do documento (DECRETO, LEI, RESOLUÇÃO CONAMA, etc.) |
| numero    | String  | Número do documento |
| ato       | String  | Identificação completa do ato normativo |
| ementa    | String  | Resumo ou descrição da lei |
| area      | String  | Área do MMA responsável |
| assunto   | String  | Tema principal da legislação |
| link      | String  | URL para acesso ao documento completo |

---

## 5. Exemplos Práticos

### 5.1 Exemplo de Consulta de CNPJ

**Requisição**:

```bash
curl -X GET "https://api.exemplo.com/?cnpj=12345678000190" \
  -H "Accept: application/json"
```

**Resposta de Sucesso (HTTP 200)**:

```json
{
  "status": "true",
  "result": {
    "nome": "EMPRESA EXEMPLO LTDA",
    "fantasia": "EXEMPLO",
    "cep": "01310-100",
    "email": "contato@exemplo.com.br",
    "telefone": "(11) 3456-7890",
    "atividade": {
      "text": "Comércio varejista de artigos diversos"
    }
  }
}
```

### 5.2 Exemplo de Consulta de Leis

**Requisição**:

```bash
curl -X GET "https://api.exemplo.com/leis" \
  -H "Accept: application/json"
```

**Resposta de Sucesso (HTTP 200)**:

```json
[
  {
    "ano": 2020,
    "documento": "RESOLUÇÃO CONAMA",
    "numero": "499",
    "ato": "Resolução CONAMA Nº 499/2020",
    "ementa": "Dispõe sobre o licenciamento da atividade de coprocessamento de resíduos em fornos rotativos de produção de clínquer.",
    "area": "CONAMA",
    "assunto": "AGENDA AMBIENTAL URBANA",
    "link": "http://conama.mma.gov.br/?option=com_sisconama&task=arquivo.download&id=798"
  },
  {
    "ano": 2018,
    "documento": "RESOLUÇÃO CONAMA",
    "numero": "491",
    "ato": "Resolução CONAMA Nº 491/2018",
    "ementa": "Dispõe sobre padrões de qualidade do ar.",
    "area": "CONAMA",
    "assunto": "AGENDA AMBIENTAL URBANA",
    "link": "http://conama.mma.gov.br/?option=com_sisconama&task=arquivo.download&id=766"
  }
]
```

---

## 6. Tratamento de Erros

### 6.1 Códigos de Status HTTP

| Código | Descrição | Causa Comum |
|--------|-----------|-------------|
| 200    | OK | Requisição bem-sucedida |
| 400    | Bad Request | CNPJ inválido ou malformado |
| 404    | Not Found | Endpoint não encontrado |
| 500    | Internal Server Error | Erro interno do servidor |
| 503    | Service Unavailable | Serviço externo indisponível (circuit breaker ativo) |

### 6.2 Mensagens de Erro Esperadas

**CNPJ Inválido (HTTP 400)**:

```json
{
  "message": "CNPJ inválido."
}
```

### 6.3 Validação de CNPJ

O CNPJ deve:
- Conter exatamente 14 dígitos numéricos
- Passar na validação do dígito verificador
- Formatos aceitos: `XX.XXX.XXX/XXXX-XX` ou `XXXXXXXXXXXXXX`

---

## 7. Boas Práticas

### 7.1 Para Agentes de IA

1. **Validação Prévia**: Sempre valide o formato do CNPJ antes de fazer a requisição.

2. **Cache de Respostas**: A API utiliza cache interno para a consulta de leis. Considere implementar cache local para evitar requisições repetidas.

3. **Tratamento de Erros**: Implemente tratamento adequado para todos os códigos de erro possíveis.

4. **Formato de CNPJ**: Prefira enviar o CNPJ sem formatação (apenas números) para evitar problemas de encoding.

5. **Consulta de Leis**: A lista de leis é estática e raramente atualizada. Considere fazer cache da resposta por um período mais longo.

### 7.2 Exemplo de Implementação em Python

```python
import requests

def consultar_cnpj(cnpj: str) -> dict:
    """Consulta dados de empresa pelo CNPJ."""
    # Remove formatação do CNPJ
    cnpj_limpo = ''.join(filter(str.isdigit, cnpj))
    
    if len(cnpj_limpo) != 14:
        raise ValueError("CNPJ deve conter 14 dígitos")
    
    response = requests.get(
        f"https://api.exemplo.com/?cnpj={cnpj_limpo}",
        headers={"Accept": "application/json"}
    )
    response.raise_for_status()
    return response.json()

def consultar_leis() -> list:
    """Retorna lista de leis ambientais."""
    response = requests.get(
        "https://api.exemplo.com/leis",
        headers={"Accept": "application/json"}
    )
    response.raise_for_status()
    return response.json()
```

### 7.3 Exemplo de Implementação em JavaScript

```javascript
async function consultarCnpj(cnpj) {
  // Remove formatação do CNPJ
  const cnpjLimpo = cnpj.replace(/\D/g, '');
  
  if (cnpjLimpo.length !== 14) {
    throw new Error('CNPJ deve conter 14 dígitos');
  }
  
  const response = await fetch(`https://api.exemplo.com/?cnpj=${cnpjLimpo}`, {
    headers: { 'Accept': 'application/json' }
  });
  
  if (!response.ok) {
    throw new Error(`Erro: ${response.status}`);
  }
  
  return response.json();
}

async function consultarLeis() {
  const response = await fetch('https://api.exemplo.com/leis', {
    headers: { 'Accept': 'application/json' }
  });
  
  if (!response.ok) {
    throw new Error(`Erro: ${response.status}`);
  }
  
  return response.json();
}
```

---

## 8. Limites e Throttling

### 8.1 Consulta de CNPJ

- A API de CNPJ depende de um serviço externo (Hub do Desenvolvedor)
- O circuit breaker é ativado após múltiplas falhas consecutivas
- Recomenda-se aguardar alguns segundos entre requisições consecutivas
- Em caso de erro 503, aguarde 30 segundos antes de tentar novamente

### 8.2 Consulta de Leis

- A consulta de leis utiliza cache interno
- Não há limite de requisições definido
- Os dados são carregados de um arquivo CSV local

### 8.3 Recomendações

- Implemente exponential backoff em caso de erros
- Não faça mais de 10 requisições por segundo para consulta de CNPJ
- Para consulta de leis, uma requisição diária é suficiente devido ao cache

---

## 9. Autenticação

### 9.1 Consulta de CNPJ

A consulta de CNPJ utiliza autenticação via token configurado no servidor. O token é gerenciado internamente pela aplicação através da variável de ambiente `TOKEN_RECEITAWS`.

**Para usuários da API**: Não é necessário enviar credenciais nas requisições. A autenticação é transparente.

### 9.2 Consulta de Leis

A consulta de leis não requer autenticação. O endpoint é público e pode ser acessado livremente.

---

## 10. Informações Adicionais

### 10.1 Documentação OpenAPI/Swagger

A API disponibiliza documentação interativa através do Swagger UI:

- **Swagger UI**: `{base_url}/swagger-ui.html`
- **OpenAPI JSON**: `{base_url}/v3/api-docs`

### 10.2 Tecnologias Utilizadas

- Spring Boot 3.3.1
- Spring Cloud OpenFeign (para chamadas HTTP)
- Resilience4j (para circuit breaker)
- Spring Cache
- OpenCSV (para leitura de dados de leis)

### 10.3 Contato e Suporte

Para dúvidas ou problemas com a API, consulte o repositório do projeto ou abra uma issue no GitHub.

---

## 11. Changelog

| Versão | Data | Descrição |
|--------|------|-----------|
| 1.0.0  | -    | Versão inicial com endpoints de CNPJ e Leis |
