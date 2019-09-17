var express = require('express')
  , app = express()
  , http = require('http')
  , server = http.createServer(app)
  , io = require('socket.io').listen(server);
var axios=require('axios')
server.listen(8000, function() {
    console.log('서버 실행 중..')
})
// usernames which are currently connected to the chat
var usernames = {};
var user_ids= {};
// rooms which are currently available in chat
var rooms = [];
var room_num;
var name;
var user_id;
var creaters= [];
var chat_num;
const request = require('request');

// var xhr = XMLHttpRequest();
// routing
app.get('/path', function (req, res) {
  user_id=req.query.user_id
  name = req.query.name
  chat_num=req.query.chat_num
  console.log(user_id,name,chat_num)
  res.sendfile(__dirname + '/static/index.html');
  // path?name=dd&chat_num=pk
});

app.get('/create',function(req,res){
  // create?room=pk&creater=ss
  room_num = req.query.room
  if (rooms.length){
    for(var room in rooms){
      console.log(room)
      if(room === req.query.room){console.log('잇음')}
      else{
        rooms.push(req.query.room)
        creaters[req.query.creater]=req.query.creater_id
        console.log('없음')
      }
      break;
    }
    console.log(rooms,creaters)
  }
  else{
    rooms.push(req.query.room)
    creaters[req.query.creater]=req.query.creater_id
    console.log(rooms,creaters)
  }

});

io.sockets.on('connection', function (socket) {

	// when the client emits 'adduser', this listens and executes
	socket.on('adduser', function(start){
		// store the username in the socket session for this client
		socket.username = name;
		// store the room name in the socket session for this client
		socket.room = chat_num;
		// add the client's username to the global list
		usernames[name] = name;
    user_ids[name] = user_id;
		// send client to room 1
		socket.join(chat_num);
		// echo to client they've connected
		socket.emit('updatechat', 'SERVER', 'you have connected to'+ chat_num);
		// echo to room 1 that a person has connected to their room
		socket.broadcast.to('room1').emit('updatechat', 'SERVER', name + ' has connected to this room');
		socket.emit('updaterooms', rooms, chat_num);
	});

	// when the client emits 'sendchat', this listens and executes
	socket.on('sendchat', function (data) {
		// we tell the client to execute 'updatechat' with 2 parameters
		io.sockets.in(socket.room).emit('updatechat', socket.username, data);
	});

	socket.on('switchRoom', function(newroom){
		socket.leave(socket.room);
		socket.join(newroom);
		socket.emit('updatechat', 'SERVER', 'you have connected to '+ newroom);
		// sent message to OLD room
		socket.broadcast.to(socket.room).emit('updatechat', 'SERVER', socket.username+' has left this room');

		// update socket session room title
		socket.room = newroom;
		socket.broadcast.to(newroom).emit('updatechat', 'SERVER', socket.username+' has joined this room');
		socket.emit('updaterooms', rooms, newroom);
	});


	// when the user disconnects.. perform this
	socket.on('disconnect', function(){
		// remove the username from global usernames list
		delete usernames[socket.username];
		// update list of users in chat, client-side
		io.sockets.emit('updateusers', usernames);
    if(socket.username in creaters === true){
      // xhr.open('GET','http://192.168.31.84:8080/api/chat/' + socket.room + '/delete/'+ creaters[socket.username]');
      // xhr.setRequestHeader('Content-type',"application/json")
      // xhr.send(data)
      // console.log('spring전송')
      // axios.get('http://192.168.31.84:8080/api/chat/' + socket.room + '/delete/'+ creaters[socket.username]')
      // io.sockets.emit('creater_out',socket.room,creaters[socket.username]);
      // var response_out = http.getUrl('http://192.168.31.84:8080/api/chat/' + socket.room + '/delete/'+ creaters[socket.username], {format: 'xmljs'})
      // console.log(response_out)
      request('http://192.168.31.84:8080/api/chat/' + socket.room + '/delete/'+ creaters[socket.username], function (error, response, body) {
        //callback
        });
      delete creaters[socket.username];
      delete user_ids[socket.username];
      console.log(creaters,user_ids)
    }
    else if(socket.username in creaters === false){
      console.log(1111)
      request('http://192.168.31.84:8080/api/chat/' + socket.room + '/leave/'+ user_ids[socket.username], function (error, response, body) {
        //callback
        });
      delete user_ids[socket.username];
      console.log(user_ids)
    }
		// echo globally that this client has left
		socket.broadcast.to(socket.room).emit('updatechat', 'SERVER', socket.username + ' has left this room');
		socket.leave(socket.room);
	});
});
