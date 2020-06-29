package br.com.codenation;

import java.time.LocalDate;

public class Time {
    private Long id;                      //Identificador do time
    private String nome;                  //Nome do Time
    private LocalDate dataCriacao;        //Data de criação do time
    private String corUniformePrincipal;  //Cor do uniforme principal do time
    private String corUniformeSecundario; //Cor do uniforme secundário do time

    private Long capitao;                 //Id do jogador que é o capitão do time

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCapitao() {
        return capitao;
    }

    public void setCapitao(Long capitao) {
        this.capitao = capitao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario){
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        //this.capitao = 0L;
    }
}
