require("dotenv").config();
const express = require("express");
const logger = require("morgan");

require("./config/mongoose");
require("./models/sequelize");

const handleError = require("./errors");
const routes = require("./routes");

const app = express();

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));


//set the template engine ejs
app.set('view engine', 'ejs');

// setting up routes
routes(app);

// error handler
app.use(handleError);

module.exports = app;
