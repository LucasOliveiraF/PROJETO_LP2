package sistema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ajuda.Ajuda;
import ajuda.AjudaOnline;
import ajuda.AjudaPresencial;
import aluno.Aluno;
import aluno.OrdemAlunoPorEmail;
import aluno.OrdemAlunoPorMatricula;
import aluno.OrdemAlunoPorNome;
import excecoes.Excecao;
import tutor.OrdemTutorPorEmail;
import tutor.OrdemTutorPorMatricula;
import tutor.OrdemTutorPorNome;
import tutor.Tutor;

public class Sistema {
	
	private List<Aluno> alunos;
	private List<Tutor> tutores;
	private List<Ajuda> ajudas;
	private int dinheiro = 0;
	private Comparator<Aluno> ordemAluno;
	private Comparator<Tutor> ordemTutor;
	
	public Sistema() {
		this.alunos = new ArrayList<>();
		this.tutores = new ArrayList<>();
		this.ajudas = new ArrayList<>();
		this.ordemAluno = new OrdemAlunoPorNome();
		this.ordemTutor = new OrdemTutorPorNome();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {

		for (Aluno aluno : alunos) {
			Excecao.validaEmail(email, "no cadastro de aluno");
			
			if(aluno.getMatricula().equals(matricula))
				throw new IllegalArgumentException("Erro no cadastro de aluno: Aluno de mesma matricula ja cadastrado");
			}

		Aluno aluno = new Aluno(matricula, nome, codigoCurso, telefone, email);
		alunos.add(aluno);
		Collections.sort(this.alunos, this.ordemAluno);
		
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
		Tutor tutor = new Tutor(aluno);
		
		if (this.tutores.contains(tutor)) {
			this.getTutor("matricula", matricula).cadastraDisciplina(disciplina, proficiencia);
		} else {
			this.tutores.add(tutor);
			this.getTutor("matricula", matricula).cadastraDisciplina(disciplina, proficiencia);
		}
		
		Collections.sort(this.tutores, ordemTutor);
		
	}
	
	public String recuperaTutor(String matricula) throws Exception {

		Excecao.validaString(matricula, "Erro na busca por tutor: matricula nao pode ser vazia ou nula");
		if (this.getAluno(matricula) == null)
			throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		if (this.getTutor("matricula", matricula) == null)
				throw new IllegalArgumentException("Erro na busca por tutor: Tutor nao encontrado");
		
		return this.getAluno(matricula).toString();
	}
	
	public String listarTutores() {
		
		if (this.tutores.isEmpty())
			throw new NullPointerException("Erro ao listar tutores: Nenhum tutor cadastrado");
		
		String retorno = "";
		
		for (Tutor tutor: this.tutores) {
			retorno += this.getAluno(tutor.getMatricula()).toString() + ", ";
		}
		
		return retorno.trim().substring(0, retorno.length() - 2);
		
	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		
		Excecao.validaEmail(email, "no cadastrar horario");
		if (this.getTutor("email", email) == null)
			throw new NullPointerException("Erro no cadastrar horario: tutor nao cadastrado");
		
		this.getTutor("email", email).cadastraHorario(horario, dia);
		
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) {
		
		Excecao.validaEmail(email, "no cadastrar local de atendimento");
		if (this.getTutor("email", email) == null)
			throw new NullPointerException("Erro no cadastrar local de atendimento: tutor nao cadastrado");
		
		this.getTutor("email", email).cadastraLocalDeAtendimento(local);
		
	}
	
	public boolean consultaHorario(String email, String horario, String dia) {
		Excecao.validaEmail(email, "na consulta de Horario");
		
		if (this.getTutor("email", email) == null)
			return false;
		
		return this.getTutor("email", email).consultaHorario(horario, dia);
		
		
	}
	
	public boolean consultaLocal(String email, String local) {
		Excecao.validaEmail(email, "na consulta de local de atendimento");
		
		if (this.getTutor("email", email) == null)
			return false;
		
		return this.getTutor("email", email).consultaLocal(local);
	}
	
	private Tutor getTutor(String atributo, String parametro) {
		
		if (atributo.equalsIgnoreCase("email")) {
			for (Tutor tutor : this.tutores) {
				if (tutor.getEmail().equals(parametro))
					return tutor;
			}
		} else if (atributo.equalsIgnoreCase("matricula")) {	
			for (Tutor tutor : this.tutores) {
				if (tutor.getMatricula().equals(parametro))
					return tutor;
			}
		}
		return null;
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
		List<Tutor> lista = new ArrayList<>();
		
		for (Tutor tutor : this.tutores) {
			if (tutor.consultaDisciplinas(disciplina) && tutor.consultaHorario(horario, dia) && tutor.consultaLocal(localInteresse)) {
				lista.add(tutor);
			}
		}
		
		int profic = 0;
		
		for (Tutor tutor : lista) {
			if (tutor.getProficiencia(disciplina) > profic)
				matrTutor = tutor.getMatricula();
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
		List<Tutor> lista = new ArrayList<>();
		
		for (Tutor tutor : this.tutores) {
			if (tutor.consultaDisciplinas(disciplina)) {
				lista.add(tutor);
			}
		}
		
		int profic = 0;
		
		for (Tutor tutor : lista) {
			if (tutor.getProficiencia(disciplina) > profic)
				matrTutor = tutor.getMatricula();
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
		
		Tutor tutor = this.getTutor("matricula", this.ajudas.get(idAjuda-1).getMatrTutor());
		
		tutor.setAvaliacao(nota);
		this.ajudas.get(idAjuda-1).setAvaliado();
	}
	
	public String pegarNota(String matriculaTutor) {
		
		if (this.getAluno(matriculaTutor) == null)
			throw new NullPointerException("Erro ao pegar nota de tutor: Aluno nao encontrado");
		if (this.getTutor("matricula", matriculaTutor) == null)
			throw new NullPointerException("Erro ao pegar nota de tutor: Tutor nao encontrado");
		
		return this.getTutor("matricula", matriculaTutor).pegarNota();
	}
	
	public String pegarNivel(String matriculaTutor) {
		
		if (this.getTutor("matricula", matriculaTutor) == null)
			throw new NullPointerException("Erro ao pegar nivel de tutor: Tutor nao encontrado");
		
		return this.getTutor("matricula", matriculaTutor).pegarNivel();
	}
	
	public void doar(String matriculaTutor, int totalCentavos) {
		
		Excecao.validaNumeroPositivo(totalCentavos, "Erro na doacao para tutor: totalCentavos nao pode ser menor que zero");
		if (this.getTutor("matricula", matriculaTutor) == null)
			throw new NullPointerException("Erro na doacao para tutor: Tutor nao encontrado");
		
		Tutor tutor = this.getTutor("matricula", matriculaTutor);
		
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
		if (this.getTutor("email", emailTutor) == null)
			throw new NullPointerException("Erro na consulta de total de dinheiro do tutor: Tutor nao encontrado");
		
		return this.getTutor("email", emailTutor).totalDinheiroTutor();
		
	}
	
	public int totalDinheiroSistema() {
		return this.dinheiro;
	}
	
	public void configurarOrdem(String atributo) {
		
		Excecao.validaString(atributo, "Erro ao configurar ordem: atributo nao pode ser vazio ou nulo");
		
		if (atributo.equalsIgnoreCase("nome")) {
			this.ordemAluno = new OrdemAlunoPorNome();
			this.ordemTutor = new OrdemTutorPorNome();
			Collections.sort(this.alunos, this.ordemAluno);
			Collections.sort(this.tutores, this.ordemTutor);
		} else if (atributo.equalsIgnoreCase("email")) {
			this.ordemAluno = new OrdemAlunoPorEmail();
			this.ordemTutor = new OrdemTutorPorEmail();
			Collections.sort(this.alunos, this.ordemAluno);
			Collections.sort(this.tutores, this.ordemTutor);
		} else if (atributo.equalsIgnoreCase("matricula")) {
			this.ordemAluno = new OrdemAlunoPorMatricula();
			this.ordemTutor = new OrdemTutorPorMatricula();
			Collections.sort(this.alunos, this.ordemAluno);
			Collections.sort(this.tutores, this.ordemTutor);
		} else {
			throw new IllegalArgumentException("Erro ao configurar ordem: atributo invalido");
		}
	}
	
}
	
