package aluno;

import java.util.Comparator;

/**
 * Classe que ordena aluno pela matricula
 * @author Lucas Oliveira e Rute Farias.
 *
 */

public class OrdemAlunoPorMatricula implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return o1.getMatricula().compareTo(o2.getMatricula());
	}

}
