package ajuda;

/**
 * Classe que representa uma ajuda presencial. Na ajuda presencial o aluno indica disciplina, dia e local de interesse
 * para receber a ajuda. AjudaPresencial herda de Ajuda.
 * @author Lucas Oliveira e Rute Farias
 *
 */

public class AjudaPresencial extends Ajuda {

	private String horario, dia, localInteresse;
	
	/**
	 * Constroi uma ajuda presencial a partir do horario , dia e local de interesse.
	 * @param matrAluno matricula do aluno
	 * @param matrTutor matricula do tutor
	 * @param disciplina disciplina de interesse do aluno para receber ajuda
	 * @param horario horario para ajuda 
	 * @param dia dia da semana, ex: seg, ter, qua, qui, sex.
	 * @param localInteresse local onde se pretende receber/dar ajuda.
	 */
	
	public AjudaPresencial(String matrAluno, String matrTutor, String disciplina, String horario, String dia, String localInteresse) {
		
		if (matrAluno.trim().isEmpty() || matrAluno == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: matricula de aluno nao pode ser vazio ou em branco");
		if (matrTutor.trim().isEmpty() || matrTutor == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: tutor nao encontrado");
		if (disciplina.trim().isEmpty() || disciplina == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: disciplina nao pode ser vazio ou em branco");
		if (horario.trim().isEmpty() || horario == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: horario nao pode ser vazio ou em branco");
		if (dia.trim().isEmpty() || dia == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: dia nao pode ser vazio ou em branco");
		if (localInteresse.trim().isEmpty() || localInteresse == null)
			throw new NullPointerException("Erro no pedido de ajuda presencial: local de interesse nao pode ser vazio ou em branco");
		
		this.matrAluno = matrAluno;
		this.matrTutor = matrTutor;
		this.disciplina = disciplina;
		this.horario = horario;
		this.dia = dia;
		this.localInteresse = localInteresse;
	}

	/**
	 * Representacao de um tutor: Tutor - ..., horario - ..., dia - ..., local - ..., disciplina - ...
	 */
	
	@Override
	public String pegarTutor() {
		return "Tutor - "+ this.matrTutor +", horario - "+ this.horario +", dia - " + this.dia +", local - " + this.localInteresse +", disciplina - " + this.disciplina;
	}

	@Override
	public String getInfoAjuda(String atributo) {
		if (atributo.equalsIgnoreCase("disciplina"))
			return this.disciplina;
		else if (atributo.equalsIgnoreCase("horario"))
			return this.horario;
		else if (atributo.equalsIgnoreCase("dia"))
			return this.dia;
		else if (atributo.equalsIgnoreCase("localInteresse"))
			return this.localInteresse;
		else if (atributo.trim().isEmpty())
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao pode ser vazio ou em branco");
		else
			throw new IllegalArgumentException("Erro ao tentar recuperar info da ajuda : atributo nao encontrado");
	}

}
