<template>
  <v-layout>
     <v-flex xs12 sm12 md12 lg12 xl12>
  <div class="footer">
    <div class="topBtn" @click="goTop()" v-scroll="onScroll" v-show="shown">
      <v-btn flat icon color="indigo" dark>
        <v-icon large dark color="red">touch_app</v-icon>
      </v-btn>
    </div>
    <p>&copy; {{ new Date().getFullYear() }} Trip Together</p>
    <p>developer</p>

       <span style="padding-right:2%">Instargram @dacstar</span>
       <span style="padding-right:2%">Instargram  @ch2rrie</span>
       <span style="padding-right:2%">Instargram @heon_9u</span>
       <span style="padding-right:2%">Google giljune91@gmail.com</span>
       <span style="padding-right:2%">Instargram @dong_s92</span>


  </div>
</v-flex>
</v-layout>
</template>
<script>
export default {
  name: "Footer",
  data() {
    return {
      shown: false,
      icons:'fab fa-instagram',


    }
  },
  methods: {
    goTop() {
      window.scrollTo({
        top: 0,
        left: 0,
        behavior: "smooth"
      });
    },
    onScroll(e) {
      const offsetTop = (window.pageYOffset || e.target.scrollTop || 0)
      this.shown = offsetTop > 10
    }
  }
}
</script>
<style>
.footer {
  background-color: #272727;
  color: #fff;
  height: 100%;
  position: relative;
  text-align: center;
}
.footer p {
  color: #fff;
  line-height: 64px;
  font-size: 14px;
}
.topBtn {
  position: fixed;
  right: 2%;
  bottom: 2%;
  width: 40px;
  line-height: 40px;
  display: block;
  border-radius: 5px;
}
</style>
