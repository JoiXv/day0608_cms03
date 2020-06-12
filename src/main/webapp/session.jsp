<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>session</title>
	</head>
	<body>
 			姓名：${name }<br>
 			年龄：${age }<br>
 			出生日期：<%=session.getAttribute("birthday") %>
	</body>
</html>