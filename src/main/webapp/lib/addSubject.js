function  checkForm() {
    if(checkTitle()&&checkOption()&&checkTypeNum()){
        return true;
    }else {
        alert("请规范输入投票内容");
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