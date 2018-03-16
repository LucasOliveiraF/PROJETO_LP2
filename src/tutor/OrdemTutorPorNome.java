package tutor;

import java.util.Comparator;

/**
 * Classe que ordena tutor pelo nome.
 * @author Lucas Oliveira e Rute Farias.
 *
 */

public class OrdemTutorPorNome implements Comparator<Tutor> {

	@Override
	public int compare(Tutor o1, Tutor o2) {
		if (o1.getNome().equals(o2.getNome())) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getNome().compareTo(o2.getNome());
	}

}
