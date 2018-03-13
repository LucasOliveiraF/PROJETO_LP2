package quemMeAjuda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ajuda.Ajuda;
import ajuda.AjudaOnline;
import ajuda.AjudaPresencial;
import excecoes.Excecao;

public class Sistema {
	
	private List<Aluno> alunos;
	private Map<Aluno, Tutor> tutores;
	private List<Ajuda> ajudas;
	private int dinheiro = 0;
	
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.tutores = new HashMap<>();
		this.ajudas = new ArrayList<>();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {

				
		for (Aluno aluno : alunos) {
			Excecao.validaEmail(email, "no cadastro de aluno");
			
			if(aluno.getMatricula().equals(matricula))
				throw new IllegalArgumentException("Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
					
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

		Excecao.validaString(matricula, "Erro na busca por tutor: matricula nao pode ser vazia ou nula");
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
		List<Aluno> lista = new ArrayList<>(conj);
		Collections.sort(lista);
		
		
		for (Aluno aluno : lista) {
			retorno += aluno.toString() + ", ";
		}
		
		return retorno.trim().substring(0, retorno.length() - 2);
		
	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		
		Excecao.validaEmail(email, "no cadastrar horario");
		if (this.getTutorPorEmail(email) == null)
			throw new NullPointerException("Erro no cadastrar horario: tutor nao cadastrado");
		
		this.getTutorPorEmail(email).cadastraHorario(horario, dia);
		
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) {
		
		Excecao.validaEmail(email, "no cadastrar local de atendimento");
		if (this.getTutorPorEmail(email) == null)
			throw new NullPointerException("Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		this.getTutorPorEmail(email).cadastraLocalDeAtendimento(local);
		
	}
	
	public boolean consultaHorario(String email, String horario, String dia) {
		Excecao.validaEmail(email, "na consulta de Horario");
		
		if (this.getTutorPorEmail(email) == null)
			return false;
		
		return this.getTutorPorEmail(email).consultaHorario(horario, dia);
		
		
	}
	
	public boolean consultaLocal(String email, String local) {
		Excecao.validaEmail(email, "na consulta de local de atendimento");
		
		if (this.getTutorPorEmail(email) == null)
			return false;
		
		return this.getTutorPorEmail(email).consultaLocal(local);
	}
	
	private Tutor getTutorPorEmail(String email) {
		
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
		
		Excecao.validaString(matrAluno, "Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		Excecao.validaString(disciplina, "Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		Excecao.validaString(horario, "Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		Excecao.validaDiaDaSemana(dia, "no pedido de ajuda presencial");
		Excecao.validaString(localInteresse, "Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");
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
		Excecao.validaString(matrAluno, "Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");
		Excecao.validaString(disciplina, "Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");
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
		
		Excecao.validaNumeroEstritamentePositivo(idAjuda, "Erro ao tentar recuperar tutor : id nao pode menor que zero ");
		Excecao.validaNumeroRange(idAjuda, this.ajudas.size(), "Erro ao tentar recuperar tutor : id nao encontrado ");
		
		return this.ajudas.get(idAjuda-1).pegarTutor();
	}
	
	public String getInfoAjuda(int idAjuda, String atributo) {
		
		Excecao.validaNumeroEstritamentePositivo(idAjuda, "Erro ao tentar recuperar info da ajuda : id nao pode menor que zero ");
		Excecao.validaNumeroRange(idAjuda, this.ajudas.size(), "Erro ao tentar recuperar info da ajuda : id nao encontrado ");
		
		return this.ajudas.get(idAjuda-1).getInfoAjuda(atributo);
	}
	
	public void avaliarTutor (int idAjuda, int nota) {
		
		Excecao.validaNumeroPositivo(nota, "Erro na avaliacao de tutor: nota nao pode ser menor que 0");
		Excecao.validaNumeroRange(nota, 5, "Erro na avaliacao de tutor: nota nao pode ser maior que 5");		
		Excecao.validaNumeroEstritamentePositivo(idAjuda, "Erro na avaliacao de tutor: id nao pode menor que zero ");
		Excecao.validaNumeroRange(idAjuda, this.ajudas.size(), "Erro na avaliacao de tutor: id nao encontrado ");
		
		if (this.ajudas.get(idAjuda-1).getAvaliado() == true)
			throw new RuntimeException("Erro na avaliacao de tutor: Ajuda ja avaliada");
		
		Aluno aluno = this.getAluno(this.ajudas.get(idAjuda-1).getMatrTutor());
		
		this.tutores.get(aluno).setAvaliacao(nota);
		this.ajudas.get(idAjuda-1).setAvaliado();
	}
	
	public String pegarNota(String matriculaTutor) {
		
		if (this.getAluno(matriculaTutor) == null)
			throw new NullPointerException("Erro ao pegar nota de tutor: Aluno nao encontrado");
		if (!this.tutores.containsKey(this.getAluno(matriculaTutor)))
			throw new NullPointerException("Erro ao pegar nota de tutor: Tutor nao encontrado");
		
		return this.tutores.get(this.getAluno(matriculaTutor)).pegarNota();
	}
	
	public String pegarNivel(String matriculaTutor) {
		
		if (this.getAluno(matriculaTutor) == null)
			throw new NullPointerException("Erro ao pegar nivel de tutor: Aluno nao encontrado");
		if (!this.tutores.containsKey(this.getAluno(matriculaTutor)))
			throw new NullPointerException("Erro ao pegar nivel de tutor: Tutor nao encontrado");
		
		return this.tutores.get(this.getAluno(matriculaTutor)).pegarNivel();
	}
	
	public void doar(String matriculaTutor, int totalCentavos) {
		
		Excecao.validaNumeroPositivo(totalCentavos, "Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		if (this.getAluno(matriculaTutor) == null)
			throw new NullPointerException("Erro na doacao para tutor: Tutor nao encontrado");
		if (!this.tutores.containsKey(this.getAluno(matriculaTutor)))
			throw new NullPointerException("Erro na doacao para tutor: Tutor nao encontrado");
		
		Tutor tutor = this.tutores.get(this.getAluno(matriculaTutor));
		
		double porc;
    	
    	if (tutor.pegarNivel().equals("TOP")) {
    		porc = 0.9 + ((tutor.getAvaliacao() % 4.5) / 10);
    	} else if (tutor.pegarNivel().equals("APRENDIZ")) {
    		porc = 0.4 - ((3.0 - tutor.getAvaliacao()) / 10);
    	} else {
    		porc = 0.8;
    	}
    	
    	int doar = (int) ((totalCentavos) * porc);
    	
    	this.dinheiro += totalCentavos - doar;
    	
    	tutor.doar(doar);
		
	}
	
	public int totalDinheiroTutor(String emailTutor) {
		
		if (emailTutor.trim().isEmpty() || emailTutor == null)
			throw new IllegalArgumentException("Erro na consulta de total de dinheiro do tutor: emailTutor nao pode ser vazio ou nulo");
		if (!emailTutor.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro na consulta de total de dinheiro do tutor: Email invalido");
		if (this.getTutorPorEmail(emailTutor) == null)
			throw new NullPointerException("Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		
		return this.getTutorPorEmail(emailTutor).totalDinheiroTutor();
		
	}
	
	public int totalDinheiroSistema() {
		return this.dinheiro;
	}
	
}
	
