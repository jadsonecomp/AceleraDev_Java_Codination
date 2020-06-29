package br.com.codenation;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

public class Jogador {
    private Long id;                            //Identificador do Jogador
    private Long idTime;                        //Identificador do time
    private String nome;                        //Nome do Jogador
    private LocalDate dataNascimento;           //Data de nascimento do Jogador
    private Integer nivelHabilidade;            //Nível de habilidade do jogador (0 a 100)
    private BigDecimal salario;                 //Salário do jogador
    private LocalDate dataAtual;                //Data no momento da execução
    private int idadeEmDia;                     //Idade do jogador em dias

    private int idadeEmNanoSegundo;                     //Idade do jogador em dias

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(Integer nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdadeEmDia() {
        return idadeEmDia;
    }

    public void setIdadeEmDia(int idadeEmDia) {
        this.idadeEmDia = idadeEmDia;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public int getIdadeEmNanoSegundo() {
        return idadeEmNanoSegundo;
    }

    public void setIdadeEmNanoSegundo(int idadeEmNanoSegundo) {
        this.idadeEmNanoSegundo = idadeEmNanoSegundo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario){
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
        this.dataAtual = LocalDate.now();

        Period periodo = Period.between(this.dataNascimento, this.dataAtual);
        //Duration duracao = Duration.between(this.dataNascimento, this.dataAtual);
        this.idadeEmDia = periodo.getDays();
        //this.idadeEmNanoSegundo = duracao.getNano();
    }
}
