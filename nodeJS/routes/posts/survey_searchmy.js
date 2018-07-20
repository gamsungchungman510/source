const fs = require("fs")


module.exports.in = (req, res) => {
  if(!(req.body.id)){
      res.send({"success" : false, "log" : '입력값 오류'}).end();
  }
  else{
    fs.readFile("routes/posts/data.json",'utf8',(err,data)=>{
      if(err){
        res.send({"success" : false, "log" : err}).end();
      }
      else{
        let obj = JSON.parse(data);
        let tmp = []
          for (let j = 0; j < obj.length; j++){
              if (req.body.id === obj[j].id){
                  tmp.push(obj[j])
              }
          }
        if(tmp.length === 0){
            res.send({"success" : true, "list" : []}).end()

        }else{
            res.send({"success" : true, "list" : tmp}).end()
        }
      }
    });
  }
  
}
