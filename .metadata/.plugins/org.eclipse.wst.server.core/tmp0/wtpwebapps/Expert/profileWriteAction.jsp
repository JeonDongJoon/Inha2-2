<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="profileType.ProfileTypeDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="profile" class="profileType.ProfileTypeDTO" scope="page" />
<jsp:setProperty name="profile" property="profileTitle" />
<jsp:setProperty name="profile" property="profileContent" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>맛집 게시판</title>
</head>
<body>
	 <%
	 	String userID = null;
	 	if(session.getAttribute("userID") != null){
	 		userID = (String) session.getAttribute("userID");
	 	}
	 	int boardID = 0;
		if (request.getParameter("boardID") != null){
			boardID = Integer.parseInt(request.getParameter("boardID"));
		}

	 	if(userID == null){
	 		PrintWriter script = response.getWriter();
	 		script.println("<script>");
			script.println("alert('로그인을 해주세요.')");
	 		script.println("location.href = 'login.jsp'");
	 		script.println("</script>");
	 	} else {
	 		if (profile.getProfileTitle().equals("") || profile.getProfileContent().equals("")){
	 			PrintWriter script = response.getWriter();
		 		script.println("<script>");
		 		script.println("alert('입력이 안된 사항이 있습니다.')");
		 		script.println("history.back()");
		 		script.println("</script>");
		 	} else {
		 		ProfileTypeDAO profileDAO = new ProfileTypeDAO();
		 		int profileID = profileDAO.write(profile.getProfileTitle(), userID, profile.getProfileContent());
		 		if (profileID == -1){
			 		PrintWriter script = response.getWriter();
			 		script.println("<script>");
			 		script.println("alert('글쓰기에 실패했습니다.')");
			 		script.println("history.back()");
			 		script.println("</script>");
			 	}
			 	else{
			 		PrintWriter script = response.getWriter();
					script.println("<script>");
			 		script.println("alert('글쓰기에 성공 했습니다.')");
			 		script.println("location.href = 'profile.jsp'");
			 		script.println("</script>");
			 	}
		 	}
	 	}
	 %>
</body>
</html>