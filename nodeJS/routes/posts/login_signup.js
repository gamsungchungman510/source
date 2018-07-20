const fs = require("fs")


module.exports.in = (req, res) => {
    if(!(req.body.id&&req.body.pw&&req.body.category)){
        res.send({"success" : false, "log" : '입력값 오류'}).end();
    }
    else{
        fs.readFile("routes/posts/accounts.json",'utf8',(err,data)=>{
            if(err){
                res.send({"success" : false, "log" : err}).end();
            }
            else{
                let obj = JSON.parse(data);
                same = false
                for (let i = 0; i < obj.length; i++){
                    if (obj[i].id === req.body.id){
                        same = true
                        break
                    }
                }
                if (same){
                    res.send({"success" : false, "log" : 0}).end();
                }
                else {
                    let tmp = req.body.category.replace('[','').replace(']','').split(',')
                    for (let i = 0; i < tmp.length; i++){
                        tmp[i] = parseInt(tmp[i])
                    }
                    obj.push({
                        "id" : req.body.id,
                        "pw" : req.body.pw,
                        "category" : tmp,
                        "point" : 200,

                    })
                    fs.writeFile('routes/posts/accounts.json',JSON.stringify(obj),'utf8',(err)=>{
                        if(err){
                            res.send({"success" : false, "log" : err}).end();
                        }
                        res.send({"success" : true}).end();
                    });
                }
            }
            
        });
    }

}
