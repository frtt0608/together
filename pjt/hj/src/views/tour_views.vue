<template>
  <v-app>
    <Header></Header>
    <v-content>
      <Traveling></Traveling>
    </v-content>
    <!-- <Map></Map> -->
  </v-app>
</template>

<script>
import Header from '@/components/Header'
import Footer from '@/components/Footer'
import Traveling from '@/components/Traveling'
import Map from '@/components/Map'

export default {
  name: 'index',
  components : {
    Header,
    Footer,
    Traveling,
    Map
  },
  data () {
    return {
      msg: 'Welcome'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style >

</style>
