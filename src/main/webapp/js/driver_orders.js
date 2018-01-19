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

            $('#active-driver-order-table tbody > tr').remove();
            $('#active-driver-order-table tbody').append('<tr><td>' + order.id + '</td>'
                                            +'<td>' + order.customerId + '</td>'
                                            +'<td>' + order.begin.x + ', ' + order.begin.y + '</td>'
                                            +'<td>' + order.end.x + ', ' + order.end.y + '</td>'
                                            +'<td>' + order.price + '</td>'
                                            +'<td>' + order.status + '</td>'
                                            +'<td>' + order.rating + '</td>'
                                            +'<td><button onclick="nextStep(' + '&quot;' + order.status + '&quot;' + ', ' + order.id + ');">OK</button></td></tr>');
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
