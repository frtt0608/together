<template>
  <div>
    <Header></Header>
    <div style="height: 400px;" v-if="detail_form.length === 0">
      <div class="semipolar-spinner" :style="spinnerStyle" style="margin:100px auto;">
      <div class="ring"></div>
      <div class="ring"></div>
      <div class="ring"></div>
      <div class="ring"></div>
      <div class="ring"></div>
    </div>
  </div>

  <v-container fluid grid-list-sm>
  <v-layout wrap>
    <v-spacer></v-spacer><v-spacer></v-spacer>
    <v-flex>
      <div id="map" v-if="detail_form" style="width:100%;height:50%;" ></div><p></p>
      <v-card max-width="350">
        <v-system-bar color="pink darken-2"></v-system-bar>
        <br>
        <span><h4>{{detail_form.address}}</h4></span>
        <v-spacer></v-spacer>
        <v-container
          class="pa-2"
          fluid
          grid-list-md>
          <v-layout column>
            <v-flex>
              <v-card
                color="#FFFFFF"
                :flat="flat"
                dark>
                <v-card-text class="white--text">
                </v-card-text>
                <v-card-actions>
                  <!-- <v-btn text  @click="like_fun"><v-icon>favorite</v-icon> {{this.click_like}}</v-btn> -->
                  <template   v-if="heart_toggle">
                    <v-btn text  @click="like_fun" icon color="black"><v-icon>favorite</v-icon></v-btn>
                  </template >
                  <template   v-else>
                    <v-btn text  @click="like_fun" icon color="red"><v-icon>favorite</v-icon></v-btn>
                  </template >
                  <v-btn text v-if="click_schedule == false" @click="schedule_fun" outlined>일정 담기</v-btn>
                  <v-btn text v-if="click_schedule == true" @click="schedule_fun" outlined>일정을 담았습니다</v-btn>
                </v-card-actions>
              </v-card>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card>
    </v-flex>

    <v-card
      class="mx-auto"
      :elevation="6"
      :width="760"
      :height="wheight"
    >
        <v-container fluid grid-list-md>
        <v-layout wrap>
        <v-flex>
        <v-img
           v-if="media"
          :height="300"
          :width="725"
          :src="detail_form.image"
        >
        </v-img>
      </v-flex>
          <v-flex
            v-for="n in log"
            :key="n"
            style="display: inline-block;"
            >
          <v-img
            :src="n.link"
            :height="120"
            :width="135"
          >
          </v-img>
        </v-flex>
      </v-layout></v-container>
      <!-- <v-card-title v-else>I'm a title</v-card-title> -->
        <v-container>
        <v-rating
           :value="score_AVG"
           color="amber"
           half-increments
           dense
           size="14"
           readonly
         ></v-rating><div class="grey--text ml-4"> {{score_AVG}} </div></v-container>
      <v-card-text>{{detail_form.title}}</v-card-text>
      <v-card-text>{{cat3_lists[detail_form.cat3]}}</v-card-text>

      <template>
      <div v-if="score_click === false "class="text-center">
        <v-rating v-model="rating"></v-rating>
        <v-btn @click="score_fun">평점 선택</v-btn>
      </div>
      <div v-if="score_click"class="text-center">
        <h3>{{rating}}점을 평가하셨습니다</h3>
        <v-btn @click="score_fun">다시 평가하기</v-btn>
      </div>
    </template>
    <v-spacer></v-spacer><v-spacer></v-spacer>
    <v-container style="height:2%">
      <v-layout>
        <v-flex>
          <textarea v-model="message" style="width:90%;" placeholder="관광지에 대한 후기를 남겨보세요!"></textarea> <v-btn @click="comment_fun">후기 작성</v-btn>
        </v-flex>
      </v-layout>
      <v-layout v-if="comments.length">
        <v-flex>
        <div>
        <template v-for="board in comments">
      {{board.nickname}}: {{board.comment}} <v-btn v-if="uid == board.user_id"@click="cdelete(board.comment_pk)"class="ma-2" color="red" dark>delete
        <v-icon dark right>block</v-icon>
      </v-btn>
      <br>
      <br>
    </template>
  </div>
  </v-flex>
      </v-layout>
    </v-container>

    </v-card>

    <v-spacer></v-spacer><v-spacer></v-spacer>
  </v-layout>

</v-container>

</div>

</template>
<script>
import Header from '@/components/Header'
import tour from '@/views/tour'
import Footer from '@/components/Footer'

