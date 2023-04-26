// if ($("#payment_radio_1").prop("checked", true)) {
//     $("payment-post-2").hide();
// }
$('input[name=flexRadioDefault]').prop('checked', false);
$("#payment-post-1").hide();
$("#payment-post-2").hide();
$(document).ready(function() {
    $("input:radio").click(function() {
        if ($(this).val() == "payment_radio_1") {
            $("#payment-post-1").show();
            $("#payment-post-2").hide();
        } else {
            $("#payment-post-1").hide();
            $("#payment-post-2").show();
        }
    });
});