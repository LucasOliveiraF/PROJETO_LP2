package excecoes;

public class Excecao {
	
	private final static String expressaoEmail = "(.+)@(.+)";
	
	public static void validaString(String parametro, String msg) {
		if (parametro.trim().isEmpty() || parametro == null)
			throw new NullPointerException(msg);
	}
	
	public static void validaEmail(String email, String msg) {
		if (email.trim().isEmpty() || email == null)
			throw new NullPointerException("Erro " + msg + ": email nao pode ser vazio ou em branco");
		if (!email.matches(expressaoEmail))
			throw new IllegalArgumentException("Erro " + msg + ": Email invalido");
	}
	
	public static void validaNumeroEstritamentePositivo(int numero, String msg) {
		if (numero <= 0)
			throw new IndexOutOfBoundsException(msg);
	}
	
	public static void validaNumeroPositivo(int numero, String msg) {
		if (numero < 0)
			throw new IndexOutOfBoundsException(msg);
	}
	
	public static void validaNumeroRange(int numero, int minimo, int maximo, String msg) {
		if (numero < minimo || numero > maximo)
			throw new IndexOutOfBoundsException(msg);
	}
	
	public static void validaNumeroRange(int numero, int maximo, String msg) {
		if (numero > maximo)
			throw new IndexOutOfBoundsException(msg);
	}
	
	public static void validaDiaDaSemana(String dia, String msg) {
		if (dia.trim().isEmpty() || dia == null)
			throw new NullPointerException("Erro " + msg + ": dia nao pode ser vazio ou em branco");
		if (!dia.equalsIgnoreCase("seg") && !dia.equalsIgnoreCase("ter") && !dia.equalsIgnoreCase("qua") && !dia.equalsIgnoreCase("qui") && !dia.equalsIgnoreCase("sex"))
			throw new IllegalArgumentException("Erro " + msg + ": dia invalido");
	}

}
