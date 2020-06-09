const { UserSocket } = require("../models/mongo");
const { CreateMessage } = require("../services/messages");

exports.onMessage = async (data, io) => {
  let message = await CreateMessage(JSON.parse(data));

  let ids = [message.channel.user.id, message.channel.product.user_id];
  let sockets = await UserSocket.find({
    $or: [{ user_id: ids[0] }, { user_id: ids[1] }],
  });

  try {
    sockets.map((user) =>
      io.to(user.socket_id).emit("new-message", JSON.stringify(message))
    );
  } catch (err) {
    console.log({ err });
  }

  // let ids = await helpers.findChannelParticipants(message.channel_id);
  // console.log({ ids });
  // let sockets = await UserSocket.findAll({
  //   where: { user_id: { [Sequelize.Op.in]: ids } },
  // });
  // console.log({ sockets });
  // sockets.map((user) => {
  //   io.to(user.socket_id).emit("message", message);
  // });
  // let product =
  // let id = await UserSocket.findAll({where: {userId: message.}})
  // socket.emit("new_message", message);
  // io.sockets.socket().emit("new_message", message);
};

exports.onMakeOffer = (data, socket) => {
  let url = socket.handshake.headers.referer;
  url = url.split("channels/");
  url = url[1].split("/");

  console.log({ onMakeOffer: "onMakeOffer" + socket.id });

  socket.broadcast.to(url[0]).emit("offer-made", {
    offer: data.offer,
    socket: socket.id,
  });
};

exports.onMakeAnswer = (data, socket) => {
  let url = socket.handshake.headers.referer;
  url = url.split("channels/");
  url = url[1].split("/");

  console.log({ onMakeAnswer: "onMakeAnswer" + socket.id });

  socket.broadcast.to(url[0]).emit("answer-made", {
    socket: socket.id,
    answer: data.answer,
  });
};
