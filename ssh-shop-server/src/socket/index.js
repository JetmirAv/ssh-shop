const chat = require("./chat");
const { UserSocket, Channel, Sequelize } = require("../models/sequelize");
const Messages = require("../../messages");

const findChannelParticipants = async (channel_id) => {
  let channel = await Channel.findOne({
    where: { id: channel_id },
    include: [
      {
        association: "product",
        include: [
          {
            association: "user",
            attributes: ["id"],
          },
        ],
        attributes: ["id"],
      },
      { association: "user", attributes: ["id"] },
    ],
    attributes: ["id"],
  });

  return [channel.product.user.id, channel.user.id];
};

module.exports = (io) => {
  // Handle connection
  io.on("connection", async (socket) => {
    console.log("Connected succesfully to the socket ...");

    socket.on("sign-in", async (data) => {
      try {
        await UserSocket.create({ user_id: data.id, socket_id: socket.id });
      } catch (err) {
        //force disconnect
        console.log({ err });
      }
    });

    socket.on("disconnect", async () => {
      UserSocket.findOne({ where: { socket_id: socket.id } }).then((so) =>
        so.destroy()
      );
    });

    socket.on("message", async (message) => {
      Messages.push(message);

      let ids = await findChannelParticipants(message.channel_id);
      console.log({ ids });

      let sockets = await UserSocket.findAll({
        where: { user_id: { [Sequelize.Op.in]: ids } },
      });
      console.log({ sockets });

      sockets.map((user) => {
        io.to(user.socket_id).emit("message", message);
      });

      // let product =
      // let id = await UserSocket.findAll({where: {userId: message.}})

      // socket.emit("new_message", message);
      // io.sockets.socket().emit("new_message", message);
    });

    socket.on("make-offer", async (data) => {
      console.log({ data });
      let ids = await findChannelParticipants(data.channel);
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
      });
    });
  });
};
