var express = require('express')
var bodyParser = require('body-parser');
var app = express()
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json())
app.use(express.static('./'))
var session = require('express-session');
var sessionOptions = {
  secret: "secret",
  resave : true,
  saveUninitialized : false
};

app.use(session(sessionOptions));



app.listen(3003, function () {
	console.log('Example app listening on port 3003!')
})

