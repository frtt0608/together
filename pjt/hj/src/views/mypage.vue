<template>
  <div>
  <Header></Header>
  <div class="mypage">
    <h1 class="subheading grey--text ma-3 text-center">My Page</h1>

    <v-container class="my-3">
      <v-layout row wrap justify-center>
        <v-card flat class="text-xs-center ma-3" min-width="500">
          <v-form ref="form" v-model="valid" lazy-validation>
            <v-responsive class="pt-4">
              <v-img
                src="http://mnews.imaeil.com/inc/photos/2018/10/04/2018100410494025802_l.jpg"
                class="grey lighten-2"
                ma-3
              ></v-img>
              <v-btn flat color="grey" class="right">
                <v-icon small left>add_a_photo</v-icon>프로필 이미지 변경
              </v-btn>
            </v-responsive>

            <v-card-text>
              <v-text-field
                name="email"
                label="Email"
                id="email"
                prepend-icon="mail"
                hint="메일 표기 형식을 지켜주세요. ex) xxxx@gmail.com"
                v-model="user.email"
                disabled
              ></v-text-field>
              <v-text-field
                name="name"
                label="Name"
                id="name"
                prepend-icon="account_box"
                clearable
                hint="이름을 입력해주세요."
                :rules="[rules.counter]"
                counter="25"
                v-model="user.name"
              ></v-text-field>
              <v-text-field
                name="nickname"
                label="Nickname"
                id="nickname"
                prepend-icon="face"
                clearable
                hint="별명을 입력해주세요."
                :rules="[rules.counter]"
                counter="25"
                v-model="user.nickname"
              ></v-text-field>
              <v-text-field
                name="desc"
                label="Desc"
                id="desc"
                prepend-icon="description"
                clearable
                hint="자신을 한마디로 표현해주세요."
                :rules="[rules.counter]"
                counter="25"
                v-model="user.description"
              ></v-text-field>
              <v-text-field
                name="phone"
                label="Phone"
                id="phone"
                prepend-icon="smartphone"
                clearable
                hint="전화번호를 입력해주세요."
                :rules="[rules.counter]"
                counter="25"
                v-model="user.phone_number"
              ></v-text-field>
              <v-checkbox
                label="Would you like to travel with someone else?"
                v-model="user.together"
                value="value"
                color="green"
              ></v-checkbox>
            </v-card-text>

            <v-card-actions class="justify-center">
              <v-btn flat color="grey" @click="submit">
                <v-icon small left>message</v-icon>
                <span>Edit Userinfo</span>
              </v-btn>
              <Editpassword></Editpassword>
              <!-- <v-btn flat color="grey" @click="submit()">
              <v-icon small left>edit</v-icon>
              <span>Edit Password</span>
              </v-btn>-->
            </v-card-actions>
          </v-form>
        </v-card>
      </v-layout>
    </v-container>
  </div>
</div>
</template>

<script>

import Editpassword from '@/components/Editpassword'
import Header from '@/components/Header'

export default {
  components: {
    Editpassword,
    Header
  },
  data: function () {
    return {
      valid: true,
      user_pk: this.$session.get('lo').user_pk,
      rules: {
        counter: value => value.length <= 25 || 'Max 25 characters',
        email: value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail.'
        }
      },
      user: {
        description: '',
        email: '',
        grade: 0,
        nickname: '',
        name: '',
        phone_number: '',
        profile_image: '',
        user_pk: 0,
        together: 0,
        password: ''
      }
    }
  },
  methods: {
    getUserInfo () {
      var swg_addr = 'http://192.168.31.84:8080/api/user/'
      var user_key = this.user_pk
      var url = swg_addr + user_key

      axios.post(url)
        .then(res => {
          console.log(res.data)
          this.user.email = res.data.email
          this.user.name = res.data.name
          this.user.nickname = res.data.nickname
          this.user.email = res.data.email
          this.user.description = res.data.description
          this.user.phone_number = res.data.phone_number
          this.user.together = res.data.together
          this.user.user_pk = res.data.user_pk
        })
    },
    submit () {
      if (this.$refs.form.validate()) {
        var URL = 'http://192.168.31.84:8080/api/user/' + this.user.user_pk + '/update'
        axios.post(URL, this.user)
          .then(res => {
            console.log(this.user)
          })
      }
    }
  },
  mounted () {
    this.getUserInfo()
  }
}
</script>
