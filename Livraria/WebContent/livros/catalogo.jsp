<jsp:useBean id="livrariaBean" class="livraria.negocio.LivrariaBean"
	scope="page">
	<jsp:setProperty name="livrariaBean" property="sistema"
		value="${sistemaLivraria}" />
</jsp:useBean>
<c:if test="${!empty param.Add}">
	<c:set var="idL" value="${param.Add}" />
	<jsp:setProperty name="livrariaBean" property="idLivro" value="${idL}" />
	<c:set var="livroAdicionado" value="${livrariaBean.livro}" />
	&nbsp;&nbsp;
	<h3>
		<font color="red" size="+2"> Você adicionou o livro <em>${livroAdicionado.titulo}</em>
			ao seu carrinho de compras.
		</font>
	</h3>
	&nbsp;&nbsp;
</c:if>
<c:choose>
	<c:when test="${sessionScope.cart.numeroItens > 0}">
		<c:url var="url" value="/livros/mostrarCarrinho">
			<c:param name="limpar" value="0" />
			<c:param name="remover" value="0" />
		</c:url>
		<p>
			<strong><a href="${url}">Ver carrinho de compras</a>&nbsp;&nbsp;&nbsp;
				<c:url var="url" value="/livros/comprar"/> <a href="${url}">Finalizar
					compras</a> </strong>
		</p>
	</c:when>
	<c:otherwise>
Seu carrinho de compras está vazio.
</c:otherwise>
</c:choose>
<br>
&nbsp;
<br>
&nbsp;
<h3>Livros disponíveis para compra:</h3>
<center>
	<table summary="layout">
		<c:forEach var="livro" begin="0" items="${livrariaBean.livros}">
			<tr>
				<c:set var="idLivro" value="${livro.idLivro}" />
				<td bgcolor="#ffffaa"><c:url var="url" value="/livros/detalhesLivro">
						<c:param name="idLivro" value="${idLivro}"/>
					</c:url> <a href="${url}"> 
					<strong>${livro.titulo}&nbsp; 
					</strong>
				</a>
				</td>
				<td bgcolor="#ffffaa" rowspan=2>
				<fmt:formatNumber value="${livro.preco}" type="currency"/> &nbsp;
				</td>
				<td bgcolor="#ffffaa" rowspan=2>
				<c:url var="url" value="/livros/catalogo">
						<c:param name="Add" value="${idLivro}"/>
					</c:url>
					<p>
						<strong> <a href="${url}">&nbsp;Adicionar livro ao
								carrinho&nbsp; </a>
						</strong></td>
			</tr>
			<tr>
				<td bgcolor="#ffffff">&nbsp;&nbsp;<em>${livro.autores}&nbsp;</em>
				</td>
			</tr>
		</c:forEach>
	</table>
</center>