<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=1024">
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <link href="https://api.mapbox.com/mapbox-gl-js/v2.4.1/mapbox-gl.css" rel="stylesheet">
    <script src="https://api.mapbox.com/mapbox-gl-js/v2.4.1/mapbox-gl.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Weather</title>
    <script>
    //API Keys
    var weatherKey = "<?= $_SERVER["openweathermap"];?>";
    mapboxgl.accessToken = "<?= $_SERVER["mapbox"];?>";



    //Used for map
    var map;
    var usrLat, usrLong;
    var retrievedCoords = false;

    //Weather Units
    var isCelcius = false;

    window.onload = function getLocation() {
        if (navigator.geolocation) {
            retrievedCoords = true;
            navigator.geolocation.getCurrentPosition(displayMap);
        }
        //Verify that enter press clicks zip code button
        let zipBox = document.getElementById('zip-typed');
        zipBox.addEventListener('keyup', function(event) {
            if (event.keyCode == 13) {
                event.preventDefault();
                document.getElementById('zip-button').click();
            }
        });
    }

    function showLocationError() {
        alert('Error getting your location!');
    }

    function zipClicked() {
        let zipField = document.getElementById('zip-typed');
        let zipEntered = zipField.value;
        if (zipEntered.length != 5) {
            alert('Zip code must be 5 numbers!');
            return;
        }
        $('html, body').animate({ scrollTop: 0 }, 'fast');
        getLatLong();
    }

    function displayMap(position) {
        //Set center of map to user location, otherwise default center to middle of USA
        usrLat = retrievedCoords ? position.coords.latitude : 40;
        usrLong = retrievedCoords ? position.coords.longitude : -95;
        map = new mapboxgl.Map({
            container: 'map',
            style: 'mapbox://styles/kwikmatt/cksqkm26y1df617nxyjgk17f8',
            center: [usrLong, usrLat],
            zoom: retrievedCoords ? 11 : 3
        });
        map.on('moveend', () => {
            getZip();
        });
        getZip();
    }

    async function getZip() {
        let lat = map.getCenter().lat;
        let long = map.getCenter().lng;
        let url = 'https://api.openweathermap.org/data/2.5/weather?lat=' + lat + '&lon=' + long + '&appid=214164ec971a2392bc3bd503b9174fff&units=imperial';
        let response = await fetch(url);
        let data = await response.json();
        let pIDs = ['currenttemp', 'weatherdesc', 'feelslike', 'rangetoday', 'currentpres', 'currenthum', 'windspeed'];
        let values = getData(data);
        for (var i = 0; i < values.length; i++) {
            document.getElementById('details-' + pIDs[i]).textContent = values[i];
        }
        let locationHeading = document.getElementById('legend-weather');
        locationHeading.textContent = 'Weather for ' + data.name;
        let weatherImage = document.getElementById('weatherImage');
        weatherImage.setAttribute('src', 'https://openweathermap.org/img/wn/' + data.weather[0].icon + '@2x.png');
    }

    function getData(data) {
        let values = [
            'Current Temperature: ' + (isCelcius ? FtoC(data.main.temp) : (data.main.temp)).toString() + getUnit(),
            'Weather Description: ' + data.weather[0].description.charAt(0).toUpperCase() + data.weather[0].description.substring(1), 
            'Feels Like: ' + (isCelcius ? FtoC(data.main.feels_like) : data.main.feels_like).toString() + getUnit(),
            'Range Today: ' + (isCelcius ? FtoC(data.main.temp_min) : data.main.temp_min).toString() + getUnit() + ' - ' + (isCelcius ? FtoC(data.main.temp_max) : data.main.temp_max).toString() + getUnit(),
            'Current Pressure: ' + data.main.pressure + ' hPa', 
            'Current Humidity: ' + data.main.humidity + '%', 
            'Current Wind Speed: ' + (isCelcius ? miToKm(data.wind.speed) : data.wind.speed).toString() + (isCelcius ? ' kph' : ' mph').toString() + ' @ ' + data.wind.deg + '° (' + getCardinalDirection(data.wind.deg) + ')'
        ];
        return values;
    }

    function FtoC(number) {
        return ((number - 32) * (5.0/9.0)).toFixed(1);
    }

    function miToKm(number) {
        return (number * 1.609344).toFixed(1);
    }

    function changeUnits() {
        isCelcius = !isCelcius;
        getZip();
    }

    function getUnit() {
        return isCelcius ? ' °C' : ' °F';
    }

    async function getLatLong() {
        try {
            let response = await fetch('https://api.openweathermap.org/data/2.5/weather?zip=' + document.getElementById('zip-typed').value + ',us&appid=' + weatherKey);
            let data = await response.json();
            map.setCenter([data.coord.lon, data.coord.lat]);
            getZip();
        } catch (e) {
            alert('Zip code not found. Ensure you entered a US zip code and try again.');
        }
    }

    function getCardinalDirection(degree) {
        if ((degree >= 349 && degree <= 360) || (degree >= 0 && degree <= 11)) return 'N';
        let directions = ['N', 'NNE', 'NE', 'ENE', 'E', 'ESE', 'SE', 'SSE', 'S', 'SSW', 'SW', 'WSW', 'W', 'WNW', 'NW', 'NNW', 'N'];
        let minDegrees = [349, 12, 34, 57, 79, 102, 124, 147, 169, 192, 214, 237, 259, 282, 304, 327];
        let maxDegrees = [11, 33, 56, 78, 101, 123, 146, 168, 191, 213, 236, 258, 281, 303, 326, 348];
        for (var i = 0; i < directions.length; i++) {
            if (degree <= maxDegrees[i] && degree >= minDegrees[i]) {
                return directions[i];
            }
        }
        return 'CLM';
    }
    </script>
