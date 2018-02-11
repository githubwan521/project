<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
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
            <p id="logoP"><img id="logo" alt="BS" src="img/logo.jpg"><span>Billion Star</span></p>
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
                    src="images/icon_change_grey.png">修改密码
            </div>
        </div>
    </div>
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
                        <div class="row">
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">002</div>
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">致远厅</div>
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">我是致远</div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#changeSource">
                                    修改
                                </button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#deleteSource">
                                    删除
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">003</div>
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">大地厅</div>
                            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">啦啦啦啦啦啦</div>
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#changeSource">
                                    修改
                                </button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#deleteSource">
                                    删除
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">004</div>
                            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3">天空厅</div>
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
                            <option>风</option>
                            <option>星</option>
                            <option>云</option>
                            <option>雨</option>
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
                            座位行
                        </div>
                        <div class="col-xs-2" style="padding-left: 2px;">
                            列数
                        </div>
                        <div class="col-xs-2">
                            损坏数
                        </div>
                        <div class="col-xs-3">
                            编辑
                        </div>
                    </div>
                    <div class="tablebody">
                        <div class="sitTable">
                            <table class="table  table-responsive">
                                <tr>
                                    <td valign="middle" class="col-xs-2" rowspan="3">风</td>
                                    <td class="col-xs-3">第一排</td>
                                    <td class="col-xs-2">10</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">此排信息</a>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="col-xs-3">第二排</td>
                                    <td class="col-xs-2">10</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">此排信息</a>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="col-xs-3">第三排</td>
                                    <td class="col-xs-2">10</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">此排信息</a>

                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="sitTable">
                            <table class="table  table-responsive">
                                <tr>
                                    <td valign="middle" class="col-xs-2" rowspan="3">星</td>
                                    <td class="col-xs-3">第一排</td>
                                    <td class="col-xs-2">7</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">0</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">此排信息</a>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="col-xs-3">第二排</td>
                                    <td class="col-xs-2">7</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">1</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">此排信息</a>

                                    </td>
                                </tr>
                                <tr>
                                    <td class="col-xs-3">第三排</td>
                                    <td class="col-xs-2">7</td>
                                    <td class="col-xs-2" style="padding-left: 40px!important;">2</td>
                                    <td class="col-xs-3" style="padding-left: 50px!important;">
                                        <a class="linkCcc" href="#sitDetail" aria-controls="char" role="tab"
                                           data-toggle="tab">此排信息</a>

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
                    <span href="#sitt" aria-controls="sitt" role="tab" data-toggle="tab" style="cursor: pointer;"><span
                            class="glyphicon glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp;返回上一页</span>
                </div>
                <div class="data-div">
                    <div class="row tableHeader">
                        <div class="col-xs-6 ">
                            此排座位编码
                        </div>
                        <div class="col-xs-6 ">
                            此排占座率(不含损坏的座位)
                        </div>

                    </div>
                    <div class="tablebody">

                        <div class="row">
                            <div class="col-xs-6 ">
                                DWSA7785325ASWDWQ551DA
                            </div>
                            <div class="col-xs-6">
                                50%
                            </div>

                        </div>

                    </div>

                </div>
                <!--页码块-->
                <footer class="footer">
                    <ul class="pagination">
                        <li>
                            <select>
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                                <option>6</option>
                                <option>7</option>
                                <option>8</option>
                                <option>9</option>
                                <option>10</option>
                            </select>
                            页
                        </li>
                        <li class="gray">
                            共10页
                        </li>
                        <li>
                            <i class="glyphicon glyphicon-menu-left">
                            </i>
                        </li>
                        <li>
                            <i class="glyphicon glyphicon-menu-right">
                            </i>
                        </li>
                    </ul>
                </footer>
            </div>
            <!--用户管理模块-->
            <div role="tabpanel" class="tab-pane" id="user">
                <div class="check-div form-inline">
                    <div class="col-xs-3">
                        <button class="btn btn-yellow btn-xs" data-toggle="modal" data-target="#addUser">添加用户</button>
                    </div>
                    <div class="col-xs-4">
                        <input type="text" class="form-control input-sm" placeholder="输入文字搜索">
                        <button class="btn btn-white btn-xs ">查 询</button>
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
                        <div class="col-xs-2">用户类型</div>
                        <div class="col-xs-2">用户名</div>
                        <div class="col-xs-2">电话</div>
                        <div class="col-xs-2">邮箱</div>
                        <div class="col-xs-2">操作</div>
                    </div>
                    <div class="tablebody">
                        <div class="row">
                            <div class="col-xs-2 ">000001</div>
                            <div class="col-xs-2">管理员</div>
                            <div class="col-xs-2">motian</div>
                            <div class="col-xs-2">13688889999</div>
                            <div class="col-xs-2">765885195@qq.com</div>
                            <div class="col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#UpdateUser">修改</button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#DeleteUser">删除</button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-2 ">000002</div>
                            <div class="col-xs-2">售票员</div>
                            <div class="col-xs-2">mo</div>
                            <div class="col-xs-2">13**********</div>
                            <div class="col-xs-2">765885195@qq.com</div>
                            <div class="col-xs-2">
                                <button class="btn btn-success btn-xs" data-toggle="modal" data-target="#UpdateUser">修改</button>
                                <button class="btn btn-danger btn-xs" data-toggle="modal" data-target="#DeleteUser">删除</button>
                            </div>
                        </div>
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
                                    <form class="form-horizontal" action="" onsubmit="alert('保存成功')">
                                        <div class="form-group">
                                            <label for="sel" class="col-xs-3 control-label">用户类型：</label>
                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi" id="sel">
                                                    <option value="">系统管理员</option>
                                                    <option value="">剧院经理</option>
                                                    <option value="">售票员</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserName" class="col-xs-3 control-label">用户名：</label>
                                            <div class="col-xs-8 ">
                                                <input type="text" class="form-control input-sm duiqi"
                                                       maxlength="20" required oninvalid="setCustomValidity('请输入用户名')"
                                                       id="AddUserName" oninput="setCustomValidity('')">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserTel" class="col-xs-3 control-label">电话：</label>
                                            <div class="col-xs-8">
                                                <input type="input" class="form-control input-sm duiqi" id="AddUserTel" maxlength="13">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="AddUserEml" class="col-xs-3 control-label">电子邮箱：</label>
                                            <div class="col-xs-8">
                                                <input type="email" class="form-control input-sm duiqi" id="AddUserEml">
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                                            <button type="submit" class="btn btn-xs btn-xs btn-green" onclick="">保 存</button>
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
                                <h4 class="modal-title" id="gridSystemModalLabel">修改用户</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">用户类型：</label>
                                            <div class="col-xs-8">
                                                <select class=" form-control select-duiqi">
                                                    <option value="">系统管理员</option>
                                                    <option value="">剧院经理</option>
                                                    <option value="">售票员</option>
                                                    <option value="">观众</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sLink" class="col-xs-3 control-label">用户名：</label>
                                            <div class="col-xs-8 ">
                                                <input type="" class="form-control input-sm duiqi" id="sLink111222"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sKnot" class="col-xs-3 control-label">电话：</label>
                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="sKnot111222"
                                                       placeholder="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="sOrd" class="col-xs-3 control-label">电子邮箱：</label>
                                            <div class="col-xs-8">
                                                <input type="" class="form-control input-sm duiqi" id="sOrd111222"
                                                       placeholder="">
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                                <button type="submit" class="btn btn-xs btn-xs btn-green" onclick="btnAction20()">保 存
                                </button>
                            </div>

                            <script>
                                function btnAction20() {
                                    var reg = new RegExp("^1[358]\\d{9}$");
                                    var reg2 = new RegExp("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,5}$");
                                    var reg3 = new RegExp("[!~\\{}@/'\"#$%&^*]");
                                    if (document.getElementById("sLink111222").value == "") {
                                        alert("用户名不能为空");
                                    } else if (reg3.test(document.getElementById("sLink111222").value)) {
                                        alert("用户名不能含非法字符");
                                    } else if (document.getElementById("sKnot111222").value == "") {
                                        alert("电话不能为空");
                                    } else if (!reg.test(document.getElementById("sKnot111222").value)) {
                                        alert("请输入正确手机号");
                                    } else if (document.getElementById("sOrd111222").value == "") {
                                        alert("电子邮箱不能为空");
                                    } else if (!reg2.test(document.getElementById("sOrd111222").value)) {
                                        alert("请输入正确的电子邮箱");
                                    } else {
                                        alert("修改成功");
                                        $("#reviseUser").modal('hide');
                                    }
                                };
                            </script>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

                <!--弹出删除用户警告窗口-->
                <div class="modal fade" id="DeleteUser" role="dialog" aria-labelledby="gridSystemModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="gridSystemModalLabel">提示</h4>
                            </div>
                            <div class="modal-body">
                                <div class="container-fluid">
                                    确定要删除该用户？删除后不可恢复！
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-xs btn-white" data-dismiss="modal">取 消</button>
                                <button type="button" class="btn  btn-xs btn-danger">保 存</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->

            </div>

            <!-- 登录用户管理模块   密码-->
            <div role="tabpanel" class="tab-pane" id="chan">

                <div style="padding: 50px 0;margin-top: 50px;background-color: #fff; text-align: right;width: 420px;margin: 50px auto;">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">原密码：</label>
                            <div class="col-xs-5">
                                <input type="" class="form-control input-sm duiqi" id="sKnot1" placeholder=""
                                       style="margin-top: 7px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">新密码：</label>
                            <div class="col-xs-5">
                                <input type="" class="form-control input-sm duiqi" id="sKnot2" placeholder=""
                                       style="margin-top: 7px;">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sKnot" class="col-xs-4 control-label">重复密码：</label>
                            <div class="col-xs-5">
                                <input type="" class="form-control input-sm duiqi" id="sKnot3" placeholder=""
                                       style="margin-top: 7px;">
                            </div>
                        </div>
                        <div class="form-group text-right">
                            <div class="col-xs-offset-4 col-xs-5" style="margin-left: 169px;">
                                <button type="reset" class="btn btn-xs btn-white">取 消</button>

                                <button type="button" class="btn btn-xs btn-xs btn-green" id="sub4"
                                        onclick="btnAction3()">保 存
                                </button>
                            </div>
                        </div>
                        <script>
                            function btnAction3() {
                                var reg = new RegExp("^(\w){6,20}$");
                                if (document.getElementById("sKnot1").value == "") {
                                    alert("请输入原密码");
                                } else if (document.getElementById("sKnot2").value == "") {
                                    alert("请输入新密码");
                                } else if (!reg.test(document.getElementById("sKnot2").value)) {
                                    alert("密码不规范(6-20个字母、数字、下划线)");
                                } else if (document.getElementById("sKnot3").value == "") {
                                    alert("请重复输入新密码");
                                } else if (document.getElementById("sKnot2").value == document.getElementById("sKnot3").value) {
                                    alert("修改成功");
                                    document.getElementById("sKnot1").value = "";
                                    document.getElementById("sKnot2").value = "";
                                    document.getElementById("sKnot3").value = "";
                                } else {
                                    alert("两次密码不匹配");
                                }
                            };
                        </script>
                    </form>
                </div>

            </div>


        </div>
    </div>
</body>

</html>