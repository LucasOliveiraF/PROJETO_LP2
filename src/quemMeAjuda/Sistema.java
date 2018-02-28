package quemMeAjuda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
	
	private List<Aluno> alunos;
	private Map<Aluno, Tutor> tutores;
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
	
	
	public String listarAlunos() {
		String retornaAlunos = "";
		for (Aluno aluno : alunos) {
			retornaAlunos += aluno.toString() + NL;		
		}
		return retornaAlunos.trim();
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		
		if (this.recuperaAluno(matricula) == null) {
			throw new Exception("Erro na definicao de papel: Tutor nao encontrado");
		}
		
		Aluno aluno = this.recuperaAluno(matricula);
		
		if (this.tutores.containsKey(aluno)) {
			this.tutores.get(aluno).cadastraDisciplina(disciplina, proficiencia);
		} else {
			this.tutores.put(aluno, new Tutor());
			this.tutores.get(aluno).cadastraDisciplina(disciplina, proficiencia);
		}
	}
	
	public String recuperaTutor(String matricula) {
		
		if (matricula.trim().isEmpty() || matricula == null)
			throw new IllegalArgumentException();
		if (this.recuperaAluno(matricula) == null)
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = this.recuperaAluno(matricula);
		
		if (!this.tutores.containsKey(aluno))
				throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		return this.recuperaAluno(matricula).toString();
	}
	
	public String listarTutores() throws Exception {
		
		if (this.tutores.isEmpty())
			throw new Exception();
		
		String retorno = "";
		
		for (Aluno aluno : this.tutores.keySet()) {
			retorno += aluno.toString() + NL;
		}
		
		return retorno.trim();
		
	}
	
	public void cadastrarHorario(String email, String horario, String dia) throws Exception {
		
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro no cadastrar horario: Email invalido");
		
		this.recuperaTutorPorEmail(email).cadastraHorario(horario, dia);
		
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) throws Exception {
		
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: Email invalido");
		
		this.recuperaTutorPorEmail(email).cadastraLocalDeAtendimento(local);
		
	}
	
	public boolean consultaHorario(String email, String horario, String dia) throws Exception {
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro na consulta de Horario: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro na consulta de Horario: Email invalido");
		
		return this.recuperaTutorPorEmail(email).consultaHorario(horario, dia);
		
		
	}
	
	public boolean consultaLocal(String email, String local) throws Exception {
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro na consulta de local de atendimento: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro na consulta de local de atendimento: Email invalido");
		
		return this.recuperaTutorPorEmail(email).consultaLocal(local);
	}
	
	private Tutor recuperaTutorPorEmail(String email) throws Exception {
		
		Aluno temp = null;
		
		for (Aluno aluno : this.tutores.keySet()) {
			if (aluno.getEmail().equals(email)) {
				temp = aluno;
				break;
			}
		}
		
		if (temp == null)
			throw new Exception();
		
		return this.tutores.get(temp);
	}
		
}
	
