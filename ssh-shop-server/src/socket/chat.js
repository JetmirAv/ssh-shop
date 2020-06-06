const Messages = require("../../messages");
const { Sequelize } = require("../models/sequelize");
const { Message, UserSocket } = require("../models/mongo");

const { CreateMessage } = require("../services/messages");

const onMessage = async (data, io) => {
  let message = await CreateMessage(JSON.parse(data));

  let ids = [message.channel.user.id, message.channel.product.user_id];
  let sockets = await UserSocket.find({
    $or: [{ user_id: ids[0] }, { user_id: ids[1] }],
  });

  console.log({ sockets });

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

module.exports = {
  onMessage,
};
