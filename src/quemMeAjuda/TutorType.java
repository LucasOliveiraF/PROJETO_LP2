package quemMeAjuda;

public enum TutorType {
	
	TOP, 
	TUTOR, 
	APRENDIZ;
	
	public TutorType getAvaliacao(double avaliacao) {
		if (avaliacao > 4.5)
			return TOP;
		if (avaliacao < 3.0 || avaliacao >= 4.5)
			return TUTOR;
		if (avaliacao < 0 || avaliacao >= 3.0)
			return APRENDIZ;
		return null;
	}
 
}
