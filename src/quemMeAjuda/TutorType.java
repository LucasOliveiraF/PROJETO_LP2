package quemMeAjuda;

public enum TutorType {
	
	TOP(4.5), 
	TUTOR(3.0), 
	APRENDIZ(0);
	
	private double avaliacao;
	
	private TutorType(double avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public TutorType getAvaliacao() {
		if (avaliacao > 4.5)
			return TOP;
		if (avaliacao < 3.0 && avaliacao >= 4.5)
			return TUTOR;
		if (avaliacao < 0 && avaliacao >= 3.0)
			return APRENDIZ;
		return null;
	}
 
}
