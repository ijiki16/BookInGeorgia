<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>File Upload</title>
</head>
<body>
	<form action="Uploader" method="post" enctype="multipart/form-data">
		<input type="file" name="file" value = "select image....">
		<button id="bt1" type = "submit">Upload</button>
	</form>
</body>
</html>