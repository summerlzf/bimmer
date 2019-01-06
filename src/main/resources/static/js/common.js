
$.common = {

    initForm: function (box, data) {
        if(!data) {
            box.find('input[name]').val('');
            return;
        }
        for(var k in data) {
            box.find('input[name="' + k + '"]').val(data[k]);
        }
    }
};