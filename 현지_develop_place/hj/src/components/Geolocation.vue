<template>

  <div id="geo">
    <button type="button" @click="myFunction()">Click Me</Button>
        <p>Lat dd= {{ lat }} Lon = {{ lon }}</p>
        <p>{{ error }}</p>
  </div>

</template>

<script>

  export default {
    name: 'geo',
    data() {
        return {
            view: false,
            lat: '',
            lon: '',
            error: ''
        }
    },
    methods: {
        myFunction: function() {
            if(navigator.geolocation){
                navigator.geolocation.getCurrentPosition(this.showPosition);
            }else{
                this.error = "Geolocation is not supported.";
            }
        },
        showPosition:function (position) {
            this.lat = position.coords.latitude;
            this.lon = position.coords.longitude;
        }
    }
};
</script>

<style scoped>

</style>
