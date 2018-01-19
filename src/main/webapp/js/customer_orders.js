$(document).ready(function() {
    getActiveOrder();
    setInterval('getActiveOrder()', 10000);
});

function getActiveOrder() {
    $.ajax({
        type: 'GET',
        url: 'controller?command=get_active_customer_order',
        dataType: 'json',
        success: function(data) {
            var order = data[0],
                driver = data[1];

            var cancelButtonText = '';

            if (order.status === 'NEW' || order.status === 'WAITING') {
                cancelButtonText = '<td><button onclick="cancelOrder(' + order.id + ');">' + $('#cancel-label').val() + '</button></td>';
            }

            if (order === null) {
                $('#active-customer-order').hide();
            } else {
                $('#active-customer-order').show();
            }

            $('#active-customer-order-table tbody > tr').remove();
            $('#active-customer-order-table tbody').append('<tr><td>' + order.id + '</td>'
                +'<td>' + driver.name + '</td>'
                +'<td>' + driver.surname + '</td>'
                +'<td>' + driver.phone + '</td>'
                +'<td>' + driver.car.model + '</td>'
                +'<td>' + driver.car.number.join('') + '</td>'
                +'<td>' + order.begin.x + ', ' + order.begin.y + '</td>'
                +'<td>' + order.end.x + ', ' + order.end.y + '</td>'
                +'<td>' + order.price + '</td>'
                +'<td>' + order.status + '</td>'
                +'<td>' + order.rating + '</td>'
                + cancelButtonText
                +'</tr>');
        }
    });
}

function cancelOrder(orderId) {
    $.get("controller", {command: 'cancel_order', id: orderId});
    $('#active-customer-order').hide();
}