package qma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tutor {
	
	private String disciplina;
	private Map<String, String> horarios;
	private Set<String> locais;
	private int proficiencia;
	private int avaliacao;
	private int dinheiro;
	
	public Tutor(String disciplina, int proficiencia) {
		
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new IllegalArgumentException();
		if (proficiencia <= 0) {
			throw new IndexOutOfBoundsException("Erro na definicao de papel: Proficiencia invalida");
		}
		
		this.disciplina = disciplina;
		this.horarios = new HashMap<>();
		this.locais = new HashSet<>();
		this.proficiencia = proficiencia;
		this.avaliacao = 4;
		this.dinheiro = 0;
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
