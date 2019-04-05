   var mymap = L.map('mapid').setView([35.68090, -93.33400], 8);

   var completed = JSON.parse(document.getElementById("map").getAttribute("data-completed"));
   var notCompleted = JSON.parse(document.getElementById("map").getAttribute("data-notCompleted"));

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
      iconAnchor: [22, 45],
      popupAnchor: [-3, -76]
   });

   var i;

   for (i = 0; i < completed.length; i++)
   {
      var mapLocation = completed[i];
      var id = completed[i].waterfallId;
      var name = completed[i].waterfallName;

      var handler = function(j)
      {
        return function()
        {
            window.location.href = "/waterfall/" + j;
        }
      }(id);

      var marker = L.marker([mapLocation.fallLatitude, mapLocation.fallLongitude], {icon: myIcon}).addTo(mymap);
      marker.bindTooltip(name);
      marker.on('click', handler);
   }



    for (i = 0; i < notCompleted.length; i++)
    {
     var mapLocation = notCompleted[i];
     var id = notCompleted[i].waterfallId;
     var name = notCompleted[i].waterfallName;

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

