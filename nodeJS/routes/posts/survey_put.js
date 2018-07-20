const fs = require("fs")


module.exports.in = (req, res) => {
    if(!(req.body.id&&req.body.name&&req.body.inform&&req.body.price&&req.body.category&&req.body.questions&&req.body.deadline&&req.body.active)){
        res.send({"success" : false, "log" : '입력값 오류'}).end();
    }
    else{
        fs.readFile("routes/posts/data.json",'utf8',(err,data)=>{
            if(err){
                res.send(err).end();
            }
            else{
                let obj = JSON.parse(data);
                obj.push({
                    "id" : req.body.id,
                    "name" : req.body.name,
                    "inform" : req.body.inform,
                    "price" : parseInt(req.body.price),
                    "category" : parseInt(req.body.category),
                    "questions" : JSON.parse(req.body.questions),
                    "deadline" : JSON.parse(req.body.deadline),
                    "active" : parseInt(req.body.active),
                    "results" : []
                })

                fs.writeFile('routes/posts/data.json',JSON.stringify(obj),'utf8',(err)=>{
                    if(err){
                        res.send({"success" : false, "log" : err}).end();
                    }
                    res.send({"success" : true}).end();
                });
            }
        });
    }

}
