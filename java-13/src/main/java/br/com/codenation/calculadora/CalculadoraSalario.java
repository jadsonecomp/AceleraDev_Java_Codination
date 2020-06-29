package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		double salarioLiquido = calcularIrrf(calcularInss(salarioBase));
		salarioLiquido = (salarioLiquido < 1039) ? 0.0 : salarioLiquido;
		return Math.round(salarioLiquido);
	}

	private double calcularInss(double salarioBase) {
		double salarioDesInss = 0.0;
		if(salarioBase <= 1500){
			salarioDesInss = salarioBase - (0.08 * salarioBase);
		}else if(salarioBase > 1500 && salarioBase <= 4000){
			salarioDesInss = salarioBase - (0.09 * salarioBase);
		}else{
			salarioDesInss = salarioBase - (0.11 * salarioBase);
		}
		return salarioDesInss;
	}

	private double calcularIrrf(double salarioBase) {
		double salarioDesIrrf = 0.0;
		if(salarioBase <= 3000){
			salarioDesIrrf = salarioBase;
		}else if(salarioBase > 3000 && salarioBase <= 6000){
			salarioDesIrrf = salarioBase - (0.075 * salarioBase);
		}else{
			salarioDesIrrf = salarioBase - (0.15 * salarioBase);
		}
		return salarioDesIrrf;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/