<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modify.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="/netbank/style/style.css">
	<link rel="stylesheet" type="text/css" href="/netbank/style/default.css">

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function check()
	{
	
      	var isMobile=/^(?:13\d|15\d)\d{5}(\d{3}|\*{3})$/;   
       	var isPhone=/^((0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/; 
       	var isCardid=/^\d{17}(\d|x)$/;
		var username=document.getElementById("username").value;
		var pwd=document.getElementById("pwd").value;
		var confirmpwd=document.getElementById("confirmpwd").value;
		var balance=document.getElementById("balance").value;
		var realname=document.getElementById("realname").value;
		var age=document.getElementById("age").value;
		var address=document.getElementById("address").value;
		var telephone=document.getElementById("telephone").value;
		var cardid=document.getElementById("cardid").value;
		
		
		document.getElementById("errorusername").innerHTML="";
		document.getElementById("errorpwd").innerHTML="";
		document.getElementById("errorconfirmpwd").innerHTML="";
		document.getElementById("errorbalance").innerHTML="";
		document.getElementById("errorrealname").innerHTML="";
		document.getElementById("errorage").innerHTML="";
		document.getElementById("erroraddress").innerHTML="";
		document.getElementById("errortelephone").innerHTML="";
		document.getElementById("errorcardid").innerHTML="";
		if(username=="")
		{
				document.getElementById("errorusername").innerHTML="name";
				return false;
		}else if(pwd=="")
		{
				document.getElementById("errorpwd").innerHTML="pwd";
				return false;
		}else if(confirmpwd=="")
		{
				document.getElementById("errorconfirmpwd").innerHTML="confirm";
				return false;
		}else if(balance=="")
		{
				document.getElementById("errorbalance").innerHTML="ammount";
				return false;
		}else if(realname=="")
		{
				document.getElementById("errorrealname").innerHTML="name";
				return false;
		}else if(address=="")
		{
				document.getElementById("erroraddress").innerHTML="address";
				return false;
		}else if(telephone=="")
		{
				document.getElementById("errortelephone").innerHTML="phont";
				return false;
		}else if(cardid=="")
		{
				document.getElementById("errorcardid").innerHTML="SSN";
				return false;
		}else if(age=="")
		{
				document.getElementById("errorage").innerHTML="age";
				return false;
		}else
		{
			if(pwd!=confirmpwd)
				{
					document.getElementById("errorconfirmpwd").innerHTML="error";
					return false;
				}
			if(!(balance.search(/^[\+\-]?\d+\.?\d*$/)==0))
			{
				
				document.getElementById("errorbalance").innerHTML="error";
				return false;
			}else{
				if(parseFloat(balance)<10)
				{
						document.getElementById("errorbalance").innerHTML="error";
						return false;
				}
			}
			if(!(age.search(/^[\+\-]?\d+\.?\d*$/)==0))
			{
				
				document.getElementById("errorage").innerHTML="error";
				return false;
			}else{
				if(parseFloat(age)<18)
				{
						document.getElementById("errorage").innerHTML="error";
						return false;
				}else if(parseFloat(age)>99)
				{
					document.getElementById("errorage").innerHTML="error";
						return false;
				}
			}
			if(!isPhone.test(telephone)&&!isMobile.test(telephone))
			{
				document.getElementById("errortelephone").innerHTML="error";
				return false;
			}
			if(!isCardid.test(cardid))
			{
				document.getElementById("errorcardid").innerHTML="error";
				return false;
			}
			return true;
		}
		
		
		
	}
</script>
  </head>
  
  <body>
  <form method="post" name="myform" action="/netbank/admin/kaihu" onsubmit="return check()">
  <div align="center">
  	<table width="450" class="table">
			<tbody>
				<tr>
					<td width="100">name：</td>
					<td>
						<input id="username" type="text" name="account.username"/>
						<span id="errorusername" style="color:red;">${message}</span>						
					</td>
				</tr>
				<tr>
					<td>pwd：</td>
					<td>
						<input id="pwd" type="password" name="account.password"/>
						<span id="errorpwd" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td>confirm：</td>
					<td>
						<input id="confirmpwd" type="password"/>
						<span id="errorconfirmpwd" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td>ammount：</td>
					<td>
						<input id="balance" type="text" name="account.balance" />
						<span id="errorbalance" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td>name：</td>
					<td>
						<input type="text" id="realname" name="personinfo.realname"/>
						<span id="errorrealname" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td>age：</td>
					<td>
						<input id="age" type="text" name="personinfo.age""/>
						<span id="errorage" style="color:red;"></span>
					</td>
				</tr>
				<tr>
				<td>Gender：</td>
				<td>
						<select name="personinfo.sex" >
							<option value="male" >male</option>
							<option value="female" >female</option>
						</select></td></tr> 
				<tr><td>address：</td>
				<td>
					<input id="address" type="text" name="personinfo.address""/>
					<span id="erroraddress" style="color:red;"></span>
				</td></tr>
				<tr>
					<td>phone：</td>
					<td>
						<input id="telephone" name="personinfo.telephone" value="${personinfo.telephone}" type="text"/>
						
						<font size="1">phone</font>
						<span id="errortelephone" style="color:red;"></span>
					</td>
				</tr>
				<tr>
					<td>SSN：</td>
					<td>
					<input id="cardid" type="text" name="personinfo.cardid" value="${personinfo.cardid}"/>
					<font size="1" >11 digits</font>
					<span id="errorcardid" style="color:red;"></span>
					</td>
				</tr>
				<tr>
				<td></td>
				<td><input type="submit" value="send" /> <br></td></tr>
			</tbody>
		</table>
		<div style="color:red;">
        <s:fielderror /> 
    	</div> 
		</div>
		<br>
  </form>  	
   </body>
</html>
