package quemMeAjuda;

public class AjudaOnline extends Ajuda {

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
