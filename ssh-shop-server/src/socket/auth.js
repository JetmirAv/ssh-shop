const { UserSocket } = require("../models/sequelize");

const onSignIn = async (data) => {
  try {
    await UserSocket.create({ user_id: data.id, socket_id: socket.id });
  } catch (err) {
    //force disconnect
    console.log({ err });
  }
};

const onDisconnect = async () => {
  UserSocket.findOne({ where: { socket_id: socket.id } }).then((so) => {
    if (so) so.destroy();
  });
};

module.exports = {
  onSignIn,
  onDisconnect,
};
