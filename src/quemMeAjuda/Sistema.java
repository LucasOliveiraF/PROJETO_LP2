package quemMeAjuda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
	
	private List<Aluno> alunos;
	private Map<String, Tutor> tutores;
	private final String NL = System.lineSeparator();
	
	
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.tutores = new HashMap<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		alunos.add(aluno);
		Collections.sort(this.alunos);
	}
	
	private Aluno recuperaAluno(String matricula) {
		for (Aluno aluno : alunos) {
			if(aluno.getMatricula() == matricula) {
				return aluno;
			} 
		}
		
		return null;
	}
	
	
	public String listarAlunos(String matricula) {
		String retornaAlunos = "";
		for (Aluno aluno : alunos) {
			if(aluno.getMatricula() == matricula) {
				retornaAlunos += "/n" + aluno.toString();
			}		
		}
		return retornaAlunos;
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		
		if (this.recuperaAluno(matricula) == null) {
			throw new Exception("Erro na definicao de papel: Tutor nao encontrado");
		}
		
		if (this.tutores.containsKey(matricula)) {
			this.tutores.get(matricula).cadastraDisciplina(disciplina, proficiencia);
		} else {
			this.tutores.put(matricula, new Tutor());
			this.tutores.get(matricula).cadastraDisciplina(disciplina, proficiencia);
		}
	}
	
	public String recuperaTutor(String matricula) {
		
		if (matricula.trim().isEmpty() || matricula == null)
			throw new IllegalArgumentException();
		if (!this.tutores.containsKey(matricula))
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		return this.recuperaAluno(matricula).toString();
	}
	
	public String listarTutores() throws Exception {
		
		if (this.tutores.isEmpty())
			throw new Exception();
		
		String retorno = "";
		
		for (String matricula : this.tutores.keySet()) {
			retorno += this.recuperaAluno(matricula).toString() + NL;
		}
		
		return retorno.trim();
		
	}
		
}
	
