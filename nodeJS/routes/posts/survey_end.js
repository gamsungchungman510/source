const fs = require("fs")


module.exports.in = (req, res) => {
    if(!(req.body.id&&req.body.name&&req.body.point&&req.body.maker&&req.body.data)){
        res.send({"success" : false, "log" : '입력값 오류'}).end();
    }
    else{
        let point = parseInt(req.body.point)
        fs.readFile("routes/posts/accounts.json",'utf8',(err,data)=>{
            if(err){
                res.send({"success" : false, "log" : err}).end();
            }
            else{
                let maker = 0
                let my = 0
                let obj = JSON.parse(data);
                for (let i = 0; i < obj.length; i++){
                    if (obj[i].id === req.body.maker) {
                        if (obj[i].point-point < 0) {
                            obj[i].point = 0
                            fs.writeFile('routes/posts/accounts.json',JSON.stringify(obj),'utf8',(err)=>{
                                if(err){
                                    res.send({"success" : false, "log" : err}).end();
                                }
                                res.send({"success" : true, "point":obj[i].point}).end();
                            });
                            for (let j = 0; j < obj.length; j++) {
                                if (obj[j].id === req.body.maker)
                                    obj[j].active = false
                            }
                        }
                        else{
                            obj[i].point = parseInt(obj[i].point).point-point
                            fs.writeFile('routes/posts/accounts.json',JSON.stringify(obj),'utf8',(err)=>{

                            });
                        }
                        maker = obj[i].point
                        break
                    }
                }
                for (let i = 0; i < obj.length; i++){
                    if (obj[i].id === req.body.id) {
                        if (obj[i].point === 0) {
                            obj[i].point = point
                            fs.writeFile('routes/posts/accounts.json',JSON.stringify(obj),'utf8',(err)=>{
                            });
                            for (let j = 0; j < obj.length; j++) {
                                if (obj[j].id === req.body.maker)
                                    obj[j].active = true
                            }
                        }
                        else{
                            obj[i].point = parseInt(obj[i].point)+point
                            fs.writeFile('routes/posts/accounts.json',JSON.stringify(obj),'utf8',(err)=>{
                            });
                        }
                        my = obj[i].point
                        break
                    }
                }
                fs.readFile("routes/posts/data.json",'utf8',(err,data)=>{
                    if(err){
                        res.send(err).end();
                    }
                    else{
                        let obj = JSON.parse(data);
                        for (let i = 0; i < obj.length; i++){
                            if (obj[i].name === req.body.name) {
                                let tmp = JSON.parse(obj[i].result)
                                tmp.push(data)
                                obj[i].result = tmp
                                break
                            }
                        }

                        fs.writeFile('routes/posts/data.json',JSON.stringify(obj),'utf8',(err)=>{
                            if(err){
                                res.send({"success" : false, "log" : err}).end();
                            }
                            res.send({"success" : true}).end();
                        });
                    }
                });
                res.send({"success" : true, "my" : my, 'maker' : maker})

            }
        });




    }

}
