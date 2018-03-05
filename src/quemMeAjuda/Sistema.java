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
	private List<Ajuda> ajudas;
	
	
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.tutores = new HashMap<>();
		this.ajudas = new ArrayList<>();
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
	
	private Aluno getAluno(String matricula) {
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
	
	public String listarTutores() {
		
		if (this.tutores.isEmpty())
			throw new RuntimeException();
		
		String retorno = "";
		Set<Aluno> conj = this.tutores.keySet();
		List<Aluno> lista = new ArrayList(conj);
		Collections.sort(lista);
		
		
		for (Aluno aluno : lista) {
			retorno += aluno.toString() + ", ";
		}
		
		return retorno.trim().substring(0, retorno.length() - 2);
		
	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro no cadastrar horario: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro no cadastrar horario: Email invalido");
		if (this.recuperaTutorPorEmail(email) == null)
			throw new NullPointerException("Erro no cadastrar horario: tutor nao cadastrado");
		
		this.recuperaTutorPorEmail(email).cadastraHorario(horario, dia);
		
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) {
		
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro no cadastrar local de atendimento: Email invalido");
		if (this.recuperaTutorPorEmail(email) == null)
			throw new NullPointerException("Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		this.recuperaTutorPorEmail(email).cadastraLocalDeAtendimento(local);
		
	}
	
	public boolean consultaHorario(String email, String horario, String dia) {
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro na consulta de Horario: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro na consulta de Horario: Email invalido");
		
		if (this.recuperaTutorPorEmail(email) == null)
			return false;
		
		return this.recuperaTutorPorEmail(email).consultaHorario(horario, dia);
		
		
	}
	
	public boolean consultaLocal(String email, String local) {
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro na consulta de local de atendimento: email nao pode ser vazio ou em branco");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro na consulta de local de atendimento: Email invalido");
		
		if (this.recuperaTutorPorEmail(email) == null)
			return false;
		
		return this.recuperaTutorPorEmail(email).consultaLocal(local);
	}
	
	private Tutor recuperaTutorPorEmail(String email) {
		
		Aluno temp = null;
		
		for (Aluno aluno : this.tutores.keySet()) {
			if (aluno.getEmail().equals(email)) {
				temp = aluno;
				break;
			}
		}
		
		if (temp == null)
			return null;
		
		return this.tutores.get(temp);
	}

	public String getInfoAluno(String matricula, String atributo) throws Exception {
		
		if (this.getAluno(matricula) == null)
			throw new NullPointerException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
		
		return this.getAluno(matricula).getInfoAluno(atributo);
	}
	
	public int pedirAjudaPresencial (String matrAluno, String disciplina, String horario, String dia, String localInteresse) {
		if (matrAluno.trim().isEmpty() || matrAluno == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		if (horario.trim().isEmpty() || horario == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		if (dia.trim().isEmpty() || dia == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		if (localInteresse.trim().isEmpty() || localInteresse == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");
		if (this.getAluno(matrAluno) == null)
			throw new NullPointerException("Erro ao pedia Ajuda: Aluno inexistente");
		
		String matrTutor = null;
		
		for (Aluno aluno : this.tutores.keySet()) {
			Tutor tutor = this.tutores.get(aluno);
			if (tutor.consultaDisciplinas(disciplina) && tutor.consultaHorario(horario, dia) && tutor.consultaLocal(localInteresse)) {
				matrTutor = aluno.getMatricula();
				break;
			}
		}
		
		Ajuda ajuda = new AjudaPresencial(matrAluno, matrTutor, disciplina, horario, dia, localInteresse);
		
		this.ajudas.add(ajuda);
		
		return this.ajudas.size();
	}
	
	public int pedirAjudaOnline (String matrAluno, String disciplina) {
		if (matrAluno.trim().isEmpty() || matrAluno == null)
			throw new NullPointerException("Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new NullPointerException("Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");
		if (this.getAluno(matrAluno) == null)
			throw new NullPointerException("Erro ao pedia Ajuda: Aluno inexistente");
		
		String matrTutor = null;
		
		for (Aluno aluno : this.tutores.keySet()) {
			Tutor tutor = this.tutores.get(aluno);
			if (tutor.consultaDisciplinas(disciplina)) {
				matrTutor = aluno.getMatricula();
				break;
			}
		}
		
		Ajuda ajuda = new AjudaOnline(matrAluno, matrTutor, disciplina);
		
		this.ajudas.add(ajuda);
		
		return this.ajudas.size();
	}
	
	public String pegarTutor(int idAjuda) {
		
		if (idAjuda <= 0)
			throw new IndexOutOfBoundsException("Erro ao tentar recuperar tutor : id nao pode menor que zero ");
		if (idAjuda > this.ajudas.size())
			throw new IndexOutOfBoundsException("Erro ao tentar recuperar tutor : id nao encontrado ");
		
		return this.ajudas.get(idAjuda-1).pegarTutor();
	}
	
	public String getInfoAjuda(int idAjuda, String atributo) {
		if (idAjuda <= 0)
			throw new IndexOutOfBoundsException("Erro ao tentar recuperar info da ajuda : id nao pode menor que zero ");
		if (idAjuda > this.ajudas.size())
			throw new IndexOutOfBoundsException("Erro ao tentar recuperar info da ajuda : id nao encontrado ");
		
		return this.ajudas.get(idAjuda-1).getInfoAjuda(atributo);
	}
		
}
	
