<template>
  <v-layout column align-center>
    <v-layout wrap justify-space-around>
    </v-layout>
    <v-form
      ref="form"
    >
      <v-text-field
        v-model="title"
        label="제목을 입력해주세요"
        required
      ></v-text-field>
      <v-text-field
        v-model="description"
        label="함께할 여행지, 여행 목적과 함께 내용을 기재해주세요."
        required
      ></v-text-field>

      <v-select
        v-model="select"
        :items="sex"
        :rules="[v => !!v || 'Item is required']"
        label="성별"
        required
      ></v-select>

      <v-checkbox
        v-model="checkbox"
        :rules="[v => !!v || 'You must agree to continue!']"
        label="제목과 내용, 성별을 모두 작성하셨나요?"
        required
      ></v-checkbox>

      <v-btn
        :disabled="!valid"
        color="success"
        class="mr-4"
        @click="validate"
      >
        Validate
      </v-btn>

      <v-btn
        color="error"
        class="mr-4"
        @click="reset"
      >
        Reset Form
      </v-btn>

    </v-form>
  </v-layout>
</template>

<script>
import axios from 'axios'
import VueAxios from 'vue-axios'
import index from '@/views/index'
import Map from  '@/components/Map'
import test from  '@/views/test'
export default {
  name: 'dmap',
  data: () => ({
      valid: true,
      title: '',
      description:'',
      select: null,
      sex: [
        '남성',
        '여성',
        '남/여 무관'
      ],
      checkbox: false,

    }),

    methods: {
      validate () {
        if (this.$refs.form.validate()) {
          this.snackbar = true
          console.log(this.title)
          console.log(this.description)
          console.log(this.select)
        }
      },
      reset () {
        this.$refs.form.reset()
      },
    },
};
</script>

<style scoped>

</style>
