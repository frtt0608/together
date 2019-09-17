<template>
    <div v-if="this.$session.get('lo')" class="travel">
      <h1 class="my-5 text-xs-center">
        {{this.$session.get('lo').nickname}}님은 현재 {{Traveling.props.city}}에서 여행중이시네요!</h1>
      <v-container>
        <v-layout wrap align-center>
        <v-flex wrap align-center style="margin:px; 0px;"><Map></Map>
          <v-card
            color="#385F73"
            dark>
            <v-card-text class="white--text">
            </v-card-text>
            <v-card-actions>
              <h3>{{Traveling.props.city}}에서 현재 {{this.login_users.length}}명의 여행자가 있네요! 여행자들과 함께 여행해보시겠어요?</h3>
              <!-- <v-btn text @click="">함께 여행하기!</v-btn> -->
              <withmodal></withmodal>
            </v-card-actions>
          </v-card>
        </v-flex>
        <v-flex wrap align-center>
          <p>{{Traveling.props.city}} 지역의 테마 별 여행지 추천을 도와드릴까요?
          <v-btn color="pink" dark v-show="click_thema==false" @click="click_thema=true">테마추천</v-btn>
          <v-menu v-if="click_thema" offset-y
             transition="slide-x-transition"
             bottom
             right>
             <v-btn slot="activator" color="#FF9800" dark class="deep-orange">
               {{type}}
             </v-btn>
              <v-list>
                <v-list-tile
                  v-for="(item, index) in this.types"
                  :key="index"
                  @click="type_click(index)">
                  <v-list-tile-title>{{ item[0]}}</v-list-tile-title>
                </v-list-tile>
              </v-list>
          </v-menu>
          <v-menu v-if="type !=='대분류 선택'"
            transition="slide-y-transition"
            bottom>
            <v-btn slot="activator" color="#607D8B" dark class="purple">
              {{thema1}}
            </v-btn>
            <v-list>
              <v-list-tile v-for="( index, item ) in this.types[thema_index][1]" :key="index" @click="thema1_click(item)">
                <v-list-tile-title>{{ item }}</v-list-tile-title>
              </v-list-tile>
            </v-list>
          </v-menu>
          <v-btn icon><v-icon v-if="click_thema" @click="informLocate(Traveling.props.city,type,thema1);">search</v-icon></v-btn>
          </p>

          <!-- <v-btn color="pink" dark @click="setWeather()"><v-icon left>brightness_5</v-icon>날씨</v-btn> -->

          <p>미세먼지에 따라 {{Traveling.props.city}}의 관광지를 추천해 드릴까요?
          <v-btn color="indigo" @click="informDust(Traveling.props.city)" dark><v-icon left>cloud</v-icon>미세먼지</v-btn></p>

          <p>
            {{Traveling.props.city}}의 병원 위치를 알려드릴까요?
            <v-btn color="success" dark @click="informHospital(index.props.lat, index.props.lon)">
              <v-icon left>add_to_queue</v-icon>병원
            </v-btn>
          </p>

        </v-flex>
        </v-layout>
      </v-container>

      <h1 v-if="mise_click" class="my-5 text-xs-center">
        현재 {{Traveling.props.city}}의 미세먼지는 {{this.mise_inform['pm10_s']}}, 초미세먼지는 {{this.mise_inform['pm25_s']}} 이네요! </h1>

      <h1 v-if="hospital_click" class="my-5 text-xs-center">근처의 병원을 알려드리겠습니다</h1>
      <div v-if="hospital_click" id="hospimap" style="width:100%; height:350px;"></div>

      <template class="font_notable image" v-if="this.trip_inform.length">
        <paginated-list :list-array="this.trip_inform" />
      </template>

      <div style="height: 400px;" v-if="load === true">
        <div class="semipolar-spinner" :style="spinnerStyle" style="margin:100px auto;">
        <div class="ring"></div>
        <div class="ring"></div>
        <div class="ring"></div>
        <div class="ring"></div>
        <div class="ring"></div>
      </div>
    </div>
    </div>
    <div v-else>
      <h1 class="my-5 text-xs-center">여행중 페이지를 이용하려면 로그인을 하세요</h1>
    </div>
