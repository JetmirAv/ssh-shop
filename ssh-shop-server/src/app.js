require("dotenv").config();
const fs = require("fs");
const path = require("path");
const express = require("express");
const cors = require("cors");
const logger = require("morgan");
const debug = require("debug")("server:server");

const host = process.env.NODE_HOST || "localhost";
const port = +process.env.NODE_PORT || 3000;

require("./models/sequelize");

const errorHandler = require("./errors");
var mongodb = require("./config/mongodb");

mongodb.connectToServer(function (err, client) {
  if (err) console.log(err);
});

const routes = require("./routes");
const socketRoutes = require("./socket");

const key = fs.readFileSync(path.resolve(__dirname, "../key.pem"));
const cert = fs.readFileSync(path.resolve(__dirname, "../cert.pem"));

const app = express();
// const https = require("https").createServer({ key, cert }, app);
const httpServer = require("http").createServer(app);
const io = require("socket.io")(httpServer);

app.use(cors());
app.use(logger("dev"));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// setting up routes
routes(app);

// setting up socket routes
socketRoutes(io);
// io.on("connection", (socket) => {
//   console.log({ socket });
// });

// error handler
app.use(errorHandler);

/**
 * Event listener for HTTP server "listening" event.
 */

const onListening = () => {
  debug("Server started on: http://" + host + ":" + port);
  console.info("Server started on: http://" + host + ":" + port);
};

httpServer.listen(port, host, onListening);
