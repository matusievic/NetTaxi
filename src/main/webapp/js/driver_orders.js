$(document).ready(function() {
    getActiveOrder();
    setInterval('getActiveOrder()', 10000);
});

function getActiveOrder() {
    $.ajax({
        type: 'GET',
        url: 'controller?command=get_active_taxidriver_order',
        dataType: 'json',
        success: function(order) {
            if (order === null) {
                $('#active-driver-order').hide();
            } else {
                $('#active-driver-order').show();
            }


            var idLabel = $('#id-field').val();
            var customerLabel = $('#customer-field').val();
            var beginLabel = $('#begin-field').val();
            var endLabel = $('#end-field').val();
            var priceLabel = $('#price-field').val();
            var statusLabel = $('#status-field').val();
            var ratingLabel = $('#rating-field').val();

            $('#active-driver-order-table tbody > tr').remove();
            $('#active-driver-order-table tbody').append('<tr><td data-label="' + idLabel + '">' + order.id + '</td>'
                                            +'<td data-label="' + customerLabel + '"><a href="/controller?command=display_customer&id=' + order.customerId + '">' + order.customerId + '</a></td>'
                                            +'<td data-label="' + beginLabel + '">' + order.begin.x + ', ' + order.begin.y + '</td>'
                                            +'<td data-label="' + endLabel + '">' + order.end.x + ', ' + order.end.y + '</td>'
                                            +'<td data-label="' + priceLabel + '">' + order.price + '</td>'
                                            +'<td data-label="' + statusLabel + '">' + order.status + '</td>'
                                            +'<td data-label="' + ratingLabel + '">' + order.rating + '</td>'
                                            +'<td><button onclick="nextStep(' + '&quot;' + order.status + '&quot;' + ', ' + order.id + ');">' + getActivity(order.status) + '</button></td></tr>');
        }
    });
}

function nextStep(status, orderId) {
    switch (status) {
        case 'NEW':
            $.get('controller', {command: 'choose_order', id: orderId});
            break;
        case 'WAITING':
            $.get('controller', {command: 'accept_order', id: orderId});
            break;
        case 'ACCEPTED':
            $.get('controller', {command: 'finish_order', id: orderId});
            break;
    }
    getActiveOrder();
}

function getActivity(status) {
    switch (status) {
        case 'NEW': return $('#choose-field').val();
        case 'WAITING': return $('#accept-field').val();
        case 'ACCEPTED': return $('#finish-field').val();
    }
}