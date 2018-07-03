<%@page contentType="text/html;charset=UTF-8" language="java"
        pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/view/common/url.jsp" %>
<!DOCTYPE HTML>
<%@include file="IE_lang.jsp" %>
<head>
    <title>管理首页</title>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <%@include file="head.jsp" %>
    <style>
        .layui-body {
            background-color: #f3f3f4;
        }

        .leftChart {
            height: 300px;
        }

        .rightChart {
            height: 300px;
        }

        .body-content {
            padding: 15px;
            background-color: #fff;
            margin: 15px;
        }

        .leftTips > h2, .rightTips > h2 {
            color: orange;
            font-size: 15px;
        }

        .leftTips, .rightTips {
            padding: 0 10px;
        }

        .dynamic.item {
            margin-left: -10px;
            padding: 10px 30px;
        }

        .body-content {
            height: auto;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@include file="top.jsp" %>
    <div class="layui-body">
        <div class="body-content">
            <div class="content">
                <div class="layui-fluid">
                    <div class="indexContent layui-row">
                        <div class="leftCon layui-col-md6">
                            <div id="leftChart" class="leftChart"></div>
                            <div class="leftTips">
                                <h2>最新动态</h2>
                                <div class="dynamic_list">
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="rightCon layui-col-md6">
                            <div class="rightChart" id="rightChart"></div>
                            <div class="rightTips">
                                <h2>最新动态</h2>
                                <div class="dynamic_list">
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                    <div class="dynamic item">
                                        <span style="width: 100%">
                                            <span>1.</span>
                                                <span>商家开放沟通会直播啦!</span>
                                                <span style="float: right;color: #999">08-11</span>
                                        </span>
                                        <hr/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
<%@include file="js.jsp" %>
<script src="${staticFilePath}resource/plugins/echarts-2.2.7/build/dist/echarts.js"></script>
<script>
    //JavaScript代码区域
    layui.use(['element', 'laypage', 'layer'], function () {
        var element = layui.element;
        var page = layui.laypage;
        var layer = layui.layer;
    });

    //引入echart.js
    require.config({
        paths: {
            echarts: '${staticFilePath}resource/plugins/echarts-2.2.7/build/dist/'
        }
    });
    require([
            'echarts',
            'echarts/chart/pie'
        ],
        function (ec) {
            var leftEle = document.getElementById('leftChart');
            var rightEle = document.getElementById('rightChart');
            var leftChart = ec.init(leftEle);
            var rightChart = ec.init(rightEle);
            var leftOption = {
                title: {
                    text: '商户后台统计数据',
                    subtext: '',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['外卖', '团购', '自助点餐', '广告', '注册数量']
                },
                toolbox: {
                    show: false,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1548
                                }
                            }
                        },
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: 335, name: '外卖'},
                            {value: 310, name: '团购'},
                            {value: 234, name: '自助点餐'},
                            {value: 135, name: '广告'},
                            {value: 1548, name: '注册数量'}
                        ]
                    }
                ]
            }
            var rightOption = {
                title: {
                    text: '用户后台统计数据',
                    subtext: '',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['新用户注册量', '外卖购买量', '自助点餐购买量', '团购购买量', '短信发送量']
                },
                toolbox: {
                    show: false,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        magicType: {
                            show: true,
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1548
                                }
                            }
                        },
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                calculable: true,
                series: [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: [
                            {value: 335, name: '新用户注册量'},
                            {value: 310, name: '外卖购买量'},
                            {value: 234, name: '自助点餐购买量'},
                            {value: 135, name: '团购购买量'},
                            {value: 1548, name: '短信发送量'}
                        ]
                    }
                ]
            }
            leftChart.setOption(leftOption);
            rightChart.setOption(rightOption);
        }
    )
</script>
</body>
</html>
