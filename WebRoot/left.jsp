<%@page contentType="text/html;charset=UTF-8" import="java.sql.*,java.util.*"%>

<%
if(session.getAttribute("user")!=null){
//--------------------------------------logged------------------------------------------------------------------
	
%>
<html>
<head>
<title>Manager page</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
<link rel="stylesheet" type="text/css" href="style/default.css">

</head>
<BODY>

<table cellpadding=0 cellspacing=0 width=200 align=center class="table">

  <tr>
        <td height=25 align="center" bgcolor="#DBC2B0"> 
          <b>Manage</b></td>
  </tr>
  <tr>
    <td>
            <table cellpadding=0 cellspacing=0 align=center width=180 class="table" >                           
              <tr> 
                <td height=20><a href=/netbank/deposit.jsp target=right >deposite</a></td>
              </tr>
              <tr> 
                <td height=20><a href=/netbank/withdrawal.jsp target=right>withdraw</a></td>
              </tr>
              <tr> 
                <td height=20><a href=/netbank/transfer.jsp target=right>transfer</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/transaction/list?pager.curPage=1 target=right>check transcation</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/information.jsp target=right>check information</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/modify.jsp target=right>edit personal information</a></td>
              </tr>
               <tr> 
                <td height=20><a href=/netbank/changepwd.jsp target=right>eddit password</a></td>
              </tr>
               <tr> 
                <td height=20 ><a href=/netbank/user/user_logout target=_top>logout</a></td>
              </tr>
              </table>
	</td>
  </tr>
</table>
<%
}
%>