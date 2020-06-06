const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const schema = Schema(
  {
    user_id: { type: Schema.Types.Number, required: true },
    socket_id: { type: Schema.Types.String, required: true },
  },
  { collection: "user-sockets" }
);

module.exports = mongoose.model("UserSocket", schema);
