# Valida Brasil

![CI](https://github.com/JotaPePG/valida-brasil/actions/workflows/ci.yml/badge.svg)

Biblioteca Java para validação de CPF e CNPJ seguindo o algoritmo oficial da Receita Federal.

Projeto prático de pós-graduação para demonstração de pipeline de Integração Contínua com GitHub Actions.

---

## Como rodar localmente

**Pré-requisitos:** Java 25 e Maven instalados.

```bash
# Clonar o repositório
git clone https://github.com/JotaPePG/valida-brasil.git
cd valida-brasil

# Compilar
mvn compile

# Rodar os testes
mvn test
```

---

## Estrutura do projeto

```
valida-brasil/
├── .github/workflows/ci.yml
├── src/
│   ├── main/java/.../validador/
│   │   ├── ValidadorCPF.java
│   │   └── ValidadorCNPJ.java
│   └── test/java/.../validador/
│       ├── ValidadorCPFTest.java
│       └── ValidadorCNPJTest.java
└── pom.xml
```

---

## Reflexão Técnica