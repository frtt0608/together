<template>
  <div>
    <div id="map" style="width:700px;height:300px;"></div>
    <v-flex>{{test.props.lat}}</v-flex>
  </div>
</template>

<script>
import axios from 'axios'
import VueAxios from 'vue-axios'
import index from '@/views/index'
import Map from  '@/components/Map'
import test from  '@/views/test'
export default {
  name: 'dmap',
  components:{
    index,
    Map,
    test
  },
  data() {
    return {
      view: false,
      error: '',
      test
    }
  },
  props:{
    lat:{type:String},
    lon:{type:String},

  },
  methods: {
    get(name){
      console.log(name.city_name)
    },
    setMap(){
      navigator.geolocation.getCurrentPosition((position) =>{
        // this.lat=position.coords.latitude;
        // this.lon=position.coords.longitude;
        // console.log(position.coords.latitude)
        var container = document.getElementById('map');
        var mapOptions = {
        // center: new daum.maps.LatLng(36.3549777,127.2961516),
        center: new daum.maps.LatLng( position.coords.latitude , position.coords.longitude),
        level: 5 //지도의 레벨(확대, 축소 정도)
        }
        var map = new daum.maps.Map(container, mapOptions);

        var geocoder = new kakao.maps.services.Geocoder();
        var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
            infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
        // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
        searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
            searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                    detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                    var content = '<div class="bAddr">' + detailAddr + '</div>';
                    // 마커를 클릭한 위치에 표시합니다
                    marker.setPosition(mouseEvent.latLng);
                    marker.setMap(map);
                    // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                    infowindow.setContent(content);
                    infowindow.open(map, marker);
                }
            });
        });
        // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'idle', function() {
            searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        });
        function searchAddrFromCoords(coords, callback) {
            // 좌표로 행정동 주소 정보를 요청합니다
            geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
        }
        function searchDetailAddrFromCoords(coords, callback) {
            // 좌표로 법정동 상세 주소 정보를 요청합니다
            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }
        // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
        function displayCenterInfo(result, status) {
            if (status === kakao.maps.services.Status.OK) {
              var info
                 for(var i = 0; i < result.length; i++) {
                   if(result[i].region_type === 'H') {
                     info = result[i].address_name;
                     // console.log(info)
                   }
                 }
              }
              console.log(test.props.lat)
              test.props.lat = info
              console.log(test.props.lat)
          }
          this.get(position);

      }, function(error) {
        console.error(error);
        }, {
          enableHighAccuracy: false,
          maximumAge: 0,
          timeout: Infinity
          });

    },
  },
  created() {
    this.setMap();
  },
  mounted(){
    this.get(this)
  }
};
</script>

<style scoped>

</style>
