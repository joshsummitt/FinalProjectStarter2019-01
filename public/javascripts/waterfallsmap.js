var mymap = L.map('mapid').setView([35.12148, -92.93410], 8);

var mapInfo = JSON.parse(document.getElementById("map").getAttribute("data-mapInfo"));

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibHJmbHJmIiwiYSI6ImNqdG94bWQwdTQ4Mng0Ym11YW52MWZ5bnEifQ.lZEH9-1FD4H2KYhVZDIe4Q', {
maxZoom: 18,
attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
  '<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
  'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
id: 'mapbox.streets'
}).addTo(mymap);

var i;

for (i = 0; i < mapInfo.length; i++)
{
   var mapLocation = mapInfo[i];
   L.marker([mapLocation.fallLatitude, mapLocation.fallLongitude]).addTo(mymap);
}

L.circle([51.508, -0.11], {
color: 'red',
fillColor: '#f03',
fillOpacity: 0.5,
radius: 1000
}).addTo(mymap);

L.polygon([
[51.509, -0.08],
[51.503, -0.06],
[51.51, -0.047]
]).addTo(mymap);

function onClick(e) {
    //alert(this.getLatLng());
    window.location.href = "/waterfall/1";
}