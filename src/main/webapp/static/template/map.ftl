<!DOCTYPE html>
<html>
  	<head>
      	<meta charset="UTF-8">
      	<title>map.html</title>
  	</head>
	<body>
		姓名：${name}；
		<#if age lte 18><!-- lte表示小于等于，lt表示小于，gt表示大于 -->
			小盆友好好读书
			<#else>
			努力赚钱
		</#if>
		
		<#if id lte 5>
			right
			<#else>
			wrong
		</#if>
		
		年龄：${age}
	</body>
</html>