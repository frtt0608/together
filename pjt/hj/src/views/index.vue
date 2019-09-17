<template>
  <div v-bind:style="{ backgroundImage: `url(${backgroundImage})`}">
    <!-- <div v-for="weather in index.props.weather.type">{{weather.info}}</div> -->
    <Header></Header>
    <div class="font_Gaegu" style="height:200px;">
      <v-btn @mouseover="weather_btn = !weather_btn" style="background-color: #ffffff; height: 40px; background-color: rgba( 255, 255, 255, 0 );" flat=flase> {{index.props.weather.type[0].info}}</v-btn>
      <p style="font-size:20px; margin-left:30px;">{{index.props.now}} <br>{{index.props.city}}</p>
      <p v-for="weather in index.props.weather.type"  v-if="weather_btn">
          {{weather.info}} {{ weather.date }} {{weather.time}}
      </p>
    </div>
    <div style="height:400px;">
      <v-flex text-xs-center>
        <span class="text-shadow display-4">
          <div class="font_indei" style="padding-top:20px; transform: rotate(-20deg)"><p style="color:black; font-size:80px;">South Korea ,</p><p style="font-size: 90px; color:black">Travel !</p></div>
        </span>
      </v-flex>
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import Mainimage from '@/components/Mainimage'
import index from '@/views/index'
import { posix } from 'path';
export default {
  name: 'index',
  components : {
    Header,
    Footer,
    Mainimage,
  },
  props: {
    lat: '',
    lon: '',
    city: '',
    weather : [
    ],
    now: ''
  },
  data () {
    return {
      backgroundImage  : 'https://www.zoomtravelinsurance.com.au/assets/upload/south-korea-travel-insurance-best.jpg',
      index,
      weather_btn: false
    }
  },
  // props: {
  //   lat: {type:String},
  //   lon: {type:String}
  // },
  methods: {
    searchWeather() {
      // console.log(index.props.lat+' '+ index.props.lon);
      const key = "ed959cca83201f4e7425e15ef3ff3bbe"
      const K="http://api.openweathermap.org/data/2.5/forecast?lat=" + index.props.lat + "&lon=" + index.props.lon + "&appid="
      const url = K + key
      var d= new Date(); 		 				 // 출력형태 Tue Feb 07 2017 23:25:32 GMT+0900 (KST)
      // console.log(d.getHours())
      // console.log(String(d.getHours()))
      var st_date = new Date().toISOString().substr(0, 10).replace('T', ' ');
      index.props.now = d.toISOString().substr(0, 11).replace('T', ' ');
      // console.log(st_date)
      // console.log(url)
      axios.get(url)
        .then((response) => {
            console.log(response)
            // console.log(response.data.city.name)
            // index.weather.city=response.data.city.name
            index.props.city=response.data.city.name

            response.data.list.forEach( function(weather) {
              // console.log(Number(weather.dt_txt.substr(-8).substr(0,2)))
              if(weather.dt_txt.substr(0,10) === st_date && d.getHours() > Number(weather.dt_txt.substr(-8).substr(0,2))){}
              else{
                if (Number(weather.dt_txt.substr(-8).substr(0,2)) === 3 || Number(weather.dt_txt.substr(-8).substr(0,2)) === 9
                    || Number(weather.dt_txt.substr(-8).substr(0,2)) === 15 || Number(weather.dt_txt.substr(-8).substr(0,2)) === 21){}
                else {
                  // console.log(weather.dt_txt.substr(0,10),weather.dt_txt.substr(-8).substr(0,2)+'시', weather.weather[0].description)
                  (index.props.weather.type).push({
                    date : weather.dt_txt.substr(5,6),
                    time: weather.dt_txt.substr(-8).substr(0,2)+'시',
                    info: weather.weather[0].description});
                }
              }
            });
            console.log(index.props.weather.type)
        })
    },
    getLocation() {
      if (navigator.geolocation) { // GPS를 지원하면
        navigator.geolocation.getCurrentPosition(function(position) {
          // console.log(position.coords.latitude + ' ' + position.coords.longitude);
          index.props.lat=position.coords.latitude;
          index.props.lon=position.coords.longitude;
          // console.log(index.lat+' '+ index.lon);
          // console.log(index.methods)
          index.methods.searchWeather();
        }, function(error) {
          console.error(error);
          }, {
            enableHighAccuracy: false,
            maximumAge: 0,
            timeout: Infinity
            });

      }
      else {
        console.log('GPS를 지원하지 않습니다');
      }
    },
  },
  created() {
    this.getLocation();
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >

</style>
