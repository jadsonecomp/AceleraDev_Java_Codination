package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("O texto não pode ser vazio");
        }
        CriptografiaJulioCesar criptoJulioCesar = new CriptografiaJulioCesar();
        criptoJulioCesar.setCasaDecimal(3); // no exemplo 3
        criptoJulioCesar.setTexto("abcdefghijklmnopqrstuvwxyz"); // base da criptografia, no caso o alfabeto
        criptoJulioCesar.setFrase(texto); //frase de teste
        criptoJulioCesar.cripto(false);

        return criptoJulioCesar.getFraseConvertida();

    }

    @Override
    public String descriptografar(String texto) {
        if (texto.isEmpty()) {
            throw new IllegalArgumentException("O texto não pode ser vazio");
        }
        CriptografiaJulioCesar criptoJulioCesar = new CriptografiaJulioCesar();
        criptoJulioCesar.setCasaDecimal(3); // no exemplo 3
        criptoJulioCesar.setTexto("abcdefghijklmnopqrstuvwxyz"); // base da criptografia, no caso o alfabeto
        criptoJulioCesar.setFrase(texto); //frase de teste
        criptoJulioCesar.cripto(true);

        return criptoJulioCesar.getFraseConvertida();
    }

}


class TratamentoTexto {  // faz o tratamento da frase passada

    public String textoMinusculo(String texto){
        return texto.toLowerCase();
    }

    public Boolean textoValido(String texto){
        return texto.trim().length() > 0;
    }

    public String retiraEspacoBranco(String texto){
        return texto.trim();
    }

}

class CriptografiaJulioCesar { // classe responsável por executar a criptografia

    private String texto;     // base da criptografia, no caso o alfabeto
    private String frase;     // frase a ser convertida
    private String fraseConvertida;   // a frase convertida

    public String getFraseConvertida() {
        return fraseConvertida;
    }

    public void setFraseConvertida(String fraseConvertida) {
        this.fraseConvertida = fraseConvertida;
    }
    private int casaDecimal;  // casa decimal da conversão

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }

    public int getCasaDecimal() {
        return casaDecimal;
    }

    public void setCasaDecimal(int casaDecimal) {
        this.casaDecimal = casaDecimal;
    }

    public void cripto(boolean decripta){ // função responsável pela lógica da critografia da frase, executa a criptografia Júlio Cezar
        fraseConvertida = "";
        TratamentoTexto tratamento = new TratamentoTexto();

        if (tratamento.textoValido(texto) && tratamento.textoValido(frase)){
            // executa os tratamentos do texto e frase passadas
            texto = tratamento.retiraEspacoBranco(texto);
            texto = tratamento.textoMinusculo(texto);
            frase = tratamento.retiraEspacoBranco(frase);
            frase = tratamento.textoMinusculo(frase);

            fraseConvertida = converteFrase(texto, frase, casaDecimal, decripta); // executa a criptografia

        }else{
            System.out.println("Por favor informar texto e frase válidos");
        }

        setFraseConvertida(fraseConvertida);
    }

    public static int procuraPosicaoCaracter(String texto, char carater){  // Esse texto, neste caso, refere-se ao alfabeto
        int posicao = 0;
        posicao = texto.indexOf(String.valueOf(carater));   // procura a posicao do caracter na string
        return posicao;
    }

    public static int retornaPosicaoAtualizadaDecripta(int posicao, int casaDecimal){ // retorna a posição atualizada do caracter de acordo com a casa decimal e a posição no texto
        int posicaoAtualizada = 0;

        posicaoAtualizada = posicao - casaDecimal;

        if (posicaoAtualizada < 0){                      // validação pensando no número de letras do alfabeto inglês: 26
            posicaoAtualizada = 26 - Math.abs(posicaoAtualizada);  // estou considerando da posição 0 a 25
        }

        return posicaoAtualizada;
    }

    public static int retornaPosicaoAtualizadaEncripta(int posicao, int casaDecimal){
        int posicaoAtualizada = 0;

        posicaoAtualizada = posicao + casaDecimal;

        if (posicaoAtualizada > 25){                      // validação pensando no número de letras do alfabeto inglês: 26
            posicaoAtualizada = posicaoAtualizada - 26;  // estou considerando da posição 0 a 25
        }

        return posicaoAtualizada;
    }

    public static char retornaCaractere(String texto, int posicao){   //retorna o caractere convertido // Esse texto, neste caso, refere-se ao alfabeto
        char caracter;

        caracter = texto.charAt(posicao);   // procura o caracter na string em uma dada posição

        return caracter;
    }

    public static String converteFrase(String texto, String frase, int casaDecimal, Boolean decripta){  // texto base (alfabeto) / a frase a ser convertida / a casa decimal da conversão
        String fraseConvertida = "";
        int posicao = 0;
        int posicaoAtualizada = 0;
        char[] frasearray = frase.toCharArray();   // Converte a frase passada em um array de caracter
        char letra;

        for(char caracter : frasearray){           // varre todos os caracteres do array(da frase a ser convertida)
            posicao = 0;
            posicaoAtualizada = 0;

            posicao = procuraPosicaoCaracter(texto, caracter); //procuro a posição deste caracter no alfabeto

            if (posicao >= 0){ //se eu acho a posição deste caracter no alfabeto, eu o converto e concateno na string responsável pela frase convertida
                if(decripta) {
                    posicaoAtualizada = retornaPosicaoAtualizadaDecripta(posicao, casaDecimal);
                }else{
                    posicaoAtualizada = retornaPosicaoAtualizadaEncripta(posicao, casaDecimal);
                }
                letra = retornaCaractere(texto, posicaoAtualizada);

                fraseConvertida = fraseConvertida.concat(String.valueOf(letra));
            }else{                                                               //caso não ache o caracter no alfabeto, mantêm o mesmo
                fraseConvertida = fraseConvertida.concat(String.valueOf(caracter));
            }
        }


        return fraseConvertida;
    }

}


