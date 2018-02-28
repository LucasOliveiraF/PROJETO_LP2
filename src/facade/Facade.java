package facade;

import easyaccept.EasyAccept;
import quemMeAjuda.Sistema;

public class Facade {
	
	private Sistema sistema;
	
	public static void main(String[] args) {
		args = new String[] {"facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		sistema = new Sistema();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) throws Exception {
		sistema.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	public String recuperaAluno(String matricula) {
		return "";
	}
	
	public String listarAlunos() {
		return sistema.listarAlunos();
	}
	
	public String getInfoAluno(String matricula, String atributo) {
		return "";
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		this.sistema.tornarTutor(matricula, disciplina, proficiencia);
	}
	
	public String recuperaTutor(String matricula) {
		return this.sistema.recuperaTutor(matricula);
	}
	
	public String listarTutores() throws Exception {
		return this.sistema.listarTutores();
	}
	
	public void cadastrarHorario(String email, String horario, String dia) throws Exception {
		this.sistema.cadastrarHorario(email, horario, dia);
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) throws Exception {
		this.sistema.cadastrarLocalDeAtendimento(email, local);
	}
	
	public boolean consultaHorario(String email, String horario, String dia) throws Exception {
		return this.sistema.consultaHorario(email, horario, dia);
	}
	
	public boolean consultaLocal(String email, String local) throws Exception {
		return this.sistema.consultaLocal(email, local);
	}
	
}
