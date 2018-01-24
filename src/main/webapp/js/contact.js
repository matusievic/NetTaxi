ymaps.ready(init);

function init() {
    var myMap = new ymaps.Map("contact-map", {
        center: [53.912139, 27.594718],
        zoom: 15,
        controls: []
    });

    myMap.geoObjects
        .add(new ymaps.Placemark([53.912139, 27.594718], {
            balloonContent: 'We are here'
        }, {
            preset: 'islands#circleDotIcon',
            iconColor: '#F7CA18'
        }));
}