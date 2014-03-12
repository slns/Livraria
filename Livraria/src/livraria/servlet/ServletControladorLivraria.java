package livraria.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import livraria.negocio.CarrinhoCompras;
import livraria.negocio.Livraria;
import livraria.negocio.Livro;
import livraria.negocio.excecoes.CompraException;
import livraria.negocio.excecoes.LivroNaoEncontradoException;

public class ServletControladorLivraria extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String idLivro = null;
		String limpar = null;
		int quantidade = 0;
		Livro livro = null;
		Livraria livraria = (Livraria) getServletContext().getAttribute(
				LivrariaContextListener.SISTEMA_LIVRARIA);
		HttpSession session = request.getSession();
		CarrinhoCompras carrinho = (CarrinhoCompras) session
				.getAttribute("cart");
		String acaoSelecionada = request.getServletPath();
		
		if (carrinho == null) {
			carrinho = new CarrinhoCompras();
			session.setAttribute("cart", carrinho);
		}
	
		if (acaoSelecionada.equals("/livros/catalogo")) {
			idLivro = request.getParameter("Add");
			if (!idLivro.equals("")) {
				try {
					
					
					livro = livraria.getLivro(idLivro);
					carrinho.adicionar(livro);
					/*response.getWriter().write(livro.getTitulo());
					response.getWriter().flush();
					return;*/
				} catch (LivroNaoEncontradoException ex) {
					// isso não deve acontecer
				} /*catch (IOException e) {
					// isso não deve acontecer
				}*/
			}
		} else if (acaoSelecionada.equals("/livros/recibo")) {
			try {
				livraria.comprarLivros(carrinho);
			} catch (CompraException ex) {
				ex.printStackTrace();
				// Aqui deve ser feito um tratamento da exceção
			}
		} else if (acaoSelecionada.equals("/livros/mostrarCarrinho")) {
			idLivro = request.getParameter("remover");
			if (idLivro != null) {
				carrinho.remover(idLivro);
			}
			limpar = request.getParameter("limpar");
			if ((limpar != null) && limpar.equals("limpar")) {
				carrinho.limpar();
			}
			idLivro = request.getParameter("alterar");
			if (idLivro != null) {
				quantidade = Integer.parseInt(request
						.getParameter("quantidade"));
				if (quantidade == 1) {
					carrinho.aumentarQuantidade(idLivro);
				} else if (quantidade == -1) {
					carrinho.diminuirQuantidade(idLivro);
				}
			}
		}
		
		
		String tela = acaoSelecionada +".jsp";
		
		
		try {
			request.getRequestDispatcher(tela).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.doGet(request, response);
	}
}