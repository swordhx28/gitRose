<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>编程大典</title>
<script src="static/js/jquery-3.3.1.min.js"></script>
<script src="static/js/wangEditor.min.js"></script>
<script>
        $(function () {
        	 var E = window.wangEditor;
        	 var editor = new E("#menu","#editor");
        	 editor.customConfig.menus = ['head', 'bold', 'fontSize','fontName', 'underline','foreColor', 'link', 'list', 'justify', 'quote', 'table', 'video', 'code','image'];
        	 editor.customConfig.uploadImgServer = '<%=basePath%>index/upload.jhtml'; //上传URL
        	 editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024;
        	 editor.customConfig.uploadImgMaxLength = 5;    
        	 editor.customConfig.uploadFileName = 'myFileName';
        	 editor.customConfig.uploadImgHooks = {
        	 customInsert: function (insertImg, result, editor) {
        	      // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
        	      // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
        	      // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
        	      var url =result.data;
        	      insertImg(url);
        	      // result 必须是一个 JSON 格式字符串！！！否则报错
        	             }
        	         };
        	 editor.create();
        	editor.$textElem.attr('contenteditable', ${ admin==null?"\"false\"":"\"true\""});//默认关闭编辑器
            
        	
            //获取浏览器的高度  java-业务逻辑
            $("[id=box-left],[id=box-middle],[id=box-right]").css("height", $(document).height());
        	
        	$("[id=editor]").css("height" , $(document).height()-40);
            $("[id=box-left-buttom]").css("height", $(document).height() - 100-30);
            //当窗口最大化最小化的时候,会调用该方法，重新计算浏览器的高度和宽度
            $(window).resize(function () {
                //当窗口大小发生改变的时候，重新计算高度
                $("[id=box-left],[id=box-middle],[id=box-right]").css("height", $(document).height());
                $("[id=box-left-buttom]").css("height", $(document).height() - 100);
                //重新计算宽度
                $("[id=box-left]").css("width", $(document).width()*0.65);
                $("[id=box-right]").css("width", $(document).width()*0.345);
                $("[id=box-middle]").css("width", $(document).width()*0.005);
            });
            $("[id=box-middle]").mousedown(function () {
                $(document).mousemove(function (e) {
                    //设置[id=box-left]的div随着鼠标的x轴坐标
                    $("[id=box-left]").css("width", e.clientX);
                    var w = $("body").width() - $("[id=box-left]").width() - $("[id=box-middle]").width();
                    $("[id=box-right]").css("width", w);
                });
                //当鼠标左键释放的时候,解绑mousemove，这时候宽度就不会随着鼠标的移动而变化
                $(document).mouseup(function () {
                    $(document).unbind("mousemove");
                });
            });
            <c:if test="${ not empty admin }">
            $("[id=mainItem]").dblclick(function () {
                /*var div = "<div style='background-color: white;width: 70%;
                height: 25px;color:#1b3749;border: 1px solid grey;margin-top: 5px;
                margin-left: 20px; ' contenteditable='true'>请输入……</div>"*/
                var div = "<div  style='height: auto;width: 70%; margin-top: 2px; margin-left: 20px;color: white;'>" +
                    "<div name='subItem-0' " +
                        "style='background-color: #1b3749; width: 100%; height: 25px; " +
                        "border: none;' contenteditable='false'>请输入……</div>" +
                    "<div name='subItem-1' style='margin-top: 3px;margin-left: 25px;'></div>" +
                    "</div>"
                $("[id=indexItem]").append(div);
            });
            </c:if>
            //对于新添加的标签,此种方法无效,只能用on方法去监听事件
            /*$("[id=indexItem] div").keydown(function (e) {
                alert("xx");
                console.info($(this).val());
            });*/
            <c:if test="${ not empty admin }">
            $("body").on("keydown", "[name=subItem-0]", function (event) {
                //只监听enter按键
                if (event.which == "13") {
                	var oThis=$(this);
                    //让当前的div不可编辑
                    $(this).attr("contenteditable", false);
                    $(this).css("background-color", "#1b3749");
                    $(this).css("color", "white").css("border", "none");
                    console.info($(this));
                    var menu={id:"0"};
                    if($(this).attr("id")!=undefined){
                    	menu.id=$(this).attr("id");
                    }
                    menu.title=$(this).text();
                    menu.pid=0;
                    $.post("index/menu.jhtml",menu,function(id){
                    	//第一次创建目录索引,要从数据库返回一个id
                    	//下次有了id,就进行更新操作
                    	oThis.attr("id",id);
                    });
                }
            });
            //双击进入编辑模式
            $("body").on("dblclick", "[name=subItem-0]", function (event) {
                $(this).attr("contenteditable", true);
                $(this).css("background-color", "white").css("color", "#1b3749");
                $(this).css("border", "1px solid grey").css("cursor", "pointer");
            });

            //禁用鼠标右键,并且单击body,菜单隐藏
            $('body').on("contextmenu", function () {
                return false;
            }).click(function () {
                $("div[name=menu]").hide();
            });
            </c:if>
            //
          <c:if test="${ not empty admin }">
            //添加右键菜单 获取[id=indexItem]下所有div
            $("body").on("contextmenu", "[id=indexItem]>div", function (e) {
                $("div[name=menu]").remove();//把原来的右键菜单删除
               // console.info(e);
                var div = "<div name='menu' style='display:none;box-sizing: border-box;position: absolute;width: 80px;" +
                    "border-radius: 5px;background-color: white;font-weight: bold;border:1px solid #1b3749;" +
                    "font-size:12px;line-height:25px;color:#1b3749;'>" +
                    "<div style='text-align:center;cursor:pointer;'>" +
                    "<span name='itemAdd' style='color: #1b3749;font-size: 14px;'>添加子项</span>" +
                    "</div>" +
                    "<div  style='text-align:center;cursor:pointer;'>" +
                    "<span name='itemUp' style='color: #1b3749;font-size: 14px;'>向上移动</span>" +
                    "</div>" +
                    "<div style='text-align:center;cursor:pointer;'>" +
                    "<span name='itemDel' style='color: #1b3749;font-size: 14px;'>删除该项</span>" +
                    "</div>" +
                    "</div>";
                //把div标签添加到body元素中
                $(div).appendTo('body');
                var x = e.pageX;
                var y = e.pageY;
                $("div[name=menu]").css("left", x).css("top", y).show();
                //当鼠标放在右键(快捷)菜单的时候,显示背景色
                //教学法 案例驱动开发  项目驱动开发 测试驱动开发
                //获取点击的html标签
                var oThis= $(e.target);
                $("div[name=menu] span").on("mouseover mouseout click", function (e) {
                    switch (e.type) {
                        case "mouseover":
                            $(this).css("background-color", "#ccc");
                            break;
                        case "mouseout":
                            $(this).css("background-color", "white");
                            break;
                        case "click":
                            var name=$(this).attr("name");
                            switch (name) {
                                case "itemAdd":
                                	
                                    console.info(oThis.children("div:last-child"));
                                    //获取[name=subItem-1]的div,并去查找最后一个子div
                                var posid=oThis.next().children("div:last-child").attr("posid");
                                    posid=(posid==undefined)?0:parseInt(posid)+1;
                                    var div="<div posid=\""+posid+"\" name='subItem-2' " +
                                        "style='width: 90%;height: 25px;background-color: " +
                                        "white; color: #1b3749;' contenteditable='true'>请输入……</div>";
                                    oThis.next().append(div);
                                    break;
                                case "itemDel":
                                	var data={};
                                	data.id=oThis.attr("id");
                                    if(oThis.attr("name")=="subItem-0"){
                                    	data.level="parent";
                                    	//通过ajax删除指定id的menu
	                                   $.post("index/delMenu.jhtml",data,function(){
	                                	   oThis.parent().remove();
	                                   });
                                    }else if(oThis.attr("name")=="subItem-2"){
                                    	   data.level="child";
                                    	   $.post("index/delMenu.jhtml",data,function(){
                                    		   oThis.remove();
    	                                   });
                                    }
                                    break;
                                case "itemUp":
                                    //先克隆一份div
                                  //  console.info(oThis);
                             var data={};
                                 data.curid= oThis.attr("id");
                                 data.previd= oThis.prev().attr("id");
                                $.post("index/alterPosid.jhtml",data,function(){
                                	  var prevDiv=  oThis.prev().clone();
                                      //把上一个div删除
                                      oThis.prev().remove();
                                      //上一个clone的div追加到oThis后面
                                      oThis.after(prevDiv);
                                });
                               break;
                            }
                            break;
                    }
                });
            });
            </c:if>
            <c:if test="${ not empty admin }">
            $("body").on("keydown dblclick", "[name=subItem-2]", function (e) {
                //只监听enter按键
              var oThis=$(this);
                switch (e.type) {
                    case "keydown":
                        if (e.which == "13") {
                            //让当前的div不可编辑
                            $(this).attr("contenteditable", false);
                            $(this).css("background-color", "#1b3749").css("color", "white").css("border", "none");
                            var menu={id:"0"};
                            menu.id=(oThis.attr("id")!=undefined)?oThis.attr("id"):"0";
                            menu.title=oThis.text();
                            menu.pid=oThis.parent().prev().attr("id");
                            menu.posid=oThis.attr("posid");
                            $.post("index/menu.jhtml",menu,function(id){
                            	oThis.attr("id",id);
                            	oThis.attr("pid",menu.pid);
                            });
                        }
                        break;
                    case "dblclick":
                        $(this).attr("contenteditable", true);
                        $(this).css("background-color", "white").css("color", "#1b3749");
                        $(this).css("border", "1px solid grey").css("cursor", "pointer");
                        break;
                }
            });
            </c:if>
            var isMove=true;
            $("body").on("click", "div[name=subItem-2]", function (e) {
                if(isMove==false){return};
                //用1000毫秒的时间，宽度变为300px
                $("#box-left").animate({width:250},1000);
                $("#box-right").animate({width:$(document).width()-250-$("#box-middle").width()},1000);
                isMove=false;
            });
            //目录折叠效果
            $("body").on("click", "div[name=subItem-0]", function (e) {
            	var oThis=$(this);
                if($(this).attr("isclick")!="down"){
                    $(this).next().children().slideUp();
                    $(this).attr("isclick","down");
                }else {
                	if(oThis.attr("hasChild")!="true"){
                			var data={};
                			data.pid=$(this).attr("id");
		                	$.post("index/findMenu.jhtml",data,function(data){
		               		console.info(data);
		               		//var data="id=4,title=数据类型,pid=3|id=5,title=逻辑控制,pid=3"
		               	var mbox=data.split("|");
		               		for(var i=0;i<mbox.length;i++){
		               			//获取id的值
		               		var id=mbox[i].split(",")[0].split("=")[1];
		               			//获取title的值
		               		var title=mbox[i].split(",")[1].split("=")[1];
		               			//获取pid的值
		               		 var pid=mbox[i].split(",")[2].split("=")[1];
		               		  	var div="<div name=\"subItem-2\"  id=\""+id+"\" pid=\""+pid+ "\" "+
		               		  	" style=\"width: 90%;height: 25px;background-color: #1b3749; color: #ccc;\" >"+title+"</div>";
		               			oThis.next().append(div);
		               			//用作标记
		               			oThis.attr("hasChild","true");
		               		}
		               	});
                }else{
               	 	$(this).next().children().slideDown();
             	 	$(this).attr("isclick","up");
                }
                }
            });
            //点击子目录的时候字体变白
            $("body").on("click","[name=subItem-2]",function(e){
            	$("div[name=subItem-2]").css("color","#ccc").removeAttr("click");
            	$(this).attr("contenteditable")=="true"?$(this).css("color","#1b3749"):$(this).css("color","white");
            	//做一个当前点击目录的标记,目的是可以根据当前链接保存文章内容
            	$(this).attr("click","on");
            	var data={};
            	if ($("div[click=on]").attr("id")!=undefined){
            		data.id=$("div[click=on]").attr("id");
                	$.post("index/getData.jhtml",data,function(msg){
                		editor.txt.html(msg);
                	});
            	}
            
            });
            <c:if test="${ not empty admin }">
            //监听键盘的ctrl+s键
            $(document).keydown(function(e){
     		   if( e.ctrlKey  == true && e.keyCode == 83 ){
     			   var item={};
     			    item.id=$("div[click=on]").attr("id");
     			    item.content=editor.txt.html();
     	 			$.post("index/saveData.jhtml",item);
     		      return false; // 截取返回false就不会保存网页了
     		   }
     		});
            </c:if>
        });
    </script>
    <!-- <style >*{text-overflow: ellipsis;overflow: hidden;white-space: nowrap;font-size: 18px;font-weight: bold;}</style> -->
