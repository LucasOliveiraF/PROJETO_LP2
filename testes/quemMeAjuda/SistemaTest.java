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
		
		assertEquals("110118009 - Aluno1 - 123 - aluno@ccc.ufcg.edu.br" + NL +
				"112118009 - Aluna1 - 123 - aluna@ccc.ufcg.edu.br", sistema.listarTutores());
		
		sistema.cadastrarHorario("aluno@ccc.ufcg.edu.br", "08:00", "SEG");
		sistema.cadastrarHorario("aluno@ccc.ufcg.edu.br", "10:00", "QUA");
		sistema.cadastrarHorario("aluna@ccc.ufcg.edu.br", "10:00", "ter");
		sistema.cadastrarHorario("aluna@ccc.ufcg.edu.br", "08:00", "qui");
		
		sistema.cadastrarLocalDeAtendimento("aluna@ccc.ufcg.edu.br", "LCC1");
		sistema.cadastrarLocalDeAtendimento("aluno@ccc.ufcg.edu.br", "LCC2");
		
		assertEquals(true, sistema.consultaHorario("aluno@ccc.ufcg.edu.br", "08:00", "seg"));
		assertEquals(false, sistema.consultaHorario("aluno@ccc.ufcg.edu.br", "10:00", "ter"));
		assertEquals(true, sistema.consultaHorario("aluna@ccc.ufcg.edu.br", "10:00", "ter"));
		assertEquals(false, sistema.consultaHorario("aluna@ccc.ufcg.edu.br", "08:00", "seg"));
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
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioEmailVazio() throws Exception {
		sistema.cadastrarHorario("   ", "08:00", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraHorarioEmailNulo() throws Exception {
		sistema.cadastrarHorario(null, "08:00", "seg");
	}
	
	@Test(expected=Exception.class)
	public void cadastraHorarioEmailInvalido() throws Exception {
		sistema.cadastrarHorario("@ccc.ufcg.edu", "08:00", "seg");
	}
	
	@Test(expected=Exception.class)
	public void cadastraHorarioEmailNaoCadastrado() throws Exception {
		sistema.cadastrarHorario("aluno@ccc.ufcg.edu", "08:00", "seg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioVazio() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.cadastrarHorario("aluno@aluno", "   ", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraHorarioNulo() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.cadastrarHorario("aluno@aluno", null, "seg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioDiaVazio() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.cadastrarHorario("aluno@aluno", "08:00", "   ");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraHorarioDiaNulo() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.cadastrarHorario("aluno@aluno", "08:00", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraLocalEmailInvalido() throws Exception {
		sistema.cadastrarLocalDeAtendimento("@ccc.ufcg.edu", "LCC1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraLocalEmailVazio() throws Exception {
		sistema.cadastrarLocalDeAtendimento("   ", "LCC1");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraLocalEmailNulo() throws Exception {
		sistema.cadastrarLocalDeAtendimento(null, "LCC1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraLocalLocalVazio() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.cadastrarLocalDeAtendimento("aluno@aluno", "   ");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraLocalLocalNulo() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.cadastrarLocalDeAtendimento("aluno@aluno", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioEmailVazio() throws Exception {
		sistema.consultaHorario("   ", "08:00", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioEmailNulo() throws Exception {
		sistema.consultaHorario(null, "08:00", "seg");
	}
	
	@Test(expected=Exception.class)
	public void consultaHorarioEmailInvalido() throws Exception {
		sistema.consultaHorario("@ccc.ufcg.edu", "08:00", "seg");
	}
	
	@Test(expected=Exception.class)
	public void consultaHorarioEmailNaoCadastrado() throws Exception {
		sistema.consultaHorario("aluno@ccc.ufcg.edu", "08:00", "seg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioVazio() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.consultaHorario("aluno@aluno", "   ", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioNulo() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.consultaHorario("aluno@aluno", null, "seg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioDiaVazio() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.consultaHorario("aluno@aluno", "08:00", "   ");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioDiaNulo() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.consultaHorario("aluno@aluno", "08:00", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaLocalEmailInvalido() throws Exception {
		sistema.consultaLocal("@ccc.ufcg.edu", "LCC1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaLocalEmailVazio() throws Exception {
		sistema.consultaLocal("   ", "LCC1");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaLocalEmailNulo() throws Exception {
		sistema.consultaLocal(null, "LCC1");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaLocalLocalVazio() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.consultaLocal("aluno@aluno", "   ");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaLocalLocalNulo() throws Exception {
		sistema.cadastrarAluno("Aluno", "123", 1010, "", "aluno@aluno");
		sistema.tornarTutor("123", "Disciplina", 5);
		sistema.consultaLocal("aluno@aluno", null);
	}

}
