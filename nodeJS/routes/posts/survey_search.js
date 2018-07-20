const fs = require("fs")


module.exports.in = (req, res) => {
  if(!(req.body.id&&req.body.category)){
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
          if (JSON.parse(req.body.category).length === 0){
              for (let i = 0;  i < obj.length; i++){
                  if (req.body.id !== obj[i].id){
                      tmp.push(obj[i])
                  }
              }
          }
          else{
            let category = JSON.parse(req.body.category)
              for (let i = 0; i < category.length; i++){
                  for (let j = 0; j < obj.length; j++){
                      if (req.body.id !== obj[j].id && category[i] === obj[j].category && obj[j].active === 1){
                          tmp.push(obj[j])
                      }
                  }
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
