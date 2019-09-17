<template>
  <div>
    <Header></Header>
    <!-- scale, slide, slide-x, slide-y -->
    <v-container fluid>
      <v-layout wt text-center text-xs-center justify-center style="padding-bottom:2%">
        <div class="wt align-center justify-center text-center">
          <p style="font-size: 60px;">Schedule</p>
          <p class="font">스케줄을 관리하고 확인하세요</p>
        </div>
      </v-layout>
      <v-layout>
        <v-flex xs12>
          <v-layout class="align-space-between justify-space-around row fill-height">

            <div class="divbtn">
              <v-menu offset-y
                 origin="center center"
                 transition="scale-transition"
                 bottom>
                 <v-btn slot="activator" color="#4CAF50" dark>
                   내가 담은 일정 목록
                 </v-btn>
                  <v-list>
                  <v-list-tile
                    v-for="(item, index) in items"
                    :key="index"
                    @click="getpost(item)">
                    <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                  </v-list-tile>
                  </v-list>
              </v-menu>

            </div>
            <div class="divwidth">
              <v-card class="mx-auto" :outlined="false" :width="this.width" :height="this.height">
                <v-img v-if="true" class="white--text" height="200px" :src="img_url">
                  <v-card-title class="align-end fill-height">{{title}}</v-card-title>
                </v-img>
                <v-card-title v-else>I'm a title</v-card-title>
                <v-card-text>{{description}}</v-card-text>
                <v-card-text>
                  <form>
                    <v-text-field
                      label="start"
                      type="date"
                      v-model="start"
                      data-vv-name="start"
                      @click="start_to(1)"
                      required
                    ></v-text-field>
                    <v-text-field v-if="start_toggle" @click="end_to()"label="end" type="date" v-model="end" data-vv-name="end" required></v-text-field>
                      <v-text-field v-if="end_toggle" @click="stime_to()"label="출발시각" type="time" v-model="s_time" data-vv-name="end" required></v-text-field>
                        <v-text-field v-if="stime_toggle"  @click="reset_to()" label="도착시각" type="time" v-model="e_time" data-vv-name="end" required></v-text-field>

                  </form>
                </v-card-text>
                <v-card-actions v-if="actions">
                  <v-btn text @click="setpost()">Click</v-btn>
                </v-card-actions>
              </v-card>
            </div>
          </v-layout>
        </v-flex>
      </v-layout>
      <div class="layoutdiv">
        <template v-if="this.trip_inform.length" class="font_notable image">
          <paginated-list0 :list-array="this.trip_inform" />
        </template>
      </div>
    </v-container>
  </div>
</template>
<script>
import Header from '@/components/Header'
import PaginatedList0 from './PaginatedList0'
export default {
  name: 'schedule',
  components: {
    Header,
    PaginatedList0
  },
  data () {
    return {
      width: '100%',
      height: '200px',
      id: '',
      start_toggle:false,
      end_toggle:false,
      stime_toggle:false,
      s_time:'',
      e_time:'',
      load: false,
      start: '',
      end: '',
      actions: true,
      title: 'Im a title',
      description: 'Im card text',
      img_url: 'https://cdn.vuetifyjs.com/images/cards/docks.jpg',
      items: [],
      post: [],
      trip_inform: [],
      save_inform: []

    }
  },

  methods: {
    gettdata () {
      this.id = this.$session.get('lo').user_pk
      var url = 'http://192.168.31.84:8080/api/post/' + this.id + '/incart'
      axios.post(url).then(res => {
        console.log(res.data)
        this.items = res.data
      }).catch(error => {
        console.log('야 잘 안됫어ㅠㅠ')
      })
    },
    start_to(start){

      if(start == 1){
        this.start_toggle=true
      }
    },
    end_to(){
      if(!this.end_toggle){
        this.end_toggle=true
      }

    },
    stime_to(){

      if(!this.stime_toggle){
        this.stime_toggle=true
      }

    },
    reset_to(){

      if(e_time!=''){
      if(this.start_toggle){
        this.end_toggle=false
      }
      if(this.end_toggle){
        this.end_toggle=false
      }
      if(this.stime_toggle){
        this.stime_toggle=false
      }

    }
    },
    getpost (items) {
      var post_id = items.post_pk
      this.img_url = items.image
      this.title = items.title
      this.description = items.address
      this.post = items
    },
    setpost () {
      // this.trip_inform=[];

      this.setschedule()
      // this.trip_inform.push(this.post);
    },
    setschedule () {
      var url = 'http://192.168.31.84:8080/api/schedule/add'
      axios.post(url, {
        user_id: this.id,
        post_id: this.post.post_pk,
        schedule_title: this.post.title,
        schedule_image: this.post.image,
        start_date: this.start,
        end_date: this.end,
        start_time:this.s_time,
        end_time:this.e_time
      }).then(res => {
        if (res.data!='이미 일정에 있습니다.') {
          alert(res.data)
          this.getschedule();
        }else{
          alert(res.data)

        }
      })
    },
    getschedule () {

      var url = 'http://192.168.31.84:8080/api/schedule/' + this.$session.get('lo').user_pk

      axios.post(url).then(res => {
        var res_type = []
        for (var [index, res_type] of Object.entries(res.data)) {
          console.log(res_type)
          this.trip_inform.push(res_type)

        }
      })
    }
  },

  mounted () {
    this.gettdata()
    this.getschedule()
  },
  watch: {
    newest() {},
    viewSort() {},
  },
}
</script>
<style>
.divwidth {
  width: 50%;
}
.divbtn {
  width: 20%;
}
.semipolar-spinner,
.semipolar-spinner * {
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

.layoutdiv {
  padding-top: 30%;
}

.font{
  font-size:20px;
}
.title {
 font-size: 60px;
 font-weight: bold;
}
</style>
