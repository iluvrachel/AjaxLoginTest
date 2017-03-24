<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js">
</script>
<script language="javascript">
function login(myform){//传入表单参数
    if(myform.username.value==""){       //验证用户名是否为空
        alert("请输入用户名！");loginform.username.focus();return false;
    }
    if(myform.password.value==""){       //验证密码是否为空
        alert("请输入密码！");loginform.password.focus();return false;
    }   
    //var param="/LoginService?action=login&username="+myform.username.value+"&password="+myform.password.value;  //将登录信息连接成字符串，作为发送请求的参数
    //alert(param);
    $.ajax({
        url:"LoginService",
        async: false,
        //data: $('#myform').submit(),
        data: {username:myform.username.value,password:myform.password.value},
        //data:"username="+myform.username.value+"&password="+myform.password.value,
        
        type:"GET",
        dataType:"json",
        //contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//防止乱码
        error: function(data) {  
            alert(data);  
         }  ,
        success:function(data){
            if(data == false){
                alert("您输入的用户名或密码有错！");loginform.username.focus();return false;
            }else{
                window.location.href = "index.jsp";//跳转到主页
            }
        }
    });
}
</script>
<title>login</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="style.css">


</head>

<body>
	<%@include file="common/header.jsp"%>
<div><form name="myform" id="myform" action="login" method="post">
    账号：<span id="tdName">
    <input type="text" id="username" name="username" />     </span><br><br>
    <span id="pwdBox">                             
    密码：<span id="tdPass">
  <input type="password" id="password" name="password" /></span><br><br>
    </span>
    <span id="state"></span><br>
         <div align="center" id="buttom" >
          <span ><input type="button" onClick="login(this.form)" value="登录" /></span>
    </div>
    </form>
    </div>
</body>
</html>