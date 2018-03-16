package aluno;

import java.util.Comparator;

public class OrdemAlunoPorNome implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		if (o1.getInfoAluno("nome").equals(o2.getInfoAluno("nome"))) {
			return o1.getMatricula().compareTo(o2.getMatricula());
		}
		return o1.getInfoAluno("nome").compareTo(o2.getInfoAluno("nome"));
	}

}
