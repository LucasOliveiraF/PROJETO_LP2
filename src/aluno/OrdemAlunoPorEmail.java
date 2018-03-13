package aluno;

import java.util.Comparator;

public class OrdemAlunoPorEmail implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		if (o1.getEmail().equals(o2.getEmail())) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getEmail().compareTo(o2.getEmail());
	}

}
