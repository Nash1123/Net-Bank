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
    
    <title>transfer</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="style/style.css" >
	<link rel="stylesheet" type="text/css" href="style/default.css" >
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" >
	
	function transfer(){
	var otherid=document.getElementById("otherid").value;
	var money=document.getElementById("trMoney").value;
		if(otherid!=""&&money!="")
		{
				if(!(otherid.search(/^[\+\-]?\d+\.?\d*$/)==0))
				{
					document.getElementById("errorotherid").innerHTML="error";
					return false;
				}
				if(!(money.search(/^[\+\-]?\d+\.?\d*$/)==0))
				{
					document.getElementById("errormoney").innerHTML="error";
					return false;
				}else
				{
					if(parseFloat(money)==0)
					{
						document.getElementById("errormoney").innerHTML="error";
						return false;
					}
					return confirm("confirm?");
				}
		}
		else {
			alert("error！");
			return false;
			
		}
	}
	
	function disptime(){
	var now=new Date();
	
	var year=now.getFullYear();
	var month=now.getMonth()+1;
	var date=now.getDate();
	var hour=now.getHours();
	var minute=now.getMinutes();
	var second =now.getSeconds();
		document.getElementById("datetime").value=year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
		//year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
		setTimeout("disptime()", 1000);
	}
</script>
  </head>
  	
 <body onload="disptime()">
<form  method="post" name="myform" action="/netbank/transaction/transfer" onsubmit="return transfer()">
	<div align="center">
	<table width="400" border="0" class="table">
		<tbody>
			<tr>
			<td>&nbsp;time：</td>
			<td><input type="text" name="log.datetime" id="datetime"></td>
			</tr>
			<tr>
			<td width="100">&nbsp;account：</td>
			<td>
				<input type="text" name="log.otherid" id="otherid" value="${log.otherid }">
				<span id="errorotherid" style="color:red;"></span>
			</td></tr>
			<tr>
			<td>&nbsp;amount：</td>
			<td>
				<input type="text" name="log.trMoney" id="trMoney" value="${log.trMoney}">
				<span id="errormoney" style="color:red;"></span>
			</td>						
			</tr>
			<tr>
			<td>&nbsp;</td>
			<td>&nbsp;<input type="Submit" value="transfer" /> </td>
			</tr>
		</tbody>
	</table>
		<div style="color:red;"> 
        <s:fielderror /> 
    	</div> 
	</div> 				
</form>
 </body>
</html>
