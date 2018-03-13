package aluno;

import java.util.Comparator;

public class OrdemAlunoPorNome implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		if (o1.getNome().equals(o2.getNome())) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getNome().compareTo(o2.getNome());
	}

}