</head>
<body style="padding: 0px;margin: 0px;">
	<div id="container" style="width: 100%;height: auto">
		<div id="box-left"
			style="width: 65%;height: 0px;background-color: #1b3749;float: left;color:white;
                         text-overflow: ellipsis;overflow: hidden;white-space: nowrap;font-size: 18px;font-weight: bold;">
			<div id="box-left-top"
				style="width: 100%;height:100px;color: white;font-size: 45px;line-height: 100px;
                                 font-weight: bold;text-align: center;background-color: #1b3749;">
				编程大典</div>
			<div id="box-left-buttom"
				style="width: 100%;height:100px;background-color: #1b3749;border-top: 1px solid grey; ">
				<div id="mainItem" style="cursor: pointer;">目录结构索引数</div>
				<div id="indexItem">
					<c:forEach items="${menubox}" var="menu">
						<div
							style='height: auto;width: 70%; margin-top: 2px; margin-left: 20px;color: white;'>
							<div name='subItem-0' pid="${menu.pid }" id="${menu.id }"
								style='background-color: #1b3749; width: 100%; height: 25px; border: none;cursor: pointer;'
								contenteditable='false'>${menu.title }</div>
							<div name='subItem-1' style='margin-top: 3px;margin-left: 25px;cursor: pointer;'></div>
						</div>
					</c:forEach>
				</div>
			</div>
			<div style="width: 100%;height: 30px;border-top: 1px solid grey;text-align: center;">
				<form action="index/login.jhtml"  method="post">
						<!-- 从cookie中获取数据 -->
						<input type="hidden"  name="username" value="${cookie.username.value}"/>
						<!-- 从cookie中获取数据 -->
						<input type="hidden"  name="password" value="${cookie.password.value }"/>
						<input id="time" style="width: 100%;height: 30px;text-align: center; 
                                                                 background-color: #1b3749;color:white;border: none;" type="submit"  value="欢迎光临">
				</form>
			</div>
		</div>
		<div id="box-middle" style="width: 0.5%;height: 0px;background-color: #cccccc;float: left;cursor: e-resize;"></div>
		<div id="box-right" style="width: 34.5%;height: 0px;float: left;">
				 <div id="menu"  style="width: 100%;border:1px solid grey;height: 40px;border: 1px solid grey; "></div>
				 <div id="editor" style="width: 100%;height:0px;color: black;height:200px"></div>
		</div>
	</div>
</body>
<script>
 <c:if test="${ not empty admin }">
  //每隔一秒钟发送一次ajax请求
  window.setInterval(function(){
	  $.get("index/time.jhtml",function(time){
		  $("input[id=time]").val(time);
	  });
  }, 1000); 
  </c:if>
</script>
</html>