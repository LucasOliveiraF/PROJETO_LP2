package tutor;

import java.util.Comparator;

/**
 * Classe que ordena tutor pelo email
 * @author Lucas Oliveira e Rute Farias.
 *
 */

public class OrdemTutorPorEmail implements Comparator<Tutor> {

	@Override
	public int compare(Tutor o1, Tutor o2) {
		if (o1.getEmail().equals(o2.getEmail())) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getEmail().compareTo(o2.getEmail());
	}

}
