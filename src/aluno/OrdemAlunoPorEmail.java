package aluno;

import java.util.Comparator;

/**
 * Classe que ordena alunos por email.
 * @author Lucas Oliveira e Rute Farias.
 *
 */

public class OrdemAlunoPorEmail implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		if (o1.getEmail().equals(o2.getEmail())) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getEmail().compareTo(o2.getEmail());
	}

}
