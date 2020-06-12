<%@ page pageEncoding="UTF-8"%>
<div class="app-sidebar__overlay" data-toggle="sidebar"></div>
<aside class="app-sidebar">
     	<div class="app-sidebar__user">	
     		<img class="app-sidebar__user-avatar" width="48px" height="48px" src="/static/system/images/hhhh.jpg" alt="User Image">
	       	<div>
	         	<p class="app-sidebar__user-name">${user_in_session.nickName }</p><!-- 登陆成功，拿到session中的user对象，显示昵称 -->
	       	</div>
     	</div>
     	<ul class="app-menu">
	        <li>
	        	<a class="app-menu__item" href="/system/article/index">
		        	<i class="app-menu__icon fa fa-dashboard"></i>
		        	<span class="app-menu__label">文章管理</span>
	        	</a>
	        </li>
	        <li>
	        	<a class="app-menu__item" href="/system/slide/index">
		        	<i class="app-menu__icon fa fa-dashboard"></i>
		        	<span class="app-menu__label">轮播管理</span>
	        	</a>
	        </li>
     	</ul>
</aside>