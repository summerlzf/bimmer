
$.common = {

    initForm: function (box, data) {
        if(!data) {
            box.find('input[name]').val('');
            return;
        }
        for(var k in data) {
            box.find('input[name="' + k + '"]').val(data[k]);
        }
    },

    pagin: function (box, totalCount, totalPage, pageNum, pageSize, fn) {
        box.pagination(totalCount, {
            callback: function (pageIndex) {
                var page = pageIndex + 1;
                if(page >= 1 && page <= totalPage) {
                    fn(page);
                }
            },
            items_per_page: pageSize || 10,
            num_display_entries: 10,
            current_page: (pageNum || 1) - 1,
            num_edge_entries: 0,
            // link_to: "#a",
            prev_text: "上一页",
            next_text: "下一页",
            ellipse_text: "...",
            prev_show_always: true,
            next_show_always: true,
            renderer: "defaultRenderer",
            show_if_single_page: true,
            load_first_page: false
        });
    }
};