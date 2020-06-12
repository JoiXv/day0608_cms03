<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <%@include file="/WEB-INF/views/common/topStatic.jsp" %>
    <title>CMS登陆</title>
  </head>
  <body>
    <section class="material-half-bg">
      <div class="cover"></div>
    </section>
    <section class="login-content">
      <div class="logo">
        <h1>cms后台登陆</h1>
      </div>
      <div class="login-box">
        <form class="login-form" method="post" action="/system/login" id="loginForm">
          <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i>登陆&emsp;<span id="loginMessage"></span></h3>
          <div class="form-group">
            <label class="control-label">用户名</label>
            <input class="form-control" type="text"  name="username" placeholder="用户名" autofocus>
          </div>
          <div class="form-group">
            <label class="control-label">密码</label>
            <input class="form-control" type="password" name="password" placeholder="密码">
          </div>
          <div class="form-group">
            <div class="utility">
              <div class="animated-checkbox">
                <label>
                  <input type="checkbox" name="remember" value="1"><span class="label-text">记住我</span>
                </label>
              </div>
            </div>
          </div>
          <div class="form-group btn-container">
            <button class="btn btn-primary btn-block" type="button" id="loginButton">
            <i class="fa fa-sign-in fa-lg fa-fw"></i>登陆</button>
          </div>
        </form>
      </div>
    </section>
    
    <!-- 引入底部静态资源 -->
     <%@include file="/WEB-INF/views/common/buttomStatic.jsp" %>
     <script type="text/javascript">
     	$(function(){
     		$("#loginButton").on("click",function(){
     			login();
     		});//click
     		
     		/* 按下enter键完成登陆,e表示这次事件的源头，触发这次事件的源头 */
     		$(document).on("keypress",function(e){
     			//alert(String.fromCharCode(e.keyCode));
				if(e.keyCode == 13){//回车键是13
					login();
				}      			
     		});
     		
     		//前台js获取cookie
     		var cookies = document.cookie;
     		var cookieArr =  cookies.split(";");
     		if(cookies.indexOf("username") != -1){//如果cookies里有username，表示点击了“记住我”选项
     			var username = null;
     			var password = null;
     			for(var index in cookieArr){
     				if(cookieArr[index].lastIndexOf("username") != -1){
     					//获取cookie中username
     					username = cookieArr[index].substring(cookieArr[index].lastIndexOf("=")+1);
     				}
     				if(cookieArr[index].lastIndexOf("password") != -1){
     					//获取cookie中password
     					password = cookieArr[index].substring(cookieArr[index].lastIndexOf("=")+1);
     				}
     			}//for in
     			$("input[name = 'username']").val(username);
     			$("input[name = 'password']").val(password);
     			$("input[name = 'remember']").prop("checked",true);
     		}else{//如果没点击“记住我”，清空
     			$("input[name = 'username']").val("");
     			$("input[name = 'password']").val("");
     			$("input[name = 'remember']").prop("checked",false);
     		}
     		
     	});//入口函数
     	
     	function login(){//login在函数里面调用，所以写在入口函数的里面和外面都可以
     		$("#loginForm").ajaxSubmit({//抽取ajax请求
					success:function(data){
						if(data.success){
							//跳转到后台首页
							location.href = "/system/index";
						}else{
							$("#loginMessage").html(data.msg).css("color","red").css("fontSize","16px");
						}
					},
					dataType:"json"//设置返回数据类型为json格式
			});//loginForm
     	};//login
     </script>
  </body>
</html>
