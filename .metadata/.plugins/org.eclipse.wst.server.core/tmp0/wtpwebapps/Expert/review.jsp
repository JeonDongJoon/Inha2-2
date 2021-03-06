<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter"%>

<%@ page import="user.UserDAO"%>

<%@ page import="jobType.JobTypeDAO" %>

<%@ page import="jobType.JobTypeDTO" %>

<%@ page import="review.ReviewDAO"%>

<%@ page import="review.ReviewDTO"%>

<%@ page import="java.util.ArrayList"%>

<%@ page import="java.net.URLEncoder"%>

<!doctype html>

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
          <li class="nav-item">

            <a class="nav-link" href="profile.jsp">전문가</a>
          </li>
           <li class="nav-item active">

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

    <div class="container">

      <form method="get" action="./review.jsp" class="form-inline mt-3">

		<select name = "jobType" class="form-control mx-1 mt-2">
		<%
			JobTypeDAO jobTypeDAO = new JobTypeDAO();
			ArrayList<JobTypeDTO> list = jobTypeDAO.getList();
			for(int i = 0; i < list.size(); i++){
		%>
			<option value = <%=list.get(i).getJobType() %>>
			<%=list.get(i).getJobType() %>
			</option>
		<%
			}
		%>
		</select>
        <select name="searchType" class="form-control mx-1 mt-2">

          <option value="최신순">최신순</option>

          <option value="추천순" <%if(searchType.equals("추천순")) out.println("selected");%>>추천순</option>

        </select>

        <input type="text" name="search" class="form-control mx-1 mt-2" value="<%= search %>" placeholder="내용을 입력하세요.">

        <button type="submit" class="btn btn-primary mx-1 mt-2">검색</button>

        <a class="btn btn-primary mx-1 mt-2" data-toggle="modal" href="#registerModal">등록하기</a>

        <a class="btn btn-danger ml-1 mt-2" data-toggle="modal" href="#reportModal">신고</a>

      </form>

<%

	ArrayList<ReviewDTO> reviewList = new ArrayList<ReviewDTO>();

	reviewList = new ReviewDAO().getList(reviewDivide, searchType, search, pageNumber);

	if(reviewList != null)

	for(int i = 0; i < reviewList.size(); i++) {

		if(i == 5) break;

		ReviewDTO review = reviewList.get(i);

%>

      <div class="card bg-light mt-3">

        <div class="card-header bg-light">

          <div class="row">

            <div class="col-8 text-left"><small><%=review.getExpertName()%></small></div>

            <div class="col-4 text-right">

              종합 <span style="color: red;"><%=review.getTotalScore()%></span>

            </div>

          </div>

        </div>

        <div class="card-body">

          <h5 class="card-title">

            <%=review.getReviewTitle()%>&nbsp;<small></small>

          </h5>

          <p class="card-text"><%=review.getReviewContent()%></p>

          <div class="row">

            <div class="col-9 text-left">

              시간 준수 <span style="color: red;"><%=review.getTimeScore()%></span>

              친절도 <span style="color: red;"><%=review.getComfortableScore()%></span>

              재상담 의사 <span style="color: red;"><%=review.getReviewScore()%></span>

              <span style="color: green;">(추천: <%=review.getLikeCount()%>)</span>

            </div>

            <div class="col-3 text-right">

              <a onclick="return confirm('추천하시겠습니까?')" href="./likeAction.jsp?reviewID=<%=review.getReviewID()%>">추천</a>

              <a onclick="return confirm('삭제하시겠습니까?')" href="./deleteAction.jsp?reviewID=<%=review.getReviewID()%>">삭제</a>

            </div>

          </div>

        </div>

      </div>

<%

	}

%>

    </div>

    <ul class="pagination justify-content-center mt-3">

      <li class="page-item">

<%

	if(pageNumber <= 0) {

%>     

        <a class="page-link disabled">이전</a>

<%

	} else {

%>

		<a class="page-link" href="./review.jsp?reviewDivide=<%=URLEncoder.encode(reviewDivide, "UTF-8")%>&searchType=<%=URLEncoder.encode(searchType, "UTF-8")%>&search=<%=URLEncoder.encode(search, "UTF-8")%>&pageNumber=<%=pageNumber - 1%>">이전</a>

<%

	}

%>

      </li>

      <li class="page-item">

<%

	if(reviewList.size() < 6) {

%>     

        <a class="page-link disabled">다음</a>

<%

	} else {

%>

		<a class="page-link" href="./review.jsp?reviewDivide=<%=URLEncoder.encode(reviewDivide, "UTF-8")%>&searchType=<%=URLEncoder.encode(searchType, "UTF-8")%>&search=<%=URLEncoder.encode(search, "UTF-8")%>&pageNumber=<%=pageNumber + 1%>">다음</a>

<%

	}

