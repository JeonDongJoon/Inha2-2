<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="user.UserDAO"%>

<%@ page import="review.ReviewDAO"%>

<%@ page import="likey.LikeyDTO"%>

<%@ page import="java.io.PrintWriter"%>

<%

	String userID = null;

	if(session.getAttribute("userID") != null) {

		userID = (String) session.getAttribute("userID");

	}

	if(userID == null) {

		PrintWriter script = response.getWriter();

		script.println("<script>");

		script.println("alert('로그인을 해주세요.');");

		script.println("location.href = 'userLogin.jsp'");

		script.println("</script>");

		script.close();

		return;

	}

	request.setCharacterEncoding("UTF-8");

	String reviewID = null;

	if(request.getParameter("reviewID") != null) {

		reviewID = (String) request.getParameter("reviewID");

	}

	ReviewDAO reviewDAO = new ReviewDAO();

	if(userID.equals(reviewDAO.getUserID(reviewID))) {

		int result = new ReviewDAO().delete(reviewID);

		if (result == 1) {

			session.setAttribute("userID", userID);

			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("alert('삭제가 완료되었습니다.');");

			script.println("location.href='review.jsp'");

			script.println("</script>");

			script.close();

			return;

		} else {

			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("alert('데이터베이스 오류가 발생했습니다.');");

			script.println("history.back();");

			script.println("</script>");

			script.close();

			return;

		}

	} else {

		PrintWriter script = response.getWriter();

		script.println("<script>");

		script.println("alert('자신이 쓴 글만 삭제 가능합니다.');");

		script.println("history.back();");

		script.println("</script>");

		script.close();

		return;

	}

%>