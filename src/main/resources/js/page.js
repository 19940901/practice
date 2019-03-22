$(function () {
    // 获取评论列表
    function getComment(postId, pageIndex, order) {
        var _ctx = $("meta[name='ctx']").attr("content");
        // 获取 CSRF Token
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: _ctx + '/comments',
            type: 'GET',
            data: {
                "async": true,
                "postId": postId,
                "pageIndex": pageIndex,
                "order": order
            },
            beforeSend: function (request) {
                request.setRequestHeader(header, token); // 添加  CSRF Token
            },
            success: function (data) {
                $("#comment-wrapper").html(data);
            },
            error: function () {
                layer.msg("出现错误，请尝试刷新页面!", {icon: 2, anim: 6});
            }
        });
    };
    //切换评论排序规则
    $(document).on('click', '.tabs-order .new-sort', function () {
        var pageIndex = $(this).attr("pageIndex");
        getComment(postId, pageIndex, "new");
    });
    $(document).on('click', '.tabs-order .hot-sort', function () {
        var pageIndex = $(this).attr("pageIndex");
        getComment(postId, pageIndex, "hot");
    });
    //分页获取评论列表
    $(document).on('click', '.tcd-number', function () {
        var order = $(this).parents(".paging-box").attr("data-order");
        if ($(this).hasClass('current')) {
            return false;
        }
        var pageIndex = $(this).attr("pageIndex");
        getComment(postId, pageIndex, order);
    });
    //跳转到指定的页号
    $(document).on('keydown', '.jump-page-size', function (event) {
        var max = parseInt($(this).attr("max"));
        var pageIndex = parseInt($(this).val());
        var order = $('.paging-box').attr('data-order');
        if (event.keyCode == "13") {//keyCode=13是回车键
            if (pageIndex == "" || pageIndex == null) {
                return false;
            }
            if (!/^\d+$/.test(pageIndex)) {
                pageIndex = 1;
            }
            if (pageIndex < 1) {
                pageIndex = 1;
            }
            if (pageIndex > max) {
                pageIndex = max;
            }
            getComment(postId, pageIndex, order);
        }
    })
// 初始化 博客评论列表
    getComment(postId, 1, "new");
})