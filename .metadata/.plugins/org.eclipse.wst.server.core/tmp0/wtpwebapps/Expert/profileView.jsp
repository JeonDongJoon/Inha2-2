<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="profileType.ProfileTypeDAO" %>
<%@ page import="profileType.ProfileTypeDTO" %>
<%@ page import="user.UserDAO"%>
<%@ page import="user.UserDTO"%>
<%@ page import="java.io.File" %>
<!doctype html>

<html>

  <head>

    <title></title>

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
	
	int profileID = 0;
	if(request.getParameter("profileID") != null){
		profileID = Integer.parseInt(request.getParameter("profileID"));
	}
	
	// 만약 넘어온 데이터가 없다면
	if(profileID == 0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('유효하지 않은 글입니다')");
		script.println("location.href='profile.jsp'");
		script.println("</script");
	}
	
	// 유효한 글이라면 구체적인 정보를 'profileDTO'라는 인스턴스에 담는다
	ProfileTypeDTO profileDTO = new ProfileTypeDAO().getProfileDTO(profileID);

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
	<!-- 게시판 글 보기 양식 영역 시작 -->
	<div class="container">
		<div class="row">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color: #eeeeee; text-align: center;">전문가 Expert 사이트</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%;">글 제목</td>
						<td colspan="2"><%= profileDTO.getProfileTitle().replaceAll(" ", "&nbsp;")
								.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2"><%= profileDTO.getUserID() %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="height: 200px; text-align: left;"><%= profileDTO.getProfileContent().replaceAll(" ", "&nbsp;")
							.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
				</tbody>
			</table>
			<a href="profile.jsp" class="btn btn-primary">목록</a>
			
			<!-- 해당 글의 작성자가 본인이라면 수정과 삭제가 가능하도록 코드 추가 -->
			<%
				if(userID != null && userID.equals(profileDTO.getUserID())){
			%>
					<a href="profileUpdate.jsp?profileID=<%= profileID %>" class="btn btn-primary">수정</a>
					<a href="profileDeleteAction.jsp?profileID=<%= profileID %>" class="btn btn-primary">삭제</a>
			<%
				}
			%>
			<a href="kakaoPay.jsp" class="btn btn-primary">결제 및 채팅</a>
		</div>
	</div>
	<!-- 게시판 글 보기 양식 영역 끝 -->
	
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