const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const schema = Schema(
  {
    channel_id: { type: Schema.Types.Number, required: true },
    author_id: { type: Schema.Types.Number, required: true },
    content: { type: Schema.Types.String, required: true },
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
  { collection: "messages" }
);

module.exports = mongoose.model("Message", schema);
