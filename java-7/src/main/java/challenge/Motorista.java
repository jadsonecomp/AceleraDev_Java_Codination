package challenge;


import java.util.Objects;

public class Motorista {

    private final String nome;

    private final int idade;

    private final int pontos;

    private final String habilitacao;

    private Motorista(String nome, int idade, int pontos, String habilitacao) {
        if(ValidaNome(nome)) {
            this.nome = nome;
        }else{
            throw new NullPointerException("Nome Vazio");
        }

        if(ValidaIdade(idade)) {
            this.idade = idade;
        }else{
            throw new IllegalArgumentException("Idade Negativa");
        }

        if(ValidaPontos(pontos)) {
            this.pontos = pontos;
        }else{
            throw new IllegalArgumentException("Pontos negativos");
        }

        if(ValidaHabilitacao(habilitacao)) {
            this.habilitacao = habilitacao;
        }else{
            throw  new NullPointerException("Habilitação vazia");
        }


    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getPontos() {
        return pontos;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    protected static boolean ValidaNome(String nome){
        return !nome.equals(null);
    }

    protected static boolean ValidaHabilitacao(String habilitacao){
        return !habilitacao.equals(null);
    }

    protected static boolean ValidaIdade(int idade){
        return (idade > 0);
    }

    protected static boolean ValidaPontos(int pontos){
        return (pontos > 0);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Motorista motorista = (Motorista) o;
        return Objects.equals(habilitacao, motorista.habilitacao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(habilitacao);
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", pontos=" + pontos +
                ", habilitacao='" + habilitacao + '\'' +
                '}';
    }

    public static MotoristaBuilder builder() {
        return new MotoristaBuilder();
    }


    public static class MotoristaBuilder {

        private String nome;

        private int idade;

        private int pontos;

        private String habilitacao;


        private MotoristaBuilder() {
        }

        public MotoristaBuilder withNome(String nome) {
            if(ValidaNome(nome)) {
                this.nome = nome;
            }else{
                throw new NullPointerException("Nome Vazio");
            }
            return this;
        }

        public MotoristaBuilder withIdade(int idade) {
            if(ValidaIdade(idade)) {
                this.idade = idade;
            }else{
                throw new IllegalArgumentException("Idade Negativa");
            }
            return this;
        }

        public MotoristaBuilder withPontos(int pontos) {
            if(ValidaPontos(pontos)) {
                this.pontos = pontos;
            }else{
                throw new IllegalArgumentException("Pontos negativos");
            }
            return this;
        }

        public MotoristaBuilder withHabilitacao(String habilitacao) {
            if(ValidaHabilitacao(habilitacao)) {
                this.habilitacao = habilitacao;
            }else{
                throw  new NullPointerException("Habilitação vazia");
            }
            return this;
        }


        public Motorista build() {
            return new Motorista(nome, idade, pontos, habilitacao);
        }
    }
}
