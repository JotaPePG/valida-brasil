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

### 1. Decisões técnicas mais relevantes na construção do pipeline

A primeira decisão relevante foi a escolha do **Java 25 como plataforma**. Por ser a versão LTS mais recente em 2026, garante suporte de longo prazo e compatibilidade com as ferramentas do ecossistema. Associado a isso, a distribuição **Temurin (Adoptium)** foi escolhida por ser a implementação OpenJDK mais amplamente adotada em ambientes corporativos e totalmente suportada pelo `actions/setup-java`.

A escolha do **Maven como gerenciador de build** foi motivada pela sua maturidade, padronização de estrutura de projetos e integração nativa com o GitHub Actions via cache de dependências. O cache configurado no próprio `setup-java` evita o download repetido das dependências a cada execução do pipeline, reduzindo o tempo de run de forma significativa.

O **JUnit 5 (Jupiter)** foi adotado por ser a versão moderna do framework, com suporte a anotações mais expressivas como `@Nested` e `@DisplayName`, que tornam os relatórios de teste muito mais legíveis e organizados — especialmente útil no contexto de CI, onde o log precisa comunicar falhas com clareza.

A decisão de **separar compilação e testes em estágios distintos** (stages 4 e 5) foi intencional: falhas de compilação são detectadas antes mesmo de tentar rodar os testes, tornando o feedback mais rápido e preciso. Isso segue o princípio de *fail fast* — uma das práticas centrais da Integração Contínua.

Por fim, o uso do `if: always()` no stage de relatório garante que o resumo dos testes seja publicado **mesmo quando o pipeline falha**, permitindo que o desenvolvedor veja exatamente quantos e quais testes quebraram sem precisar navegar pelos logs brutos.
 
---

### 2. Impactos da ausência de testes automatizados

Sem testes, a validação depende de verificações manuais — lentas e inconsistentes. Isso foi demonstrado na prática durante este trabalho: a inversão de um único operador lógico no `ValidadorCPF` quebrou o comportamento esperado silenciosamente. Em produção, esse tipo de regressão só seria descoberto por um usuário afetado. Além disso, a ausência de testes desencoraja refatorações, acelerando o acúmulo de dívida técnica.
 
---

### 3. Possibilidades de evolução para Entrega Contínua

O pipeline atual valida o código a cada commit. O próximo passo natural seria adicionar `mvn package` para gerar o `.jar` versionado e publicá-lo automaticamente no GitHub Packages. Em projetos maiores, seria possível evoluir para deploys automáticos com gates de aprovação entre ambientes e estratégias como *blue-green* para minimizar riscos em produção.
 
---

### 4. Riscos mitigados pela Integração Contínua

A CI elimina o *merge hell* ao incentivar integrações frequentes e pequenas. Resolve o problema do *"works on my machine"* ao garantir que o código é sempre validado em um ambiente padronizado. Previne regressões silenciosas ao rodar todos os testes a cada commit. E fornece rastreabilidade completa, permitindo identificar exatamente qual commit introduziu uma falha.