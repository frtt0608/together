<template>
 <div class="container">
   <table class="freetable table width-100%">
     <col width="10%"><col width="30%"><col width="10%"><col width="15%"><col width="20%">
     <tbody>
     <tr>
       <td v-html="topic_notices.border_pk"></td>   <td  v-html="topic_notices.title"></td>  <td  v-html="topic_notices.name"></td> <td  v-html="topic_notices.hits"></td>
     </tr>
     <tr>
       <!-- <td v-html="board.title"><router-link :to="{ name: 'freedetail', params: {id : board.id} }"></router-link></td> -->
       <td></td><td  v-html="topic_notices.content"></td>
     </tr>
     </tbody>
   </table>
   <div class="container max-width-lg">
     <button class="btn btn--primary btn--md" style="margin:5px;" @click="borderlist()" >목록</button>
   </div>
 </div>
</template>
<script>
export default {
 name : "freedetail",
 components : {
 },
 data() {
   return {
    topic_notices : [],
    id:''
     }
   },
   methods : {
     created(){
       this.id = this.$route.params.id;
       var spring_url = 'http://192.168.31.84:8080/api/border/'+this.id
       axios.get(spring_url).then((res)=>{
         this.topic_notices=res.data;
     })
   },
   borderlist(){
       this.$router.push({name:'border'});
   }
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
