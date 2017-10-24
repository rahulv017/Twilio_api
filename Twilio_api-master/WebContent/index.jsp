<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <form action="/sms" method="post">
      <input type="text" value="name" name="name" > <br>
      <input type="text" value="number" name="number" ><br>
      <input type="text" value="message" name="message" ><br>
      Your Sleeping Time From:<input type="text" value="HH:MM:SS" name="time" ><br>
             To<input type="text" value="HH:MM:SS" name="time1">
      <input type="submit">
      
  </form>
</body>
</html>