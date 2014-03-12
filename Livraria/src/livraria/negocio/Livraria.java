package livraria.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import livraria.negocio.excecoes.CompraException;
import livraria.negocio.excecoes.LivroNaoEncontradoException;

public class Livraria {
	private List<Livro> estoqueLivros;

	public Livraria() {
		estoqueLivros = new ArrayList<Livro>();
		popularLivros();
	}

	private void popularLivros() {
		
		Livro livro = new Livro();
		livro.setIdLivro("0596005407");
		livro.setAno(2008);
		livro.setTitulo("Head First Servlets and JSP");
		livro.setDescricao("Livro sobre Servlets e JSP.");
		livro.setAutores("Bryan Basham, Kathy Sierra, Bert Bates");
		livro.setQuantidade(10);
		livro.setPreco(200.5);
		estoqueLivros.add(livro);
		
		livro = new Livro();
		livro.setIdLivro("9788576053576");
		livro.setAno(2008);
		livro.setTitulo("Core Java - Volume 1");
		livro.setDescricao("Livro sobre tecnologias usadas na programação Java.");
		livro.setAutores("Gary Cornell");
		livro.setQuantidade(10);
		livro.setPreco(135.9);
		estoqueLivros.add(livro);
		
		livro = new Livro();
		livro.setIdLivro("9788573935721");
		livro.setAno(2007);
		livro.setTitulo("Desenvolvendo Aplicações Web com JSP, Servlets, “ + “JavaServer Faces, Hibernate, EJB 3 Persistence e Ajax");
		livro.setDescricao("Livro sobre tecnologias usadas na programação Java para Web.");
		livro.setAutores("Edson Gonçalves");
		livro.setQuantidade(10);
		livro.setPreco(110.9);
		estoqueLivros.add(livro);
		
		livro = new Livro();
		livro.setIdLivro("8534614881");
		livro.setAno(2003);
		livro.setTitulo("Aprenda J2EE com EJB, JSP,Servlets, JNDI e XML");
		livro.setDescricao("Livro sobre tecnologias usadas na programação Java para Web com EJB, JSP,Servlets, JNDI e XML.");
		livro.setAutores("Peter Roxburgh");
		livro.setQuantidade(10);
		livro.setPreco(220.9);
		estoqueLivros.add(livro);
		
		livro = new Livro();
		livro.setIdLivro("9788573935723");
		livro.setAno(2007);
		livro.setTitulo("JSF Eficaz");
		livro.setDescricao("Livro sobre tecnologias usadas na programação Java para Web com JSF.");
		livro.setAutores("Edson Gonçalves");
		livro.setQuantidade(10);
		livro.setPreco(45.9);
		estoqueLivros.add(livro);
		
		livro = new Livro();
		livro.setIdLivro("9788573935724");
		livro.setAno(2007);
		livro.setTitulo("JSP Servelet JAVA ");
		livro.setDescricao("Livro sobre tecnologias usadas na programação Jsp, Servlets e J2EE.");
		livro.setAutores("Rodrigo Fernandes de Mello");
		livro.setQuantidade(10);
		livro.setPreco(160.9);
		estoqueLivros.add(livro);
		
		livro = new Livro();
		livro.setIdLivro("9788573936575");
		livro.setAno(2007);
		livro.setTitulo("Livro JAVA na WEB com JSF");
		livro.setDescricao("Livro sobre tecnologias usadas na programação Java para Web com JSF.");
		livro.setAutores("Yuri Marx Pereira Gomes");
		livro.setQuantidade(10);
		livro.setPreco(200.9);
		estoqueLivros.add(livro);
	}

	public List<Livro> getLivros() {
		return Collections.unmodifiableList(estoqueLivros);
	}

	public Livro getLivro(String idLivro) throws LivroNaoEncontradoException {
		Livro livroProcurado = null;
		for (Livro book : estoqueLivros) {
			if (book.getIdLivro().equals(idLivro)) {
				livroProcurado = book;
			}
		}
		if (livroProcurado == null) {
			throw new LivroNaoEncontradoException(
					"Não foi possível encontrar o livro: " + idLivro);
		}
		return livroProcurado;
	}

	public void comprarLivros(CarrinhoCompras carrinho) throws CompraException {
		Collection<ItemCompra> items = carrinho.getItens();
		Iterator<ItemCompra> i = items.iterator();
		while (i.hasNext()) {
			ItemCompra item = (ItemCompra) i.next();
			Livro livro = (Livro) item.getItem();
			String id = livro.getIdLivro();
			int quantity = item.getQuantidade();
			comprarLivro(id, quantity);

		}
	}

	public void comprarLivro(String idLivro, int qtdComprada)
			throws CompraException {
		Livro livroSelecionado;
		try {
			livroSelecionado = getLivro(idLivro);
		} catch (LivroNaoEncontradoException e) {
			throw new CompraException(e.getMessage());
		}
		int qtdEstoque = livroSelecionado.getQuantidade();
		if ((qtdEstoque - qtdComprada) >= 0) {
			int novaQtd = qtdEstoque - qtdComprada;
			livroSelecionado.setQuantidade(novaQtd);
		} else {
			throw new CompraException("Livro " + idLivro
					+ " sem estoque suficiente.");
		}
	}

	public void fechar() {
		// liberaria conexões de banco de dados, se usasse
	}
}