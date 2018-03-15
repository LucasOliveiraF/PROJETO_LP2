package sistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import aluno.Aluno;
import tutor.Tutor;

public class GerenciadorDeDados {
	
	private String diretorio;
	
	public GerenciadorDeDados(String diretorio) {
		this.diretorio = diretorio;
	}
	
	public void salvarAlunos(List<Aluno> alunos) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(this.diretorio + File.separator + "alunos" + ".txt");
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(alunos);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}
	}
	
	public void salvarTutores(List<Tutor> tutores) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(this.diretorio + File.separator + "tutores" + ".txt");
			@SuppressWarnings("resource")
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(tutores);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new IOException(e);
				}
			}
		}
	}

}
