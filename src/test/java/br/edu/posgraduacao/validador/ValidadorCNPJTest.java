package br.edu.posgraduacao.validador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidadorCNPJ")
class ValidadorCNPJTest {

    private ValidadorCNPJ validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorCNPJ();
    }

    // CNPJs VÁLIDOS

    @Nested
    @DisplayName("Quando o CNPJ é válido")
    class CnpjValido {

        @Test
        @DisplayName("deve aceitar CNPJ com pontuação (11.222.333/0001-81)")
        void deveAceitarCnpjComPontuacao() {
            assertTrue(validador.isValido("11.222.333/0001-81"));
        }

        @Test
        @DisplayName("deve aceitar CNPJ apenas com dígitos (11222333000181)")
        void deveAceitarCnpjSemPontuacao() {
            assertTrue(validador.isValido("11222333000181"));
        }

        @Test
        @DisplayName("deve aceitar outro CNPJ válido (45.997.418/0001-53)")
        void deveAceitarOutroCnpjValido() {
            assertTrue(validador.isValido("45.997.418/0001-53"));
        }
    }

    // CNPJs INVÁLIDOS — dígito verificador errado

    @Nested
    @DisplayName("Quando o dígito verificador está errado")
    class DigitosVerificadoresErrados {

        @Test
        @DisplayName("deve rejeitar CNPJ com primeiro dígito errado")
        void deveRejeitarPrimeiroDigitoErrado() {
            assertFalse(validador.isValido("11.222.333/0001-01"));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ com segundo dígito errado")
        void deveRejeitarSegundoDigitoErrado() {
            assertFalse(validador.isValido("11.222.333/0001-80"));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ com ambos os dígitos errados")
        void deveRejeitarAmbosDigitosErrados() {
            assertFalse(validador.isValido("11.222.333/0001-00"));
        }
    }

    // CNPJs INVÁLIDOS — formato

    @Nested
    @DisplayName("Quando o formato é inválido")
    class FormatoInvalido {

        @Test
        @DisplayName("deve rejeitar CNPJ nulo")
        void deveRejeitarCnpjNulo() {
            assertFalse(validador.isValido(null));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ vazio")
        void deveRejeitarCnpjVazio() {
            assertFalse(validador.isValido(""));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ com apenas espaços")
        void deveRejeitarCnpjComEspacos() {
            assertFalse(validador.isValido("   "));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ com letras")
        void deveRejeitarCnpjComLetras() {
            assertFalse(validador.isValido("AB.222.333/0001-81"));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ com menos de 14 dígitos")
        void deveRejeitarCnpjCurto() {
            assertFalse(validador.isValido("1122233300018"));
        }

        @Test
        @DisplayName("deve rejeitar CNPJ com mais de 14 dígitos")
        void deveRejeitarCnpjLongo() {
            assertFalse(validador.isValido("112223330001810"));
        }
    }

    // CNPJs INVÁLIDOS — caso especial: todos os dígitos iguais

    @Nested
    @DisplayName("Quando todos os dígitos são iguais")
    class DigitosIguais {

        @Test
        @DisplayName("deve rejeitar 00.000.000/0000-00")
        void deveRejeitarZeros() {
            assertFalse(validador.isValido("00.000.000/0000-00"));
        }

        @Test
        @DisplayName("deve rejeitar 11.111.111/1111-11")
        void deveRejeitarUns() {
            assertFalse(validador.isValido("11.111.111/1111-11"));
        }

        @Test
        @DisplayName("deve rejeitar 99.999.999/9999-99")
        void deveRejeitarNoves() {
            assertFalse(validador.isValido("99.999.999/9999-99"));
        }
    }
}