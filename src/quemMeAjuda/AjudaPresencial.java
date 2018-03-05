package quemMeAjuda;

public class AjudaPresencial extends Ajuda {

	private String horario, dia, localInteresse;
	
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
