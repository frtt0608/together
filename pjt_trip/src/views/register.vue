<template>

  <v-container fluid>
    <v-layout wt align-center justify-center style="padding-bottom:2%">
      <p style="font-size: 60px;">Sign up</p>
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
                            v-validate="'required|min:4|max:49'"
                          label="password"
                          v-model="form.password"
                          type='password'
                          :error-messages="errors.collect('password')"
                          data-vv-name="password"
                          required
                        ></v-text-field>
                        <v-text-field
                             v-validate="'required|min:11|max:13'"
                          label="phone_number"
                          v-model="form.phone_number"
                          :error-messages="errors.collect('phone_number')"
                          data-vv-name="phone_number"
                          suffix="Only number"
                          type='text'
                          required
                        ></v-text-field>
                        <v-text-field
                            v-validate="'required|min:4|max:49'"
                          label="nickname"
                          v-model="form.nickname"
                          type='text'
                          :error-messages="errors.collect('nickname')"
                          data-vv-name="nickname"
                          required
                        ></v-text-field>

                        <v-select
                          v-model="form.select"
                          :items="form.sex"
                          :rules="[v => !!v || 'Item is required']"
                          label="성별"
                          required
                        ></v-select>

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
        password:'',
        sex:'',
        check:false,
        select: null,
        sex: [
          '남성',
          '여성'
        ],
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
          together:this.form.check,
          sex:this.form.select
        })
        .then( response =>{
          if(response.data == '회원가입에 실패했습니다. 이메일 또는 닉네임 중복을 확인해주세요.'){
          alert("회원가입에 실패했습니다. 이메일 또는 닉네임 중복을 확인해주세요.")
        }else{

            alert("축하드립니다 가입 완료되었습니다")
                this.$router.push('/')
        }

          console.log('response : ', JSON.stringify(response, null, 2)) }).catch( error => { console.log('failed', error)

        })
    },
    checksign(){
      var email=this.form.email
      var nickname=this.form.nickname
      var password=this.form.password
      var phone_number=this.form.phone_number
      var sex=this.form.select

      if(email == ' '){
        alert("email을 입력해주세요")
      }else if(!email.includes('@')){
         alert("이메일 형식을 맞춰서 입력해주세요")
      }else if(nickname == ' '){
        alert("nickname을 입력해주세요")
      }else if(nickname.length>=50){
        alert("닉네임은 최대 49자까지 가능 합니다")
      }else if(phone_number == ' ' || phone_number.includes('-')){
        alert("핸드폰 번호를 입력해주세요")
      }else if(password == ' '){
        alert("비밀번호를 입력해주세요")
      }else if(sex == ' '){
        alert("성별을 입력해주세요")
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
.font{
  font-size:20px;
}
</style>