</head>

<style>
    @font-face {
        font-family: EverSinceNewYork;
        src: url('KGEverSinceNewYork.ttf');
        font-stretch: semi-expanded;
    }
    body {
        margin: 0;
        padding: 0;
        background-color: rgb(46, 46, 46);
        overflow: hidden;
    }
    .container {
        color: rgb(196, 196, 196);
        font-family: EverSinceNewYork, Verdana, Geneva, Tahoma, sans-serif;
    }
    #map {
        top: 0;
        bottom: 0;
        border: 5px solid black;

    }
    #map, #details {
        position: absolute;
        width: 45%;
        height: 50%;
        margin: 2% 2% 2% 2%;
        border-radius: 20px;
    }
    #details {
        bottom: 0;
        float: right;
        overflow: auto;
        top: 0;
        right: 10px;
        padding: 10px;
        font-size: 24px;
        text-align: center;
    }
    #zip-entry {
        text-align: center;
        padding: 12px 20px;
        margin-left: auto;
        margin-right: auto;
        margin-bottom: 15vh;
        margin-top: 60vh;
        bottom: 0;
        width: 20%;
    }
    fieldset {
        border-radius: 10px;
    }
    #zip-typed {
        font-size: 36px;
        text-align: center;
        width: 10vw;
        background-color: rgb(155, 155, 155);
        border-radius: 5px;
    }
    input[type=button] {
        margin-top: 20px;
        text-align: center;
        font-size: 24px;
        font-family: Verdana, Geneva, Tahoma, sans-serif;
        background-color:rgb(46, 46, 46);
        color:rgb(196, 196, 196);
        border: 2px groove rgb(134, 134, 134);
    }
    input[type=button]:hover {
        background-color:rgb(73, 73, 73);
        cursor: pointer;
    }
    #footer {
        bottom: 0;
        text-align: center;
        font-size: 8px;
        width: 100%;
        position: fixed;
    }
    #footer a {
        color: inherit;
    }
    #fieldset-weather {
        border-color: black;
        border: 5px solid black;
    }
</style>
<body>
    <div class="container">
        <div id="map"></div>
        <div class="info">
            <div id="details">
                <fieldset id="fieldset-weather">
                    <legend id="legend-weather">Weather for ?</legend>
                    <p id="details-currenttemp">Current Temperature: ?</p>
                    <p id="details-weatherdesc">Weather Description: ?</p>
                    <p id="details-feelslike">Feels Like: ?</p>
                    <p id="details-rangetoday">Range Today: ?</p>
                    <p id="details-currentpres">Current Pressure: ?</p>
                    <p id="details-currenthum">Current Humidity: ?</p>
                    <p id="details-windspeed">Current Wind Speed: ?</p>
                    <img id="weatherImage" src="?"/>
                </fieldset>
            </div>
        </div>
        <div id="zip-entry">
            <fieldset>
                <legend>Change Location</legend>
                <h3>Drag the map around or type a zip code below: </h3>
                <br>
                <input type="text" id="zip-typed"/>
                <br>
                <input type="button" id="zip-button" value="Submit" onclick="zipClicked()"/>
                <br>
                <input type="button" id="recenter" value="Your Location" onclick="navigator.geolocation.getCurrentPosition(displayMap)"/>
                <br>
                <input type="button" id="units-button" value="Change Units" onclick="changeUnits()"/>
            </fieldset>
        </div>
        <br><br>
        <p id="footer">
            Note: this website will not work correctly if you do not allow location access. Your latitude and longitude are only sent to an api to retrieve map and weather data, and are not stored anywhere. Please change your location permission to 'allow' then please <a href=".">refresh</a> the page.
            <br>
            Weather data provided by <a href="https://openweathermap.org/" target="_blank">openweathermap</a>. Map data provided by <a href="https://www.mapbox.com/" target="_blank">mapbox</a>. &copy;2021 Matthew Vandenberg. Reuse of this page without consent is prohibited. Thank you for understanding.
        </p>
    </div>
</body>
</html>