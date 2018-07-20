const express = require('express');

const controller = {
    put : require('./survey_put.js'),
    search : require('./survey_search.js'),
    signup : require('./login_signup.js'),
    signin : require('./login_signin.js'),
    userinfo : require('./user_info.js'),
    namesearch : require('./survey_searchname.js'),
    mysearch : require('./survey_searchmy.js'),
    usepoint : require('./use_point.js'),
    end : require('./survey_end.js')
};

const router = express.Router();

router.post('/put', controller.put.in);

router.post('/search', controller.search.in);

router.post('/signup', controller.signup.in);

router.post('/userinfo', controller.userinfo.in);

router.post('/signin', controller.signin.in)

router.post('/namesearch', controller.namesearch.in)

router.post('/usepoint', controller.usepoint.in)

router.post('/mysearch', controller.mysearch.in)

router.post('/end', controller.end.in)

module.exports = router;
