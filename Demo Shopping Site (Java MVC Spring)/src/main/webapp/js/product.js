$(".amount-product-spinner").spinner({ min: 0, value: 0, step: 1 });
$('.ui-spinner a.ui-spinner-button').css('display', 'none');
$('.stepUp').click(function() {
    $(this).closest('.amount-product').find(".amount-product-spinner").spinner("stepUp");
});

$('.stepDown').click(function() {
    $(this).closest('.amount-product').find(".amount-product-spinner").spinner("stepDown");
});