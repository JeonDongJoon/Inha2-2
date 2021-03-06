<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="review.ReviewDTO"%>

<%@ page import="review.ReviewDAO"%>

<%@ page import="java.io.PrintWriter"%>

<%

	request.setCharacterEncoding("UTF-8");



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

	String reviewName = null;

	String expertName = null;

	String timeDivide = null;

	String reviewDivide = null;

	String reviewTitle = null;

	String reviewContent = null;

	String totalScore = null;

	String timeScore = null;

	String comfortableScore = null;

	String reviewScore = null;

	
	if(request.getParameter("expertName") != null) {

		expertName = (String) request.getParameter("expertName");

	}


	if(request.getParameter("timeDivide") != null) {

		timeDivide = (String) request.getParameter("timeDivide");

	}

	if(request.getParameter("reviewDivide") != null) {

		reviewDivide = (String) request.getParameter("reviewDivide");

	}

	if(request.getParameter("reviewTitle") != null) {

		reviewTitle = (String) request.getParameter("reviewTitle");

	}

	if(request.getParameter("reviewContent") != null) {

		reviewContent = (String) request.getParameter("reviewContent");

	}

	if(request.getParameter("totalScore") != null) {

		totalScore = (String) request.getParameter("totalScore");

	}

	if(request.getParameter("timeScore") != null) {

		timeScore = (String) request.getParameter("timeScore");

	}

	if(request.getParameter("comfortableScore") != null) {

		comfortableScore = (String) request.getParameter("comfortableScore");

	}

	if(request.getParameter("reviewScore") != null) {

		reviewScore = (String) request.getParameter("reviewScore");

	}

	if (expertName == null || timeDivide == null ||

			reviewDivide == null || reviewTitle == null || reviewContent == null || totalScore == null ||

					timeScore == null || comfortableScore == null || reviewScore == null ||

			reviewTitle.equals("") || reviewContent.equals("")) {

		PrintWriter script = response.getWriter();

		script.println("<script>");

		script.println("alert('입력이 안 된 사항이 있습니다.');");

		script.println("history.back();");

		script.println("</script>");

		script.close();

		return;

	} else {

		ReviewDAO reviewDAO = new ReviewDAO();

		int result = reviewDAO.write(new ReviewDTO(0, userID, expertName,

				timeDivide, reviewDivide, reviewTitle, reviewContent,

				totalScore, timeScore, comfortableScore, reviewScore, 0));

		if (result == -1) {

			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("alert('평가 등록에 실패했습니다.');");

			script.println("history.back();");

			script.println("</script>");

			script.close();

			return;

		} else {

			PrintWriter script = response.getWriter();

			script.println("<script>");

			script.println("location.href = './review.jsp';");

			script.println("</script>");

			script.close();

			return;

		}

	}

%>