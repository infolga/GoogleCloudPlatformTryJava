var textarea;
var Solve;
var error;
$(function () {
    Solve = $('#Solve');
    textarea = $('#soursText');
    error = $('#error');

});

function CalcInput(v) {
    textarea.val(textarea.val() + v);
}

function CalcClear() {
    textarea.val('');
    Solve.html('');
    textarea.removeClass('is-invalid');
    error.html('');
}

function CalcSolve() {

    $.ajax({
        type: 'POST',
        // make sure you respect the same origin policy with this url:
        // http://en.wikipedia.org/wiki/Same_origin_policy
        url: '/demo',
        data: {
            'str': textarea.val()
        },
        success: function (msg) {
            var obj = JSON.parse(msg);
            textarea.removeClass('is-invalid');
            if (obj.solve) {
                Solve.html(obj.solve);
            } else {
                Solve.html('');
            }

            Solve.html(obj.solve);

            if (obj.error) {
                textarea.addClass('is-invalid');
                error.html(obj.error);
            }
        }
    });
}