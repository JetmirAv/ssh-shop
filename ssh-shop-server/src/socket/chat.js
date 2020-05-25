const Messages = require("../../messages");
const { UserSocket, Sequelize } = require("../models/sequelize");

const onMessage = async (message) => {
  Messages.push(message);

  let ids = await helpers.findChannelParticipants(message.channel_id);
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
};

module.exports = {
  onMessage,
};
