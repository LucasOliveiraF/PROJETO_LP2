package ajuda;

/**
 * Classe que representa uma ajuda online, a ajuda online pode ser feita atraves do email ou hangouts.
 * AjudaOnline herda de Ajuda. Toda ajudaOnline tem matricula do aluno, matricula do tutor e a disciplina.
 * @author Lucas Oliveira e Rute Farias
 *
 */

public class AjudaOnline extends Ajuda {
	
	/**
	 * Constroi uma ajudaOnline a partir da matricula do aluno, matricula do tutor e disciplina
	 * @param matrAluno matricula do aluno
	 * @param matrTutor matricula do tutor
	 * @param disciplina disciplina de interesse do aluno para receber ajuda
	 */

	public AjudaOnline(String matrAluno, String matrTutor, String disciplina) {
		
		if (matrAluno.trim().isEmpty() || matrAluno == null)
			throw new NullPointerException("Erro no pedido de ajuda online: matricula de aluno nao pode ser vazio ou em branco");
		if (matrTutor.trim().isEmpty() || matrTutor == null)
			throw new NullPointerException("Erro no pedido de ajuda online: tutor nao encontrado");
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new NullPointerException("Erro no pedido de ajuda online: disciplina nao pode ser vazio ou em branco");
		
		this.matrAluno = matrAluno;
		this.matrTutor = matrTutor;
		this.disciplina = disciplina;
	}
	
	/**
	 * Representacao de um tutor: Tutor - ..., disciplina - ...
	 */

	@Override
	public String pegarTutor() {
		return "Tutor - "+ this.matrTutor +", disciplina - " + this.disciplina;
	}

	@Override
	public String getInfoAjuda(String atributo) {
		if (atributo.equalsIgnoreCase("disciplina"))
			return this.disciplina;
		else if (atributo.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
		else
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
	}

}
