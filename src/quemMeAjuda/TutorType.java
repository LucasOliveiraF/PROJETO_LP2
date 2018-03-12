package quemMeAjuda;

public enum TutorType {
	
	TOP, 
	Tutor, 
	APRENDIZ;
	
	public TutorType getAvaliacao(double avaliacao) {
		if (avaliacao > 4.5)
			return TOP;
		if (avaliacao > 3.0 && avaliacao <= 4.5)
			return Tutor;
		if (avaliacao > 0 && avaliacao <= 3.0)
			return APRENDIZ;
		return null;
	}
 
}