%>

      </li>

    </ul>

    <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">

      <div class="modal-dialog">

        <div class="modal-content">

          <div class="modal-header">

            <h5 class="modal-title" id="modal">리뷰 등록</h5>

            <button type="button" class="close" data-dismiss="modal" aria-label="Close">

              <span aria-hidden="true">&times;</span>

            </button>

          </div>

          <div class="modal-body">

            <form action="./reviewRegisterAction.jsp" method="post">

              <div class="form-row">

                <div class="form-group col-sm-6">

                  <label>전문가 이름</label>

                  <input type="text" name="expertName" class="form-control" maxlength="20">

                </div>

              </div>

              <div class="form-row">

                <div class="form-group col-sm-4">

                  <label>전문가 상담 시간</label>

                  <select name="timeDivide" class="form-control">

                    <option name="오전" selected>오전</option>

                    <option name="점심">점심</option>

                    <option name="오후">오후</option>

                    <option name="저녁">저녁</option>

                  </select>

                </div>

                <div class="form-group col-sm-4">

                  <label>예약 구분</label>

                  <select name="reviewDivide" class="form-control">

                    <option name="예약상담" selected>예약상담</option>

                    <option name="기타상담">기타상담</option>

                  </select>

                </div>

              </div>

              <div class="form-group">

                <label>제목</label>

                <input type="text" name="reviewTitle" class="form-control" maxlength="20">

              </div>

              <div class="form-group">

                <label>내용</label>

                <textarea type="text" name="reviewContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>

              </div>

              <div class="form-row">

                <div class="form-group col-sm-3">

                  <label>종합</label>

                  <select name="totalScore" class="form-control">

                    <option value="5" selected>5</option>

                    <option value="4">4</option>

                    <option value="3">3</option>

                    <option value="2">2</option>

                    <option value="1">1</option>

                  </select>

                </div>

                <div class="form-group col-sm-3">

                  <label>시간 준수</label>

                  <select name="timeScore" class="form-control">

                    <option value="5" selected>5</option>

                    <option value="4">4</option>

                    <option value="3">3</option>

                    <option value="2">2</option>

                    <option value="1">1</option>

                  </select>

                </div>

                <div class="form-group col-sm-3">

                  <label>친절도</label>

                  <select name="comfortableScore" class="form-control">

                    <option value="5" selected>5</option>

                    <option value="4">4</option>

                    <option value="3">3</option>

                    <option value="2">2</option>

                    <option value="1">1</option>

                  </select>

                </div>

                <div class="form-group col-sm-3">

                  <label>재 상담 의사</label>

                  <select name="reviewScore" class="form-control">

                    <option value="5" selected>5</option>

                    <option value="4">4</option>

                    <option value="3">3</option>

                    <option value="2">2</option>

                    <option value="1">1</option>

                  </select>

                </div>

              </div>

              <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>

                <button type="submit" class="btn btn-primary">등록하기</button>

              </div>

            </form>

          </div>

        </div>

      </div>

    </div>

    <div class="modal fade" id="reportModal" tabindex="-1" role="dialog" aria-labelledby="modal" aria-hidden="true">

      <div class="modal-dialog">

        <div class="modal-content">

          <div class="modal-header">

            <h5 class="modal-title" id="modal">신고하기</h5>

            <button type="button" class="close" data-dismiss="modal" aria-label="Close">

              <span aria-hidden="true">&times;</span>

            </button>

          </div>

          <div class="modal-body">

            <form method="post" action="./reportAction.jsp">

              <div class="form-group">

                <label>신고 제목</label>

                <input type="text" name="reportTitle" class="form-control" maxlength="20">

              </div>

              <div class="form-group">

                <label>신고 내용</label>

                <textarea type="text" name="reportContent" class="form-control" maxlength="2048" style="height: 180px;"></textarea>

              </div>

              <div class="modal-footer">

                <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>

                <button type="submit" class="btn btn-danger">신고하기</button>

              </div>

            </form>

          </div>

        </div>

      </div>

    </div>

    <footer class="bg-dark mt-4 p-5 text-center" style="color: #FFFFFF;">

      Copyright ⓒ2021 전동준 All Rights Reserved.

    </footer>

    <!-- 제이쿼리 자바스크립트 추가하기 -->

    <script src="./js/jquery.min.js"></script>

    <!-- Popper 자바스크립트 추가하기 -->

    <script src="./js/popper.min.js"></script>

    <!-- 부트스트랩 자바스크립트 추가하기 -->

    <script src="./js/bootstrap.min.js"></script>

  </body>

</html>