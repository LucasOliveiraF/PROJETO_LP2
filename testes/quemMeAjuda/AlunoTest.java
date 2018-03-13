package quemMeAjuda;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aluno.Aluno;

public class AlunoTest {

				
		@Test(expected=NullPointerException.class)
		public void matriculaVaziaTest() throws Exception {
			Aluno aluno = new Aluno("", "Laura Farias", 2, "83 3342-6543", "laura.farias@ccc.ufcg.edu.br" );
		}
		
		@Test(expected=NullPointerException.class)
		public void matriculaNulaTest() throws Exception {
			Aluno aluno = new Aluno(null,"Laura Farias", 2, "83 3342-6543", "laura.farias@ccc.ufcg.edu.br");
		}
	
		@Test(expected=IndexOutOfBoundsException.class)
		public void codigoCursoZeroTest() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", 0, "83 3342-6543", "laura.farias@ccc.ufcg.edu.br");
		}
		
		@Test(expected=IndexOutOfBoundsException.class)
		public void codigoCursoNegativoTest() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", -1, "83 3342-6543", "laura.farias@ccc.ufcg.edu.br");
		}
		
		@Test(expected=NullPointerException.class)
		public void emailVazioTest() throws Exception {
			Aluno aluno = new Aluno("116277865", "Laura Farias", 2, "83 3342-6543", "" );
		}
		
		@Test(expected=NullPointerException.class)
		public void emailNuloTest() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", 2, "83 3342-6543", null);
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void emailInvalido1Test() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", 2, "83 3342-6543", "laura.farias.ccc.ufcg.edu.br");
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void emailInvalido2Test() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", 2, "83 3342-6543", "@ccc.ufcg.edu.br");
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void emailInvalido3Test() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", 2, "83 3342-6543", "laura.farias@");
		}
		
		@Test
		public void getInfoAlunoTest() throws Exception {
			Aluno aluno = new Aluno("116277865","Laura Farias", 2, "83 3342-6543", "laura.farias@ccc.ufcg.edu.br");
			System.out.println(aluno.toString());
			assertEquals("Laura Farias", aluno.getInfoAluno("nome"));
			assertEquals("83 3342-6543", aluno.getInfoAluno("telefone"));
			assertEquals("laura.farias@ccc.ufcg.edu.br", aluno.getInfoAluno("email"));
		}
		
}

