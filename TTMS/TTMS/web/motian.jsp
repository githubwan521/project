<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Employee" %>
<%@ page import="Model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head>
    <base href="<%=basePath%>">
    <title>BS</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $(".meun-item").click(function () {
                $(".meun-item").removeClass("meun-item-active");
                $(this).addClass("meun-item-active");
                var itmeObj = $(".meun-item").find("img");
                itmeObj.each(function () {
                    var items = $(this).attr("src");
                    items = items.replace("_grey.png", ".png");
                    items = items.replace(".png", "_grey.png")
                    $(this).attr("src", items);
                });
                var attrObj = $(this).find("img").attr("src");
                ;
                attrObj = attrObj.replace("_grey.png", ".png");
                $(this).find("img").attr("src", attrObj);
            });
            $("#topAD").click(function () {
                $("#topA").toggleClass(" glyphicon-triangle-right");
                $("#topA").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topBD").click(function () {
                $("#topB").toggleClass(" glyphicon-triangle-right");
                $("#topB").toggleClass(" glyphicon-triangle-bottom");
            });
            $("#topCD").click(function () {
                $("#topC").toggleClass(" glyphicon-triangle-right");
                $("#topC").toggleClass(" glyphicon-triangle-bottom");
            });
            $(".toggle-btn").click(function () {
                $("#leftMeun").toggleClass("show");
                $("#rightContent").toggleClass("pd0px");
            })
        })
    </script>
    <style>
        #dis {
            display: none;
        }
    </style>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/slide.css"/>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/flat-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery.nouislider.css">
</head>

