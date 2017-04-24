jQuery(function ($) {

    $("#request-form").on('submit', function (e) {
        e.preventDefault();

        var $this = $(this);
        var data = JSON.stringify($this.serializeObject());

        $.ajax({
            type: "POST",
            url: $this.attr('action'),
            data: data,
            contentType: 'application/json;charset=UTF-8',
        }).then(function (rs) {
            var response = JSON.stringify(rs, null, 2);
            $("#response").val(response);
        });

    });

});
