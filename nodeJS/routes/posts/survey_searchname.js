const fs = require("fs")


module.exports.in = (req, res) => {
    if(!(req.body.name)){
        res.send({"success" : false, "log" : '입력값 오류'}).end();
    }
    else{
        fs.readFile("routes/posts/data.json",'utf8',(err,data)=>{
            if(err){
                res.send({"success" : false, "log" : err}).end();
            }
            else{
                let obj = JSON.parse(data);
                let find = true
                for (let j = 0; j < obj.length; j++){
                    if (req.body.name === obj[j].name){
                        res.send({"success" : true, "survey" : obj[j]}).end()
                        find = false
                        break
                    }
                }


                if(find){
                    res.send({"success" : false, "log" : "그런 값이 없습니다."}).end()

                }
            }
        });
    }

}
