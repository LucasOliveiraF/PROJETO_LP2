package aluno;

import java.util.Comparator;

public class OrdemAlunoPorEmail implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		if (o1.getInfoAluno("email").equals(o2.getInfoAluno("email"))) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getInfoAluno("email").compareTo(o2.getInfoAluno("email"));
	}

}
