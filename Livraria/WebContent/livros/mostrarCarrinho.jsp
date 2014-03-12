<script>
	function continuar(url) {
		window.opener.open(url, "_parent");
		window.close();
	}
</script>

<jsp:useBean id="livrariaBean" class="livraria.negocio.LivrariaBean"
	scope="page">
	<jsp:setProperty name="livrariaBean" property="sistema" value="${sistemaLivraria}" />
</jsp:useBean>
<c:if test="${param.limpar}">
	<font color="red" size="+2"><strong> O carrinho de compras foi esvaziado! </strong><br>&nbsp;<br>
	</font>
</c:if>
<c:if test="${param.remover != '0' && param.remover != null}">
	<c:set var="id" value="${param.remover}" />
	<jsp:setProperty name="livrariaBean" property="idLivro" value="${id}" />
	<c:set var="livroRemovido" value="${livrariaBean.livro}" />
	<font color="red" size="+2">O seguinte livro foi removido do carrinho: <em>${livroRemovido.titulo}</em>. <br>&nbsp;<br>
	</font>
</c:if>
<c:if test="${sessionScope.cart.numeroItens > 0}">
	<font size="+2">Quantidade de itens do carrinho: ${sessionScope.cart.numeroItens}
	<c:if test="${sessionScope.cart.numeroItens == 1}"> livro. 
	</c:if> 
	<c:if test="${sessionScope.cart.numeroItens > 1}"> livros. 
	</c:if>
	</font>
	<br>&nbsp; <table summary="layout">
		<tr>
			<th align=left colspan="3">Quantidade</th>
			<th align=left>Título</th>
			<th align=left>Preço</th>
		</tr>
		<c:forEach var="itemCompra" items="${sessionScope.cart.itens}">
			<c:set var="livro" value="${itemCompra.item}" />
			<tr>
				<td bgcolor="#ffffff"><c:url var="url" value="/livros/mostrarCarrinho">
						<c:param name="alterar" value="${livro.idLivro}" />
						<c:param name="quantidade" value="1" />
					</c:url> <a href="${url}"><img src="../imagem/inc.gif" height="10" width="10" /></a>
					</td>
				<td bgcolor="#ffffff"><c:url var="url" value="/livros/mostrarCarrinho">
						<c:param name="alterar" value="${livro.idLivro}" />
						<c:param name="quantidade" value="-1" />
					</c:url> <a href="${url}"><img src="../imagem/dec.gif" height="10" width="10" /></a>
					</td>
				<td align="left" bgcolor="#ffffff">${itemCompra.quantidade}
				</td>
				<td bgcolor="#ffffaa"><c:url var="url" value="/livros/detalhesLivro">
						<c:param name="idLivro" value="${livro.idLivro}" />
						<c:param name="Clear" value="0" />
					</c:url> <strong><a href="${url}">${livro.titulo}</a></strong>
					</td>
				<td bgcolor="#ffffaa" align="right">
				<fmt:formatNumber value="${livro.preco}" type="currency" />&nbsp;
				</td>				
				<td bgcolor="#ffffaa"><c:url var="url" value="/livros/mostrarCarrinho">
						<c:param name="remover" value="${livro.idLivro}" />
					</c:url> <strong><a href="${url}">Remover</a></strong>
					</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" bgcolor="#ffffff"><br>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right" bgcolor="#ffffff">Subtotal
			</td>
			<td bgcolor="#ffffaa" align="right"><fmt:formatNumber value="${sessionScope.cart.total}" type="currency" />
			</td>
			<td><br>
			</td>
		</tr>
	</table>
	<p>&nbsp;
	<p>
		<c:url var="url" value="/livros/catalogo">
			<c:param name="Add" value="" />
		</c:url>
		<strong><a href="javascript:continuar('${url}')">Continuar comprando</a>&nbsp;&nbsp;&nbsp; 
		<c:url var="url" value="/livros/comprar" />
			<a href="javascript:continuar('${url}')">Finalizar compra
			</a>&nbsp;&nbsp;&nbsp;
			<c:url var="url" value="/livros/mostrarCarrinho">
				<c:param name="limpar" value="limpar" />
				<c:param name="remover" value="0" />
			</c:url>
			 <a href="${url}">Esvaziar carrinho
			 </a></strong>
</c:if>
<c:if test="${sessionScope.cart.numeroItens <= 0}">
	<font size="+2">Carrinnho vazio</font>
	<br>&nbsp;<br>
	<c:url var="url" value="/livros/catalogo">
		<c:param name="Add" value="" />
	</c:url>
	<strong><a href="${url}">Ver catálogo</a></strong>
</c:if>
</body>
</html>