<template>
  <div v-bind:style="{ backgroundImage: `url(${backgroundImage})`}">
    <!-- <div v-for="weather in index.props.weather.type">{{weather.info}}</div> -->
    <Header></Header>

    <!-- 예전 날씨 -->
    <!-- <div class="font_Gaegu" style="height:200px;">
      <v-btn
        @mouseover="weather_btn = !weather_btn"
        style="background-color: #ffffff; height: 40px; background-color: rgba( 255, 255, 255, 0 );"
        flat="flase"
      >{{index.props.weather.type[0].info}}</v-btn>
      <p style="font-size:20px; margin-left:30px;">
        {{index.props.now}}
        <br />
        {{index.props.city}}
      </p>
      <p
        v-for="weather in index.props.weather.type"
        v-if="weather_btn"
      >{{weather.info}} {{ weather.date }} {{weather.time}}</p>
    </div>-->

    <!-- 날씨...  -->
    <v-container grid-list-xs style="height:600px; margin-left:50px">
      <v-layout style="width:400px">
        <v-flex>
          <v-card class="mx-auto" max-width="400" style="opacity:0.9">
            <v-list-tile two-line>
              <v-list-tile-content>
                <v-list-tile-main class="headline">{{index.props.city}}</v-list-tile-main>
                <v-list-tile-sub class="grey--text text-right">{{index.props.now}}</v-list-tile-sub>
              </v-list-tile-content>
            </v-list-tile>

            <v-card-text>
              <v-layout align-center>
                <v-flex xs6 display-3>{{index.props.weather.type[0].temp}}&deg;C</v-flex>
                <v-flex xs6>
                  <v-img :src="index.props.weather.type[0].icon" alt="Sunny image" width="92"></v-img>
                </v-flex>
              </v-layout>
            </v-card-text>

            <v-list class="transparent">
              <v-list-tile v-for="forecast in index.props.forecast" :key="forecast.date">
                <v-list-tile-title>{{ forecast.date }} {{forecast.time}}</v-list-tile-title>
                <v-list-tile-subtitle class="text-right">{{ forecast.temp }}&deg;C</v-list-tile-subtitle>
                <v-list-tile-icon>
                  <v-img :src="forecast.icon" alt="Sunny image" width="30"></v-img>
                </v-list-tile-icon>
              </v-list-tile>
            </v-list>
          </v-card>
        </v-flex>

        <v-flex>
          <div style="height:300px; width:800px; padding-top:150px; padding-left:150px">
            <v-flex text-xs-center>
              <span class="text-shadow display-4">
                <div class="font_indei" style="padding-top:20px; transform: rotate(-20deg)">
                  <p style="color:black; font-size:80px;">South Korea ,</p>
                  <p style="font-size: 90px; color:black">Travel !</p>
                </div>
              </span>
            </v-flex>
          </div>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import Mainimage from '@/components/Mainimage'
import index from '@/views/index'
import { posix } from 'path'
export default {
  name: 'index',
  components: {
    Header,
    Footer,
    Mainimage
  },
  props: {
    lat: '',
    lon: '',
    city: '',
    weather: [
    ],
    forecast: [
    ],
    now: '',
    w_icon: ''
  },
  data () {
    return {
      backgroundImage: 'https://www.zoomtravelinsurance.com.au/assets/upload/south-korea-travel-insurance-best.jpg',
      index,
      weather_btn: false,
      dialog: false
    }
  },
  methods: {
    searchWeather () {
      // console.log(index.props.lat+' '+ index.props.lon);
      const key = 'ed959cca83201f4e7425e15ef3ff3bbe'
      const K = 'http://api.openweathermap.org/data/2.5/forecast?lat=' + index.props.lat + '&lon=' + index.props.lon + '&appid='
      const url = K + key

      // 출력형태 Tue Feb 07 2017 23:25:32 GMT+0900 (KST)
      var d = new Date()
      // console.log(d.getHours())
      // console.log(String(d.getHours()))
      var st_date = new Date().toISOString().substr(0, 10).replace('T', ' ')
      index.props.now = d.toISOString().substr(0, 11).replace('T', ' ')
      // console.log(st_date)
      // console.log(url)
      axios.get(url)
        .then((response) => {
          console.log(response)
          // console.log(response.data.city.name)
          // index.weather.city=response.data.city.name
          index.props.city = response.data.city.name

          response.data.list.forEach(function (weather) {
            // console.log(Number(weather.dt_txt.substr(-8).substr(0,2)))
            if (weather.dt_txt.substr(0, 10) === st_date && d.getHours() > Number(weather.dt_txt.substr(-8).substr(0, 2))) { } else {
              if (Number(weather.dt_txt.substr(-8).substr(0, 2)) === 3 || Number(weather.dt_txt.substr(-8).substr(0, 2)) === 9 ||
                Number(weather.dt_txt.substr(-8).substr(0, 2)) === 15 || Number(weather.dt_txt.substr(-8).substr(0, 2)) === 21) { } else {
                // console.log(weather.dt_txt.substr(0,10),weather.dt_txt.substr(-8).substr(0,2)+'시', weather.weather[0].description)
                (index.props.weather.type).push({
                  date: weather.dt_txt.substr(5, 6),
                  time: weather.dt_txt.substr(-8).substr(0, 2) + '시',
                  info: weather.weather[0].description,
                  temp: Math.floor(weather.main.temp - 273.15),
                  icon: 'http://openweathermap.org/img/wn/' + weather.weather[0].icon + '@2x.png'
                })
              }
            }
          })
          console.log(index.props.weather.type)
          index.props.forecast = index.props.weather.type.slice(0, 5)
          console.log(index.props.forecast)

          // // icon 가져오기
          // index.props.w_icon = 'http://openweathermap.org/img/wn/' + index.props.weather.type[0].icon + '@2x.png'
        })
    },
    getLocation () {
      if (navigator.geolocation) { // GPS를 지원하면
        navigator.geolocation.getCurrentPosition(function (position) {
          // console.log(position.coords.latitude + ' ' + position.coords.longitude);
          index.props.lat = position.coords.latitude
          index.props.lon = position.coords.longitude
          // console.log(index.lat+' '+ index.lon);
          // console.log(index.methods)
          index.methods.searchWeather()
        }, function (error) {
          console.error(error)
        }, {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
        })
      } else {
        console.log('GPS를 지원하지 않습니다')
      }
    }
  },
  created () {
    this.getLocation()
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >
</style>
