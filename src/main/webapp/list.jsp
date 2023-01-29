<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>评论区</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="assets/css/layui.css">
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>欢迎您来到评论区！</legend>
</fieldset>
<div class="layui-row" style="margin-left: 150px;margin-right: 150px">
    <div class="layui-col-md12">
        <div class="layui-row grid-demo">
            <div class="layui-col-md1">
                <div class="grid-demo grid-demo-bg1">
                    <div class="grid-demo" style="">
                        <img style="height: 70px;width: 70px;" src="./assets/image/youke.png"/>
                    </div>
                </div>
            </div>
            <div class="layui-col-md11">
                <div class="grid-demo grid-demo-bg2">
                    <div class="grid-demo grid-demo-bg1"style="">
                        <div style="font-size: 30px">
                            <p>${user_id}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-col-md12">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>请点击下方您需要的选项</legend>
                </fieldset>
            </div>
            <div class="layui-col-md12">
                <div class="grid-demo grid-demo-bg3">
                    <div class="layui-tab" lay-filter="test">
                        <ul class="layui-tab-title">
                            <li class="layui-this" lay-id="11">所有话题</li>
                            <li lay-id="22">我的话题</li>
                            <li lay-id="33">添加话题</li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item" id="contentOne">

                            </div>

                            <div class="layui-tab-item" id="contentTwo" >

                            </div>
                            <div class="layui-tab-item">
                                <form class="layui-form" action="" onsubmit="return false">
                                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                                        <legend>请添加您的话题</legend>
                                    </fieldset>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">账号</label>
                                        <div class="layui-input-block">
                                            <input type="text" id="addUser" name="kuchl" value="${user_id}" readonly required  lay-verify="required" placeholder="请输入输入框内容" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                    <div class="layui-form-item layui-form-text">
                                        <label class="layui-form-label">请输入话题内容</label>
                                        <div class="layui-input-block">
                                            <textarea id="addHuaTiContent" name="krlba" placeholder="请输入内容" class="layui-textarea"></textarea>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">选择图片1</label>
                                        <div class="layui-input-block">
                                            <input type="file" id="imageOne" onchange="changeImage1(this)" class="layui-btn" accept="image/*">
                                            <img id="onChangeImage1" style="height: 9%;width: 9%;"/>

                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <label class="layui-form-label">选择图片2</label>
                                        <div class="layui-input-block">
                                            <input type="file" id="imageTwo" onchange="changeImage2(this)" class="layui-btn" accept="image/*">
                                            <img id="onChangeImage2" style="height: 9%;width: 9%;"/>
                                        </div>
                                    </div>
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <button id="btnUp" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                                            <button id="btnCancel" type="reset" class="layui-btn layui-btn-primary">清空</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


        </div>
    </div>

</div>

</div>



