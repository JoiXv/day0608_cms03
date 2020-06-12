		document.querySelector('#table-demo-baseCode').GM({
		    gridManagerName: 'demo-baseCode',//指定表格实例的唯一标识
		    ajaxData: '/system/article/datagrid',	//前台发送ajax的请求
		    supportAjaxPage: true,						//启用分页
	        sizeData: [5,10,15,20],							//配置每页显示条数的下拉项，数组元素仅允许为正整数
	        pageSize: 5,											//配置初次进入时每页的显示条数，需要与sizeData中的值匹配
	        currentPageKey: "localPage",				//请求参数中当前页key键值,默认为cPage
	        pageSizeKey: "pageSize",						//请求参数中每页显示条数key健值, 默认为pSize
		    ajaxType: 'POST',									//请求方式
		    height:'100%',										//显示尺寸100%
		    columnData: [
		        {
		            key: 'title',										//json格式的数据的key值
		            text: '文章标题',									//表头文本
		            align:'center'
		        },{
		            key: 'type',
		            text: '文章类型',
		            align:'center',
		            template:function(cell, row, index, key){
		            	//获取到ArticleServiceImpl中设置的文章类型（注意：这里设置的是t_article_type表里所有的字段，cell接收的是t_article和t_article_type中所有字段）
		            	return cell.name;
		            }
		        },{
		            key: 'clickCount',
		            text: '点击次数',
		            align:'center'
		        },{
		            key: 'createDate',
		            text: '创建时间',
		            align:'center'
		        },{
		            key: 'enable',
		            text: '是否启用',
		            align:'center',
		            //cell：当前字段的值    row：当前行的数据，当前对象   index：当前对象的下标   key：enable
		            template:function(cell, row, index, key){
		            	return cell ? "<span style='color:green'>启用</span>":"<span style='color:red'>禁用</span>";
		            }
		        },{
	                key: 'id',
	                text: '操作&emsp;<a href="javascript:void(0);" class="addButton" style="color:olive">添加</a>',
	                align: 'center',
	                //cell：当前字段的值    row：当前行的数据，当前对象   index：当前对象的下标   key：enable
	                template: function(cell, row, index, key){
	                	return "<a href='javascript:void(0);'  style='color:olive' data-id='"+cell+"'>删除</a>&emsp;<a href='javascript:void(0);' data-row='"+JSON.stringify(row)+"' style='color:blue'>修改</a>";
	                }
		        }
		    ]
		});
		
		//注册查询点击事件
		$(function(){
			$("#queryButton").on("click",function(){
				//这个方法是GridManager提供的一个提交数据到后台的方法
				//第一个参数：GridManager的名称
				//第二个参数：提交的数据
				//请求：/system/article/datagrid
				//var titile = $("#title").val()手动获取方式
				var jsonForm = $("#queryForm").serializeObject();
				console.debug(jsonForm);
				GridManager.setQuery('demo-baseCode', jsonForm);
			});
			
			
			/* 删除数据的异步请求：给所有的删除按钮注册单击事件 */
			//事件委托
			$("#table-demo-baseCode").on("click","a[data-id]",function(){
				//获取id
				var id = $(this).data("id");
				//显示删除模态框
				$("#delModel").modal("show");
				//先注销确认按钮的所有事件：避免点击删除按钮给确认按钮绑定多个事件
				$("#delModelButton").off();
				//确认的点击事件
				$("#delModelButton").on("click",function(){
					//get请求
					$.get("/system/article/del",{"id":id},function(data){
    					if(data.success){
	    					$("#delModel").modal("hide");
	    					//刷新表格
	    					GridManager.refreshGrid('demo-baseCode');
    					}else{
    						alert(data.msg);
    					}
					},"json");//get请求
				});//"#delModelButton点击事件
			});//#table-demo-baseCode
			
			/* 新增功能：点击新增按钮绑定事件 */
			$("#table-demo-baseCode").on("click",".addButton",function(){
				//清空表单数据
				$("#saveForm").clearForm();
				//手动清空隐藏域id
				$("#saveForm input[name='id']").val("");
				
				//回显富文本的值
				var ue = UE.getEditor('container');
				ue.ready(function() {
				    //设置默认值
				    ue.setContent("");
				});
				
				//手动设置启用状态：clearForm()会将启用状态也清空
				$("#enable").prop("checked",true);
				//显示模态框
				$("#saveModel").modal("show");
			});
			
			//点击更新按钮  事件委托
			$("#table-demo-baseCode").on("click","a[data-row]",function(){
				var row = $(this).data("row");
				//清空表单数据
				$("#saveForm").clearForm();
				//手动清空隐藏域id
				$("#saveForm input[name='id']").val("");
				
				//回显富文本的值
				var ue = UE.getEditor('container');
				ue.ready(function() {
				    //设置默认值
				    ue.setContent(row.content);
				});
				
				//手动设置启用状态：clearForm()会将启用状态也清空
				$("#saveModel #enable").prop("checked",true);
				//回显数据
				$("#saveForm").setForm(row);
				//显示模态框
				$("#saveModel").modal("show");
				//console.debug(obj);
			});
			
			/*点击添加和修改模态框的确认按钮时的业务逻辑*/
			$("#saveButton").on("click",function(){
				//将表单以ajax异步请求的方式提交到后台，请求为：/system/article/save
				$("#saveForm").ajaxSubmit({//ajax提交请求
	    			success:function(result){
	    				if(result.success){
	    					//关闭模态框
	    					$("#saveModel").modal("hide");
	    					//刷新表格
	    					GridManager.refreshGrid('demo-baseCode');
	    				}else{
	    					alert(result.msg);
	    				}
	    			}
	    		});//#saveForm发送提交请求
			});//#saveButton点击事件
			
});//页面加载