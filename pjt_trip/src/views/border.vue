<template>
  <div>
  <Header></Header>
 <div class="container">
   <v-layout wt text-center text-xs-center justify-center style="padding-bottom:5%">
     <div class="wt align-center justify-center text-center">
         <p style="font-size: 60px;">Notice</p>
     </div>
   </v-layout>
   <table class="freetable table width-100%">
     <col width="10%"><col width="30%"><col width="10%"><col width="15%"><col width="20%">
     <thead>
       <tr>
         <th>번호</th>
         <th>제목</th>
         <th>작성자</th>
         <th>작성일</th>
         <th>조회수</th>
       </tr>
     </thead>
     <tbody>
     <tr v-for="board in sortedData">
       <td v-html="board.border_pk"></td>
       <td v-html="board.title" @click="showDetail(board)"></td>
       <!-- <td v-html="board.title"><router-link :to="{ name: 'freedetail', params: {id : board.id} }"></router-link></td> -->
       <td v-html="board.name"></td>
       <td v-html="board.created_at"></td>
       <td v-html="board.hits"></td>
     </tr>
     </tbody>
   </table>
   <div>
     <p>공지사항을 확인하세요</p>
   </div>
   <div v-if="this.$session.get('lo')">
   <div v-if="this.$session.get('lo').user_pk == 1">
   <div class="container max-width-lg">
     <button class="btn btn--primary btn--md" style="margin:5px;" @click="boardwrite">글쓰기</button>
   </div>
  </div>
</div>

 </div>
</div>
</template>
<script>
import Header from '@/components/Header'
export default {
 name : "border",
 components : {
   Header
 },
 props :[
   'topic'
 ],
 data() {
   return {
     newtoggle : true,
     viewtoggle : true,
     isNew : false,
     noticeToggle : false,
     topic_articles : [],
    topic_notices : []
     }
   },
   created() {
     // alert(this.topic);
   },
   methods : {
     boardwrite() {
       this.$router.push({name:'freewrite'});
       // this.$router.push({name:"write"});
     },
     showDetail(board) {
       // this.$router.push({path: ${this.topic}/detail/${board.id}, params: { info : board}});
       this.$router.push({name: 'freedetail' , params: { id: board.border_pk}});
     },
     newest: function() {
       // Set slice() to avoid to generate an infinite loop!
       // alert("newest")
       this.isNew = true
       if(this.newtoggle) {
           this.newtoggle = false;
       }else {
         this.newtoggle = true;
       }
     },
     viewSort: function() {
       this.isNew = false;
       if(this.viewtoggle) {
         this.viewtoggle = false;
       }else {
         this.viewtoggle = true;
       }
     },
     created(){
       var spring_url = 'http://192.168.31.84:8080/api/border/'
       axios.post(spring_url).then((res)=>{
            this.topic_articles=res.data
     })
   },
     notice() {
       this.noticeToggle = !this.noticeToggle;
     }
   },
   computed: {
     sortedData : function() {
       return this.topic_articles.sort((a,b) => {
         if(this.isNew) {
           if(this.newtoggle) {
             return a.id - b.id
           }else {
             return b.id - a.id
           }
         }else {
           if(this.viewtoggle) {
             return a.views - b.views
           }else {
             return b.views - a.views
           }
         }
       });
     }
   },
   watch: {
     newest() {},
     viewSort() {},
   },
   mounted() {
     this.created();
   }
}
</script>
<style>
table.freetable {
   border-collapse: separate;
   border-spacing: 3px;
   text-align: left;
   line-height: 1.5;
   border-top: 1px solid #ccc;
   margin : 20px 10px;
}
table.freetable th {
   /* width: 20%; */
   padding: 10px;
   font-weight: bold;
   vertical-align: top;
   border-bottom: 1px solid #ccc;
}
table.freetable td {
   /* width: 20%; */
   padding: 10px;
   vertical-align: top;
   border-bottom: 1px solid #ccc;
}
</style>
