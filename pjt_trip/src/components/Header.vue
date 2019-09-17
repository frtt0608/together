<template>
 <v-toolbar :color="elevation-0" style="background-color: #ffffff; background-color: rgba( 255, 255, 255, 0 );" flat=flase class="font_pacifico">
   <v-toolbar-side-icon href="/"></v-toolbar-side-icon>
   <v-toolbar-title>Everything to trip</v-toolbar-title>
   <v-spacer></v-spacer>
   <v-toolbar-items class="hidden-sm-and-down">
     <p class="psize font_Gaegu" v-if='user_toggle' >{{this.$session.get('lo').nickname}}님 환영합니다</p>
     <v-btn href="/border" flat>notice</v-btn>
       <v-btn href="/schedule" flat>schedule</v-btn>
     <v-btn v-if='user_toggle==false' href="/login" flat>Login</v-btn>
     <v-btn v-else-if='user_toggle==true'  @click="deleteId()" flat>Logout</v-btn>
     <v-btn v-show='user_toggle' href="/mypage" flat>Mypage</v-btn>
     <v-btn href="/tour" flat>Tour</v-btn>
     <v-btn href="/tour_views" flat>Traveling</v-btn>
   </v-toolbar-items>
 </v-toolbar>
</template>
<script>
export default {
 name: "Header",
 data() {
   return{
     user_toggle: false,
      user_data: []
   }
 },
 methods:{
   getdata(){
     if(this.$session.get('lo')){this.user_toggle=true}
     // this.user_data = this.$session.get('lo')
     // var spring_url = 'http://192.168.31.84:8080/api/user/' + this.user_data.user_pk
     // axios.post(spring_url,{user_pk: this.user_data.user_pk})
     //  .then(response=>{
     //    this.user_toggle=true
     //  }).catch(error =>{
     //   console.log("안돼ㅠㅠ")
     //  })
  },
  deleteId(){
    var spring_url = 'http://192.168.31.84:8080/api/user/' + this.$session.get('lo').user_pk +'/logout'
    axios.post(spring_url)
    this.$session.remove('lo')
    this.user_toggle=false;
    alert('로그아웃이 완료되었습니다')
    this.$router.push('/')
  }
},
 mounted(){
   this.getdata()

 },
}
</script>
<style>
.font_pacifico {
 font-family: 'Pacifico', cursive;
}
.psize{
 padding-top: 4.2%;
 padding-right:1%;
 font-size:16px;
white-space:nowrap;
}
</style>
