<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="test-result" method="post">
        <input type="file" name="file">
		<input type="hidden" name="type" value="create"/>
        <button type="submit">Upload</button>
    </form>
</body>
</html>