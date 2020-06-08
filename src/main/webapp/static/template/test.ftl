<!DOCTYPE html>
<html>
  	<head>
      	<meta charset="UTF-8">
      	<title>Insert title here</title>
  	</head>
	<body>
		<!-- types为FreeMarkTest中传过来的绑定名 -->
		<#list types as data>
			<h3>ID:${data.id}</h3>
			<h3>NAME:${data.name}</h3>
			<h3>CODE:${data.code}</h3>
		</#list>
	</body>
</html>