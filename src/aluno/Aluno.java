package aluno;

import java.io.Serializable;

import excecoes.Excecao;

/**
 * Classe que representa um Aluno no sistema. Todo aluno tem matricula, nome, codigo do curso, email e avaliacao.
 * Aluno pode ter telefone e o que diferencia um aluno de outro é a matricula e email
 * 
 *
 */

public class Aluno implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String matricula;
	private String nome;
	private int codigoCurso;
	private String telefone;
	private String email;
	private int avaliacao;
	
	/**
	 * Constroi um aluno apartir da matricula, nome, codigo do curso, telefone (se houver, caso nao haja usar string vazia) e email
	 * @param matricula matricula do aluno (unica)
	 * @param nome nome do aluno
	 * @param codigoCurso codigo do curso do aluno (numero inteiro)
	 * @param telefone telefone do aluno, se houver
	 * @param email email do aluno (unico)
	 */
	
	public Aluno(String matricula, String nome, int codigoCurso, String telefone, String email) {
		
		Excecao.validaString(matricula, "Erro no cadastro de aluno: Matricula nao pode ser vazia ou nula");
		Excecao.validaString(nome, "Erro no cadastro de aluno: Nome nao pode ser vazio ou nulo");
		Excecao.validaNumeroEstritamentePositivo(codigoCurso, "Erro no cadastro de aluno: Codigo do curso nao pode ser negativo");
		Excecao.validaEmail(email, "no cadastro de aluno");
		
		this.matricula  = matricula;
		this.nome = nome;
		this.codigoCurso = codigoCurso;
		this.telefone = telefone;
		this.email = email;
		this.avaliacao  = 5;
		
	}
	
	/**
	 * Retorna a representacao do aluno no formato:
	 * MATRICULA + NOME + CODIGO DO CURSO + TELEFONE (SE HOUVER) + EMAIL
	 */
	
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
	
	/**
	 * Retorna o atributo pedido (entre nome, telefone e email)
	 * @param atributo atributo pedido na chamada do metodo
	 * @return retorna um atributo (entre nome, telefone e email)
	 * @throws Exception
	 */
	
	public String getInfoAluno(String atributo) {

		if (atributo.equalsIgnoreCase("nome"))
			return this.nome;			
		if (atributo.equalsIgnoreCase("telefone") && !this.telefone.trim().isEmpty())
			return this.telefone;
		if (atributo.equalsIgnoreCase("email"))
			return this.email;
		else
			throw new IllegalArgumentException("Erro na obtencao de informacao de aluno: Aluno nao encontrado");
	
	}
 
}

