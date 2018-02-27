package quemMeAjuda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TutorTest {

	Tutor tutor;
	
	@Before
	public void before() {
		tutor = new Tutor();
	}
	
	@Test
	public void cadastraDisciplinaTest() throws Exception {
		tutor.cadastraDisciplina("Disciplina1", 1);
		tutor.cadastraDisciplina("Disciplina2", 5);
	}
	
	@Test
	public void cadastraHorarioTest() throws Exception {
		tutor.cadastraHorario("18:08", "seg");
		tutor.cadastraHorario("20:00", "Ter");
		tutor.cadastraHorario("14:00", "qua");
		tutor.cadastraHorario("12:00", "Qui");
		tutor.cadastraHorario("08:00", "sex");
		
		assertEquals(true, tutor.consultaHorario("08:00", "Sex"));
		assertEquals(true, tutor.consultaHorario("20:00", "ter"));
		assertEquals(false, tutor.consultaHorario("12:00", "Sex"));
		assertEquals(false, tutor.consultaHorario("04:00", "Sex"));
	}
	
	@Test
	public void cadastraLocal() {
		tutor.cadastraLocalDeAtendimento("LCC2");
		tutor.cadastraLocalDeAtendimento("LCC1");
		
		assertEquals(true, tutor.consultaLocal("LCC2"));
		assertEquals(true, tutor.consultaLocal("LCC1"));
		assertEquals(false, tutor.consultaLocal("LCC3"));
	}
	
	//Excecoes
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraDisciplinaVazia() throws Exception {
		tutor.cadastraDisciplina("  ", 1);
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraDisciplinaNula() throws Exception {
		tutor.cadastraDisciplina(null, 1);
	}
	
	@Test(expected=Exception.class)
	public void cadastraProficienciaMenorQue1() throws Exception {
		tutor.cadastraDisciplina("Disciplina", 0);
	}
	
	@Test(expected=Exception.class)
	public void cadastraProficienciaMaiorQue5() throws Exception {
		tutor.cadastraDisciplina("Disciplina", 6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraHorarioVazio() throws Exception {
		tutor.cadastraHorario(" ", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraHorarioNulo() throws Exception {
		tutor.cadastraHorario(null, "seg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraDiaVazio() throws Exception {
		tutor.cadastraHorario("08:00", "  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraDiaNulo() throws Exception {
		tutor.cadastraHorario("08:00", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraDiaInvalido() throws Exception {
		tutor.cadastraHorario("08:00", "dia");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void cadastraLocalVazio() throws Exception {
		tutor.cadastraLocalDeAtendimento("  ");;
	}
	
	@Test(expected=NullPointerException.class)
	public void cadastraLocalNulo() throws Exception {
		tutor.cadastraLocalDeAtendimento(null);;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaHorarioVazio() {
		tutor.consultaHorario("  ", "seg");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaHorarioNulo() {
		tutor.consultaHorario(null, "seg");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaDiaVazio() {
		tutor.consultaHorario("08:00", "  ");
	}
	
	@Test(expected=NullPointerException.class)
	public void consultaDiaNulo() {
		tutor.consultaHorario("08:00", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void consultaDiaInvalido() {
		tutor.consultaHorario("08:00", "dia");
	}

}
