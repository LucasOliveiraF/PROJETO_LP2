package quemMeAjuda;

import static org.junit.Assert.*;

import org.junit.Test;

import ajuda.Ajuda;
import ajuda.AjudaOnline;
import ajuda.AjudaPresencial;

public class AjudaTest {

	@Test
	public void AjudaOnlineTest() {
		Ajuda ajudaOnline = new AjudaOnline("11011008", "1029383", "Programacao 2");
		
		assertEquals("Tutor - 1029383, disciplina - Programacao 2", ajudaOnline.pegarTutor());
		assertEquals("Programacao 2", ajudaOnline.getInfoAjuda("disciplina"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AjudaOnlineAtributoVazioTest() {
		Ajuda ajudaOnline = new AjudaOnline("11011008", "1029383", "Programacao 2");
		ajudaOnline.getInfoAjuda("  ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AjudaOnlineAtributoInvalidoTest() {
		Ajuda ajudaOnline = new AjudaOnline("11011008", "1029383", "Programacao 2");
		ajudaOnline.getInfoAjuda("dia");
	}
	
	@Test
	public void AjudaPresencialTest() {
		Ajuda ajudaPres = new AjudaPresencial("12341", "66314", "Portugues", "18:00", "seg", "LCC2");
		
		assertEquals("Tutor - 66314, horario - 18:00, dia - seg, local - LCC2, disciplina - Portugues", ajudaPres.pegarTutor());
		assertEquals("Portugues", ajudaPres.getInfoAjuda("disciplina"));
		assertEquals("18:00", ajudaPres.getInfoAjuda("horario"));
		assertEquals("seg", ajudaPres.getInfoAjuda("dia"));
		assertEquals("LCC2", ajudaPres.getInfoAjuda("localInteresse"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AjudaPresencialAtributoVazioTest() {
		Ajuda ajudaPres = new AjudaPresencial("12341", "66314", "Portugues", "18:00", "seg", "LCC2");
		ajudaPres.getInfoAjuda("  ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void AjudaPresencialAtributoInvalidoTest() {
		Ajuda ajudaPres = new AjudaPresencial("12341", "66314", "Portugues", "18:00", "seg", "LCC2");
		ajudaPres.getInfoAjuda("matricula");
	}

}
