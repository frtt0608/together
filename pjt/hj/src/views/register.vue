<template>

  <v-container fluid>
    <v-layout wt align-center justify-center style="padding-bottom:5%">
        <p><h1>Together</h1></p>

    </v-layout>
    <v-layout wt align-center justify-center>

      <v-flex xs12 sm8 md4>
        <v-card class="elevation-1">
          <v-toolbar>
            <v-toolbar-title>Together</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>

          <v-card-text>
            <form>
                        <v-text-field
                           v-validate="'required|min:4|max:30'"
                          label="Email address"
                          type='text'
                          :error-messages="errors.collect('email')"
                          v-model="form.email"
                          suffix="@gmail.com"
                          data-vv-name="email"
                          required
                        ></v-text-field>
                        <div v-if="this.message !=' ' ">

                          <h3>{{this.message}}</h3>

                        </div>

                        <v-text-field
                            v-validate="'required|min:4|max:20'"
                          label="nickname"
                          v-model="form.nickname"
                          type='text'
                          :error-messages="errors.collect('nickname')"
                          data-vv-name="nickname"
                          required
                        ></v-text-field>
                        <v-text-field
                             v-validate="'required|min:10|max:10'"
                          label="phone_number"
                          v-model="form.phone_number"
                          :error-messages="errors.collect('phone_number')"
                          data-vv-name="phone_number"
                          suffix="0000000000"
                          type='text'
                          required
                        ></v-text-field>
                        <v-text-field
                          label="password"
                          v-model="form.password"
                          data-vv-name="password"
                          type='password'
                          required
                        ></v-text-field>
                        <v-checkbox
                v-validate="'required'"
                v-model="form.check"
                :error-messages="errors.collect('checkbox')"
                value="1"
                label="Together"
                data-vv-name="checkbox"
                type="checkbox"
                required
              ></v-checkbox>
 <v-divider class="mt-12"></v-divider>
 <v-card-actions>
          <v-btn text @click="chekcid" >이메일 중복 확인</v-btn>
          <v-spacer></v-spacer>
          <v-slide-x-reverse-transition>


          </v-slide-x-reverse-transition>
          <v-btn color="primary" text @click="checksign">가입하기</v-btn>
        </v-card-actions>
</form>
          </v-card-text>




        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</div>
</template>
<script>
import Header from '@/components/Header'
import ko from 'vee-validate/dist/locale/ko'


export default {
  name: 'register',
  components : {
    Header
  },
  $_veeValidate:{
    validator: 'new'
  },
  data () {

    return {
      title : 'HelloWorld',
      message: ' ',
      form:{
        email:'',
        nickname:' ',
        phone_number:' ',
        grade:'3',
        password:' ',
        check:false
      },
    }
  },
  methods: {
    submit(){
        var key='rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D'
        var spring_url = 'http://192.168.31.84:8080/api/user/signup/'
        alert(this.form.email)

        axios.post(spring_url,{
          email:this.form.email,
          nickname:this.form.nickname,
          phone_number:this.form.phone_number,
          grade:this.form.grade,
          password:this.form.password,
          together:this.form.check
        })
        .then( response =>{

          console.log('response : ', JSON.stringify(response, null, 2)) }).catch( error => { console.log('failed', error)

        })
    },
    checksign(){
      var email=this.form.email
      var nickname=this.form.nickname
      var password=this.form.password
      var phone_number=this.form.phone_number

      if(email == ' '){
        alert("email을 입력해주세요")
      }else if(!email.includes('@')){
         alert("이메일 형식을 맞춰서 입력해주세요")
      }else if(nickname == ' '){
        alert("nickname을 입력해주세요")
      }else if(phone_number == ' '){
        alert("핸드폰 번호를 입력해주세요")
      }else if(password == ' '){
        alert("비밀번호를 입력해주세요")
      }else{
        this.submit();
      }
    },
    chekcid(){

    var spring_url='http://192.168.31.84:8080/api/user/check_email'

    axios.post(spring_url,{email:this.form.email})
    .then(response =>{
       this.message=response.data
        alert(this.message)
      console.log('response : ', JSON.stringify(response, null, 2)) })
      .catch( error => { console.log('failed', error)
    })


}
  },
  computed: {
  }
}
</script>
<style>
.title{
  padding: 50px 12px 25px 12px;
font-size: 20px;
font-weight: bold;
}
</style>
