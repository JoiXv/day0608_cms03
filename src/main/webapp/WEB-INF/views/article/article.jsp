<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page pageEncoding="UTF-8"%>
<!-- 引入抽取的头部静态资源 -->
<%@ include file="/WEB-INF/views/common/topStatic.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
    	<title>CMS系统欢迎您</title>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
	</head>
  	<body class="app sidebar-mini">
    	<!-- 引入抽取的导航条-->
    	<%@include file="/WEB-INF/views/common/header.jsp" %>
    	
    	<!-- Sidebar menu-->
    	<!-- 引入抽取的 侧边菜单 -->
		<%@include file="/WEB-INF/views/common/leftMenu.jsp" %>
    	
    	<!-- 中间显示内容的 -->
    	<main class="app-content">
    	
    		<!-- 删除时的确认模态框 -->
			<div class="modal fade" id="delModel">
				<div class="modal-dialog">
					<div class="modal-content message_align">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
						</div>
						<div class="modal-body">
								<h6 style="color: orange">您确认要删除吗？</h6>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal" >取消</button>
							<a href="javascript:void(0);" id="delModelButton" class="btn" >确定</a>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div>
			
			<!-- 添加或者修改的模态框 -->
	<div class="modal fade bs-example-modal-lg" id="saveModel">
		<div class="modal-dialog modal-lg">
			<div class="modal-content message_align">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
			</div>
			<div class="modal-body">
				<form action="/system/article/save" method="post" class="form-horizontal" id="saveForm">
					<input type="hidden" name="id">
						<div class="form-group row">
							<label for="title" class="control-label col-md-3">文章标题</label>
							<div class="col-md-9">
								<input class="form-control" type="text" name="title">
							</div>
						</div>
						<div class="form-group row">
							<label for="typeId" class="control-label col-md-3">文章类型</label>
							<div class="col-md-9">
								<select name="typeId" class="form-control">
									<c:forEach items="${types}" var="t">
										<option value="${t.id}">${t.name}</option>
									</c:forEach>
				                 </select>
				            </div>
						</div>
				                <div class="form-group row">
				                  	<label for="enable" class="control-label col-md-3" >是否启用</label>
				                  	<div class="col-md-9">
				                    	<div class="form-check">
				                      		<label class="form-check-label">
				                        		<input class="form-check-input" type="radio" checked="checked" id="enable" name="enable" value="1">启用
				                      		</label>
				                    	</div>
				                    	<div class="form-check">
				                      		<label class="form-check-label">
				                        		<input class="form-check-input" type="radio" name="enable" value="0">禁用
				                      		</label>
				                    	</div>
				                  	</div>
				                </div>
				                <div class="form-group row">
				                  	<label for="content" class="control-label col-md-3">文章内容</label>
				                  	<div class="col-md-9">
				                   	 	<!-- <textarea class="form-control" style="resize: none" rows="4" name="content"></textarea> -->
				                   	 	<!-- 加载编辑器的容器 -->
										<script id="container" name="content" type="text/plain"></script>
				                  	</div>
				                </div>
				             </form>
			            </div>
			            <div class="modal-footer">
			                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			                <a href='javascript:void(0);' id="saveButton" class="btn btn-success" >确定</a>
			            </div>
			        </div>
			    </div>
			</div>
    	
			<!-- 高级查询 -->
			<div class="row app-title">
				<div class="col-md-12">
				<!-- 表单 -->
				<form id="queryForm" class="form-inline">
					<div class="form-group">
						<label for="title">标题：</label>
							<input type="text" class="form-control" name="title" id="title">
					</div>
					<div class="form-group" style="margin-left: 20px">
						<label>文章类型：</label>
						<select name="typeId" class="form-control" id="typeId">
							<option value="">请选择</option>
							<c:forEach items="${types }" var="t">
								<option value="${t.id}">${t.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group" style="margin-left: 20px">
						<label>是否启用：</label>
						<select name="enable" class="form-control" id="enable">
							<option value="">请选择</option>
							<option value="1">启用</option>
							<option value="0">禁用</option>
						</select>
					</div>
					<button type="button" id="queryButton" class="btn btn-success" style="margin-left: 20px">查询</button>
				</form>
				</div>	
			</div>
    	
	    	<!-- 列表展示 -->
	    	<div class="row app-title">
	    		<div class="col-md-12">
	    			<!-- 存放table列表 -->
	    			<table id='table-demo-baseCode' ></table>
	    		</div>	
	    	</div>
    	</main>
    	
		<!-- 引入抽取的尾部JavaScript代码 -->
		<%@include file="/WEB-INF/views/common/buttomStatic.jsp"  %>
		<!-- 引入自行编写的article页面的js代码 -->
		<script type="text/javascript" src="/static/system/js/article.js"></script>
		
		<!-- 配置文件 -->
    	<script type="text/javascript" src="/static/ueditor/ueditor.config.js"></script>
    	<!-- 编辑器源码文件 -->
    	<script type="text/javascript" src="/static/ueditor/ueditor.all.js"></script>
	    <!-- 实例化编辑器 -->
	    <script type="text/javascript">
	        var ue = UE.getEditor('container',{
	        	initialFrameHeight: 200,  //调整
	        	zIndex: 8888
	        });
	    </script>
		
  	</body>
</html>