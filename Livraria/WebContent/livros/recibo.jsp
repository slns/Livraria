<h3>Obrigado ${param.nome}.</h3>
<br>
<jsp:useBean id="now" class="java.util.Date" />
<jsp:setProperty name="now" property="time" value="${now.time + 432000000}" />
	
<!-- Note o uso da classe Date e da configura��o da data representada pelo objeto 
	como igual � data atual (now.time) adicionada de 5 dias 
	(equivalente a 432000000 milisegundos). -->
	
Seus livros ser�o enviados para voc� em
<fmt:formatDate value="${now}" type="date" dateStyle="full" />
.
<br>
<br>
<c:remove var="cart" scope="session" />
<c:url var="url" value="/livros/livraria" />
<strong> <a href="${url}">Continuar comprando</a>
	&nbsp;&nbsp;&nbsp;
</strong>