const { UserSocket } = require("../models/mongo");

const onSignIn = async (data, socket) => {
  try {
    data = JSON.parse(data);
    let userSocket = new UserSocket({
      user_id: data.user_id,
      socket_id: socket.id,
    });
    await userSocket.save();
  } catch (err) {
    console.log({ err });
  }
};

const onDisconnect = async (socket) => {
  UserSocket.deleteOne({ socket_id: socket.id }).exec();
};

module.exports = {
  onSignIn,
  onDisconnect,
};
