<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>Work Manager Completable Future App</h1>
	<c:forEach var="val" items="${msg}">
		<h2>${val}</h2>
	</c:forEach>
</body>
</html>