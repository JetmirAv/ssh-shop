const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const schema = Schema(
  {
    channel_id: { type: Schema.Types.Number, required: true },
    created_at: {
      type: Schema.Types.Date,
      required: true,
      default: Date.now,
    },
    updated_at: {
      type: Schema.Types.Date,
      required: true,
      default: Date.now,
    },
  },
  { collection: "call-rooms" }
);

module.exports = mongoose.model("CallRooms", schema);
