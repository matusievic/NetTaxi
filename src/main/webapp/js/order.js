ymaps.ready(init);

var begin, end, length, price;

function init() {
    // order
    $('#drivers').hide();

    // cost per km
    var DELIVERY_TARIFF = 1,
    //min price
        MINIMUM_COST = 3,
        myMap = new ymaps.Map('map', {
            center: [53.899191, 27.553824],
            zoom: 13,
            controls: []
        }),
    // price calculator panel
        routePanelControl = new ymaps.control.RoutePanel({
            options: {
                // add a title
                showHeader: true,
                title: 'Select route'
            }
        }),
        zoomControl = new ymaps.control.ZoomControl({
            options: {
                size: 'small',
                float: 'none',
                position: {
                    bottom: 145,
                    right: 10
                }
            }
        });
    // only car routes allowed
    routePanelControl.routePanel.options.set({
        types: {auto: true}
    });

    myMap.controls.add(routePanelControl).add(zoomControl);

    // get a route link
    routePanelControl.routePanel.getRouteAsync().then(function (route) {

        // routing end handler
        route.model.events.add('requestsuccess', function () {

            var activeRoute = route.getActiveRoute();
            if (activeRoute) {
                // get distance
                length = route.getActiveRoute().properties.get("distance");

                // get a price
                price = calculate(Math.round(length.value / 1000));

                $('#cost').text(length.text + ", " + price);

                end = route.getWayPoints().get(0).geometry.getCoordinates();
                begin = route.getWayPoints().get(1).geometry.getCoordinates();

                $.ajax({
                    url: 'controller',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                            command: 'find_taxidriver',
                            x: begin[0],
                            y: begin[1]
                          },
                    success: function(data) {
                        $('#drivers').show();
                        $('#drivers-table tbody > tr').remove();
                        $.each(data, function (i, element) {
                            $('#drivers-table tbody').append('<tr><td>' + element.id + '</td>' +
                                                             '<td>' + element.name + '</td>' +
                                                             '<td>' + element.surname + '</td>' +
                                                             '<td>' + element.phone + '</td>' +
                                                             '<td>' + element.car.model + '</td>' +
                                                             '<td>' + element.car.number.join('') + '</td>' +
                                                             '<td>' + element.rating + '</td>' +
                                                             '<td>' + length.text + '</td>' +
                                                             '<td>' + price + '</td>' +
                                                             '<td><button onclick="makeOrder(' + element.id + ')" href="#">OK</a></td></tr>');
                        });
                    }
                });
            }

        });

    });
    // price calculator function
    function calculate(routeLength) {
        return Math.max(routeLength * DELIVERY_TARIFF, MINIMUM_COST);
    }
}

function makeOrder(driverId) {
    $.get('controller', {
                         command: 'order_taxi',
                         id: driverId,
                         price: price,
                         begin_x: begin[0],
                         begin_y: begin[1],
                         end_x: end[0],
                         end_y: end[1]
                        }, function(data) {
        document.location.href="/controller?command=display_orders";
    });
}