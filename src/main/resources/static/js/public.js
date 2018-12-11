/**
 * require jQuery
 */
(function(window, $) {

    $.ajaxSetup({
        // 清除IE浏览器下ajax缓存
        cache: false,

        // 给所有ajax请求添加一个complete函数
        complete: function(xhr, status) {
            // 通过xhr取得响应头
            var REDIRECT = xhr.getResponseHeader("REDIRECT");
            // 如果响应头中包含 REDIRECT 则说明是拦截器返回的
            if (REDIRECT == "REDIRECT") {
                var win = window;
                while (win != win.top) {
                    win = win.top;
                }
                // 重定向
                win.location.href = xhr.getResponseHeader("CONTEXTPATH");
            }
        }
    });

})(window, jQuery);