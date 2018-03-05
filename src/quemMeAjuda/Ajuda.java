package quemMeAjuda;

public abstract class Ajuda {

	protected String matrAluno, disciplina;
	protected String matrTutor;
	
	public abstract String pegarTutor();
	
	public abstract String getInfoAjuda(String atributo);
	
}
