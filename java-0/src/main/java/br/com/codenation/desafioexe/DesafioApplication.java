package br.com.codenation.desafioexe;

import java.util.List;

import java.util.ArrayList;

public class DesafioApplication {

	private static List<Integer> serieFibonacci;

	public static List<Integer> fibonacci() {

		serieFibonacci = new ArrayList<Integer>();
		int i = 0, atualFibonacci = 0, ultimoItem = 0, penultimoItem = 0, tamanhoSequencia = 377;
		do {
			if(i<=1){
				serieFibonacci.add(i);
			}else{
				ultimoItem = serieFibonacci.size()-1;
				penultimoItem = serieFibonacci.size()-2;
				atualFibonacci = serieFibonacci.get(ultimoItem) + serieFibonacci.get(penultimoItem);
				if(atualFibonacci <= tamanhoSequencia){
					serieFibonacci.add(atualFibonacci);
				}
			}
			i++;
		} while (atualFibonacci <= tamanhoSequencia);

		return serieFibonacci;
	}

	public static Boolean isFibonacci(Integer a) {
		if (serieFibonacci != null) {
			return serieFibonacci.contains(a);
		}
		else{
			return false;
		}

	}

}