export default {
  name: "tour_detial",
  components:{
    Header,
    tour,
    Footer
  },
  data(){
    return{
      tour,
      heart_toggle:true,
      activeBtn:1,
      wheight:'10%',
      flat: false,
      detail_form: [],
      id: '',
      uid:this.$session.get('lo').user_pk,
      id_2:'',
      media: true,
      actions: true,
      cat3_lists : {A01010100 : '국립공원', A01010200 :'도립공원', A01010300 : '군립공원', A01010400: '산', A01010500: '자연생태관광지', A01010600: '자연휴양림', A01010700: '수목원',
      A01010800: '폭포', A01010900: '계곡', A01011000: '약수터' ,A01011100: '해안절경', A01011200: '해수욕장', A01011300: '섬', A01011400: '항구/포구', A01011500: '어촌',
      A01011600: '등대', A01011700: '호수', A01011800: '강', A01011900: '동굴', A01020100: '희귀동.식물', A01020200: '기암괴석',A02010100: '고궁', A02010200: '성',A02010300:'문',
      A02010400: '고택', A02010500: '생가', A02010600: '민속마을',A02010700: '유적지/사적지',A02010800: '사찰',A02010900:'종교성지',A02011000:'안보관광',A02020100:'유원지',
      A02020200: '관광단지', A02020300: '온천/욕장/스파', A02020400: '이색찜질방', A02020500: '헬스투어',A02020600: '테마공원', A02020700: '공원', A02020800: '유람선/잠수함관광',
      A02030100: '농.산.어촌 체험',A02030200: '전통체험', A02030300: '산사체험', A02030400: '이색체험',A02030500: '관광농원', A02030600: '이색거리', A02040100: '제철소',A02040200:'조선소',
      A02040300: '공단',A02040400:'발전소',A02040500: '광산',A02040600:'식음료',A02040700:'화학/금속',A02040800:'기타',A02040900:'전자/반도체',A02041000:'자동차',A02050100:'다리/대교',A02050200:'기념탑/기념비/전망대',
      A02050300:'분수',A02050400:'동상',A02050500:'터널',A02050600:'유명건물',A02060100:'박물관',A02060200:'기념관',A02060300:'전시관',A02060400:'컨벤션센터',A02060500:'미술관/화랑',A02060600:'공연장',
      A02060700:'문화원',A02060800:'외국문화원',A02060900:'도서관',A02061000:'대형서점',A02061100:'문화전수시설',A02061200:'영화관',A02061300:'어학당',A02061400:'학교',A02070100:'문화관광축제',A02070200:'일반축제',
      A02080100:'전통공연',A02080200:'연극',A02080300:'뮤지컬',A02080400:'오페라',A02080500:'전시회',A02080600:'박람회',A02080700:'컨벤션',A02080800:'무용',A02080900:'클래식음악회',A02081000:'대중콘서트',
      A02081100:'영화',A02081200:'스포츠경기',A02081300:'기타행사',C01120001:'가족코스',C01130001:'나홀로코스',C01140001:'힐링코스',C01150001:'도보코스',C01160001:'캠핑코스',C01170001:'맛코스',A03010100:'육상레포츠',
      A03010200:'수상레포츠',A03010300:'항공레포츠',A03020100:'스포츠센터',A03020200:'수련시설',A03020300:'경기장',A03020400:'인라인(실내 인라인 포함)',A03020500:'자전거하이킹',A03020600:'카트',A03020700:'골프',
      A03020800:'경마',A03020900:'경륜',A03021000:'카지노',A03021100:'승마',A03021200:'스키/스노보드',A03021300:'스케이트',A03021400:'썰매장',A03021500:'수렵장',A03021600:'사격장',A03021700:'야영장,오토캠핑장',
      A03021800:'암벽등반',A03021900:'빙벽등반',A03022000:'서바이벌게임',A03022100:'ATV',A03022200:'MTB',A03022300:'오프로드',A03022400:'번지점프',A03022500:'자동차경주',A03022600:'스키(보드) 렌탈샵',A03022700:'트래킹',
      A03030100:'윈드서핑/제트스키',A03030200:'카약/카누',A03030300:'요트',A03030400:'스노쿨링/스킨스쿠버다이빙',A03030500:'민물낚시',A03030600:'바다낚시',A03030700:'수영',A03030800:'래프팅',A03040100:'스카이다이빙',
      A03040200:'초경량비행',A03040300:'헹글라이딩/패러글라이딩',A03040400:'열기구',A03050100:'복합 레포츠',B02010100:'관광호텔',B02010200:'수상관광호텔',B02010300:'전통호텔',B02010400:'가족호텔',B02010500:'콘도미니엄',
      B02010600:'유스호스텔',B02010700:'펜션',B02010800:'여관',B02010900:'모텔',B02011000:'민박',B02011100:'게스트하우스',B02011200:'홈스테이',B02011300:'서비스드레지던스',B02011400:'의료관광호텔',B02011500:'소형호텔',
      B02011600:'한옥스테이',A04010100:'5일장',A04010200:'상설시장',A04010300:'백화점',A04010400:'면세점',A04010500:'할인매장',A04010600:'전문상가',A04010700:'공예,공방',A04010800:'관광기념품점',A04010900:'특산물판매점',
      A05020100:'한식',A05020200:'서양식',A05020300:'일식',A05020400:'중식',A05020500:'아시아식',A05020600:'패밀리레스토랑',A05020700:'이색음식점',A05020800:'채식전문점',A05020900:'바/까페',A05021000:'클럽'},
      log:'',
      click_like : 0,
      click_schedule: 0,
      rating: 1,
      score_click: false,
      score_btn: "평점 선택",
      score_AVG: 0,
      message:'',
      comments: [],
    }
  },
  methods: {
    mapinit(){
      var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
      var options = { //지도를 생성할 때 필요한 기본 옵션
      	center: new daum.maps.LatLng(Number(this.detail_form.mapy), Number(this.detail_form.mapx)), //지도의 중심좌표.
      	level: 3 //지도의 레벨(확대, 축소 정도)
      };
      var map = new daum.maps.Map(container, options); //지도 생성 및 객체 리턴
      console.log('위도경도')
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();
      // 주소로 좌표를 검색합니다

      geocoder.addressSearch(this.detail_form.address, function(result, status) {
         // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
             var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
             // 결과값으로 받은 위치를 마커로 표시합니다
             var marker = new kakao.maps.Marker({
                 map: map,
                 position: coords
             });
             // 인포윈도우로 장소에 대한 설명을 표시합니다
             // var infowindow = new kakao.maps.InfoWindow({
             //     content: '<div style="width:150px;text-align:center;padding:6px 0;">'++'</div>'
             // });
             // infowindow.open(map, marker);
             marker.setMap(map);
             // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
       console.log(coords)
       map.setCenter(coords);
        }
      });

    },
    cdelete(cpk){

      var c_pk=cpk;
      var url="http://192.168.31.84:8080/api/post/"+c_pk+'/delete_comment'
      axios.post(url).then(res=>{
        alert(res.data)
        location.reload();

      })
    },
    api_img(){
      var img_url="http://192.168.31.84:8080/api/SearchImage/"
      var value = this.detail_form.title
      console.log(value, "로 이미지 검색")
      var URL = img_url + this.detail_form.title
      axios.get(URL).then((res)=>{
        console.log(res.data)
        this.log=res.data
      })
    },
    score_lists(){
      var score_url="http://192.168.31.84:8080/api/post/"
      var value=this.detail_form.post_pk
      var URL=score_url + value + '/score'
      axios.get(URL).then((res)=>{
        console.log('이 포스트의 score 데이터')
        console.log(res)
        var num=0
         for (var [index,score_data] of Object.entries(res.data)){
          num += score_data.score
          if(score_data.user_id === this.$session.get('lo').user_pk){
            this.rating = score_data.score
            this.score_click = true
            this.score_pk = score_data.score_pk
          }
        }
        if(res.data.length){
          this.score_AVG = num / res.data.length
          console.log(this.score_AVG)}
        else{this.score_AVG = 0}
      })
    },
    comment_lists(){
      var comment_url="http://192.168.31.84:8080/api/post/"
      var value=this.detail_form.post_pk
      var URL = comment_url + value + '/comment'
      axios.get(URL).then((res)=>{
        console.log('코멘트 보여주기')
        console.log(res.data)
          var res_type = [];
          this.comments=res.data;


      })
    },
    score_fun_delete(){
      var score_url="http://192.168.31.84:8080/api/post/"
      var value=this.score_pk
      var URL=score_url + value + '/delete_score'
      axios.get(URL).then(()=>{
        this.score_lists();
      })
      console.log('삭제완료')
    },
    like_fun(){
      if(this.heart_toggle){
        this.heart_toggle=false
      }else{
        this.heart_toggle=true
      }
      var like_url = "http://192.168.31.84:8080/api/post/"
      var value = this.detail_form.post_pk
      var URL = like_url + value + '/like'
      axios.post(URL,{user_id : this.$session.get('lo').user_pk, post_id : value }).then((res) =>{
        console.log(res.data)
        this.click_like = res.data
      })
    },
    schedule_fun(){
      var like_url = "http://192.168.31.84:8080/api/post/"
      var value = this.detail_form.post_pk
      var URL = like_url + value + '/cart'
      axios.post(URL,{user_id : this.$session.get('lo').user_pk, post_id : value }).then((res) =>{
        console.log(res.data)
        this.click_schedule = res.data
      })
    },
    score_fun(){
      if(this.score_click){
        this.score_fun_delete();
        this.score_click=false
      }
      else{
        if(this.$session.get('lo')) {
        var score_url="http://192.168.31.84:8080/api/post/"
        var value=this.detail_form.post_pk
        var URL=score_url + value + '/create_score'
        axios.post(URL,{score: this.rating, user_id: this.$session.get('lo').user_pk, post_id:this.detail_form.post_pk}).then((res)=>{
            console.log(res)
            this.score_lists();
        })
        this.score_click=true }
        else {alert('로그인 후 이용해주세요!')}
      }
    },
    comment_fun(){

      if(this.message){
        var comment_url='http://192.168.31.84:8080/api/post/'
        var value=this.detail_form.post_pk
        var URL = comment_url +value + '/create_comment'
        axios.post(URL,{comment:this.message, nickname:this.$session.get('lo').nickname, user_id: this.$session.get('lo').user_pk, post_id:this.detail_form.post_pk}).then((res)=>{
        console.log(res)
        this.comment_lists();
        this.message=''
        })
      }
    },
  },
  created(){
    this.id = this.$route.params.id
    this.id_2=this.$route.params.id_2
    console.log(this.$route.params)
    var spring_url = 'http://192.168.31.84:8080/api/post/areacode/' + Number(this.id_2)
    axios.get(spring_url).then((res)=>{
      console.log(res.data)
      for ( var [index,res_detail] of Object.entries(res.data) ){
        if(res_detail.post_pk === Number(this.id)){
          console.log(res_detail)
          this.detail_form=res_detail
          this.mapinit();
          this.api_img();
          this.score_lists();
          this.comment_lists();
          axios.post("http://192.168.31.84:8080/api/post/"+this.detail_form.post_pk+"/checklike",{user_id : this.$session.get('lo').user_pk, post_id:this.detail_form.post_pk}).then((ress)=>{
            console.log('좋아요체크',ress.data)
            this.click_like=ress.data
          });
          axios.post("http://192.168.31.84:8080/api/post/"+this.detail_form.post_pk+"/checkcart",{user_id : this.$session.get('lo').user_pk, post_id:this.detail_form.post_pk}).then((ress)=>{
            console.log('카트체크',ress.data)
            this.click_schedule=ress.data
          });
        }
      }
    });
  },
  mounted(){
    console.log(this.$session.get('lo'))
  }
}
</script>
<style>
.semipolar-spinner, .semipolar-spinner * {
      box-sizing: border-box;
    }

    .semipolar-spinner {
      height: 65px;
      width: 65px;
      position: relative;
    }

    .semipolar-spinner .ring {
      border-radius: 50%;
      position: absolute;
      border: calc(65px * 0.05) solid transparent;
      border-top-color: #ff1d5e;
      border-left-color: #ff1d5e;
      animation: semipolar-spinner-animation 2s infinite;
    }

    .semipolar-spinner .ring:nth-child(1) {
      height: calc(65px - 65px * 0.2 * 0);
      width: calc(65px - 65px * 0.2 * 0);
      top: calc(65px * 0.1 * 0);
      left: calc(65px * 0.1 * 0);
      animation-delay: calc(2000ms * 0.1 * 4);
      z-index: 5;
    }

    .semipolar-spinner .ring:nth-child(2) {
      height: calc(65px - 65px * 0.2 * 1);
      width: calc(65px - 65px * 0.2 * 1);
      top: calc(65px * 0.1 * 1);
      left: calc(65px * 0.1 * 1);
      animation-delay: calc(2000ms * 0.1 * 3);
      z-index: 4;
    }

    .semipolar-spinner .ring:nth-child(3) {
      height: calc(65px - 65px * 0.2 * 2);
      width: calc(65px - 65px * 0.2 * 2);
      top: calc(65px * 0.1 * 2);
      left: calc(65px * 0.1 * 2);
      animation-delay: calc(2000ms * 0.1 * 2);
      z-index: 3;
    }

    .semipolar-spinner .ring:nth-child(4) {
      height: calc(65px - 65px * 0.2 * 3);
      width: calc(65px - 65px * 0.2 * 3);
      top: calc(65px * 0.1 * 3);
      left: calc(65px * 0.1 * 3);
      animation-delay: calc(2000ms * 0.1 * 1);
      z-index: 2;
    }

    .semipolar-spinner .ring:nth-child(5) {
      height: calc(65px - 65px * 0.2 * 4);
      width: calc(65px - 65px * 0.2 * 4);
      top: calc(65px * 0.1 * 4);
      left: calc(65px * 0.1 * 4);
      animation-delay: calc(2000ms * 0.1 * 0);
      z-index: 1;
    }

    @keyframes semipolar-spinner-animation {
      50% {
        transform: rotate(360deg) scale(0.7);
      }
    }

.v-input__slider {
    width: 100%;
  }

</style>
