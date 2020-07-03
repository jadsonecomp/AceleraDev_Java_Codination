package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import javax.lang.model.type.TypeVariable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    public BigDecimal calculo(Object classe, Class<? extends Annotation> tipoAnotacao){
        BigDecimal valor = BigDecimal.ZERO;
        for (Field campo : classe.getClass().getDeclaredFields()) {
            if((campo.isAnnotationPresent(tipoAnotacao)) && (campo.getType().equals(BigDecimal.class))){
                try {
                    campo.setAccessible(true);
                    valor = valor.add((BigDecimal) campo.get(classe));
                } catch (IllegalAccessException e) {
                    valor = valor.add(BigDecimal.ZERO);
                }
            }
        }
        return valor;
    }

    @Override
    public BigDecimal somar(Object classe) {
        return calculo(classe, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object classe) {
        return calculo(classe, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object classe) {
        return somar(classe).subtract(subtrair(classe));
    }

}
