$(document).ready(function() {
    getActiveOrder();
    setInterval('getActiveOrder()', 10000);
});

function getActiveOrder() {
    $.ajax({
        type: 'GET',
        url: 'controller?command=get_active_customer_order',
        dataType: 'json',
        success: function(order) {
            if (order === null) {
                $('#active-customer-order').hide();
            } else {
                $('#active-customer-order').show();
            }

            var cancelButtonText = '';

            if (order.status === 'NEW' || order.status === 'WAITING') {
                cancelButtonText = '<td><button onclick="cancelOrder(' + order.id + ');">' + $('#cancel-field').val() + '</button></td>';
            }

            if (order === null) {
                $('#active-customer-order').hide();
            } else {
                $('#active-customer-order').show();
            }

            var idLabel = $('#id-field').val();
            var driverLabel = $('#driver-field').val();
            var beginLabel = $('#begin-field').val();
            var endLabel = $('#end-field').val();
            var priceLabel = $('#price-field').val();
            var statusLabel = $('#status-field').val();
            var ratingLabel = $('#rating-field').val();

            $('#active-customer-order-table tbody > tr').remove();
            $('#active-customer-order-table tbody').append('<tr><td data-label="' + idLabel + '">' + order.id + '</td>'
                +'<td data-label="' + driverLabel + '"><a href="/controller?command=display_taxidriver&id=' + order.taxiDriverId + '">' + order.taxiDriverId + '</a></td>'
                +'<td data-label="' + beginLabel + '">' + order.begin.x + ', ' + order.begin.y + '</td>'
                +'<td data-label="' + endLabel + '">' + order.end.x + ', ' + order.end.y + '</td>'
                +'<td data-label="' + priceLabel + '">' + order.price + '</td>'
                +'<td data-label="' + statusLabel + '">' + order.status + '</td>'
                +'<td data-label="' + ratingLabel + '">' + order.rating + '</td>'
                + cancelButtonText
                +'</tr>');
        }
    });
}

function cancelOrder(orderId) {
    $.get("controller", {command: 'cancel_order', id: orderId});
    $('#active-customer-order').hide();
}