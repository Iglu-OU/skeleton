jQuery(function ($) {

    $("#request-form").on('submit', function (e) {
        e.preventDefault();

        var $this = $(this);
        var data = JSON.stringify({
            methodName: $this.data("method"),
            requestObject: $this.serializeObject(),
        });

        $.ajax({
            type: "POST",
            url: $this.attr('action'),
            data: data,
            contentType: 'application/json;charset=UTF-8',
        }).done(function (rs) {
            var response = JSON.stringify(rs, null, 2);
            $("#response").val(response);
        }).fail(function (arg) {
            var response = JSON.stringify(arg.responseJSON, null, 2);
            $("#response").val(response);
        });

    });

});
