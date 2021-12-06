<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="user.UserDAO"%>
<%@ page import="user.UserDTO"%>
<!DOCTYPE html>
<html>
  <head>

    <title>전문가 Expert 사이트</title>

    <meta charset="utf-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- 부트스트랩 CSS 추가하기 -->

    <link rel="stylesheet" href="./css/bootstrap.min.css">

    <!-- 커스텀 CSS 추가하기 -->

    <link rel="stylesheet" href="./css/custom.css">

  </head>

  <body>

<%

	request.setCharacterEncoding("UTF-8");

	String reviewDivide = "전체";

	String searchType = "최신순";

	String search = "";

	int pageNumber = 0;

	if(request.getParameter("reviewDivide") != null) {

		reviewDivide = request.getParameter("reviewDivide");

	}

	if(request.getParameter("searchType") != null) {

		searchType = request.getParameter("searchType");

	}

	if(request.getParameter("search") != null) {

		search = request.getParameter("search");

	}

	if(request.getParameter("pageNumber") != null) {

		try {

			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

		} catch (Exception e) {

			System.out.println("검색 페이지 번호 오류");

		}

	}

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

	}

	boolean emailChecked = new UserDAO().getUserEmailChecked(userID);

	if(emailChecked == false) {

		PrintWriter script = response.getWriter();

		script.println("<script>");

		script.println("location.href = 'emailSendConfirm.jsp'");

		script.println("</script>");

		script.close();		

		return;

	}

%>	

    <nav class="navbar navbar-expand-lg navbar-light bg-light">

      <a class="navbar-brand" href="index.jsp">전문가 Expert 사이트</a>

      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar">

        <span class="navbar-toggler-icon"></span>

      </button>

      <div class="collapse navbar-collapse" id="navbar">

        <ul class="navbar-nav mr-auto">

          <li class="nav-item">

            <a class="nav-link" href="index.jsp">메인</a>

          </li>
          <li class="nav-item active">

            <a class="nav-link" href="profile.jsp">전문가</a>
          </li>
           <li class="nav-item">

            <a class="nav-link" href="review.jsp">후기</a>
          </li>

          <li class="nav-item dropdown">

            <a class="nav-link dropdown-toggle" id="dropdown" data-toggle="dropdown">

              회원 관리

            </a>

            <div class="dropdown-menu" aria-labelledby="dropdown">

<%

	if(userID == null) {

%>

              <a class="dropdown-item" href="userLogin.jsp">로그인</a>

              <a class="dropdown-item" href="userRegister.jsp">회원가입</a>

<%

	} else {

%>

              <a class="dropdown-item" href="userLogout.jsp">로그아웃</a>

<%

	}

%>

            </div>

          </li>

        </ul>

        <form action="./review.jsp" method="get" class="form-inline my-2 my-lg-0">

          <input type="text" name="search" class="form-control mr-sm-2" placeholder="내용을 입력하세요.">

          <button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>

        </form>

      </div>

    </nav>
    <!-- 네비게이션 영역 끝 -->
	<div class="container">		<!-- 하나의 영역 생성 -->
		<div class="col-lg-4">	<!-- 영역 크기 -->
			<!-- 점보트론은 특정 컨텐츠, 정보를 두드러지게 하기 위한 큰 박스 -->
			<div class="jumbotron" style="padding-top: 20px;">
				<form method = "post" action="kakao.jsp">
					<h3 style="text-align: center;">코인 충전 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" name="name">
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" name="email" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="전화번호" name="phone" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주소" name="address" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="총가격" name="totalPrice" maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control" value="결제하기"> 
					<input type="reset" class="btn btn-primary form-control" value="취소하기">
				</form>
			</div>
		</div>	
	</div>
<footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">

     Copyright ⓒ 2021 전동준 All Rights Reserved.

    </footer>

    <!-- 제이쿼리 자바스크립트 추가하기 -->

    <script src="./js/jquery.min.js"></script>

    <!-- Popper 자바스크립트 추가하기 -->

    <script src="./js/popper.min.js"></script>

    <!-- 부트스트랩 자바스크립트 추가하기 -->

    <script src="./js/bootstrap.min.js"></script>

</body>
</html>