<script src="assets/layui.js"></script>
<script src="assets/jquery-3.6.0.min.js"></script>
<script>


    //当前登录用户的账户
    console.log("当前登录用户的账户："+${user_id});
    let userID=${user_id};

    //进入页面自动执行，将所有评论显示出来
    $(function (){
        allReview();
    });

    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块

        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);

        element.on('tab(test)', function(elem){
            location.hash = 'test='+ $(this).attr('lay-id');
        });

        $(".layui-tab-title").click(function (title) {
            if(title.target.innerText=="所有话题"){
                allReview();
            }else if(title.target.innerText=="我的话题"){
                myReview();
            }else{
                addReview();
            };
        });

    });



    //触发所有评论点击事件，将所有话题循环出来
    function allReview(){
        console.log("你触发了所有话题");
        //所以话题
        //显示所有话题
        $.ajax({
            url: 'review-all-list',
            type: 'get',
            dataType: 'json',
            success: function (res) {
                let str ="";
                //先将元素对应清空
                $("#contentOne").empty();
                //列表返回在data集合中
                layui.each(res.data, function (i, item) {
                    //这里遍历数据
                    str=
                        "<div class='layui-row'>"+
                        "<div class='layui-col-md12' style='margin-top: 20px;background-color: #f8f8f8'>"+
                        "<div class='layui-row grid-demo' >"+
                        "<div class='layui-col-md1'>"+
                        "<div class='grid-demo grid-demo-bg1' style=''>"+
                        "<img style='width: 60px;height: 60px' src='./assets/image/youke.png'/>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md4'>"+
                        "<div class='grid-demo grid-demo-bg2' style=''>"+
                        "<p id='viewUid"+i+"' style='font-size: 25px'>2000000</p>"+
                        "<p id='viewTime"+i+"' style='font-size: 18px;padding-top: 10px'>2022</p>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md7'>"+
                        "<i class='layui-icon layui-icon-heart-fill' id='dianZanA"+i+"' type='button' onclick='dianZanA("+item.pid+","+i+")' style='font-size: 30px; color: #f30843;display: none'></i>"+
                        "<i class='layui-icon layui-icon-heart' id='dianZanB"+i+"' type='button' onclick='dianZanB("+item.pid+","+i+")' style='font-size: 30px; color: #1f1e1e;display: block'></i>"+
                        "<p id='viewGoodCounts"+i+"'>100000</p>"+
                        "</div>"+
                        "<div class='layui-col-md12'>"+
                        "<div class='grid-demo grid-demo-bg3' style=''>"+
                        "<div class='layui-col-md8'>"+
                        "<div class='grid-demo grid-demo-bg2' style='word-wrap:break-word;word-break:break-all;'>"+
                        "<p id='viewMessage"+i+"' style='font-size: 30px;padding-top: 10px'>你好!</p>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md4'>"+
                        "<div class='grid-demo grid-demo-bg2' style=''></div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md12'>"+
                        "<div class='grid-demo grid-demo-bg3' style=''>"+
                        "<div class='layui-col-md3'>"+
                        "<div id='viewImageOne"+i+"' class='grid-demo grid-demo-bg2' style=''>"+
                        "<img style='padding-top: 10px;max-width: 90%;max-height:90%' />"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md3'>"+
                        "<div id='viewImageTwo"+i+"' class='grid-demo grid-demo-bg2' style=''>"+
                        "<img style='padding-top: 10px;max-width: 90%;max-height:90%' />"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md6'></div>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md12'>"+
                        "<div class='grid-demo grid-demo-bg3' style=''>"+
                        "<div class='layui-col-md10'>"+
                        "<div id='viewResponse"+i+"' class='grid-demo grid-demo-bg2' style='word-wrap:break-word;word-break:break-all;padding-top: 20px'>"+
                        "<p  style='font-size: 20px;padding-top: 10px'>202010530942:</p>"+
                        "<p  style='font-size: 20px;padding-top: 10px'>这是评论</p>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md2'>"+
                        "<div class='grid-demo grid-demo-bg2'>" +
                        "<button id='tianJiaPingLun"+i+"'>"+
                        "<i class='layui-icon layui-icon-add-1' style='font-size: 50px; color: #1f1e1e;'></i>"+
                        "</button>"+
                        "<p style='word-wrap:break-word;word-break:break-all;'>添加评论</p>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>";
                    $("#contentOne").append(str);
                    $("#viewUid"+i).html(item.uid);
                    $("#viewTime"+i).html(item.time);
                    $("#viewMessage"+i).html(item.message);

                    //图片一
                    let binaryOne="";
                    const bytesOne=new Uint8Array(item.imageOne);
                    const lenOne=bytesOne.byteLength;
                    //图片二
                    let binaryTwo="";
                    const bytesTwo=new Uint8Array(item.imageTwo);
                    const lenTwo=bytesTwo.byteLength;
                    //图片展示
                    if(lenTwo==0 && lenOne>0){
                        for(let s=0;s<lenOne;s++){
                            binaryOne+=String.fromCharCode(bytesOne[s]);
                        }
                        let imageBase64One=window.btoa(binaryOne);
                        console.log(imageBase64One);
                        $("#viewImageOne"+i).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64One+"'/>";
                        $("#viewImageOne"+i).append(image1);
                        $("#viewImageTwo"+i).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%'/>";
                        $("#viewImageTwo"+i).append(image2);

                    }else if(lenOne==0 && lenTwo>0){
                        for(let s=0;s<lenTwo;s++){
                            binaryTwo+=String.fromCharCode(bytesTwo[s]);
                        }
                        let imageBase64Two=window.btoa(binaryTwo);
                        console.log(imageBase64Two);
                        $("#viewImageOne"+i).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' />";
                        $("#viewImageOne"+i).append(image1);
                        $("#viewImageTwo"+i).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64Two+"'/>";
                        $("#viewImageTwo"+i).append(image2);

                    }else if(lenOne==0 && lenTwo==0){
                        $("#viewImageOne"+i).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' />";
                        $("#viewImageOne"+i).append(image1);
                        $("#viewImageTwo"+i).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%'/>";
                        $("#viewImageTwo"+i).append(image2);
                    } else{
                        //第一张
                        for(let s=0;s<lenOne;s++){
                            binaryOne+=String.fromCharCode(bytesOne[s]);
                        }
                        let imageBase64One=window.btoa(binaryOne);
                        console.log(imageBase64One);
                        $("#viewImageOne"+i).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64One+"'/>";
                        $("#viewImageOne"+i).append(image1);

                        //第二张
                        for(let s=0;s<lenTwo;s++){
                            binaryTwo+=String.fromCharCode(bytesTwo[s]);
                        }
                        let imageBase64Two=window.btoa(binaryTwo);
                        console.log(imageBase64Two);
                        $("#viewImageTwo"+i).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64Two+"'/>";
                        $("#viewImageTwo"+i).append(image2);
                    }


                    //添加评论的弹窗
                    $("#tianJiaPingLun"+i).click(function () {
                        layer.open({
                            type: 2,
                            title: '添加评论',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['30%', '50%'],
                            content: 'go-add-response?pid='+item.pid //iframe的url
                        });
                    });

                    //展示对应话题的评论
                    $.ajax({
                        url:"list-review-response",
                        type:"get",
                        dataType:"json",
                        data:{"pid":item.pid},
                        success:function (res) {
                            let responseSpace="";
                            $("#viewResponse"+i).empty();
                            $.each(res.reviewResponse,function (x,y) {
                                responseSpace=
                                    "<p style='padding-top: 5px;'>"+y.u_id+":&nbsp;&nbsp;"+y.r_response+"</p>";

                                $("#viewResponse"+i).append(responseSpace);
                            });
                        }
                    });

                    //点赞功能
                    //加载点赞的数量
                    $.ajax({
                        url:"get-count-good",
                        type:"get",
                        dataType:"json",
                        data:{"pid":item.pid},
                        success:function (res) {
                            $("#viewGoodCounts"+i).empty();
                            $("#viewGoodCounts"+i).html("&nbsp;&nbsp"+res.countGoods);
                        }
                    });
                    //查询当前点赞状态
                    $.ajax({
                        url:"inquire-good",
                        type:"get",
                        data:{"pid":item.pid,"uid":userID},
                        dataType:"json",
                        success:function (res) {
                            if(res.code==0){
                                $("#dianZanA"+i).attr("style","font-size: 30px; color: #f30843;display:none");
                                $("#dianZanB"+i).attr("style","font-size: 30px; color: #1f1e1e;display:bloke");
                                $.ajax({
                                    url:"get-count-good",
                                    type:"get",
                                    dataType:"json",
                                    data:{"pid":item.pid},
                                    success:function (res) {
                                        $("#viewGoodCounts"+i).empty();
                                        $("#viewGoodCounts"+i).html("&nbsp;&nbsp"+res.countGoods);
                                    }
                                });
                            }else{
                                $("#dianZanA"+i).attr("style","font-size: 30px; color: #f30843;display:bloke");
                                $("#dianZanB"+i).attr("style","font-size: 30px; color: #1f1e1e;display:none");
                                $.ajax({
                                    url:"get-count-good",
                                    type:"get",
                                    dataType:"json",
                                    data:{"pid":item.pid},
                                    success:function (res) {
                                        $("#viewGoodCounts"+i).empty();
                                        $("#viewGoodCounts"+i).html("&nbsp;&nbsp"+res.countGoods);
                                    }
                                });
                            }
                        }
                    });

                });
            }
        });
    };
    //触发点赞
    function dianZanB(pid,i){
        $.ajax({
            url:"add-good",
            type:"post",
            data:{"pid":pid,"uid":userID},
            dataType:"json",
            success:function (res) {
                if(res.code==111){
                    layer.msg("点赞成功");
                }else {
                    layer.msg("点赞失败");
                }

                //查询是否能够被点赞的方法
                dianZanCondition(pid,i);
            }
        });
    };
    //删除点赞
    function dianZanA(pid,i){
        $.ajax({
            url:"delete-good",
            type:"post",
            data:{"pid":pid,"uid":userID},
            dataType:"json",
            success:function (res) {
                if(res.code==123){
                    layer.msg("取消成功");
                }else {
                    layer.msg("取消失败");
                }


                //查询是否能够被点赞的方法
                dianZanCondition(pid,i);
            }
        });
    };
    //查询话题点赞的状态
    function dianZanCondition(pid,i){
        $.ajax({
            url:"inquire-good",
            type:"get",
            data:{"pid":pid,"uid":userID},
            dataType:"json",
            success:function (res) {
                if(res.code==0){
                    $("#dianZanA"+i).attr("style","font-size: 30px; color: #f30843;display:none");
                    $("#dianZanB"+i).attr("style","font-size: 30px; color: #1f1e1e;display:bloke");
                    $.ajax({
                        url:"get-count-good",
                        type:"get",
                        dataType:"json",
                        data:{"pid":pid},
                        success:function (res) {
                            $("#viewGoodCounts"+i).empty();
                            $("#viewGoodCounts"+i).html("&nbsp;&nbsp"+res.countGoods);
                        }
                    });
                }else{
                    $("#dianZanA"+i).attr("style","font-size: 30px; color: #f30843;display:bloke");
                    $("#dianZanB"+i).attr("style","font-size: 30px; color: #1f1e1e;display:none");
                    $.ajax({
                        url:"get-count-good",
                        type:"get",
                        dataType:"json",
                        data:{"pid":pid},
                        success:function (res) {
                            $("#viewGoodCounts"+i).empty();
                            $("#viewGoodCounts"+i).html("&nbsp;&nbsp"+res.countGoods);
                        }
                    });
                }
            }
        });
    };


    //触发我的话题点击事件，将我的话题循环出来
    function myReview(){
        console.log("你触发了我的话题");
        console.log(userID);
        //登录用户的历史话题
        $.ajax({
            url: 'get-my-review',
            type: 'get',
            dataType: 'json',
            data:{"uid":userID},
            success: function (res) {
                let strMy ="";
                //先将元素对应清空
                $("#contentTwo").empty();
                //列表返回在data集合中
                layui.each(res.data, function (m, n) {
                    //这里遍历数据
                    strMy=
                        "<div class='layui-row'>"+
                        "<div class='layui-col-md12' style='margin-top: 20px;background-color: #faf8f8'>"+
                        "<div class='layui-row grid-demo' >"+
                        "<div class='layui-col-md1'>"+
                        "<div class='grid-demo grid-demo-bg1' style=''>"+
                        "<img style='width: 60px;height: 60px' src='./assets/image/youke.png'/>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md4'>"+
                        "<div class='grid-demo grid-demo-bg2' style=''>"+
                        "<p id='viewUidMy"+m+"' style='font-size: 25px'>2000000</p>"+
                        "<p id='viewTimeMy"+m+"' style='font-size: 18px;padding-top: 10px'>2022</p>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md7'>"+
                        "<i class='layui-icon layui-icon-heart-fill' style='font-size: 30px; color: #f30843;'></i>"+
                        "<p id='viewGoodCountsMy"+m+"'>"+100000+"</p>"+
                        "</div>"+
                        "<div class='layui-col-md12'>"+
                        "<div class='grid-demo grid-demo-bg3' style=''>"+
                        "<div class='layui-col-md8'>"+
                        "<div class='grid-demo grid-demo-bg2' style='word-wrap:break-word;word-break:break-all;'>"+
                        "<p id='viewMessageMy"+m+"' style='font-size: 30px;padding-top: 10px'>你好!</p>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md4'>"+
                        "<div class='grid-demo grid-demo-bg2' style=''></div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md12'>"+
                        "<div class='grid-demo grid-demo-bg3' style=''>"+
                        "<div class='layui-col-md3'>"+
                        "<div id='viewImageOneMy"+m+"' class='grid-demo grid-demo-bg2' style=''>"+
                        "<img style='padding-top: 10px;max-width: 90%;max-height:90%' src='./assets/images/002.jpg'/>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md3'>"+
                        "<div id='viewImageTwoMy"+m+"' class='grid-demo grid-demo-bg2' style=''>"+
                        "<img style='padding-top: 10px;max-width: 90%;max-height:90%' src='./assets/image/tu3.png'/>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md6'></div>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md12'>"+
                        "<div class='grid-demo grid-demo-bg3' style=''>"+
                        "<div class='layui-col-md10'>"+
                        "<div id='viewResponseMy"+m+"' class='grid-demo grid-demo-bg2' style='word-wrap:break-word;word-break:break-all;'>"+
                        "<p style='font-size: 20px;padding-top: 10px'>202010530942:这是评论</p>"+
                        "</div>"+
                        "</div>"+
                        "<div class='layui-col-md2'>"+
                        "<div class='grid-demo grid-demo-bg2'>" +
                        "<button id='shanChuPingLunMy"+m+"' onclick='shanChu("+n.pid+","+m+")'>"+
                        "<i class='layui-icon layui-icon-delete' style='font-size: 50px; color: #1f1e1e;'></i>"+
                        "</button>"+
                        "<p style='word-wrap:break-word;word-break:break-all;'>删除此话题</p>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "</div>";
                    $("#contentTwo").append(strMy);
                    $("#viewUidMy"+m).html(n.uid);
                    $("#viewTimeMy"+m).html(n.time);
                    $("#viewMessageMy"+m).html(n.message);

                    //图片一
                    let binaryOne="";
                    const bytesOne=new Uint8Array(n.imageOne);
                    const lenOne=bytesOne.byteLength;
                    //图片二
                    let binaryTwo="";
                    const bytesTwo=new Uint8Array(n.imageTwo);
                    const lenTwo=bytesTwo.byteLength;
                    //图片展示
                    if(lenTwo==0 && lenOne>0){
                        for(let s=0;s<lenOne;s++){
                            binaryOne+=String.fromCharCode(bytesOne[s]);
                        }
                        let imageBase64One=window.btoa(binaryOne);
                        console.log(imageBase64One);
                        $("#viewImageOneMy"+m).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64One+"'/>";
                        $("#viewImageOneMy"+m).append(image1);
                        $("#viewImageTwoMy"+m).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%'/>";
                        $("#viewImageTwoMy"+m).append(image2);

                    }else if(lenOne==0 && lenTwo>0){
                        for(let s=0;s<lenTwo;s++){
                            binaryTwo+=String.fromCharCode(bytesTwo[s]);
                        }
                        let imageBase64Two=window.btoa(binaryTwo);
                        console.log(imageBase64Two);
                        $("#viewImageOneMy"+m).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' />";
                        $("#viewImageOneMy"+m).append(image1);
                        $("#viewImageTwoMy"+m).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64Two+"'/>";
                        $("#viewImageTwoMy"+m).append(image2);

                    }else if(lenOne==0 && lenTwo==0){
                        $("#viewImageOneMy"+m).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' />";
                        $("#viewImageOneMy"+m).append(image1);
                        $("#viewImageTwoMy"+m).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%'/>";
                        $("#viewImageTwoMy"+m).append(image2);
                    } else{
                        //第一张
                        for(let s=0;s<lenOne;s++){
                            binaryOne+=String.fromCharCode(bytesOne[s]);
                        }
                        let imageBase64One=window.btoa(binaryOne);
                        console.log(imageBase64One);
                        $("#viewImageOneMy"+m).empty();
                        let image1="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64One+"'/>";
                        $("#viewImageOneMy"+m).append(image1);

                        //第二张
                        for(let s=0;s<lenTwo;s++){
                            binaryTwo+=String.fromCharCode(bytesTwo[s]);
                        }
                        let imageBase64Two=window.btoa(binaryTwo);
                        console.log(imageBase64Two);
                        $("#viewImageTwoMy"+m).empty();
                        let image2="<img style='padding-top: 10px;max-width: 60%;max-height:60%' src='data:image/jpg;base64,"+imageBase64Two+"'/>";
                        $("#viewImageTwoMy"+m).append(image2);
                    }

                    //展示对应话题的评论
                    $.ajax({
                        url:"list-review-response",
                        type:"get",
                        dataType:"json",
                        data:{"pid":n.pid},
                        success:function (res) {
                            let responseSpace="";
                            $("#viewResponseMy"+m).empty();
                            $.each(res.reviewResponse,function (x,y) {
                                responseSpace=
                                    "<p style='padding-top: 5px;'>"+y.u_id+":&nbsp;&nbsp;"+y.r_response+"</p>";

                                $("#viewResponseMy"+m).append(responseSpace);
                            });
                        }
                    });

                    //加载点赞的数量
                    $.ajax({
                        url:"get-count-good",
                        type:"get",
                        dataType:"json",
                        data:{"pid":n.pid},
                        success:function (res) {
                            $("#viewGoodCountsMy"+m).empty();
                            $("#viewGoodCountsMy"+m).html("&nbsp;&nbsp;"+res.countGoods);
                        }
                    });

                });

            }
        });
    };
    //触发删除话题
    function shanChu(pid,m){
        $.ajax({
            url:"delete-my-review",
            type:"post",
            data:{"pid":pid,"uid":userID},
            dataType:"json",
            success:function (res) {
                if(res.code==111){
                    layer.msg("删除成功");
                }else if(res.code==222){
                    layer.msg("话题删除失败");
                }else if(res.code==333){
                    layer.msg("点赞删除失败");
                }else if(res.code==444){
                    layer.msg("评论删除失败");
                }else {
                    layer.msg("出错啦，请联系管理人员");
                };
                //触发我的话题点击事件，刷新页面
                myReview();
            }
        });
    };

    //将选中的图片显示出来
    function changeImage1(obj){
        const file=obj.files[0];
        let reader=new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function (){
            $("#onChangeImage1").attr("src",this.result);
        }
    }
    function changeImage2(obj){
        const file=obj.files[0];
        let reader=new FileReader();
        reader.readAsDataURL(file);
        reader.onload=function (){
            $("#onChangeImage2").attr("src",this.result);
        }
    }
    function addReview(){

        //提交想要添加的内容
        $("#btnUp").on('click',function () {
            let formData=new FormData();
            formData.append("imageOne",$("#imageOne")[0].files[0]);
            formData.append("imageTwo",$("#imageTwo")[0].files[0]);

            let user=$("#addUser").val();
            let huaTiContent=$("#addHuaTiContent").val();
            formData.append("user",user);
            formData.append("huaTiContent",huaTiContent);

            $.ajax({
                url:"add_review",
                type:"post",
                data:formData,
                contentType:false,
                processData:false,
                success:function (res) {
                    if(res.code==111){
                        layer.msg("话题添加成功了，请点击我的话题查看");

                    }else if(res.code) {
                        layer.msg("话题添加失败了")
                    }else {
                        layer.msg("图片转化失败啦！！！")
                    }
                }
            });





        });

        //清空选择的内容
        $("#btnCancel").click(function () {
            $("#addHuaTiContent").empty();
            $("#imageOne").empty();
            $("#imageTwo").empty();
        });
    }









</script>
</body>
</html>
