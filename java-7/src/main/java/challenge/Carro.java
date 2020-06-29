package challenge;

import java.util.Objects;

public class Carro {

    private final Motorista motorista;

    private final String placa;

    private final Cor cor;

    private Carro(Motorista motorista, String placa, Cor cor) {
        this.motorista = motorista;
        if(ValidaPlaca(placa)) {
            this.placa = placa;
        }else{
            throw new NullPointerException("Placa Vazia");
        }
        if(ValidaCor(cor)) {
            this.cor = cor;
        }else{
            throw new NullPointerException("Cor Vazia");
        }
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public String getPlaca() {
        return placa;
    }

    public Cor getCor() {
        return cor;
    }

    protected static boolean ValidaPlaca(String placa){
        return !placa.equals(null);
    }

    protected static boolean ValidaCor(Cor cor){
        return !cor.equals(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Carro carro = (Carro) o;
        return Objects.equals(motorista, carro.motorista) &&
                Objects.equals(placa, carro.placa) &&
                cor == carro.cor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(motorista, placa, cor);
    }

    @Override
    public String toString() {
        return "Carro{" +
                "motorista=" + motorista +
                ", placa='" + placa + '\'' +
                ", cor=" + cor +
                '}';
    }

    public static CarroBuilder builder() {
        return new CarroBuilder();
    }


    public static class CarroBuilder {

        private Motorista motorista;

        private String placa;

        private Cor cor;


        private CarroBuilder() {
        }

        public CarroBuilder withMotorista(Motorista motorista) {
            this.motorista = motorista;
            return this;
        }

        public CarroBuilder withPlaca(String placa) {
            if(ValidaPlaca(placa)) {
                this.placa = placa;
            }else{
                throw new NullPointerException("Placa Vazia");
            }
            return this;
        }

        public CarroBuilder withCor(Cor cor) {
            if(ValidaCor(cor)) {
                this.cor = cor;
            }else{
                throw new NullPointerException("Cor Vazia");
            }
            return this;
        }

        public Carro build() {
            return new Carro(motorista, placa, cor);
        }
    }
}
