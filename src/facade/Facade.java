package facade;

import java.io.IOException;

import easyaccept.EasyAccept;
import sistema.Sistema;

public class Facade {
	
	private Sistema sistema;
	
	public static void main(String[] args) {
		args = new String[] {"facade.Facade", "acceptance_test/us1_test.txt", "acceptance_test/us2_test.txt", "acceptance_test/us3_test.txt", "acceptance_test/us4_test.txt", "acceptance_test/us5_test.txt", "acceptance_test/us6_test.txt"};
		EasyAccept.main(args);
	}
	
	public Facade() {
		sistema = new Sistema();
	}
	
	public void cadastrarAluno(String nome, String matricula, int codigoCurso, String telefone, String email) {
		sistema.cadastrarAluno(nome, matricula, codigoCurso, telefone, email);
	}
	
	public String recuperaAluno(String matricula) throws Exception {
		return this.sistema.recuperaAluno(matricula);
	}
	
	public String listarAlunos() {
		return sistema.listarAlunos();
	}
	
	public String getInfoAluno(String matricula, String atributo) throws Exception {
		return this.sistema.getInfoAluno(matricula,atributo);
	}
	
	public void tornarTutor(String matricula, String disciplina, int proficiencia) throws Exception {
		this.sistema.tornarTutor(matricula, disciplina, proficiencia);
	}
	

	public String recuperaTutor(String matricula) throws Exception {
		return this.sistema.recuperaTutor(matricula);
	}
	
	public String listarTutores() throws Exception {
		return this.sistema.listarTutores();
	}
	
	public void cadastrarHorario(String email, String horario, String dia) {
		this.sistema.cadastrarHorario(email, horario, dia);
	}
	
	public void cadastrarLocalDeAtendimento(String email, String local) {
		this.sistema.cadastrarLocalDeAtendimento(email, local);
	}
	
	public boolean consultaHorario(String email, String horario, String dia) {
		return this.sistema.consultaHorario(email, horario, dia);
	}
	
	public boolean consultaLocal(String email, String local) {
		return this.sistema.consultaLocal(email, local);
	}
	
	public int pedirAjudaPresencial (String matrAluno, String disciplina, String horario, String dia, String localInteresse) {
		return this.sistema.pedirAjudaPresencial(matrAluno, disciplina, horario, dia, localInteresse);
	}
	
	public int pedirAjudaOnline (String matrAluno, String disciplina) {
		return this.sistema.pedirAjudaOnline(matrAluno, disciplina);
	}
	
	public String pegarTutor(int idAjuda) {
		return this.sistema.pegarTutor(idAjuda);
	}
	
	public String getInfoAjuda(int idAjuda, String atributo) {
		return this.sistema.getInfoAjuda(idAjuda, atributo);
	}
	
	public void avaliarTutor (int idAjuda, int nota) {
		this.sistema.avaliarTutor(idAjuda, nota);
	}
	
	public String pegarNota(String matriculaTutor) {
		return this.sistema.pegarNota(matriculaTutor);
	}
	
	public String pegarNivel(String matriculaTutor) {
		return this.sistema.pegarNivel(matriculaTutor);
	}
	
    public void doar(String matriculaTutor, int totalCentavos) {
    	this.sistema.doar(matriculaTutor, totalCentavos);
    }
    
    public int totalDinheiroTutor(String emailTutor) {
    	return this.sistema.totalDinheiroTutor(emailTutor);
    }
    
    public int totalDinheiroSistema() {
    	return this.sistema.totalDinheiroSistema();
    }
    
    public void salvar() throws IOException {
    	this.sistema.salvar();
    }

}
