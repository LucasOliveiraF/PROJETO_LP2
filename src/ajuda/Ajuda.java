package ajuda;

public abstract class Ajuda {

	protected String matrAluno, disciplina;
	protected String matrTutor;
	protected boolean avaliado;
	
	public Ajuda() {
		this.avaliado = false;
	}
	
	public abstract String pegarTutor();
	
	public abstract String getInfoAjuda(String atributo);
	
	public boolean getAvaliado() {
		return this.avaliado;
	}
	
	public void setAvaliado() {
		this.avaliado = true;
	}
	
	public String getMatrTutor() {
		return matrTutor;
	}
	
}