<body>
<div id="wrap">
    <div class="leftMeun" id="leftMeun">
        <div id="logoDiv">
            <p id="logoP">
                <a href="/TTMS/LogoutServlet">
                    <img id="logo" alt="BS" src="img/logo.jpg">
                </a>

                <a data-toggle="modal" data-target="#updateHead"><span>Billion Star</span>
                </a>
            </p>
        </div>

        <div class="meun-item meun-item-active" href="#sour" aria-controls="sour" role="tab" data-toggle="tab">
            <img src="images/icon_source.png">演出厅管理
        </div>
        <div class="meun-item" href="#seat" aria-controls="seat" role="tab" data-toggle="tab">
            <img src="images/icon_char_grey.png">座位管理
        </div>
        <div id="dis">
            <div class="meun-item" href="#user" aria-controls="user" role="tab" data-toggle="tab"><img
                    src="images/icon_user_grey.png">用户管理
            </div>
            <div class="meun-item" href="#chan" aria-controls="chan" role="tab" data-toggle="tab"><img
                    src="images/icon_change_grey.png">登录用户
            </div>
        </div>
    </div>
    <div class="modal fade" id="updateHead" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" style="width: 500px;text-align: center;">
            <div class="modal-content">
                <div class="modal-header">
                    <form name="myform" action="/TTMS/UploadServlet" method="post" enctype="multipart/form-data">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h3 class="modal-title">
                            修改头像
                        </h3>
                        <div class="modal-body">
                            <input type="file" name="head_image"><br>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" class="btn btn-primary" value="提交" onclick="return isPic()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function isPic() {
            var pic = myform.head_image.value;
            var ext = pic.substring(pic.lastIndexOf(".") + 1);
            //可以再添加允许类型
            if (ext.toLowerCase() == "jpg" || ext.toLowerCase() == "png" || ext.toLowerCase() == "gif")
                return true;
            else {
//			alert("只能上传jpg、png、gif图像!");
                return false;
            }
        }
    </script>
    <script type="text/javascript">
        var flag = "${sessionScope.usertype}";
        (function init() {
            if (flag == "Administrators") {
                document.getElementById("dis").style.display = "block";
            } else {
                document.getElementById("dis").style.display = "none";
            }
        }());

    </script>

    <div id="rightContent">
        <div class="tab-content">
            <!-- 演出厅管理模块 -->
            <div role="tabpanel" class="tab-pane active" id="sour">
                <!--左上角-->
                <div class="check-div form-inline">
                    <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addSource">添加演出厅</button>
                </div>
                <!--数据区-->
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 ">
                            编号
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">
                            演出厅名称
                        </div>
                        <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                            简介
                        </div>
                        <div class="col-lg-1 col-md-1 col-sm-1 col-xs-1">
                            操作
                        </div>
                    </div>
                    <div class="tablebody">
                        <div class="row">
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">001</div>
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">宁静厅</div>
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">测试专用</div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#changeSource">
                                    修改
                                </button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#deleteSource">
                                    删除
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!--添加演出厅弹出窗口-->
                <div class="modal fade" id="addSource" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="AddStudio">添加演出厅</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="" onsubmit="alert('保存成功')">
                                        <div class="form-group ">
                                            <label for="AddStudioName" class="col-xs-3 control-label">演出厅名：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       id="AddStudioName"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入演出厅名')"
                                                       oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddStudioInfo" class="col-xs-3 control-label">简介：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" cols="30" rows="10" maxlength="30"
                                                       class="form-control input-sm duiqi" id="AddStudioInfo">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddStudioRow" class="col-xs-3 control-label">行数：</label>
                                            <div class="col-xs-8">
                                                <input type="input " class="form-control input-sm duiqi"
                                                       id="AddStudioRow"
                                                       required oninvalid="setCustomValidity('请输入行数')"
                                                       oninput="setCustomValidity('')"
                                                       onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddStudioCol" class="col-xs-3 control-label">列数：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi"
                                                       id="AddStudioCol"
                                                       required oninvalid="setCustomValidity('请输入列数')"
                                                       oninput="setCustomValidity('')"
                                                       onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-xs btn-xs btn-white" name="close"
                                                    data-dismiss="modal">取 消
                                            </button>
                                            <button type="submit" class="btn btn-xs btn-xs btn-green">提 交</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--修改演出厅弹出窗口-->
                <div class="modal fade" id="changeSource" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="UpdateStudio">修改演出厅</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="" onsubmit="alert('修改成功')">
                                        <div class="form-group ">
                                            <label for="UpdateStudioName" class="col-xs-3 control-label">演出厅名：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       id="UpdateStudioName"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入演出厅名')"
                                                       oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateStudioInfo" class="col-xs-3 control-label">简介：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" cols="30" rows="10" maxlength="30"
                                                       class="form-control input-sm duiqi" id="UpdateStudioInfo">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateStudioRow" class="col-xs-3 control-label">行数：</label>
                                            <div class="col-xs-8">
                                                <input type="input " class="form-control input-sm duiqi"
                                                       id="UpdateStudioRow"
                                                       required oninvalid="setCustomValidity('请输入行数')"
                                                       oninput="setCustomValidity('')"
                                                       onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateStudioCol" class="col-xs-3 control-label">列数：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi"
                                                       id="UpdateStudioCol"
                                                       required oninvalid="setCustomValidity('请输入列数')"
                                                       oninput="setCustomValidity('')"
                                                       onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-xs btn-xs btn-white" name="close"
                                                    data-dismiss="modal">取 消
                                            </button>
                                            <button type="submit" class="btn btn-xs btn-xs btn-green">提 交
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--弹出删除演出厅警告窗口-->
                <div class="modal fade" id="deleteSource" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="DeleteStudio">警告</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">确定要删除该演出厅?</div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                                <button type="submit" class="btn btn-xs btn-xs btn-green" data-dismiss="modal"
                                        onclick="alert('删除成功')">确 定
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!--座位管理模块-->
            <div role="tabpanel" class="tab-pane" id="seat">
                <div class="check-div form-inline" style="">
                    <div class="col-lg-4 col-xs-7 col-md-6">

                        <label for="seat">演出厅:&nbsp;</label>
                        <select class=" form-control">
                            <option>宁静厅</option>
                            <option>致远厅</option>
                            <option>大地厅</option>
                            <option>天空厅</option>
                        </select>

                    </div>
                    <div class="col-lg-4 col-lg-offset-4 col-xs-4 col-md-5 "
                         style="padding-right: 40px;text-align: right;">
                        <input type="text" class=" form-control input-sm " placeholder="输入文字搜索">
                        <button class="btn btn-white btn-xs ">查 询</button>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-2 " style="padding-left: 20px;">
                            演出厅名
                        </div>
                        <div class="col-xs-3" style="padding-left: 20px;">
                            行数
                        </div>
                        <div class="col-xs-2" style="padding-left: 2px;">
                            列数
                        </div>
                        <div class="col-xs-2">
                            座位损坏数
                        </div>
                        <div class="col-xs-3">
                            编辑
                        </div>
                    </div>
                    <div class="tablebody">
                        <div class="sitTable">
                            <table class="table  table-responsive">
                                <tr>
                                    <td valign="middle" class="col-xs-2" rowspan="3">宁静厅</td>
                                    <td class="col-xs-3">10</td>
                                    <td class="col-xs-2">10</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">更多</a>

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="sitTable">
                            <table class="table  table-responsive">
                                <tr>
                                    <td valign="middle" class="col-xs-2" rowspan="3">致远厅</td>
                                    <td class="col-xs-3">10</td>
                                    <td class="col-xs-2">7</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">更多</a>

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="sitTable">
                            <table class="table  table-responsive">
                                <tr>
                                    <td valign="middle" class="col-xs-2" rowspan="3">大地厅</td>
                                    <td class="col-xs-3">8</td>
                                    <td class="col-xs-2">7</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">更多</a>

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="sitTable">
                            <table class="table  table-responsive">
                                <tr>
                                    <td valign="middle" class="col-xs-2" rowspan="3">天空厅</td>
                                    <td class="col-xs-3">7</td>
                                    <td class="col-xs-2">7</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">更多</a>

                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--座位详情模块-->
            <div role="tabpanel" class="tab-pane" id="sitDetail">
                <div class="check-div form-inline">
                    <span href="#seat" aria-controls="sitt" role="tab" data-toggle="tab" style="cursor: pointer;"><span
                            class="glyphicon glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp;返回上一页</span>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-6 ">
                            此厅信息
                        </div>
                        <div class="col-xs-6 ">
                            此厅规格
                        </div>
                    </div>
                    <div class="tablebody">

                        <div class="row">
                            <div class="col-xs-6 ">
                                啦啦啦啦啦啦
                            </div>
                            <div class="col-xs-6">超大
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--用户管理模块-->
            <div role="tabpanel" class="tab-pane" id="user">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addUser">添加用户</button>
                    </div>
                    <div class="col-xs-4">
                        <form method="post" action="/TTMS/EmployeeServlet?operation=find">
                            <input type="text" class="form-control input-sm" placeholder="输入文字搜索"
                                   name="search_emp_name" value="${requestScope.get("search_emp_name")}">
                            <button class="btn btn-white btn-xs">查 询</button>
                        </form>
                    </div>
                    <div class="col-lg-3 col-lg-offset-2 col-xs-4" style=" padding-right: 40px;text-align: right;">
                        <label for="user">排序:&nbsp;</label>
                        <select class=" form-control">
                            <option>用户ID</option>
                            <option>用户类型</option>
                            <option>用户名</option>
                            <option>电话</option>
                            <option>邮箱</option>
                        </select>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-2">用户ID</div>
                        <div class="col-xs-2">用户名</div>
                        <div class="col-xs-2">电话</div>
                        <div class="col-xs-2">邮箱</div>
                        <div class="col-xs-2">操作</div>
                    </div>
                    <div class="tablebody">
                        <%
                            int currentPage = 1;  //当前页
                            int allCount = 0;     //总记录数
                            int allPageCount = 0; //总页数
                            Employee Employee = null;
                            //查看request中是否有currentPage信息，如没有，则说明首次访问该页
                            if (request.getAttribute("allEmployee") != null) {
                                //获取Action返回的信息
                                currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
                                ArrayList<Employee> list = (ArrayList<Employee>) request.getAttribute("allEmployee");
                                allCount = ((Integer) request.getAttribute("allCount")).intValue();
                                allPageCount = ((Integer) request.getAttribute("allPageCount")).intValue();
                                if (list != null && list.size() > 0) {
                                    for (int i = 0; i < list.size(); i++) {
                        %>
                        <div class="row">
                            <div class="col-xs-2 "><%=list.get(i).getEmp_no()%>
                            </div>
                            <div class="col-xs-2"><%=list.get(i).getEmp_name()%>
                            </div>
                            <div class="col-xs-2"><%=list.get(i).getEmp_tel_num()%>
                            </div>
                            <div class="col-xs-2"><%=list.get(i).getEmp_email()%>
                            </div>
                            <div class="col-xs-4">
                                <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#UpdateUser">
                                    修改
                                </button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#DeleteUser">删除
                                </button>
                                <%--<a href="EmployeeServlet?method=searchById&emp_id=<%=list.get(i).getEmp_id()%>">修改</a>--%>

                                <%--<a href="EmployeeServlet?method=delete&emp_id=<%=list.get(i).getEmp_id()%>&emp_name=${search_emp_name}&currentPage=${currentPage}">删除</a>--%>
                            </div>
                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                    <!-- 分页 -->
                    <div class="text-center">
                        <ul class="pagination">
                            <li>
                                <a href="/TTMS/EmployeeServlet?operation=find&currentPage=1&emp_name=${search_emp_name}"><font
                                        color=black>首页</font></a>
                            </li>
                            <li>
                                <a href="/TTMS/EmployeeServlet?operation=find&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&emp_name=${search_emp_name}">
                                    <font color=black>上一页</font></a>
                            </li>
                            <li>
                                <a href="/TTMS/EmployeeServlet?operation=find&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&emp_name=${search_emp_name}"><font
                                        color=black>下一页</font></a>
                            </li>
                            <li>
                                <a href="/TTMS/EmployeeServlet?operation=find&currentPage=<%=allPageCount%>&emp_name=${search_emp_name}"><font
                                        color=black>末页</font></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!--弹出添加用户窗口-->
                <div class="modal fade" id="addUser" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                <h4 class="modal-title" id="AddU">添加用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="/TTMS/EmployeeServlet?operation=add"
                                          method="post">
                                        <div class="form-group">
                                            <label for="sel" class="col-xs-3 control-label">用户类型：</label>
                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi" id="sel" name="sel">
                                                    <option value="1">系统管理员</option>
                                                    <option value="2">剧院经理</option>
                                                    <option value="3">售票员</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserName" class="col-xs-3 control-label">账号：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入账号')"
                                                       id="AddUserNo" name="AddUserNo" oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserName" class="col-xs-3 control-label">用户名：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入用户名')"
                                                       id="AddUserName" name="AddUserName"
                                                       oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserTel" class="col-xs-3 control-label">电话：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi" id="AddUserTel"
                                                       name="AddUserTel" maxlength="13">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserEml" class="col-xs-3 control-label">电子邮箱：</label>
                                            <div class="col-xs-8">
                                                <input type="email" class="form-control input-sm duiqi" id="AddUserEml"
                                                       name="AddUserEml">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserAddr" class="col-xs-3 control-label">住址：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi" id="AddUserAddr"
                                                       name="AddUserAddr">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserEml" class="col-xs-3 control-label">可用于登录：</label>
                                            <div class="col-xs-8">
                                                <div class="input-sm ">
                                                    <input name="LoginPermissions" type="radio" value="30"/>是&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input name="LoginPermissions" type="radio" checked="checked"
                                                           value="31"/>否
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取消
                                            </button>
                                            </button>
                                            <button type="submit" class="btn btn-xs btn-xs btn-green" onclick="">保 存
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--弹出修改用户窗口-->
                <div class="modal fade" id="UpdateUser" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="UpdateU">修改用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal" action="/TTMS/EmployeeServlet?operation=update"
                                          method="post">
                                        <div class="form-group">
                                            <label for="sele" class="col-xs-3 control-label">用户类型：</label>
                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi" id="sele" name="sele">
                                                    <option value="">系统管理员</option>
                                                    <option value="">剧院经理</option>
                                                    <option value="">售票员</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateUserName" class="col-xs-3 control-label">账号：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入账号')"
                                                       id="UpdateUserNo" name="UpdateUserNo"
                                                       oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateUserName" class="col-xs-3 control-label">用户名：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入用户名')"
                                                       id="UpdateUserName" name="UpdateUserName"
                                                       oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateUserTel" class="col-xs-3 control-label">电话：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi"
                                                       id="UpdateUserTel" name="UpdateUserTel"
                                                       maxlength="13">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateUserEm" class="col-xs-3 control-label">电子邮箱：</label>
                                            <div class="col-xs-8">

                                                <input type="email" class="form-control input-sm duiqi"
                                                       id="UpdateUserEm" name="UpdateUserEm">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="UpdateUserAddr" class="col-xs-3 control-label">住址：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi"
                                                       id="UpdateUserAddr"
                                                       name="UpdateUserAddr">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-3 control-label">可用于登录：</label>
                                            <div class="col-xs-8">
                                                <div class="input-sm ">
                                                    <input name="LoginPermissions" type="radio" value="30"/>是&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    <input name="LoginPermissions" type="radio" checked="checked"
                                                           value="31"/>否
                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取
                                                消
                                            </button>
                                            <button type="submit" class="btn btn-xs btn-xs btn-green" onclick="">保 存
                                            </button>

                                        </div>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!--弹出删除用户警告窗口-->
                <div class="modal fade" id="DeleteUser" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel">警告</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    确定要删除该用户?删除后不可恢复!
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>

                                <button type="submit" class="btn btn-xs btn-xs btn-green" data-dismiss="modal"
                                        onclick="alert('删除成功')">确 定
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 登录用户管理模块   密码-->
            <div role="tabpanel" class="tab-pane" id="chan">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addUser">添加用户</button>
                    </div>
                    <div class="col-xs-4">
                        <form method="post" action="/TTMS/UserServlet?operation=find">
                            <input type="text" class="form-control input-sm" placeholder="输入文字搜索"
                                   name="search_emp_name" value="${requestScope.get("search_emp_name")}">
                            <button class="btn btn-white btn-xs">查 询</button>
                        </form>
                    </div>
                    <div class="col-lg-3 col-lg-offset-2 col-xs-4" style=" padding-right: 40px;text-align: right;">
                        <label for="user">排序:&nbsp;</label>
                        <select class=" form-control">
                            <option>用户ID</option>
                            <option>用户密码</option>
                            <option>用户类型</option>
                        </select>
                    </div>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-4">用户ID</div>
                        <div class="col-xs-4">用户密码</div>
                        <div class="col-xs-4">用户类型</div>
                    </div>
                    <div class="tablebody">
                        <%
                            currentPage = 1;  //当前页
                            allCount = 0;     //总记录数
                            allPageCount = 0; //总页数
                            User user = null;
                            //查看request中是否有currentPage信息，如没有，则说明首次访问该页
                            if (request.getAttribute("allUser") != null) {
                                //获取Action返回的信息
                                currentPage = ((Integer) request.getAttribute("currentPage")).intValue();
                                ArrayList<User> list = (ArrayList<User>) request.getAttribute("allUser");
                                allCount = ((Integer) request.getAttribute("allCount")).intValue();
                                allPageCount = ((Integer) request.getAttribute("allPageCount")).intValue();
                                if (list != null && list.size() > 0) {
                                    for (int i = 0; i < list.size(); i++) {
                        %>
                        <div class="row">
                            <div class="col-xs-4 "><%=list.get(i).getUser_no()%>
                            </div>
                            <div class="col-xs-4"><%=list.get(i).getUser_pass()%>
                            </div>
                            <div class="col-xs-4"><%=list.get(i).getUser_type()%>
                            </div>

                        </div>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                    <!-- 分页 -->
                    <div class="text-center">
                        <ul class="pagination">
                            <li>
                                <a href="/TTMS/UserServlet?operation=find&currentPage=1&emp_name=${search_emp_name}"><font
                                        color=black>首页</font></a>
                            </li>
                            <li>
                                <a href="/TTMS/UserServlet?operation=find&currentPage=<%=(currentPage-1)<1?1:(currentPage-1)%>&emp_name=${search_emp_name}">
                                    <font color=black>上一页</font></a>
                            </li>
                            <li>
                                <a href="/TTMS/UserServlet?operation=find&currentPage=<%=(currentPage+1)>allPageCount?allPageCount:(currentPage+1)%>&emp_name=${search_emp_name}"><font
                                        color=black>下一页</font></a>
                            </li>
                            <li>
                                <a href="/TTMS/UserServlet?operation=find&currentPage=<%=allPageCount%>&emp_name=${search_emp_name}"><font
                                        color=black>末页</font></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>