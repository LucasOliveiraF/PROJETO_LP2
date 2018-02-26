package facade;

import easyaccept.EasyAccept;

public class Facade {
	
	public static void main(String[] args) {
		args = new String[] {"facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt"};
		EasyAccept.main(args);
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		
	}
	
	public String recuperaAluno(String matricula) {
		return "";
	}
	
	public String listarAlunos() {
		return "";
	}
	
	public String getInfoAluno(String matricula, String atributo) {
		return "";
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) {
		
	}
	
	public String recuperaTutor(String matricula) {
		return "";
	}
	
	public String listarTutores() {
		return "";
	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) {
		
	}
	
	public boolean consultaHorario(String email, String horario, String dia) {
		return true;
	}
	
	public boolean consultaLocal(String email, String local) {
		return true;
	}
	
}
