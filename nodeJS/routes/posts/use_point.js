const fs = require("fs")

module.exports.in = (req, res) => {
    if(!(req.body.id&&req.body.minus)){
        res.send({"success" : false, "log" : '입력값 오류'}).end();
    }
    else{
        fs.readFile("routes/posts/accounts.json",'utf8',(err,data)=>{
            if(err){
                res.send({"success" : false, "log" : err}).end();
            }
            else{
                let obj = JSON.parse(data);
                let same = true
                for (let i = 0; i < obj.length; i++){
                    if (obj[i].id === req.body.id) {
                        if (obj[i].point-req.body.minus < 0) {
                            for (let j = 0; j < obj.length; j++){
                                if (obj[j].id === req.body.id)
                                    obj[j].active = false
                            }
                        res.send({"success": false, "data": 1}).end();
                        }
                        else{
                            obj[i].point = obj[i].point-req.body.minus
                            fs.writeFile('routes/posts/accounts.json',JSON.stringify(obj),'utf8',(err)=>{
                                if(err){
                                    res.send({"success" : false, "log" : err}).end();
                                }
                                res.send({"success" : true, "point":obj[i].point}).end();
                            });
                        }


                        same = false
                        break
                    }
                }
                if (same)
                    res.send({"success" : false, "log" : 0}).end();
            }

        });
    }

}
