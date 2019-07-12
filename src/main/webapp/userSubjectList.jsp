<%--
  Created by IntelliJ IDEA.
  User: ChenLei
  Date: 2019/7/2
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE>
<html>
<head>
    <title>我的投票</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/head.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/subjectList.css">
    <script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
    <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <style>

        body{
            background-color: #e2d8cf;
        }



    </style>
</head>
<body>
<header id="header">
    <div class="center">
        <h1 class="logo"></h1>
        <nav class="link">
            <ul>
                <li><a href="showSubject">首页</a></li>
                <li><a href="#" class="addClick">发起投票</a></li>
                <li class="active"><a href="#">我的投票</a></li>
                <li id="personC"><a href="#">个人中心</a>
                    <ul class="person">
                        <li><a href="#">个人资料</a></li>
                        <li><a href="#">历史投票</a></li>
                        <li><a href="logOut">退出登录</a></li>
                    </ul>
                </li>
                <li><a href="about.html">系统简介</a></li>
            </ul>
        </nav>
    </div>
</header>

<div class="table1">
    <table>
        <caption style="text-align: center">
            <select name="searchType" onchange="changeList(this.options[this.options.selectedIndex].value)">
                <option value="-1">状态筛选</option>
                <option value="0" <c:if test="${type==0}">selected</c:if>>全选</option>
                <option value="1" <c:if test="${type==1}">selected</c:if>>进行中</option>
                <option value="2" <c:if test="${type==2}">selected</c:if>>已结束</option>
            </select>
            <h1 style="display: inline-block">我发起的投票</h1> <hr>
        </caption>

        <tr>
            <th style="width: 5%;">序号</th>
            <th>投票名称</th>
            <th style="width: 6%">类别</th>
            <th>状态</th>
            <th>结束时间</th>
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
                <td>
                    <c:choose>
                        <c:when test="${s.status=='false'}">
                            <span style="color: red;">已结束</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color: #1db31d;">进行中</span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td> <c:choose>
                    <c:when test="${s.status=='false'}">
                        -----
                    </c:when>
                    <c:otherwise>
                        ${s.status}
                    </c:otherwise>
                </c:choose></td>
                <td colspan="2">
                    <a href="subjectDetail?subjectId=${s.id}" class="see nohover">查看</a>
                    <a href="#" class="see nohover">管理</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5" style="font-size: 18px">
                <c:choose>
                    <c:when test="${currentPage!=1}">
                        <a style="color: #337ab7;font-size: 18px;" href="userVotes?page=${currentPage-1}">上一页</a>
                    </c:when>
                    <c:otherwise>
                        上一页
                    </c:otherwise>
                </c:choose>
                共<span style="color: red;font-size: 19px">${totalPage}</span>页&nbsp;共<span style="color: red;font-size: 19px">${total}</span>有条记录&nbsp;当前是第<span style="color: red;font-size: 19px">${currentPage}</span>页&nbsp;
                <c:choose>
                    <c:when test="${currentPage != totalPage }">
                        <a style="color: #337ab7;font-size: 18px;" href="userVotes?page=${currentPage+1}">下一页</a>
                    </c:when>
                    <c:otherwise>
                        下一页
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</div>
<jsp:include page="addSubject.jsp"></jsp:include>
</body>
<script src="lib/bootstrap.min.js"></script>
<script>

    function  checkForm() {
        if(checkTitle()&&checkOption()&&checkTypeNum()){
            return true;
        }else {
            alert("请规范输入投票内容");
            return false;
        }
    }

    $(function () {
        $("#personC").mouseover(function () {
            $(".person").css('transform','scaleY(1)');
        });
        $("#personC").mouseout(function () {
            $(".person").css('transform','scaleY(0)');
        });
    });

    /**
     * 根据投票状态来筛选投票
     * @param searchType
     */
    function changeList(searchType){
        if(searchType!=-1){

            window.self.location = "${pageContext.request.contextPath}/userVotes?searchType="+searchType;
        }
    }

    /**
     * 判断选项数量和格式是否正确
     *
     * */
    function checkOption(){
        var title = $("input[name='option']");
        if(title.length<2){
            alert("投票选项至少需要两个");
            return false;
        }else {
            for(var i = 0; i<title.length; i++){
                if(title[i].value== ''){
                    //  console.log(111);
                    $(title[i]).next().text('选项内容不能为空');
                    return false;
                }else {
                    $(title[i]).next().text("");
                }
            }
        }
        return true;
    }

    /**
     * 检查选项数量是否规范
     *
     * */
    function  checkTypeNum() {
        var v = document.getElementById('types');
        var title = $("input[name='option']");
        if(v.value!='') {
            if (v.value >= title.length) {
                $(v).next().text("不能超过选项总数");
                v.value = "";
                v.focus();
                return false;
            } else if (v.value < 1) {
                $(v).next().text("选项数量至少为1");
                v.value = "";
                v.focus();
                return false;
            } else {
                $(v).next().text("");
                return true;
            }
        }
        return true;
    }

    function checkTitle(){
        var title = $("input[name='subjectTitle']");
        var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
            regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;

        if(title[0].value == ''){
            $(title[0]).next().text("标题不能为空");
            return false;
        }else {
            if(regEn.test(title[0].value)||regCn.test(title[0].value)){
                $(title[0]).next().text("标题不能包含特殊字符");
                return false;
            }
        }
        $(title[0]).next().text("");
        return true;
    }

    /**
     * 添加投票窗口显示
     */
    $('.addClick').click(function () {
        $('.modal').modal("show");
    });

    /**
     * 添加选项 并迭代ul列表子元素添加事件
     */
    $('.add-title').click(function () {
        $('.titleList').append(' <li class="col-md-12">\n' +
            '                                        <label class="col-md-3"><a href="#" title="删除选项" class="removeTitle">x</a> </label>\n' +
            '                                        <div class="col-md-8 cl cl1">\n' +
            '                                            <input type="text" onblur="checkOption()" name="option" style="width: 100%" placeholder="请输入选项名称"><span></span>\n' +
            '                                        </div>\n' +
            '                                    </li>');

        $.each($('.removeTitle'),function(index,item){
            $(item).unbind('click');
            $(item).click(function(){
                $(this).parent().parent().remove();
            });

        });
    });

    /**
     * 移除选项
     */
    $(function () {
        $.each($('.removeTitle'),function(index,item){
            $(item).unbind('click');
            $(item).click(function(){
                $(this).parent().parent().remove();
            });

        });
    });

    $(function () {
        $('input[type=radio][name=type]').change(function () {
            var inType = document.getElementById('types');
            if(this.value == '2'){
                /*$('#types').toggleClass('show');*/
                if((inType.className.indexOf('show1') ==-1)){
                  inType.classList.add('show1');
                  inType.value = 2;
                  inType.focus();
                }
            }else {
                if((inType.className.indexOf('show1') !=-1)){
                    inType.classList.remove('show1');
                    inType.value = "";
                }
            }
        });
    })
</script>
</html>
