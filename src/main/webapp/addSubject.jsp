<%--
  Created by IntelliJ IDEA.
  User: ChenLei
  Date: 2019/7/6
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <span>添加投票</span>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <div class="container-box">
                    <div class="row">
                        <div class="col-md-7 box">
                            <form action="addSubject" method="post" onsubmit="return checkForm()">
                                <div class="modal-right">
                                    <label class="col-md-3">投票标题：</label>
                                    <div class="col-md-8 cl">
                                        <input type="text" onblur="checkTitle()" name="subjectTitle" style="width: 100%" placeholder="请输入投票标题"><span></span>
                                    </div>
                                </div>
                                <div class="modal-right">
                                    <label class="col-md-3">投票选项：</label>
                                    <div class="col-md-8 cl">
                                        <input type="text" onblur="checkOption()" name="option" style="width: 100%" placeholder="请输入选项名称"><span></span>
                                    </div>
                                </div>
                                <ul class="titleList">
                                    <li class="col-md-12">
                                        <label class="col-md-3"><a href="#" title="删除选项" class="removeTitle">x</a></label>
                                        <div class="col-md-8 cl cl1">
                                            <input type="text" onblur="checkOption()" name="option" style="width: 100%" placeholder="请输入选项名称"><span></span>
                                        </div>
                                    </li>
                                    <li class="col-md-12">
                                        <label class="col-md-3"><a href="#" title="删除选项" class="removeTitle">x</a></label>
                                        <div class="col-md-8 cl cl1">
                                            <input type="text"  onblur="checkOption()" name="option" style="width: 100%" placeholder="请输入选项名称"><span></span>
                                        </div>
                                    </li>
                                </ul>
                                <div class="modal-right">
                                    <label class="col-md-3">&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                    <div class="col-md-8 cl">
                                        <span class="add-title" style="font-size: 17px;color: rgba(11, 40, 236, 0.79);">+添加选项</span>
                                    </div>
                                </div>
                                <div class="modal-right">
                                    <label class="col-md-3">截至日期：</label>
                                    <div class="col-md-8 cl">
                                        <input type="text" name="endTime" id="endTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"><span style="color: green;">不设置则默认为1小时</span>
                                    </div>
                                </div>
                                <div class="model-right">
                                    <label class="col-md-3">投票类型：</label>
                                    <div class="col-md-8" style="margin-bottom: 10px">
                                        <input type="radio" name="type" value="1" checked>单选 &nbsp;&nbsp;&nbsp;<input type="radio" name="type" value="2">多选
                                        <input type="number" name="types" class="types" min="1"  id="types" onblur="checkTypeNum()" placeholder="请输入具体数量"/>
                                        <span style="font-size: 15px;color: red; display: block;margin-top: 5px;font-weight: bold;"></span>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    &nbsp;&nbsp;&nbsp;
                                </div>
                                <div>
                                    <button class="btn" type="submit">确认添加</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div>
</body>
</html>
