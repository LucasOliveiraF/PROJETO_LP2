package quemMeAjuda;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tutor {
	
	private Map<String, Integer> disciplinas;
	private Map<String, String> horarios;
	private Set<String> locais;
	private double avaliacao;
	private int dinheiro;
	TutorType nivel;
	
	public Tutor() {
		this.disciplinas = new HashMap<>();
		this.horarios = new HashMap<>();
		this.locais = new HashSet<>();
		this.avaliacao = 4;
		this.nivel = this.nivel.Tutor;
		this.dinheiro = 0;
	}
	
	public void cadastraDisciplina(String disciplina, int proficiencia) {
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new IllegalArgumentException("Erro na definicao de papel: disciplina nao pode ser vazia ou nula");
		if (this.disciplinas.containsKey(disciplina))
			throw new RuntimeException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
		if (proficiencia < 1 || proficiencia > 5)
			throw new IndexOutOfBoundsException("Erro na definicao de papel: Proficiencia invalida");
		
		this.disciplinas.put(disciplina, proficiencia);
	}
	
	public void cadastraHorario(String horario, String dia) {
		
		if (horario.trim().isEmpty() || horario == null)
			throw new NullPointerException("Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		if (dia.trim().isEmpty() || dia == null)
			throw new NullPointerException("Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		if (!dia.equalsIgnoreCase("seg") && !dia.equalsIgnoreCase("ter") && !dia.equalsIgnoreCase("qua") && !dia.equalsIgnoreCase("qui") && !dia.equalsIgnoreCase("sex"))
			throw new IllegalArgumentException("Erro no cadastrar horario: dia invalido");
		
		this.horarios.put(horario, dia);
	}
	
	public void cadastraLocalDeAtendimento(String local) {
		
		if (local.trim().isEmpty() || local == null)
			throw new NullPointerException("Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		
		this.locais.add(local);
	}
	
	public boolean consultaHorario(String horario, String dia) {
		
		if (horario.trim().isEmpty() || horario == null)
			throw new IllegalArgumentException("Erro na consulta de horario: horario nao pode ser vazio em branco");
		if (dia.trim().isEmpty() || dia == null)
			throw new IllegalArgumentException("Erro na consulta de horario: dia nao pode ser vazio em branco");
		if (!dia.equalsIgnoreCase("seg") && !dia.equalsIgnoreCase("ter") && !dia.equalsIgnoreCase("qua") && !dia.equalsIgnoreCase("qui") && !dia.equalsIgnoreCase("sex"))
			throw new IllegalArgumentException("Erro na consulta de horario: dia invalido");
		
		return this.horarios.containsKey(horario) && this.horarios.get(horario).equalsIgnoreCase(dia);
	}
	
	public boolean consultaLocal(String local) {
		
		if (local.trim().isEmpty() || local == null)
			throw new IllegalArgumentException("Erro na consulta de local de atendimento: local nao pode ser vazio ou em branco");
		
		return this.locais.contains(local);
	}
	
	public boolean consultaDisciplinas(String disciplina) {
		if (this.disciplinas.containsKey(disciplina))
			return true;
		else
			return false;
	}
	
	public void setAvaliacao(int avaliacao) {
		
		this.avaliacao = ((this.avaliacao * 5) + avaliacao ) / 6;
		this.nivel = this.nivel.getAvaliacao(avaliacao);
	}
	
	public String pegarNota() {
		double valor = this.avaliacao;
		
		DecimalFormat df = new DecimalFormat("0.00");
		String temp = df.format(valor);
		temp = temp.replace(".",",");
		
		return temp;
	}
	
	public String pegarNivel() {
		return this.nivel.toString();
	}
	

}
