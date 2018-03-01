package quemMeAjuda;

public class Aluno implements Comparable<Aluno> {
	
	private String matricula;
	private String nome;
	private int codigoCurso;
	private String telefone;
	private String email;
	private int avaliacao;
	
	
	public Aluno(String matricula, String nome, int codigoCurso, String telefone, String email) throws Exception {
		
		if (matricula.trim().isEmpty() || matricula == null)
			throw new IllegalArgumentException("Erro no cadastro de aluno: Matricula nao pode ser vazia ou nula");
		if (nome.trim().isEmpty() || nome == null)
			throw new NullPointerException("Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		if (codigoCurso <= 0)
			throw new IllegalArgumentException("Erro no cadastro de aluno: Codigo do curso nao pode ser negativo");
		if (email.trim().isEmpty() || email == null)
			throw new IllegalArgumentException("Erro no cadastro de aluno: Email invalido");
		if (!email.matches("(.+)@(.+)"))
			throw new IllegalArgumentException("Erro no cadastro de aluno: Email invalido");
		
		this.matricula  = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone;
		this.email = email;
		this.avaliacao  = 5;
		
	}
	
	@Override
	public String toString() {
		if(this.telefone.trim().isEmpty()) {
			return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.email;
		}
		return this.matricula + " - " + this.nome + " - " + this.codigoCurso + " - " + this.telefone + " - " + this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
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
	
	public String getNome() {
		return nome;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}
	
	public String getInfoAluno(String atributo) throws Exception {

		if (atributo.equalsIgnoreCase("nome"))
			return this.nome;			
		if (atributo.equalsIgnoreCase("telefone") && !this.telefone.trim().isEmpty())
			return this.telefone;
		if (atributo.equalsIgnoreCase("email"))
			return this.email;
		else
			throw new Exception("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
	
	}
	
	@Override
	public int compareTo(Aluno aluno) {
		return this.nome.compareToIgnoreCase(aluno.getNome());
	}
	
}

