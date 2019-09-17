<template>
<div>
  <Header></Header>
  <!-- scale, slide, slide-x, slide-y -->
  <v-container fluid>
    <v-layout>
      <v-flex xs12>
        <v-layout class="align-space-between justify-space-around row fill-height">
          <div class="divbtn">
            <v-menu
             :offset-y="true"
             :open-on-hover="true"
            >
              <template v-slot:activator="{ on }">
                <v-btn color="primary" dark v-on="on">Dropdown</v-btn>
              </template>
              <v-list>
                <v-list-item v-for="(item, index) in items"
                  :key="index"
                >
                  <v-list-tile-title>{{item.title}}</v-list-tile-title>
                </v-list-item>
              </v-list>
            </v-menu>
          </div>
          <div class="divwidth">
            <v-card class="mx-auto" :outlined="false" :width="this.width" :height="this.height">
              <v-img
                v-if="true"
                class="white--text"
                height="200px"
                src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
              >
                <v-card-title class="align-end fill-height">I'm a title</v-card-title>
              </v-img>
              <v-card-title v-else>I'm a title</v-card-title>
              <v-card-text>I'm card text</v-card-text>
              <v-card-actions v-if="actions">
                <v-btn text>Click</v-btn>
              </v-card-actions>
            </v-card>
          </div>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</div>
</template>
<script>
import Header from '@/components/Header'
export default {
name: 'schedule',
components: {
  Header
},
data () {
  return {
    width: '100%',
    height: '200px',
    outlined: false,
    actions: true,
    offsetY: true,
    openOnHover: false,
    value: true,
    items: [
      { title: 'Click Me' },
      { title: 'Click Me' },
    ]
  }
}
}
</script>
<style>
.divwidth {
width: 50%;
}
.divbtn {
width: 20%;
}
</style>
