<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{on}">
        <v-btn flat color="grey" v-on="on" @click="clear()">
          <v-icon small left>edit</v-icon>
          <span>Edit Password</span>
        </v-btn>
      </template>

      <v-form ref="form">
        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>Edit Password</v-card-title>

          <v-card-text>
            <v-text-field
              v-model="pw1"
              name="password1"
              label="Password"
              id="password1"
              prepend-icon="security"
              clearable
              hint="변경할 비밀번호를 입력해주세요."
              :rules="rules"
              :counter="max"
              :type="'password'"
            ></v-text-field>
            <v-text-field
              v-model="pw2"
              name="password2"
              label="Comfirm Password"
              id="password2"
              prepend-icon="security"
              clearable
              hint="변경할 비밀번호를 다시 입력해주세요."
              :rules="rules"
              :counter="max"
              :type="'password'"
            ></v-text-field>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="grey" flat text @click="submit()">Change</v-btn>
          </v-card-actions>
        </v-card>
      </v-form>
    </v-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dialog: false,
      pw1: '',
      pw2: '',
      max: 25,
      user_pk: this.$session.get('lo').user_pk,
      user: {
        password: '',
        user_pk: 0
      }
    }
  },
  computed: {
    rules () {
      const rules = []
      if (this.max) {
        const rule =
          v => (v || '').length <= this.max ||
            `A maximum of ${this.max} characters is allowed`

        rules.push(rule)
      }

      if (!this.allowSpaces) {
        const rule =
          v => (v || '').indexOf(' ') < 0 ||
            '공백문자를 허용하지 않습니다.'

        rules.push(rule)
      }

      if (this.pw2) {
        const rule =
          v => (!!v && v) === this.pw2 ||
            '비밀번호가 일치하지 않습니다.'

        rules.push(rule)
      }

      return rules
    }
  },
  watch: {
    pw2: 'validateField',
    max: 'validateField',
    pw1: 'validateField'
  },
  methods: {
    validateField () {
      this.$refs.form.validate()
    },
    submit () {
      if (this.$refs.form.validate()) {
        this.user.password = this.pw1
        this.user.user_pk = this.user_pk
        var URL = 'http://192.168.31.84:8080/api/user/' + this.user_pk + '/changepassword'
        axios.post(URL, this.user)
          .then(res => {
            console.log(this.user)
            console.log(res)
          })
      }
      this.dialog = false
    },
    clear () {
      this.pw1 = ''
      this.pw2 = ''
    }
  }
}
</script>
