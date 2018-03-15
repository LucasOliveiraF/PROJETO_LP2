package ajuda;

/**
 * Representacao de ajuda. Cada aluno pode solicitar ajuda a um tutor de acordo com sua necessidade, 
 * existe dois tipos de ajuda, ajuda online e ajuda presencial. Ajuda eh a classe pai, ajudaOnline e ajudaPresencial 
 * herdam de Ajuda.
 * @author Lucas Oliveira e Rute Farias
 *
 */


public abstract class Ajuda {

	protected String matrAluno, disciplina;
	protected String matrTutor;
	protected boolean avaliado;
	
	
	/**
	 *Constroi uma ajuda com a verificacao de avaliado iniciando com false. 
	 */
	
	public Ajuda() {
		this.avaliado = false;
	}
		
	public abstract String pegarTutor();
	
	public abstract String getInfoAjuda(String atributo);
	
	/**
	 * Retorna um boolean caso o tutor tenha sido avaliado ou nao
	 * @return true tutor ja avaliado
	 * @return false tutor nao avaliado
	 */
	
	public boolean getAvaliado() {
		return this.avaliado;
	}
	
	/**
	 * Modidica o atributo avaliado para true, quando um tutor eh avaliado.
	 */
	
	public void setAvaliado() {
		this.avaliado = true;
	}
	
	/**
	 * Retorna uma String com a matricula do tutor
	 * @return matricula do tutor
	 */
	
	public String getMatrTutor() {
		return matrTutor;
	}
	
}
