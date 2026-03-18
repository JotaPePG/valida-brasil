package br.edu.posgraduacao.validador;

public class ValidadorCNPJ {

    private static final int[] PESOS_PRIMEIRO_DIGITO  = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_SEGUNDO_DIGITO = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public boolean isValido(String cnpj) {
        if (cnpj == null || cnpj.isBlank()) {
            return false;
        }

        String numeros = cnpj.replaceAll("[.\\-/]", "");

        if (!numeros.matches("\\d{14}")) {
            return false;
        }

        if (numeros.chars().distinct().count() == 1) {
            return false;
        }

        if (!validarDigito(numeros, PESOS_PRIMEIRO_DIGITO, 12)) {
            return false;
        }

        return validarDigito(numeros, PESOS_SEGUNDO_DIGITO, 13);
    }

    private boolean validarDigito(String cnpj, int[] pesos, int posicao) {
        int soma = 0;

        for (int i = 0; i < pesos.length; i++) {
            soma += Character.getNumericValue(cnpj.charAt(i)) * pesos[i];
        }

        int resto = soma % 11;
        int digitoEsperado = (resto < 2) ? 0 : (11 - resto);
        int digitoInformado = Character.getNumericValue(cnpj.charAt(posicao));

        return digitoEsperado == digitoInformado;
    }
}