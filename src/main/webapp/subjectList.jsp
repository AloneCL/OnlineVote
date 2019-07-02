<%--
  Created by IntelliJ IDEA.
  User: ChenLei
  Date: 2019/7/2
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
    <title>投票列表</title>
    <meta charset="utf-8">
    <style>
        li{
            list-style: none;
        }
        table,table tr th, table tr td { border:2px solid #0094ff; }
        .table1{
            top:40px;
            margin: 0 auto;
            position: relative;
            width: 35%;
        }
        .table1 table{
            text-align: center;
            margin: 0 auto;
            border-collapse: collapse;
        }
        .table1 table td{
            font-size: 20px;
            padding: 5px 10px 5px 10px;
            height: 30px;
        }
        #header{
            width: 100%;
            background-color: #333333;
            position: fixed;
            margin: 0;
            z-index: 999;
            top: 0;
        }

        #header .center{
            position: relative;
        }
        .logo{
            width: 300px;
            height: 60px;
            margin: 0 0 0 200px;
            display: inline-block;
            background: url("image/logo.png") no-repeat;
        }

        #header .link{
            top: -16px;
            right: 120px;
            height: 64px;
            display: inline-block;
            position: absolute;
        }

        #header .link>ul>li{
            text-align: center;
            width: 90px;
            line-height: 60px;
            margin-right: 8px;
            float: left;
            list-style: none;
        }

        #header .link .active{
            background-color: #000000;
        }

        #header .link>ul>li a{
            margin: 0 auto;
            cursor: pointer;
            text-decoration: none;
            color: #c7c4c4;
            font-size: 16px;
            font-weight: 500;
        }
    </style>
</head>
<body>
<header id="header">
    <div class="center">
        <h1 class="logo"></h1>
        <nav class="link">
            <ul>
                <li class="active"><a href="index.html">首页</a></li>
                <li><a href="information.html">发起投票</a></li>
                <li><a href="ticket.html">我的投票</a></li>
                <li><a href="scenery.html">个人中心</a></li>
                <li><a href="about.html">系统简介</a></li>
            </ul>
        </nav>
    </div>
</header>
<div class="table1">
    <table border="2">
        <caption><h1>投票列表显示</h1></caption>
        <tr>
            <th>序号</th>
            <th>投票名称</th>
            <th>类别</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach items="${subjectList}" var="s">
            <tr>
                <td>${s.id}</td>
                <td>${s.title}</td>
                <c:choose>
                    <c:when test="${s.type==1}">
                        <td>单选</td>
                    </c:when>
                    <c:otherwise>
                        <td>多选</td>
                    </c:otherwise>
                </c:choose>
                <td>查看</td>
                <td>参与</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
