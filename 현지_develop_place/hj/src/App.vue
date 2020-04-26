<template>
  <v-app>
    <v-content>
      <router-view/>
    </v-content>
    <Footer></Footer>
  </v-app>
</template>

<script>
import Footer from '@/components/Footer'
export default {
  name: 'App',
  components : {
    Footer,

  },
}
</script>

<style>
.font_pacifico {
  font-family: 'Pacifico', cursive;
}
.font_indei {
  font-family: 'Pacifico', cursive;
  font-family: 'Darker Grotesque', sans-serif;
  font-family: 'Hanalei', cursive;
  font-family: 'Indie Flower', cursive;
  font-weight: bold;
}
.font_notable{
  font-family: 'Pacifico', cursive;
  font-family: 'Darker Grotesque', sans-serif;
  font-family: 'Hanalei', cursive;
  font-family: 'Indie Flower', cursive;
  font-family: 'Anton', sans-serif;
  font-family: 'Notable', sans-serif;
  font-weight: bold;
}
.font_Gaegu{
  font-family: 'Gaegu', cursive;
}
.font_Noto{
  font-family: 'Noto Serif KR', serif;
  font-weight: 600;
}
.font_Yeon{
  font-family: 'Noto Serif KR', serif;
  font-family: 'Nanum Gothic Coding', monospace;
  font-family: 'Yeon Sung', cursive;
}
</style>
