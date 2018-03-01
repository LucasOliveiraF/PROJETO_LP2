package quemMeAjuda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Sistema {
	
	private List<Aluno> alunos;
	private Map<Aluno, Tutor> tutores;
	private final String NL = System.lineSeparator();
	
	
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.tutores = new HashMap<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {

				
		for (Aluno aluno : alunos) {
			if (!email.matches("(.+)@(.+)"))
				throw new IllegalArgumentException("Erro no cadastro de aluno: Email invalido");
			
			if(aluno.getMatricula().equals(matricula))
				throw new Exception("Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
					
			}

		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		alunos.add(aluno);
		Collections.sort(this.alunos);
		
	}
	
	public String recuperaAluno(String matricula) throws Exception {
		
		if (this.getAluno(matricula) == null)
			throw new Exception("Erro na busca por aluno: Aluno nao encontrado");
		
		return this.getAluno(matricula).toString();
	}
	
	private Aluno getAluno(String matricula) throws Exception{
		for (Aluno aluno : alunos) {
			if(aluno.getMatricula().equals(matricula))
				return aluno;
				
		}
		return null;
	}

	
	
	public String listarAlunos() {
		String retornaAlunos = "";
		for (Aluno aluno : alunos) {
			retornaAlunos += aluno.toString() + ", ";
		}
		return retornaAlunos.substring(0, retornaAlunos.length() - 2);
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		
		if (this.getAluno(matricula) == null) {
			throw new Exception("Erro na definicao de papel: Tutor nao encontrado");
		}
		
		Aluno aluno = this.getAluno(matricula);
		
		if (this.tutores.containsKey(aluno)) {
			this.tutores.get(aluno).cadastraDisciplina(disciplina, proficiencia);
		} else {
			this.tutores.put(aluno, new Tutor());
			this.tutores.get(aluno).cadastraDisciplina(disciplina, proficiencia);
		}
	}
	

	public String recuperaTutor(String matricula) throws Exception {

		if (matricula.trim().isEmpty() || matricula == null)
			throw new IllegalArgumentException();
		if (this.getAluno(matricula) == null)
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		Aluno aluno = this.getAluno(matricula);
		
		if (!this.tutores.containsKey(aluno))
				throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		return this.getAluno(matricula).toString();
	}
	
	public String listarTutores() throws Exception {
		
		if (this.tutores.isEmpty())
			throw new Exception();
		
		String retorno = "";
		Set<Aluno> conj = this.tutores.keySet();
		List<Aluno> lista = new ArrayList(conj);
		Collections.sort(lista);
		
		
		for (Aluno aluno : lista) {
			retorno += aluno.toString() + ", ";
		}
		
		return retorno.trim().substring(0, retorno.length() - 2);
		
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

	public String getInfoAluno(String matricula, String atributo) throws Exception {
		
		if (this.getAluno(matricula) == null)
			throw new NullPointerException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		
		return this.getAluno(matricula).getInfoAluno(atributo);
	}
		
}
	
