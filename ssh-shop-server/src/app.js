require("dotenv").config();
const express = require("express");
const logger = require("morgan");
const app = express();

require("./models/sequelize");

var mongodb = require( "./config/mongodb" );

mongodb.connectToServer( function( err, client ) {
  if (err) console.log(err);

const routes = require("./routes");

const handleError = require("./errors");

app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// setting up routes
routes(app);

// error handler
app.use(handleError);

} );
module.exports = app;
