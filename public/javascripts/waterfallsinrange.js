var lat = parseFloat(document.getElementById("map").getAttribute("data-lat"))
var long = parseFloat(document.getElementById("map").getAttribute("data-long"))
var thisName = document.getElementById("map").getAttribute("data-name")
var mymap = L.map('mapid').setView([lat, long], 9);
var mapInfo = JSON.parse(document.getElementById("map").getAttribute("data-mapInfo"));

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibHJmbHJmIiwiYSI6ImNqdG94bWQwdTQ4Mng0Ym11YW52MWZ5bnEifQ.lZEH9-1FD4H2KYhVZDIe4Q', {
maxZoom: 18,
attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
  '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
  'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
id: 'mapbox.streets'
}).addTo(mymap);

var myIcon = L.icon({
   iconUrl: '/assets/images/greenMapPin.png',
   iconSize: [30, 38],
   iconAnchor: [22, 94],
   popupAnchor: [-3, -76]
});

L.marker([lat, long], {icon: myIcon}).addTo(mymap).bindTooltip(thisName);

   var i;

   for (i = 0; i < mapInfo.length; i++)
   {
      var mapLocation = mapInfo[i];
      var id = mapInfo[i].waterfallId;
      var name = mapInfo[i].waterfallName;

      var handler = function(j)
      {
        return function()
        {
            window.location.href = "/waterfall/" + j;
        }
      }(id);

      var marker = L.marker([mapLocation.fallLatitude, mapLocation.fallLongitude]).addTo(mymap);
      marker.bindTooltip(name);
      marker.on('click', handler);

   }




