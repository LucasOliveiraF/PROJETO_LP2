package tutor;

import java.util.Comparator;

public class OrdemTutorPorMatricula implements Comparator<Tutor> {

	@Override
	public int compare(Tutor o1, Tutor o2) {
		return o1.getMatricula().compareTo(o2.getMatricula());
	}

}
