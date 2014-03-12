package livraria.negocio.excecoes;

public class LivroNaoEncontradoException extends Exception {
	public LivroNaoEncontradoException() {
	}

	public LivroNaoEncontradoException(String msg) {
		super(msg);
	}
}
