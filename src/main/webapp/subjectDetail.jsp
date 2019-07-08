<%--
  Created by IntelliJ IDEA.
  User: ChenLei
  Date: 2019/7/6
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>投票详情</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="css/subjectDetail.css">
    <link rel="stylesheet" type="text/css" href="css/head.css">
    <link rel="stylesheet" type="text/css" href="css/subjectList.css">
</head>
<body>
<jsp:include page="addSubject.jsp"></jsp:include>
<header id="header">
    <div class="center">
        <h1 class="logo"></h1>
        <nav class="link">
            <ul>
                <li class="active"><a href="showSubject">首页</a></li>
                <li><a href="#" class="addClick">发起投票</a></li>
                <li><a href="ticket.html">我的投票</a></li>
                <li><a href="scenery.html">个人中心</a></li>
                <li><a href="about.html">系统简介</a></li>
            </ul>
        </nav>
    </div>
</header>

<section>
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <div class="section-title">
                    <h2><a style="color: #337ab7;text-decoration: none" href="showSubject"> 投票列表 </a>->> 投票詳情</h2>
                </div>
            </div>
            <div class="service-area">
                <div class="row">
                    <div class="col-md-6">
                        <div class="subjectTitle">
                            <p>投票标题：${subject.title}</p>
                        </div>
                        <div>
                            <p>开始时间：<fmt:formatDate value="${subject.startTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> </p>
                            <p>截止日期：<span style="color: red;"><fmt:formatDate value="${subject.endTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> </span> &nbsp;&nbsp;&nbsp;剩余：<span style="color: #15b31b;"><c:choose><c:when test="${sTime=='false'}">-----</c:when><c:otherwise>${sTime}</c:otherwise></c:choose></span></p>
                            <p>类型：<c:choose><c:when test="${subject.type==1}">单选</c:when><c:otherwise>多选</c:otherwise></c:choose></p>
                        </div>
                    </div>
                    <form id="form">
                        <div class="col-md-8 option_bar_design">
                             <input type="text" name="userId" value="${cookie.userId.value}" hidden>
                            <input type="text" name="subjectId" value="${optionList[0].subjectId}" hidden>
                            <c:forEach var="o" items="${optionList}">
                                <div class="option-line">
                                    <div class="option-text"><input type="checkbox" name="option" value="${o.id}">选项 ${o.order}. ${o.option}</input></div>
                                    <span class="float_right"></span>
                                    <input type="text" name="optionOrder" value="${o.order}" hidden>
                                    </p>
                                    <div class="option-bar">
                                        <span></span>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                        <div class="col-md-8" style="text-align: center">
                            <span style="color: red;"></span><br>
                            <button type="button" id="submitBtn">投票</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script src="lib/bootstrap.min.js"></script>
<script>
    var checkflag = false;

    var userVote = false;

    function checkVote() {
        var url = "${pageContext.request.contextPath}/checkVote";
        $.get(url, {
            "userId" : ${cookie.userId.value},
            "subjectId":${subject.id},
        }, function(data) {
            if (data == "true") {
                alert("true");
               userVote =  true;
            } else {
                $("input[name=option]").not("input:checked").attr('disabled','disabled');
                searchNum();
                checkflag = true;
              userVote = false;
            }
        });
    }

    /**
     * 动态显示选项的数量
     * */
    function searchNum() {
        if(!checkflag){
        $.ajax({
            url:"${pageContext.request.contextPath}/searchNum",

            data:{
                "subjectId":${subject.id},
                "userId" : ${cookie.userId.value},
            },
            type:'POST',
            success:function (data) {
                console.log(data);
                var jsonObject = jQuery.parseJSON(data);
                console.log(jsonObject);
                var options = $('input[type=checkbox][name=option]');
                for(var j =0; j < jsonObject.length; j++) {
                  var e = $(':checkbox[value='+jsonObject[j].orderId+']');             //选择value为id的checkbox
                    $(e).parent().next().text(jsonObject[j].orderNum+"票");
                    if(jsonObject[j].userIsVote==true){
                        $(e).attr("checked",true);
                    }
                    $($(e).parent().next().next().next().next().children()[0]).animate({width: jsonObject[j].percent+"%"},1500);
                    $($(e).parent().next().next().next().next().children()[0]).attr("title",jsonObject[j].percent+"%");
                }
            }
        });
        }
    }

    /**
     * 投票点击
     * */
    $('#submitBtn').click(function (){
        var checkd = $("input:checkbox[name=option]:checked");
        var types = ${subject.type};

        if(!userVote||checkd.length >types||checkd.length<1){
            alert("You have already voted.");
            return false;
        }else {
             $.ajax({
                 type:'post',
                 data: $('#form').serialize(),
                 url:'${pageContext.request.contextPath}/addItem',
                 cache:false,
                 dataType:'text',
                 success:function (data) {
                     if(data=='ok'){
                         searchNum();
                     }else {
                         alert("error");
                     }
                 }
             })
        }

    });

    /**
     * 检验增加选项填写是否正确
     * */
    function  checkForm() {
        if(checkTitle()&&checkOption()&&checkTypeNum()){
            return true;
        }else {
            alert("请规范输入投票内容");
            return false;
        }
    }



    /**
     * 根据投票状态来筛选投票
     * @param searchType
     */
    function changeList(searchType){
        if(searchType!=-1){

            window.self.location = "${pageContext.request.contextPath}/showSubject?searchType="+searchType;
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
        if(v.value>=title.length){
            $(v).next().text("不能超过选项总数");
            v.value = "";
            v.focus();
            return false;
        }else if(v.value<1){
            $(v).next().text("选项数量至少为1");
            v.value = "";
            v.focus();
            return false;
        }else {
            $(v).next().text("");
            return true;
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

    /**
     * 显示或隐藏时间输入框
     */
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

    $(function () {
        checkVote();                //进入自动检查是否已投票
        var options = $("input[type=checkbox][name=option]");
        var types = ${subject.type};
        //if(types>1) {
            for (var i = 0; i < options.length; i++) {
                $(options[i]).change(function () {
                    var checkd = $("input:checkbox[name=option]:checked");
                    var leng = checkd.length;
                    if(types>leng) {
                        $("input[name=option][type=checkbox]:disabled").removeAttr("disabled");
                        $("#submitBtn").prev().prev().text("你已选择了" + leng + "个选项，还可以选择" + (types - leng) + "个选项");
                    }else {
                        $("#submitBtn").prev().prev().text("到达最大选项个数，不能再选择更多的选项了");
                        $("input[name=option]").not("input:checked").attr('disabled','disabled');

                    }
                })
        }

    });
</script>
</html>
