<template>
 <v-container fluid>
     <Header></Header>
   <v-flex style="display:none;" ><Map></Map></v-flex>
   <v-layout wt text-center text-xs-center justify-center style="padding-bottom:5%; padding-top:1%">
     <div class="wt align-center justify-center text-center">
       <p class="title">Together의 로그인</p>
       <p class="font">로그인하고 많은 혜택을 누리세요</p>
     </div>
   </v-layout>
   <v-layout wt align-center justify-center>
     <v-flex xs12 sm8 md4>
       <v-card class="elevation-1">
         <v-card-text>
           <form>
             <v-text-field
               v-validate="'required|min:4|max:30'"
               label="Email address"
               type="text"
               :error-messages="errors.collect('email')"
               v-model="login.email"
               data-vv-name="email"
               required
             ></v-text-field>
             <v-text-field
               label="password"
               v-model="login.password"
               data-vv-name="password"
               type="password"
               required
             ></v-text-field>
             <v-card-actions>
               <v-btn text @click="checksign">로그인
                 </v-btn>
               <v-spacer></v-spacer>
               <v-slide-x-reverse-transition></v-slide-x-reverse-transition>
               <v-btn href="/register" color="primary" text @click>가입하기</v-btn>
             </v-card-actions>
           </form>
         </v-card-text>
       </v-card>
     </v-flex>
   </v-layout>
 </v-container>
</template>
<script>
import Header from '@/components/Header'
import ko from 'vee-validate/dist/locale/ko'
import Map from '@/components/Map'
import Traveling from '@/components/Traveling'
export default {
 name: 'login',
 components: {
   Header,
   Map,
   Traveling,
 },
 $_veeValidate: {
   validator: 'new'
 },
 data () {
   return {
     title: 'HelloWorld',
     check: false,
     message: ' ',
     login: {
       email: '',
       password: '',
       pk: ''
     }
   }
 },
 methods: {
   submit () {
     var key = 'rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D'
     var spring_url = 'http://192.168.31.84:8080/api/user/login/'
     alert(this.login.email)
     axios.post(spring_url, {
       email: this.login.email,
       password: this.login.password,
       location: Traveling.props.city
     })
       .then(response => {
         if (response.data.password==null || response.data.email == null) {
           alert('아이디 비밀번호를 확인해주세요')
         } else {
           this.$session.start()
           this.$session.set('lo', response.data)
           this.id = this.$session.get('lo')
           console.log(this.$session.get('lo'))
           this.$router.push('/')
           // alert(this.id)
         }
         console.log('response : ', JSON.stringify(response, null, 2))
       }).catch(error => {
         console.log('failed', error)
       })
   },
   checksign () {
     var email = this.login.email
     var password = this.login.password
     if (email == ' ') {
       alert('email을 입력해주세요')
     } else if (!email.includes('@')) {
       alert('이메일 형식을 맞춰서 입력해주세요')
     } else if (password == ' ') {
       alert('비밀번호를 입력해주세요')
     } else {
       this.submit()
     }
   }
 },
 computed: {
 }
}
</script>
<style>
.title {
 font-size: 60px;
 font-weight: bold;
}
.font{
  font-size:20px;
}
</style>
