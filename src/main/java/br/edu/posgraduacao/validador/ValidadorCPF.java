package br.edu.posgraduacao.validador;

public class ValidadorCPF {
    public boolean isValido(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return false;
        }

        String numeros = cpf.replaceAll("[.\\-]", "");

        if (!numeros.matches("\\d{11}")) {
            return false;
        };

        if (numeros.chars().distinct().count() == 1) {
            return false;
        }

        if (!validarDigito(numeros, 9)) {
            return false;
        }

        return validarDigito(numeros, 10);

    }

    private boolean validarDigito(String cpf, int posicao) {
        int soma = 0;
        int peso = posicao + 1;

        for (int i = 0; i < posicao; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso;
            peso--;
        }

        int resto = (soma * 10) % 11;
        int digitoEsperado = (resto == 10 || resto == 11) ? 0 : resto;
        int digitoInformado = Character.getNumericValue(cpf.charAt(posicao));

        return digitoEsperado == digitoInformado;
    }
}
