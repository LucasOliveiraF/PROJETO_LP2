package tutor;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import aluno.Aluno;
import excecoes.Excecao;

/**
 * Classe que representa um tutor no sistema.Todo tutor eh tambem um aluno. Todo tutor possui disciplinas, 
 * horarios, locais, avaliacao, dinheiro e um nivel.
 * @author Lucas Oliveira e Rute Farias.
 */

public class Tutor implements Serializable {
 	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Aluno aluno;
	private Map<String, Integer> disciplinas;
	private Map<String, String> horarios;
	private Set<String> locais;
	private double avaliacao;
	private int dinheiro;
	TutorType nivel;

	
	/**
	 * Constroi um tutor a partir de um aluno, disciplinas, horarios, locais, avaliacao, nivel e dinheiro.
	 * @param aluno aluno constoi um tutor
	 * @param disciplinas disciplinas que o tutor pode ajudar
	 * @param horarios horario em que o tutor pode ajudar
	 * @param locais local para ajuda
	 * @param avaliacao avaliacao de quanto um tutor eh habil na disciplina, iniciando com o valor 4(0-5) 
	 * @param nivel nivel de cada tutor, pode ser definidos em tres tipos: TOP, Tutor e APRENDIZ.
	 */
		
	
	public Tutor(Aluno aluno) {
		this.aluno = aluno;
		this.disciplinas = new HashMap<>();
		this.horarios = new HashMap<>();
		this.locais = new HashSet<>();
		this.avaliacao = 4;
		this.nivel = TutorType.Tutor;
		this.dinheiro = 0;
	}
	
	/**
	 * Cadastra uma disciplina a partir do nome da disciplina e a proficiencia do aluno.
	 * 
	 */
	
	public void cadastraDisciplina(String disciplina, int proficiencia) {
		Excecao.validaString(disciplina, "Erro na definicao de papel: disciplina nao pode ser vazia ou nula");
		if (this.disciplinas.containsKey(disciplina))
			throw new RuntimeException("Erro na definicao de papel: Ja eh tutor dessa disciplina");
		Excecao.validaNumeroRange(proficiencia, 1, 5, "Erro na definicao de papel: Proficiencia invalida");
		
		this.disciplinas.put(disciplina, proficiencia);
	}
	
	/**
	 * Cadstra um horario em que o monitor estara disponivel para ajudar a partir de um horario
	 * e o dia da semana("seg", "ter", "qua", "qui", "sex")
	 */
	 	  	
	
	public void cadastraHorario(String horario, String dia) {
		
		Excecao.validaString(horario, "Erro no cadastrar horario: horario nao pode ser vazio ou em branco");
		Excecao.validaString(dia, "Erro no cadastrar horario: dia nao pode ser vazio ou em branco");
		Excecao.validaDiaDaSemana(dia, "no cadastrar horario");
		
		this.horarios.put(horario, dia);
	}
	

	/**
	 * Cadastra o local de atendimento que o tutor pode prestar ajuda a partir do local.
	 * @param local local de atendimento
	 */
	
	public void cadastraLocalDeAtendimento(String local) {
		
		Excecao.validaString(local, "Erro no cadastrar local de atendimento: local nao pode ser vazio ou em branco");
		
		this.locais.add(local);
	}


	/**
	 * Consulta um horario de atendimento a partir de um horario e um dia da semana.
	 * @param horario horario de atendimento
	 * @param dia dia da semana(ex: seg, ter, qua, qui, sex)
	 * @return boolean true caso o horario e dia estejam cadastrados
	 * @return boolean false caso o horario e dia nao estejam cadastrados
	 */
	
	public boolean consultaHorario(String horario, String dia) {
		
		Excecao.validaString(horario, "Erro na consulta de horario: horario nao pode ser vazio em branco");
		Excecao.validaDiaDaSemana(dia, "na consulta de horario");
		
		return this.horarios.containsKey(horario) && this.horarios.get(horario).equalsIgnoreCase(dia);
	}

	/**
	 * Retorna um boolean na consulta do local de atendimento.
	 * @param local local de atendimento
	 * @return true caso o local passado como paramentro exista na colecao
	 * @return false caso o local nao exista na colecao.
	 */
	
	public boolean consultaLocal(String local) {
		
		Excecao.validaString(local, "Erro na consulta de local de atendimento: local nao pode ser vazio ou em branco");
		
		return this.locais.contains(local);
	}
	
	/**
	 * Retorna um boolean na consulta das disciplinas.
	 * @param disciplina disciplina que deseja consultar
	 * @return true caso a disciplina passada como parametro exista na colecao
	 * @return false caso a disciplina nao exista na colecao
	 */
	
	public boolean consultaDisciplinas(String disciplina) {
		if (this.disciplinas.containsKey(disciplina))
			return true;
		else
			return false;
	}
	
	/**
	 * Modifica a nivel do tutor a partir da avaliacao que ele recebe.
	 * @param avaliacao avaliacao do tutor
	 */
	
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = ((this.avaliacao * 5.0) + avaliacao ) / 6.0;
		this.nivel = this.nivel.getAvaliacao(this.avaliacao);
	}
	
	/**
	 * Retorna uma String com a nota do tutor
	 * @return temp nota no formato decimal
	 */
		
	public String pegarNota() {
		double valor = this.avaliacao;
		
		DecimalFormat df = new DecimalFormat("0.00");
		String temp = df.format(valor);
		temp = temp.replace(".",",");
		
		return temp;
	}
	
	/**
	 * Retorna uma representacao do nivel do tutor
	 * @return nivel do tutor 
	 */
	
	
	public String pegarNivel() {
		return this.nivel.toString();
	}
	
	/**
	 * Retorna a avaliacao de tutor em double
	 * @return avaliacao do tutor
	 */
	
	public double getAvaliacao() {
		return avaliacao;
	}
	
	/**
	 * Doacao ao tutor
	 * @param totalCentavos valor que sera doado
	 */
	
    public void doar(int totalCentavos) {
    	this.dinheiro += totalCentavos;
    }
    
    /**
     * Retorna a soma total do dinheiro do tutor
     * @return dinheiro do tutor
     */
    
    public int totalDinheiroTutor() {
    	return this.dinheiro;
    }
    
    /**
     * Retorna o nome do tutor 
     * @return nome do tutor
     */
    
    public String getNome() {
    	return this.aluno.getNome();
    }
    
    /**
     * Retorna a matricula do tutor
     * @return matricula do tutor
     */
    
    public String getMatricula() {
		return this.aluno.getMatricula();
	}
    
    /**
     * Retorna uma String com o email do tutor
     * @return email do tutor
     */
    
    public String getEmail() {
		return this.aluno.getEmail();
	}
    
    /**
     * Retorna a proficiencia do tutor em uma determinada disciplina
     * @param disciplina disciplina que deseja saber a proficiencia
     * @return disciplina 
     */

    public int getProficiencia(String disciplina) {
    	return this.disciplinas.get(disciplina);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
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
		Tutor other = (Tutor) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		return true;
	}

}

