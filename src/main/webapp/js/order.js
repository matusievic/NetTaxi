ymaps.ready(init);

var begin, end;

function init() {
    // order
    $('#drivers').hide();

    var myMap = new ymaps.Map('map', {
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
                var length = route.getActiveRoute().properties.get("distance");

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
                        $.each(data, function (i, driver) {
                            var price = calculate(Math.round(length.value / 1000), driver.tariff);
                            $('#drivers-table tbody').append('<tr><td>' + driver.id + '</td>' +
                                                             '<td>' + driver.name + '</td>' +
                                                             '<td>' + driver.surname + '</td>' +
                                                             '<td>' + driver.phone + '</td>' +
                                                             '<td>' + driver.car.model + '</td>' +
                                                             '<td>' + driver.car.number.join('') + '</td>' +
                                                             '<td>' + driver.rating + '</td>' +
                                                             '<td>' + length.text + '</td>' +
                                                             '<td>' + price + '</td>' +
                                                             '<td><button onclick="makeOrder(' + driver.id + ', ' + price + ');">OK</button></td></tr>');
                        });
                    }
                });
            }

        });

    });
    // price calculator function
    function calculate(routeLength, tariff) {
        const MINIMUM_COST = tariff;
        return Math.max(routeLength * tariff, MINIMUM_COST);
    }
}

function makeOrder(driverId, price) {
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