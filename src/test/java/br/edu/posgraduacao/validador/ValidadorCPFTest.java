package br.edu.posgraduacao.validador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidadorCPF")
class ValidadorCPFTest {

    private ValidadorCPF validador;

    @BeforeEach
    void setUp() {
        validador = new ValidadorCPF();
    }

    // CPFs VÁLIDOS

    @Nested
    @DisplayName("Quando o CPF é válido")
    class CpfValido {

        @Test
        @DisplayName("deve aceitar CPF com pontuação (123.456.789-09)")
        void deveAceitarCpfComPontuacao() {
            assertTrue(validador.isValido("123.456.789-09"));
        }

        @Test
        @DisplayName("deve aceitar CPF apenas com dígitos (12345678909)")
        void deveAceitarCpfSemPontuacao() {
            assertTrue(validador.isValido("12345678909"));
        }

        @Test
        @DisplayName("deve aceitar outro CPF válido (529.982.247-25)")
        void deveAceitarOutroCpfValido() {
            assertTrue(validador.isValido("529.982.247-25"));
        }
    }

    // CPFs INVÁLIDOS — dígito verificador errado

    @Nested
    @DisplayName("Quando o dígito verificador está errado")
    class DigitosVerificadoresErrados {

        @Test
        @DisplayName("deve rejeitar CPF com primeiro dígito errado")
        void deveRejeitarPrimeiroDigitoErrado() {
            assertFalse(validador.isValido("123.456.789-00"));
        }

        @Test
        @DisplayName("deve rejeitar CPF com segundo dígito errado")
        void deveRejeitarSegundoDigitoErrado() {
            assertFalse(validador.isValido("123.456.789-01"));
        }

        @Test
        @DisplayName("deve rejeitar CPF com ambos os dígitos errados")
        void deveRejeitarAmbosDigitosErrados() {
            assertFalse(validador.isValido("123.456.789-00"));
        }
    }

    // CPFs INVÁLIDOS — formato

    @Nested
    @DisplayName("Quando o formato é inválido")
    class FormatoInvalido {

        @Test
        @DisplayName("deve rejeitar CPF nulo")
        void deveRejeitarCpfNulo() {
            assertFalse(validador.isValido(null));
        }

        @Test
        @DisplayName("deve rejeitar CPF vazio")
        void deveRejeitarCpfVazio() {
            assertFalse(validador.isValido(""));
        }

        @Test
        @DisplayName("deve rejeitar CPF com apenas espaços")
        void deveRejeitarCpfComEspacos() {
            assertFalse(validador.isValido("   "));
        }

        @Test
        @DisplayName("deve rejeitar CPF com letras")
        void deveRejeitarCpfComLetras() {
            assertFalse(validador.isValido("ABC.456.789-09"));
        }

        @Test
        @DisplayName("deve rejeitar CPF com menos de 11 dígitos")
        void deveRejeitarCpfCurto() {
            assertFalse(validador.isValido("1234567890"));
        }

        @Test
        @DisplayName("deve rejeitar CPF com mais de 11 dígitos")
        void deveRejeitarCpfLongo() {
            assertFalse(validador.isValido("123456789099"));
        }
    }

    // CPFs INVÁLIDOS — caso especial: todos os dígitos iguais

    @Nested
    @DisplayName("Quando todos os dígitos são iguais")
    class DigitosIguais {

        @Test
        @DisplayName("deve rejeitar 000.000.000-00")
        void deveRejeitarZeros() {
            assertFalse(validador.isValido("000.000.000-00"));
        }

        @Test
        @DisplayName("deve rejeitar 111.111.111-11")
        void deveRejeitarUns() {
            assertFalse(validador.isValido("111.111.111-11"));
        }

        @Test
        @DisplayName("deve rejeitar 999.999.999-99")
        void deveRejeitarNoves() {
            assertFalse(validador.isValido("999.999.999-99"));
        }
    }
}