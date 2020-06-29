package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private static final byte VAGASESTACIONAMENTO = 10;
    private static final byte IDADEDIRIGIR = 18;
    private static final byte PONTUACAOVALIDACARTEIRA = 20;
    private static final byte SENIOR = 55;

    private List<Carro> estacionamento = new ArrayList<Carro>();

    private boolean searchCarroEstacionamento(Carro carro){
        for (Carro automovel:estacionamento) {
            if(automovel.equals(carro)){
                return true;
            }
        }
        return false;
    }

    private void validaMotoristaEstacionamento(Carro carro){
        if(carro.getMotorista() == null){
            throw new EstacionamentoException("Carro sem motorista");
        }
    }

    private void validaIdadeMotoristaEstacionamento(Carro carro){
        if(carro.getMotorista().getIdade() < IDADEDIRIGIR){
            throw new EstacionamentoException("Motorista menor de idade");
        }
    }

    private void validaPontuacaoMotoristaEstacionamento(Carro carro){
        if(carro.getMotorista().getPontos() > PONTUACAOVALIDACARTEIRA){
            throw new EstacionamentoException("Motorista com pontuação de carteira extourada");
        }
    }

    private void addCarroEstacionamento(Carro carro){
        boolean estacionou = false;
        validaMotoristaEstacionamento(carro);
        validaIdadeMotoristaEstacionamento(carro);
        validaPontuacaoMotoristaEstacionamento(carro);

        if(estacionamento.size() < VAGASESTACIONAMENTO){
            estacionamento.add(carro);
        }else{
            for (Carro automovel:estacionamento) {
                if(automovel.getMotorista().getIdade() <= SENIOR){
                    estacionamento.remove(automovel);
                    estacionamento.add(carro);
                    estacionou = true;
                    break;
                }
            }
            if(!estacionou) {
                throw new EstacionamentoException("Todos os motoristas são Senior");
            }
        }
    }


    public void estacionar(Carro carro) {
        addCarroEstacionamento(carro);
    }

    public int carrosEstacionados() {
        return estacionamento.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return searchCarroEstacionamento(carro);
    }
}
