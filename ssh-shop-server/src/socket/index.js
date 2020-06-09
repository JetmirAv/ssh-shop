const { UserSocket } = require("../models/sequelize");
const auth = require("./auth");
const chat = require("./chat");
const helpers = require("./helpers");

module.exports = (io) => {
  // Handle connection
  io.on("connection", async (socket) => {
    console.log("Connected succesfully to the socket ... " + socket.id);

    socket.on("test", (data) => {
      console.log("====================================");
      console.log({ data });
      console.log("====================================");
    });

    socket.on("sign_in", (data) => auth.onSignIn(data, socket));

    socket.on("disconnect", () => auth.onDisconnect(socket));

    socket.on("message", (data) => chat.onMessage(data, io));

    socket.on("make-offer", chat.onMakeOffer);

    socket.on("make-answer", chat.onMakeAnswer);
  });
};
