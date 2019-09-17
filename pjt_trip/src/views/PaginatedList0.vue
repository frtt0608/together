<template>
  <div class="font_Yeon">
    <template>
      <v-container style="margin-left:30px;" fluid :grid-list-md="!$vuetify.breakpoint.xs">
        <v-layout wrap row>
          <template v-for="p in paginatedData">
            <v-flex :key="p.contentid" xs12 sm6 md4 class="pb-2">
              <v-hover v-slot:default="{hover}">
                <v-btn @click="deletea(p.schedule_pk)" style="height:300px; width:410px;">
                  <v-card :elevation="hover ? 12 : 2" :class="{ 'on-hover': hover }">
                    <v-img :src="p.schedule_image" height="300px" width="410px">
                      <v-expand-transition>
                        <div
                          v-if="hover === false"
                          class="d-flex transition-fast-in-fast-out v-card--reveal font_Yeon"
                          style="height: 100px; background-color:black;"
                        >
                          <p
                            style="padding-top:40px; color:white; font-size:25px; text-align: center; letter-spacing: 2px;"
                            class="mb-0 target"
                          >{{p.schedule_title}}</p>
                          <div>
                            <p
                              style="padding-top:20px; padding-right:35px; color:grey; font-size:15px; text-align: center;"
                            >{{p.start_date}} ~ {{p.end_date}}</p>
                            <p
                              style="padding-top:20px; padding-right:35px; color:grey; font-size:15px; text-align: center;"
                            >{{p.start_time}} ~ {{p.end_time}}</p>
                          </div>
                        </div>
                      </v-expand-transition>
                    </v-img>
                    <!-- <img :src="p.image" @mouseover="mouseOver" class="full"
              style="filter: grayscale(1) contrast(1.1) brightness(1.1) ;" >
                    </img>-->
                    <!-- <p style="padding-top:3px; color:grey; font-size:20px; text-align: center; letter-spacing: 2px;" class="mb-0 target">{{p.title}}</p>
               <div>
                    <p style="color:grey; font-size:15px; text-align: center; ">{{p.address}}</p></div><v-spacer></v-spacer>-->
                  </v-card>
                </v-btn>
              </v-hover>
            </v-flex>
          </template>
        </v-layout>
      </v-container>
    </template>

    <div class="btn-cover">
      <button
        :disabled="PaginatedList0.props.pageNum.type === 0"
        @click="prevPage"
        class="page-btn"
      >-10</button>
      <span class="page-count">{{ PaginatedList0.props.pageNum.type + 1 }} / {{ pageCount }} 페이지</span>
      <button
        :disabled="PaginatedList0.props.pageNum.type >= pageCount - 1"
        @click="nextPage"
        class="page-btn"
      >+10</button>
    </div>
  </div>
</template>

<script>
import PaginatedList0 from './PaginatedList0'
import schedule from '@/views/schedule'
import { posix } from 'path'
import tour_modal from '@/components/tour_modal'
export default {
  name: 'paginated-list0',
  components: {
    tour_modal,
    schedule
  },
  data () {
    return {
      PaginatedList0,
      dialog: false
    }
  },
  props: {
    listArray: {
      type: Array,
      required: true
    },
    pageSize: {
      type: Number,
      required: false,
      default: 10
    },
    pageNum: 0
  },
  methods: {
    deletea (spk) {
      var url = 'http://192.168.31.84:8080/api/schedule/' + spk + '/delete'
      axios.post(url)
      alert("삭제 되었습니다.")
      location.reload();
    },
    nextPage () {
      PaginatedList0.props.pageNum.type += 1
    },
    prevPage () {
      PaginatedList0.props.pageNum.type -= 1
    },
    mouseOver () {
      this.hover = !this.hover
    }
  },
  computed: {
    pageCount () {
      let listLeng = this.listArray.length,
        listSize = this.pageSize,
        page = Math.floor(listLeng / listSize)
      if (listLeng % listSize > 0) page += 1

      /*
      아니면 page = Math.floor((listLeng - 1) / listSize) + 1;
      이런식으로 if 문 없이 고칠 수도 있다!
      */
      return page
    },
    paginatedData () {
      const start = PaginatedList0.props.pageNum.type * this.pageSize,
        end = start + this.pageSize
      console.log(this.listArray.slice(start, end))
      return this.listArray.slice(0, end)
      // 0-9, 10,19, 20,29
    }
  }
}
</script>

<style>
.btn-cover {
  margin-top: 1.5rem;
  text-align: center;
}
.btn-cover .page-btn {
  width: 5rem;
  height: 2rem;
  letter-spacing: 0.5px;
}
.btn-cover .page-count {
  padding: 0 1rem;
}
.full {
  max-width: 100%;
  max-height: 100%;
}
img {
  width: 400px;
  height: 300px;
  border-radius: 2px;
  box-shadow: 1px 1px 3px 1px rgba(0, 0, 0, 0.5);
  transition: width 1s;
  display: block;
  margin: 0px auto;
}
.target {
  display: inline-block;
  width: 250px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
.v-card {
  transition: opacity 0.4s ease-in-out;
}

.v-card:not(.on-hover) {
  opacity: 0.75;
}

.show-btns {
  color: rgba(255, 255, 255, 1) !important;
}
.v-card--reveal {
  bottom: 0;
  position: absolute;
  width: 100%;
}
</style>
