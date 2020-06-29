package br.com.codenation;

import java.util.Arrays;

public class StatisticUtil {

	private static void validacao(int[] elements){
		if(elements == null){
			throw new NullPointerException("Array Vazio");
		}
		if(elements.length == 0){
			throw new ArithmeticException("Array inválido");
		}
	}

	public static int average(int[] elements) { //Cálculo da média
		validacao(elements);

		double media = Arrays.stream(elements).average().getAsDouble();

		return ((int) media);
	}

	public static int mode(int[] elements) { //Cálculo da moda
		validacao(elements);

		int somaValoresAntigo = 0;
		int somaValoresAtual = 0;
		int mode = 0;

		for (int i : elements) {
			somaValoresAntigo = 0;
			for (int j : elements) {
				if(i == j){
					somaValoresAntigo++;
				}
			}
			if(somaValoresAntigo>somaValoresAtual){
				somaValoresAtual = somaValoresAntigo;
				mode = i;
			}
		}
		return mode;
	}

	public static int median(int[] elements) { //Cálculo da mediana
		validacao(elements);

		Arrays.sort(elements);

		return ((elements.length % 2) == 0) ? ((elements[(elements.length/2)-1]+elements[(elements.length)/2])/2) : (elements[elements.length/2]);
	}
}