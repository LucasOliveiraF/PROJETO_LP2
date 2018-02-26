package quemMeAjuda;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tutor extends Aluno {
	
	private Map<String, Integer> disciplinas;
	private Map<String, String> horarios;
	private Set<String> locais;
	private int avaliacao;
	private int dinheiro;
	
	public Tutor() {
		this.disciplinas = new HashMap<>();
		this.horarios = new HashMap<>();
		this.locais = new HashSet<>();
		this.avaliacao = 4;
		this.dinheiro = 0;
	}
	
	public void cadastraDisciplina(String disciplina, int proficiencia) throws Exception {
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new IllegalArgumentException();
		if (this.disciplinas.containsKey(disciplina))
			throw new Exception("Erro na definicao de papel: Ja eh tutor dessa disciplina");
		if (proficiencia <= 0)
			throw new IndexOutOfBoundsException("Erro na definicao de papel: Proficiencia invalida");
		
		this.disciplinas.put(disciplina, proficiencia);
	}
	
	public void cadastraHorario(String horario, String dia) {
		this.horarios.put(horario, dia);
	}
	
	public void cadastraLocalDeAtendimento(String local) {
		this.locais.add(local);
	}
	
	public boolean consultaHorario(String horario, String dia) {
		return this.horarios.containsKey(horario) && this.horarios.get(horario).equals(dia);
	}
	
	public boolean consultaLocal(String local) {
		return this.locais.contains(local);
	}

}
