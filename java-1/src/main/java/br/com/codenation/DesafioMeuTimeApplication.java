package br.com.codenation;

import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	List<Time> timeList = new ArrayList<Time>();
	List<Jogador> jogadorList = new ArrayList<Jogador>();
	Long capitao;
	Long idTime;
	String nomeJogador;
	String nomeTime;
	BigDecimal salario;

	public boolean buscaIdObjeto(Long id, boolean isJogador){
		if(isJogador){
			for (Jogador jogador: jogadorList) {
				if(jogador.getId() == id){
					idTime = jogador.getIdTime();
					nomeJogador = jogador.getNome();
					salario = jogador.getSalario();
					return true;
				}
			}
		}else{
			for (Time time: timeList) {
				if(time.getId() == id){
					capitao = time.getCapitao();
					nomeTime = time.getNome();
					return true;
				}
			}
		}
		return false;
	}

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if((!timeList.isEmpty())&&(buscaIdObjeto(id, false))){
			throw new IdentificadorUtilizadoException();
		}
		timeList.add(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));

	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if((!jogadorList.isEmpty())&&((buscaIdObjeto(id, true)))){
			throw new IdentificadorUtilizadoException();
		}
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}
		jogadorList.add(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	public void definirCapitao(Long idJogador) {
		if(!buscaIdObjeto(idJogador, true)){
			throw new JogadorNaoEncontradoException();
		}
		for (Time time: timeList) {
			if(time.getId() == idTime){
				time.setCapitao(idJogador);
			}
		}
		//timeList.get(idTime.intValue()).setCapitao(idJogador); //verificar se é o idtime mesmo ou se tem que fazer um for pra buscar
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		long capitaoTime;
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}
		if(capitao == null){
			throw new CapitaoNaoInformadoException();
		}

		return capitao;
	}

	public String buscarNomeJogador(Long idJogador) {
		if(!buscaIdObjeto(idJogador, true)){
			throw new JogadorNaoEncontradoException();
		}
		return nomeJogador;
	}

	public String buscarNomeTime(Long idTime) {
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}
		return nomeTime;
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}
		List<Long> idJogadorList = new ArrayList<Long>();

		for (Jogador jogador: jogadorList) {
			if(jogador.getIdTime() == idTime){
				idJogadorList.add(jogador.getId());
			}
		}

		Collections.sort(idJogadorList, new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				return o1 < o2 ? -1 : (o1 > o2 ? +1 : 0);
			}
		});

		return idJogadorList;

	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}

		List<Jogador> jogadorTimeList = new ArrayList<Jogador>();

		for (Jogador jogador: jogadorList) {
			if(jogador.getIdTime() == idTime){
				jogadorTimeList.add(jogador);
			}
		}

		Collections.sort(jogadorTimeList,(o1, o2) -> {
			Jogador p1 = (Jogador) o1;
			Jogador p2 = (Jogador) o2;
			return p1.getNivelHabilidade() < p2.getNivelHabilidade() ? -1 : (p1.getNivelHabilidade() > p2.getNivelHabilidade() ? +1 : 0);
		});

		return jogadorTimeList.get(jogadorTimeList.size()-1).getId();

	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}
		List<Jogador> jogadorTimeList = new ArrayList<Jogador>();

		for (Jogador jogador: jogadorList) {
			if(jogador.getIdTime() == idTime){
				jogadorTimeList.add(jogador);
			}
		}

		Collections.sort(jogadorTimeList,(o1, o2) -> {
			Jogador p1 = (Jogador) o1;
			Jogador p2 = (Jogador) o2;
			return p1.getDataNascimento().compareTo(p2.getDataNascimento());
		}); //ordena da menor pra maior data

		return jogadorTimeList.get(0).getId();
	}

	public List<Long> buscarTimes() {
		List<Long> idTimeList = new ArrayList<Long>();
		for (Time time : timeList){
			idTimeList.add(time.getId());
		}
		return idTimeList;
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		if(!buscaIdObjeto(idTime, false)){
			throw new TimeNaoEncontradoException();
		}
		List<Jogador> jogadorTimeList = new ArrayList<Jogador>();

		for (Jogador jogador: jogadorList) {
			if(jogador.getIdTime() == idTime){
				jogadorTimeList.add(jogador);
			}
		}

		Collections.sort(jogadorTimeList,(o1, o2) -> {
			Jogador p1 = (Jogador) o1;
			Jogador p2 = (Jogador) o2;
			return p1.getSalario().compareTo(p2.getSalario());
		});

		return jogadorTimeList.get(jogadorTimeList.size()-1).getId();
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		if(!buscaIdObjeto(idJogador, true)){
			throw new JogadorNaoEncontradoException();
		}
		return salario;
	}

	public List<Long> buscarTopJogadores(Integer top) {
		List<Long> jogadorListTop = new ArrayList<Long>();
		if(!jogadorList.isEmpty()){
			List<Jogador> jogadorListOrder = new ArrayList<Jogador>();
			for (Jogador jogador: jogadorList) {
				jogadorListOrder.add(jogador);
			}

			Collections.sort(jogadorListOrder,(o1, o2) -> {
				Jogador p1 = (Jogador) o1;
				Jogador p2 = (Jogador) o2;
				return p1.getNivelHabilidade() < p2.getNivelHabilidade() ? -1 : (p1.getNivelHabilidade() > p2.getNivelHabilidade() ? +1 : 0);
			});

			for (int i = jogadorListOrder.size()-1; i > (jogadorListOrder.size()-1) - top; i-- ){
				jogadorListTop.add(jogadorListOrder.get(i).getId());
			}
		}
		return jogadorListTop;
	}

}
