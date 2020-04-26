<template>
  <div class="footer">
    <div class="topBtn" @click="goTop()" v-scroll="onScroll" v-show="shown">
      <v-btn flat icon color="indigo" dark>
        <v-icon large dark color="red">touch_app</v-icon>
      </v-btn>
    </div>
    <p>&copy; {{ new Date().getFullYear() }} Trip</p>
  </div>
</template>
<script>
export default {
  name: "Footer",
  data() {
    return {
      shown: false
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
  height: 64px;
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
