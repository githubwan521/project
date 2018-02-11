<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FreeHTML5.co"/>
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive"/>


    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content=""/>
    <meta name="twitter:image" content=""/>
    <meta name="twitter:url" content=""/>
    <meta name="twitter:card" content=""/>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/style.css">


    <!-- Modernizr JS -->
    <script src="js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <meta http-equiv="Content-Type" content="text/html" ;charset="utf-8">
    <title>login</title>
    <style>
        #toolTip {
            display: none; /*默认不显示*/
            color: red;
        }
    </style>
    <script>
        window.onload = function () {
            var state = "${sessionScope.login}";
            if (state != null) {
                <%request.getSession().removeAttribute("login");%>
            }
        }
    </script>

</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-md-4 col-md-offset-4">

            <form action="/TTMS/LoginServlet" name="login" method="post" onsubmit="" class="fh5co-form animate-box"
                  data-animate-effect="fadeIn">
                <h2>登 录</h2>
                <div class="form-group">
                    <label for="username" class="sr-only">Username</label>
                    <input id="username" name="username" maxlength="20" type="text" onblur="check()" onFocus="clr()"
                           placeholder="请输入用户名" required oninvalid="setCustomValidity('请输入用户名')"
                           oninput="setCustomValidity('')"  onkeyup="this.value=this.value.replace(/[^\w]/g,'');"
                           autofocus class="form-control" autocomplete="off">
                    <br>
                </div>
                <div class="form-group">
                    <label for="userpass" class="sr-only">Password</label>
                    <input id="userpass" name="userpass" maxlength="16" type="password" placeholder="请输入密码"
                           required autofocus oninvalid="setCustomValidity('请输入密码')" oninput="setCustomValidity('')"
                           onkeyup="this.value=this.value.replace(/[^\w]/g,'');"
                           autocomplete="off" class="form-control">
                </div>
                <div class="form-group">
                    <label for="remember"><input type="checkbox" id="remember"> 记住我</label>
                </div>
                <div id="toolTip" class="form-group">

                </div>

                <div class="form-group">
                    <input type="submit" value="Sign In" class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign In Form -->
            <script type="text/javascript">
                var http_request;

                function clr() {
                    document.getElementById("toolTip").style.display = "none";//显示提示框
                }

                function check() {
                    if (document.login.username.value == "") {

                    } else {
                        createRequest(document.login.username.value);
                    }
                }

                function createRequest(username) {
                    http_request = false;
                    var url = "/TTMS/RegisterServlet";
                    if (window.XMLHttpRequest) {//非IE
                        http_request = new XMLHttpRequest();
                    } else if (window.ActiveXObject) {//IE
                        http_request = new ActiveXObject("Microsoft.XMLHTTP");//创建XMLHttpRequest对象
                    }

                    if (!http_request) {
                        alert("不能创建XMLHttpRequest对象");
                        return false;
                    }
                    //采用POST方式，异步传输
                    http_request.open("post", url, true);
                    //POST方式，必须加入如下头信息设定
                    http_request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                    http_request.onreadystatechange = complete;//调用返回结果处理函数
                    http_request.send("username=" + username);
                }

                function complete() {
                    if (http_request.readyState == 4) {//判断请求状态
                        if (http_request.status == 200) {//请求成功,开始处理返回结果
                            document.getElementById("toolTip").innerText = http_request.responseText;
                            document.getElementById("toolTip").style.display = "block";//显示提示框
                        } else {
                            alert("你请求的页面有错误" + http_request.status);
                        }
                    }
                }
            </script>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="js/main.js"></script>


</body>
</html>

