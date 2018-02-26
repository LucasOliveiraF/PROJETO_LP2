package quemMeAjuda;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
	
	private List<Aluno> alunos;
	private Map<String, Tutor> tutores;
	
	
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.tutores = new HashMap<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		alunos.add(aluno);
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
	
		
}
	
