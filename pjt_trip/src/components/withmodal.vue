<template>
  <v-layout justify-center>
    <v-dialog v-model="dialog" fullscreen hide-overlay transition="dialog-bottom-transition">
      <template v-slot:activator="{ on }">
        <v-btn dark v-on="on">함께 여행하기</v-btn>
      </template>
      <v-card>
        <v-toolbar dark color="primary">
          <v-btn icon dark @click="dialog = false">
            <v-icon>close</v-icon>
          </v-btn>
          <v-toolbar-title>Together with you !</v-toolbar-title>
          <v-spacer></v-spacer>
          <!-- <v-toolbar-items>
            <v-btn dark text @click="dialog = false">Save</v-btn>
          </v-toolbar-items> -->
        </v-toolbar>
        <v-list three-line subheader>
          <v-subheader>{{this.$session.get('lo').nickname}} 여행자님!</v-subheader>
            <v-list-item>
                <v-list-item-content>
                  <!-- <v-list-item-title>Content filtering {{Traveling.props.city}}</v-list-item-title> -->
                  <v-list-item-subtitle class="text-center"> 현재 {{Traveling.props.city}}에서는 {{login_users.length}}명의 여행자가 여행중이네요! </v-list-item-subtitle>
                </v-list-item-content>
            </v-list-item>
            <!-- <v-list-item>
              <v-list-item-content>
                <v-list-item-title>Password</v-list-item-title>
                <v-list-item-subtitle>Require password for purchase or use password to restrict purchase</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item> -->
        </v-list>
        <v-divider></v-divider>
        <v-layout>
        <v-flex>
        <v-list three-line subheader>
          <v-subheader><v-btn color="pink" dark @click="chat_lists()">{{Traveling.props.city}} 목록 보기</v-btn></v-subheader>
          <v-list-item>
            <!-- <v-list-item-action>
              <v-checkbox v-model="notifications"></v-checkbox>
            </v-list-item-action> -->
            <v-list-item-content>
              <!-- <v-list-item-title>Notifications</v-list-item-title> -->
            <v-list-item-subtitle v-if="chats.length">
            <template>
              <v-card
                max-width="400"
              >
                <v-container
                  class="pa-2"
                  fluid
                  grid-list-md
                >
                  <v-layout column>
                    <v-flex
                      v-for="(chat, i) in chats"
                      :key="i">
                      <v-card
                        :key="chat.chat_pk"
                        dark
                        v-if="chat.opener_id === $session.get('lo').user_pk">
                        <v-list-item three-line>
                          <v-list-item-content class="align-self-start">
                            <v-list-item-title
                              class="headline mb-2"
                              v-text="chat.title"
                            ></v-list-item-title>
                            <v-list-item-subtitle v-text="chat.description"></v-list-item-subtitle>
                            <v-list-item-subtitle v-text="chat.sex"></v-list-item-subtitle>
                            <v-btn color="pink" dark @click="delete_chat(chat.chat_pk, $session.get('lo').user_pk)">방 삭제</v-btn>
                            <!-- <v-btn color="pink" dark  @click="join_chat(chat.chat_pk,$session.get('lo').user_pk)">채팅 참여하기</v-btn> -->
                            <!-- <v-btn color="pink" dark  @click="out_chat(chat.chat_pk,$session.get('lo').user_pk)">채팅 나가기</v-btn> -->

                          </v-list-item-content>
                          <!-- <v-list-item-avatar
                            size="125"
                            tile
                          >
                            <v-img :src="item.src"></v-img>
                          </v-list-item-avatar> -->
                        </v-list-item>
                      </v-card>
                      <v-card
                        :key="chat.chat_pk"
                        dark
                        v-else>
                        <v-list-item three-line>
                          <v-list-item-content class="align-self-start">
                            <v-list-item-title
                              class="headline mb-2"
                              v-text="chat.title"
                            ></v-list-item-title>
                            <v-list-item-subtitle v-text="chat.sex"></v-list-item-subtitle>
                            <v-btn color="pink" dark  @click="join_chat(chat.chat_pk,$session.get('lo').user_pk)">채팅 참여하기</v-btn>
                            <!-- <v-btn color="pink" dark  @click="out_chat(chat.chat_pk,$session.get('lo').user_pk)">채팅 나가기</v-btn> -->
                            <!-- <v-list-item-subtitle v-text="item.artist"></v-list-item-subtitle> -->
                          </v-list-item-content>
                        </v-list-item>
                      </v-card>
                      {{chat.user_cnt}}명 접속
                    </v-flex>
                  </v-layout>
                </v-container>
              </v-card>
            </template>
            </v-list-item-subtitle>
            <!-- </v-list-item-subtitle v-else>
            <h3>채팅방 목록이 없네요!</h3>
            </v-list-item-subtitle> -->
            </v-list-item-content>

          </v-list-item>


        </v-list>
      </v-flex>
      <v-flex>
        <v-btn color="pink" dark @click="click_create = true">여행 인연 찾기</v-btn>
            <v-layout v-if="click_create" column align-center>
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
                  생성
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


        <!-- 방장이면 삭제하기가 방장이 아니면 방 참여하기 or 나가기 -->
      </v-flex>
      </v-layout>
      </v-card>
    </v-dialog>
  </v-layout>
