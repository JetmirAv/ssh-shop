const { UserSocket } = require("../models/sequelize");
const auth = require("./auth");
const chat = require("./chat");
const helpers = require("./helpers");

module.exports = (io) => {
  // Handle connection
  io.on("connection", (socket) => {
    console.log("Connected succesfully to the socket ... " + socket.id);

    // if (socket.handshake.headers.referer.includes("meet")) {
    //   let url = socket.handshake.headers.referer;
    //   url = url.split("channels/");
    //   url = url[1].split("/");
    //   console.log({ url });

    //   socket.join(url[0]);
    // }

    socket.on("create-room", async (data) => {
      console.log({ data: data.split("/")[2] });
      await socket.join(data.split("/")[2]);

      console.log({ io: io.sockets.adapter.rooms });
    });

    socket.on("sign_in", (data) => auth.onSignIn(data, socket));

    socket.on("disconnect", () => auth.onDisconnect(socket));

    socket.on("message", (data) => chat.onMessage(data, io));

    socket.on("make-offer", (data) => chat.onMakeOffer(data, socket, io));

    socket.on("make-answer", (data) => chat.onMakeAnswer(data, socket));
  });
};
