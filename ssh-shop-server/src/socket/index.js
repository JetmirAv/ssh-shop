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

    socket.on("sign-in", auth.onSignIn);

    socket.on("disconnect", auth.onDisconnect);

    socket.on("message", chat.onMessage);

    socket.on("make-offer", async (data) => {
      console.log({ data });
      let ids = await helpers.findChannelParticipants(data.channel);
      let toSend = ids.filter((val) => val !== data.author);

      console.log({ toSend });

      let socketId = await UserSocket.findAll({
        where: { user_id: toSend[0] },
      });

      socketId.map((soc) => {
        socket.to(soc.socket_id).emit("offer-made", {
          offer: data.offer,
          socket: socket.id,
          channel: data.channel,
        });
      });

      // let channel = await
    });

    socket.on("make-answer", (data) => {
      socket.to(data.to).emit("answer-made", {
        socket: socket.id,
        answer: data.answer,
        channel: data.channel,
        offer: data.offer,
      });
    });
  });
};
