package tutor;

/**
 * Classe que representa os tres niveis de tutor. O tutor pode ser: TOP, Tutor ou APRENDIZ.
 * @author Lucas Oliveira e Rute Farias
 *
 */

public enum TutorType {
	
	TOP, 
	Tutor, 
	APRENDIZ;
	
	/**
	 * Retorna o nivel do tutor a partir do calculo da sua nota. 
	 * @param avaliacao avaliacao que o tutor recebe apos prestar ajuda
	 * @return o nivel do tutor 
	 * 
	 */
	
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
