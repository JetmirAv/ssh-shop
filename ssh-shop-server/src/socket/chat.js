const { UserSocket } = require("../models/mongo");
const { Channel } = require("../models/sequelize");
const { CreateMessage } = require("../services/messages");
const { GetProduct } = require("../services/products");

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
};

exports.onCallRequest = async (data, io) => {
  let { author_id, channel_id, room_id } = JSON.parse(data);

  console.log({ testiknej: data });

  let channel = await Channel.findByPk(channel_id);
  if (!channel) throw new Error("Not found");
  if (author_id === channel.user_id) {
    let product = await GetProduct(channel.product_id);
    if (!product) throw new Error("Not found");
    author_id = product.user_id;
  }

  console.log({ author_id });

  const sockets = await UserSocket.find({
    $or: [{ user_id: author_id }],
  });

  console.log({ sockets });

  sockets.map((soc) =>
    io.to(soc.socket_id).emit("incoming-call", JSON.stringify({ room_id }))
  );
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