</div>
</template>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=b199d31d68f1934a33c9bf1ab864e840"></script>

<script>
import Map from '@/components/Map'
import Geolocation from '@/components/Geolocation'
import Traveling from '@/components/Traveling'
import PaginatedList from '@/views/PaginatedList'
import withmodal from '@/components/withmodal'
import index from '@/views/index'
  export default {
    name: 'Traveling',
    components : {
      Map,
      Geolocation,
      Traveling,
      PaginatedList,
      withmodal,
      index,
    },
    props:{
      city : {type:String},
      lat: { type: Number },
      lon: { type: Number },
      weather: [],
      hospital: []
    },
    data() {
      return{
        index,
        city: '대전',
        areas: {
          1: '서울', 2: '인천', 3: '대전', 4: '대구', 5: '광주', 6: '부산', 7: '울산', 8: '세종',
          31: '경기', 32: '강원', 33: '충북', 34: '충남', 35: '경북', 36: '경남', 37: '전북', 38: '전남', 39: '제주'
        },
        types: {
          12 : ['관광지',{'자연관광지':'A0101','관광자원': 'A0102','역사관광지': 'A0201','휴양관광지': 'A0202','체험관광지': 'A0203','산업관광지': 'A0204','건축/조형물': 'A0205'}] ,
          14: ['문화시설',{'문화시설':'A0206'}],
          15: ['행사/공연/축제',{'축제': 'A0207','공연/행사': 'A0208'}],
          25: ['여행코스',{'가족코스' : 'C0112', '나홀로코스': 'C0113', '힐링코스': 'C0114', '도보코스': 'C0115', '캠핑코스': 'C0116','맛코스': 'C0117'}],
          28: ['레포츠',{'레포츠소개': 'A0301', '육상레포츠': 'A0302', '수상레포츠': 'A0303', '항공레포츠': 'A0304','복합레포츠': 'A0305'}],
          32: ['숙박',{'숙박시설': 'B0201'}],
          38: ['쇼핑',{'쇼핑': 'A0401'}],
          39: ['음식점',{'음식점': 'A0502'}]
        },
        click_thema: false,
        click_type: '대분류 선택',
        trip_inform: {type:Object},
        click_thema1: '소분류 선택',
        type:'대분류 선택',
        thema1:'소분류 선택',
        thema_index:'',
        load: false,
        mise_inform : [],
        Traveling,
        mise_click: false,
        login_users:[],
        hospital_click: false,
        weather_click: false,
        weather_out: false,
        weather_in: false
      }
    },
    methods : {
      setuser() {
        if (this.$session.get('lo')) {
          this.login_users = []
          var city_name = Traveling.props.city
          console.log(Traveling.props.city)
          var spring_url = 'http://192.168.31.84:8080/api/user/locate'
          axios.post(spring_url, {
            location: city_name,
            loginuser_pk: 0,
            user_id: 0
          }).then((res) => {
            console.log(res.data)
            for (var [key, login_user] of Object.entries(res.data)) {
              this.login_users.push(login_user)
            }
          })
        }
      },
      type_click(index){
      console.log(index)
      this.type=this.types[index][0]
      console.log(this.type)
      this.thema_index=index
      this.thema1="소분류 선택"
      },
      thema1_click(item){
        console.log(item)
        this.thema1=item
      },
      informLocate(area,type,thema1){
        this.hospital_click= false
        this.mise_click= false
        this.load=true
        this.trip_inform=[]
        console.log(area,type)
        // var url='http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey='
        // var key='rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D'
        // var URL= url+key+'&MobileOS=ETC&MobileApp=AppTest&areaCode='
        //지역 선택할 경우
        if (area !== ''){
          for(let [key, value] of Object.entries(this.areas)){
            //선택한 지역 분류data에서 번호 불러오기
            if(area === value){
              var spring_url = 'http://192.168.31.84:8080/api/post/areacode/'+key
              axios.get(spring_url).then((res)=>{
                console.log(1)
                console.log(res)
                //테마 선택안하고 지역만 선택할 경우
                if(type === '대분류 선택'){
                  this.trip_inform=res.data
                  this.load=false
                  console.log(this.trip_inform)
                  PaginatedList.props.pageNum.type=0
                } else if(type !== '대분류 선택'){ //지역선택후 테마 선택
                  for(let [key1, value] of Object.entries(this.types)){
                    //테마 분류된 테이블중에서 선택한 테마의 번호를 가져오기 위함
                    if(type === value[0]){
                      console.log(type)
                      var list_restype=[] //리스트에 저장해서 뿌려줄 data에 저장
                      for ( var [index,res_type] of Object.entries(res.data) ){
                        //선택한 테마 번호와 data 테마id와 같은 것 불러오기
                        if (res_type.content_type_id === Number(key1)){
                          if(thema1 === '소분류 선택')
                            {list_restype.push(res_type)
                            this.load=false} //리스트 저장
                          else if(thema1 !== '소분류 선택'){
                            console.log(this.types[key1][1][thema1])
                            if(this.types[key1][1][thema1] === res_type.cat2){
                              list_restype.push(res_type)
                              this.load=false
                            }
                          }
                        }
                      }//리스트저장for문
                      console.log(list_restype)
                      this.trip_inform=list_restype //뿌려줄 vue data에 저장한다.
                      this.load=false
                      PaginatedList.props.pageNum.type=0
                  }//if(type === value)
                }//types for문
              }//테마선택한 else if
            })//axios
              .catch(err => {
                console.log(err);
              })//.catch
            }//if(area === value)
          }//area for문
        } else if(type === '대분류 선택'){ //지역 선택안할 경우, 선택하라고 알람
          alert('테마를 선택 안하셨네요! 지역기반으로 추천해드려요~')
        }
      },
      informDust(city){
        console.log(city)
        //pm25Grade1h = { 1: '좋음', 2: '보통', 3: '나쁨', 4: '매우나쁨'} //1시간마다 미세먼지,
        var table = { 0: '매우좋음', 1: '좋음', 2: '보통', 3: '나쁨', 4: '매우나쁨'}
        var data = [0,1,2,3,4]
        console.log(city)
        this.click_thema = false
        this.hospital_click= false
        this.load=true
        this.type='대분류 선택'
        var url = 'http://192.168.31.84:8080/api/FindDust/' + city
        axios.get(url).then((res)=>{
          console.log(res.data)
          console.log(res.data.length)
          var num10 = 0
          var num25 = 0
          for(var [index,informs] of Object.entries(res.data)){
            num10 += informs.pm25Grade1h
            num25 += informs.pm10Grade1h
          }
          num10 = num10 / res.data.length
          num25 = num25 / res.data.length
          console.log('미세',num10,'초미세',num25)
          var near10 = 0;
          var abs10 = 0;
          var min10 = 100;
          var near25 = 0;
          var abs25 = 0;
          var min25 = 100;
          for (var i = 0; i < data.length; i++){
            abs10 = ((data[i] - num10) < 0) ? -(data[i] - num10) : (data[i] - num10);
            if (abs10 < min10){
              min10 = abs10; //MIN
              near10 = data[i] //near : 가까운값
            }
          }
          for (var i = 0; i < data.length; i++){
            abs25 = ((data[i] - num25) < 0) ? -(data[i] - num25) : (data[i] - num25);
            if (abs25 < min25){
              min25 = abs25; //MIN
              near25 = data[i] //near : 가까운값
            }
          }
          console.log(table[near10],table[near25])
          this.mise_inform['pm10'] = near10
          this.mise_inform['pm10_s'] = table[near10]
          this.mise_inform['pm25'] = near25
          this.mise_inform['pm25_s'] = table[near25]

          console.log(this.mise_inform)
          this.givedust(city);
        })//axios 미세먼지
      },//informDust함수
      givedust(area){
        this.mise_click= true
        this.load=true
        this.trip_inform=[]
        var cat3_lists_nomise = {A01010100 : '국립공원', A01010200 :'도립공원', A01010300 : '군립공원', A01010400: '산', A01010500: '자연생태관광지', A01010600: '자연휴양림', A01010700: '수목원',
        A01010800: '폭포', A01010900: '계곡', A01011000: '약수터' ,A01011100: '해안절경', A01011200: '해수욕장', A01011300: '섬', A01011400: '항구/포구', A01011500: '어촌',
        A01011600: '등대', A01011700: '호수', A01011800: '강', A01011900: '동굴', A01020100: '희귀동.식물', A01020200: '기암괴석',A02010100: '고궁', A02010200: '성',A02010300:'문',
        A02010400: '고택', A02010500: '생가', A02010600: '민속마을',A02010700: '유적지/사적지',A02010800: '사찰',A02010900:'종교성지',A02011000:'안보관광',A02020100:'유원지',
        A02020200: '관광단지', A02020600: '테마공원', A02020700: '공원',
        A02030100: '농.산.어촌 체험',A02030200: '전통체험', A02030300: '산사체험', A02030400: '이색체험',A02030500: '관광농원', A02030600: '이색거리', A02040100: '제철소',A02040200:'조선소',
        A02040300: '공단',A02040400:'발전소',A02040500: '광산',A02040600:'식음료',A02040700:'화학/금속',A02040800:'기타',A02040900:'전자/반도체',A02041000:'자동차',A02050100:'다리/대교',A02050200:'기념탑/기념비/전망대',
        A02050300:'분수',A02050400:'동상',A02050500:'터널',A02050600:'유명건물',A02060600:'공연장',
        A02070100:'문화관광축제',A02070200:'일반축제',
        A02080100:'전통공연',A02081200:'스포츠경기',A02081300:'기타행사',C01120001:'가족코스',C01130001:'나홀로코스',C01140001:'힐링코스',C01150001:'도보코스',C01160001:'캠핑코스',A03010100:'육상레포츠',
        A03010200:'수상레포츠',A03010300:'항공레포츠',A03020100:'스포츠센터',A03020200:'수련시설',A03020300:'경기장',A03020400:'인라인(실내 인라인 포함)',A03020500:'자전거하이킹',A03020600:'카트',A03020700:'골프',
        A03020800:'경마',A03020900:'경륜',A03021100:'승마',A03021200:'스키/스노보드',A03021300:'스케이트',A03021400:'썰매장',A03021500:'수렵장',A03021600:'사격장',A03021700:'야영장,오토캠핑장',
        A03021800:'암벽등반',A03021900:'빙벽등반',A03022000:'서바이벌게임',A03022100:'ATV',A03022200:'MTB',A03022300:'오프로드',A03022400:'번지점프',A03022500:'자동차경주',A03022600:'스키(보드) 렌탈샵',A03022700:'트래킹',
        A03030100:'윈드서핑/제트스키',A03030200:'카약/카누',A03030300:'요트',A03030400:'스노쿨링/스킨스쿠버다이빙',A03030500:'민물낚시',A03030600:'바다낚시',A03030700:'수영',A03030800:'래프팅',A03040100:'스카이다이빙',
        A03040200:'초경량비행',A03040300:'헹글라이딩/패러글라이딩',A03040400:'열기구',A03050100:'복합 레포츠',A04010100:'5일장',A04010200:'상설시장'}
        var cat3_lists_mise = {A02020300: '온천/욕장/스파', A02020400: '이색찜질방', A02020500: '헬스투어', A02020800: '유람선/잠수함관광',A02060100:'박물관',A02060200:'기념관',A02060300:'전시관',A02060400:'컨벤션센터',
        A02060500:'미술관/화랑',A02060700:'문화원',A02060800:'외국문화원',A02060900:'도서관',A02061000:'대형서점',A02061100:'문화전수시설',A02061200:'영화관',A02061300:'어학당',A02061400:'학교',
        A02080200:'연극',A02080300:'뮤지컬',A02080400:'오페라',A02080500:'전시회',A02080600:'박람회',A02080700:'컨벤션',A02080800:'무용',A02080900:'클래식음악회',A02081000:'대중콘서트',
        A02081100:'영화',C01170001:'맛코스',A03021000:'카지노',B02010100:'관광호텔',B02010200:'수상관광호텔',B02010300:'전통호텔',B02010400:'가족호텔',B02010500:'콘도미니엄',
        B02010600:'유스호스텔',B02010700:'펜션',B02010800:'여관',B02010900:'모텔',B02011000:'민박',B02011100:'게스트하우스',B02011200:'홈스테이',B02011300:'서비스드레지던스',B02011400:'의료관광호텔',B02011500:'소형호텔',
        B02011600:'한옥스테이',A04010300:'백화점',A04010400:'면세점',A04010500:'할인매장',A04010600:'전문상가',A04010700:'공예,공방',A04010800:'관광기념품점',A04010900:'특산물판매점',
        A05020100:'한식',A05020200:'서양식',A05020300:'일식',A05020400:'중식',A05020500:'아시아식',A05020600:'패밀리레스토랑',A05020700:'이색음식점',A05020800:'채식전문점',A05020900:'바/까페',A05021000:'클럽'}
        if (area !== ''){
          for(let [key, value] of Object.entries(this.areas)){
            //선택한 지역 분류data에서 번호 불러오기
            if(area === value){
              var spring_url = 'http://192.168.31.84:8080/api/post/areacode/'+key
              axios.get(spring_url).then((res)=>{
                console.log('미세지역돌리기')
                console.log(res)
                for(var [index,informs] of Object.entries(res.data)){
                if(this.mise_inform['pm10'] <= 2){
                   if ( (informs.cat3) in cat3_lists_nomise){
                     // console.log(informs)
                     this.trip_inform.push(informs)
                   }
                 }else{
                   if((informs.cat3) in cat3_lists_mise){
                     this.trip_inform.push(informs)
                   }
                 }
                }
                this.load=false
              });

            }//area==value if
          }// area for문 key value
        }//area if
      },
      informHospital(lat, lon) {
        this.weather_click = false
        this.hospital_click = true
        this.mise_click = false
        this.click_thema = false
        this.load = true
        // this.type = '대분류 선택'
        // alert(index.props.lat+" "+index.props.lon)
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function (position) {
            // console.log(position.coords.latitude + ' ' + position.coords.longitude);
            // console.log(index.lat+' '+ index.lon);
            // console.log(index.methods)
            var url = 'http://192.168.31.84:8080/api/FindHospital/' + position.coords.longitude + '/' + position.coords.latitude
            console.log(url)
            axios.get(url).then((res) => {
              console.log(res.data)
              res.data.forEach(hospi => {
                // console.log(hospi)
                (Traveling.props.hospital.type).push({
                  xpos: hospi.xpos,
                  ypos: hospi.ypos,
                  name: hospi.yadmNm
                })
              });

              // 지도 생성
              var mapContainer = document.getElementById('hospimap'), // 지도를 표시할 div
                mapOption = {
                  center: new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude), // 지도의 중심좌표
                  level: 6 // 지도의 확대 레벨
                };

              var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

              var positions = []
              // 마커를 표시할 위치와 title 객체 배열입니다
              for (var i = 0; i < Traveling.props.hospital.type.length; i++) {
                (positions).push({
                  title: Traveling.props.hospital.type[i].name,
                  latlng: new kakao.maps.LatLng(Traveling.props.hospital.type[i].ypos, Traveling.props.hospital.type[i].xpos),
                  text: Traveling.props.hospital.type[i].name
                })
              }

              console.log(positions)
              // 마커 이미지의 이미지 주소입니다
              var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

              for (var i = 0; i < Traveling.props.hospital.type.length; i++) {
                // 마커 이미지의 이미지 크기 입니다
                var imageSize = new kakao.maps.Size(24, 35);

                // 마커 이미지를 생성합니다
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                  map: map, // 마커를 표시할 지도
                  position: positions[i].latlng, // 마커를 표시할 위치
                  title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                  image: markerImage, // 마커 이미지
                });
              }
            })
          }, function (error) {
            console.error(error)
          }, {
            enableHighAccuracy: false,
            maximumAge: 0,
            timeout: Infinity
          })
      }//if
      else {
        console.log('GPS를 지원하지 않습니다')
      }
      },
    },
    mounted(){
      setTimeout(this.setuser, 500)
    },
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
