<% 
if (session.getAttribute("rol") == null) { 
  response.sendRedirect(request.getContextPath()+"/index.jsp");
  return;
}
%>