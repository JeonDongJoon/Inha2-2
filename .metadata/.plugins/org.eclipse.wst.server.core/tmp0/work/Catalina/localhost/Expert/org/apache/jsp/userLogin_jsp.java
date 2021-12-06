/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.54
 * Generated at: 2021-12-06 12:26:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;

public final class userLogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("java.io.PrintWriter");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!doctype html>\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("  <head>\r\n");
      out.write("\r\n");
      out.write("    <title>전문가 Expert 사이트</title>\r\n");
      out.write("\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("\r\n");
      out.write("    <!-- 부트스트랩 CSS 추가하기 -->\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"./css/bootstrap.min.css\">\r\n");
      out.write("\r\n");
      out.write("    <!-- 커스텀 CSS 추가하기 -->\r\n");
      out.write("\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"./css/custom.css\">\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("\r\n");


	String userID = null;

	if(session.getAttribute("userID") != null) {

		userID = (String) session.getAttribute("userID");

	}

	if(userID != null) {

		PrintWriter script = response.getWriter();

		script.println("<script>");

		script.println("alert('로그인이 된 상태입니다.');");

		script.println("location.href = 'index.jsp'");

		script.println("</script>");

		script.close();	

	}


      out.write("	\r\n");
      out.write("\r\n");
      out.write("    <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\r\n");
      out.write("\r\n");
      out.write("      <a class=\"navbar-brand\" href=\"index.jsp\">전문가 Expert 사이트</a>\r\n");
      out.write("\r\n");
      out.write("      <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbar\">\r\n");
      out.write("\r\n");
      out.write("        <span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("\r\n");
      out.write("      </button>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"collapse navbar-collapse\" id=\"navbar\">\r\n");
      out.write("\r\n");
      out.write("        <ul class=\"navbar-nav mr-auto\">\r\n");
      out.write("\r\n");
      out.write("          <li class=\"nav-item active\">\r\n");
      out.write("\r\n");
      out.write("            <a class=\"nav-link\" href=\"index.jsp\">메인</a>\r\n");
      out.write("\r\n");
      out.write("          </li>\r\n");
      out.write("           <li class=\"nav-item\">\r\n");
      out.write("            <a class=\"nav-link\" href=\"profile.jsp\">전문가</a>\r\n");
      out.write("          </li>\r\n");
      out.write("           <li class=\"nav-item\">\r\n");
      out.write("\r\n");
      out.write("            <a class=\"nav-link\" href=\"review.jsp\">후기</a>\r\n");
      out.write("          </li>\r\n");
      out.write("\r\n");
      out.write("          <li class=\"nav-item dropdown\">\r\n");
      out.write("\r\n");
      out.write("            <a class=\"nav-link dropdown-toggle\" id=\"dropdown\" data-toggle=\"dropdown\">\r\n");
      out.write("\r\n");
      out.write("              회원 관리\r\n");
      out.write("\r\n");
      out.write("            </a>\r\n");
      out.write("\r\n");
      out.write("            <div class=\"dropdown-menu\" aria-labelledby=\"dropdown\">\r\n");
      out.write("\r\n");


	if(userID == null) {


      out.write("\r\n");
      out.write("\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"userLogin.jsp\">로그인</a>\r\n");
      out.write("\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"userRegister.jsp\">회원가입</a>\r\n");
      out.write("\r\n");


	} else {


      out.write("\r\n");
      out.write("\r\n");
      out.write("              <a class=\"dropdown-item\" href=\"userLogout.jsp\">로그아웃</a>\r\n");
      out.write("\r\n");


	}


      out.write("\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("          </li>\r\n");
      out.write("\r\n");
      out.write("        </ul>\r\n");
      out.write("\r\n");
      out.write("        <form action=\"./index.jsp\" method=\"get\" class=\"form-inline my-2 my-lg-0\">\r\n");
      out.write("\r\n");
      out.write("          <input type=\"text\" name=\"search\" class=\"form-control mr-sm-2\" placeholder=\"내용을 입력하세요.\">\r\n");
      out.write("\r\n");
      out.write("          <button class=\"btn btn-outline-success my-2 my-sm-0\" type=\"submit\">검색</button>\r\n");
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("    </nav>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"container mt-3\" style=\"max-width: 560px;\">\r\n");
      out.write("\r\n");
      out.write("      <form method=\"post\" action=\"./userLoginAction.jsp\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("\r\n");
      out.write("          <label>아이디</label>\r\n");
      out.write("\r\n");
      out.write("          <input type=\"text\" name=\"userID\" class=\"form-control\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("\r\n");
      out.write("          <label>비밀번호</label>\r\n");
      out.write("\r\n");
      out.write("          <input type=\"password\" name=\"userPassword\" class=\"form-control\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <button type=\"submit\" class=\"btn btn-primary\">로그인</button>\r\n");
      out.write("\r\n");
      out.write("      </form>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <footer class=\"bg-dark mt-4 p-5 text-center\" style=\"color: #FFFFFF;\">\r\n");
      out.write("\r\n");
      out.write("     Copyright ⓒ 2021 전동준 All Rights Reserved.\r\n");
      out.write("\r\n");
      out.write("    </footer>\r\n");
      out.write("\r\n");
      out.write("    <!-- 제이쿼리 자바스크립트 추가하기 -->\r\n");
      out.write("\r\n");
      out.write("    <script src=\"./js/jquery.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Popper 자바스크립트 추가하기 -->\r\n");
      out.write("\r\n");
      out.write("    <script src=\"./js/popper.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- 부트스트랩 자바스크립트 추가하기 -->\r\n");
      out.write("\r\n");
      out.write("    <script src=\"./js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
