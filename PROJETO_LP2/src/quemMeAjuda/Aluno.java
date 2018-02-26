package quemMeAjuda;

public class Aluno {
	
	private String matricula;
	private String nome;
	private int codigoCurso;
	private String telefone;
	private String email;
	private int avaliacao;
	
	
	public Aluno(String matricula, String nome, int codigoCurso, String telefone, String email) {
		this.matricula  = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = "";
		this.email = email;
		this.avaliacao  = 5;
		
	}
	
	@Override
	public String toString() {
		if(this.telefone.trim() == "") {
			return this.matricula + "-" + this.nome + "-" + this.codigoCurso + "-" + this.email;
		}
		return this.matricula + "-" + this.nome + "-" + this.codigoCurso + "-" + this.telefone + "-" + this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	public String getMatricula() {
		return matricula;
	}
	
	public String getInfoAluno(String atributo) throws Exception {
		if (atributo.equals("nome"))
			return this.nome;
		else if (atributo.equals("telefone") && !this.telefone.trim().isEmpty())
			return this.telefone;
		else if (atributo.equals("email"))
			return this.email;
		else
			throw new Exception();
	}


	
	
	
}
