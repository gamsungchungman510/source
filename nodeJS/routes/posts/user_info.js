const fs = require("fs")

module.exports.in = (req, res) => {
    if(!(req.body.id)){
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
                    if (obj[i].id === req.body.id){
                        res.send({"success" : true, "data" : obj[i]}).end();
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