</template>
<script>
import Traveling from '@/components/Traveling'
import withmodal from '@/components/withmodal'
  export default {
    name: 'withmodal',
    components: {
      Traveling,
      withmodal
    },
    props:{
      chatusers : []
    },
    data () {
      return {
        dialog: false,
        notifications: false,
        sound: true,
        widgets: false,
        Traveling,
        login_users: [],
        chats:[],
        chat_creater:[],
        click_create: false,
        join_click: false,
        chat_users: {},
        withmodal,
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
      }
    },
    methods:{
      create_chat(){
        var url = 'http://192.168.31.84:8080/api/chat/create'
        axios.post(url,{
                    description:this.description, title:this.title,
                    location:Traveling.props.city, user_id:this.$session.get('lo').user_pk,
                    opener_id:this.$session.get('lo').user_pk, nickname:this.$session.get('lo').nickname,
                    sex:this.select}).then((res)=>{
                      if(res.data !== ""){
                        console.log('채팅방 만들기')
                        console.log(res)
                        this.chat_lists();
                        this.click_create = false
                        axios.get('http://192.168.31.57:8000/create?room='+ res.data.chat_pk + '&creater=' + res.data.nickname +'&creater_id=' + res.data.opener_id)
                        window.open('http://192.168.31.57:8000/path?chat_num='+ res.data.chat_pk + '&name=' + res.data.nickname + '&user_id=' + res.data.opener_id,'_blank', 'width=700, height=700')
                      }
                        else{
                        alert("이미 채팅방을 개설하셨어요!")
                      }
                    });
      },
      chat_lists(){
        var chat_url="http://192.168.31.84:8080/api/chat/locate/"
        var city_name = Traveling.props.city
        var URL = chat_url + city_name
        axios.get(URL).then((res)=>{
          console.log('해당 지역의 채팅방 목록 보여주기')
          console.log(res)
          this.chats = res.data
          this.chat_users={}
          // for(var [index, chatroom] of Object.entries(res.data)){
          //   // console.log(chatroom.chat_pk)
          //   // var chat_users_url="http://192.168.31.84:8080/api/chat/" + chatroom.chat_pk
          //   axios.get("http://192.168.31.84:8080/api/chat/" + chatroom.chat_pk).then((response)=>{
          //     // console.log(chatroom.chat_pk)
          //     console.log(Object.keys(response.data)[0])
          //     console.log(response.data[Object.keys(response.data)[0]])
          //     console.log(withmodal.props.chatusers)
          //     this.chat_users[Object.keys(response.data)[0]]=response.data[Object.keys(response.data)[0]]
          //     withmodal.props.chatusers.type.push(this.chat_users)
          //   })
          // }console.log(this.chat_users) //{ 7 : [] , 8: [], 9: []}
        })
      },
      delete_chat(chat_pk,num){
        // creater가 채팅방 삭제하기
        console.log(chat_pk,num)
        var url = 'http://192.168.31.84:8080/api/chat/' + chat_pk + '/delete/'+ num
        axios.get(url).then((res)=>{
          console.log('삭제')
          alert("삭제 되었습니다")
          this.chat_lists();
        })
      },
      join_chat(chat_pk,user_pk){
        // user가 채팅방 참여하기
        // 192.168.31.57:8000
        this.join_click = true
        var url ='http://192.168.31.84:8080/api/chat/' + chat_pk + '/enter/' + user_pk
        var chat_url='http://192.168.31.57:8000/'+ chat_pk
        axios.post(url,{nickname:this.$session.get('lo').nickname, chat_id:chat_pk, user_id: user_pk}).then((res)=>{
          console.log(res)
          if(res.data === 0 ){
          console.log('사용자의 채팅방참여')
          window.open('http://192.168.31.57:8000/path?chat_num='+ chat_pk + '&name=' + this.$session.get('lo').nickname + '&user_id=' + user_pk,'_blank', 'width=700, height=700', '_blank', 'width=700, height=700',);
          this.chat_lists();}
          else if(res.data === 1){
            alert('성별이 맞지 않아 참여할 수 없습니다.')
          }
          // else if(res.data === 2){
          //
          // }
        })
      },
      out_chat(chat_pk,user_pk){
        //user가 채팅방 나가기
        console.log('나가기',chat_pk,user_pk)
        var url ='http://192.168.31.84:8080/api/chat/' + chat_pk + '/leave/' + user_pk
        axios.get(url).then((res)=>{
          console.log(res)
          this.join_click=false
          this.chat_lists();
        })

      },
      setuser() {
        if (this.$session.get('lo')) {
          this.login_users = []
          var city_name = Traveling.props.city
          console.log(Traveling.props.city)
          var spring_url = 'http://192.168.31.84:8080/api/user/locate'
          axios.post(spring_url, {
            location: city_name,
            loginuser_pk: 0,
            user_id: 0
          }).then((res) => {
            console.log(res.data)
            for (var [key, login_user] of Object.entries(res.data)) {
              this.login_users.push(login_user)
            }
          })
        }
      },
      validate () {
        if (this.$refs.form.validate()) {
          this.snackbar = true
          console.log(this.title)
          console.log(this.description)
          console.log(this.select)
          this.create_chat();
        }
      },
      reset () {
        this.$refs.form.reset()
      },
    },
    mounted(){
      setTimeout(this.setuser,500);
      // console.log(1111)
      // setTimeout(this.chat_lists,500);
    }
  }
</script>
