package quemMeAjuda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	Sistema sistema;
	private final String NL = System.lineSeparator();
	
	@Before
	public void Before() {
		sistema = new Sistema();
	}
	
	@Test
	public void tornarTutorTest() throws Exception {
		sistema.cadastrarAluno("Aluno1", "110118009", 123, "", "aluno@ccc.ufcg.edu.br");
		sistema.cadastrarAluno("Aluna1", "112118009", 123, "", "aluna@ccc.ufcg.edu.br");
		sistema.tornarTutor("110118009", "Disciplina1", 4);
		sistema.tornarTutor("112118009", "Disciplina1", 4);
		
		assertEquals("110118009 - Aluno1 - 123 - aluno@ccc.ufcg.edu.br", this.sistema.recuperaTutor("110118009"));
		assertEquals("112118009 - Aluna1 - 123 - aluna@ccc.ufcg.edu.br", this.sistema.recuperaTutor("112118009"));
		
		assertEquals("112118009 - Aluna1 - 123 - aluna@ccc.ufcg.edu.br" + NL +
				"110118009 - Aluno1 - 123 - aluno@ccc.ufcg.edu.br", sistema.listarTutores());
		
		System.out.println(sistema.listarTutores());
	}
	
	// Excecoes
	
	@Test(expected=Exception.class)
	public void tornarTutorAlunoNaoExiste() throws Exception {
		sistema.tornarTutor("110293273", "Disciplina", 4);
	}
	
	@Test(expected=Exception.class)
	public void tornarTutorMatriculaVazia() throws Exception {
		sistema.tornarTutor("  ", "Disciplina", 4);
	}
	
	@Test(expected=Exception.class)
	public void tornarTutorMatriculaNula() throws Exception {
		sistema.tornarTutor(null, "Disciplina", 4);
	}
	
	@Test(expected=Exception.class)
	public void tornarTutorDisciplinaVazia() throws Exception {
		sistema.tornarTutor("110293273", "   ", 4);
	}
	
	@Test(expected=Exception.class)
	public void tornarTutorDisciplinaNula() throws Exception {
		sistema.tornarTutor("110293273", null, 4);
	}
	
	@Test(expected=Exception.class)
	public void tornarTutorProficienciaMenorQue1() throws Exception {
		sistema.tornarTutor("110293273", "Disciplina", 0);
	}
	
	@Test(expected=Exception.class)
	public void tornarTutorProficienciaMaiorQue5() throws Exception {
		sistema.tornarTutor("110293273", "Disciplina", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void recuperaTutorInexistente() {
		sistema.recuperaTutor("1029238");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void recuperaTutorMatriculaVazia() {
		sistema.recuperaTutor("   ");
	}
	
	@Test(expected=NullPointerException.class)
	public void recuperaTutorMatriculaNula() {
		sistema.recuperaTutor(null);
	}
	
	@Test(expected=Exception.class)
	public void listaTutoresVazio() throws Exception {
		sistema.listarTutores();
	}

}
