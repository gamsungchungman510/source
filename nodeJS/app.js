const express = require('express');
const app = express();
const http = require('http').Server(app)
const fs = require("fs")
const bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');
const path = require('path');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));


app.get('/api/get/all', (req, res) => {
    fs.readFile("routes/posts/data.json",'utf8',(err,data)=>{
        if(err){
          res.send(err).end();
        }
        else{
          let obj = JSON.parse(data);
          res.send(obj).end()
          
        }
      });
});

app.use('/api/posts', require('./routes/posts'));
 
app.get('*', (req, res) => {
    res.status(404).end();
});

app.listen(3000, () => {
    console.log('localhost:3000 에서 실행중');
});
