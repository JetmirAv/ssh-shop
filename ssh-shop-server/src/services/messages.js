const Message = require("../models/mongo/message");
const { Channel } = require("../models/sequelize");

const GetMessages = async (channel_id) => {
  try {
    let messages = await Message.find({ channel_id }).sort({ created_at: -1 });
    return messages;
  } catch (error) {
    throw error;
  }
};

const CreateMessage = async (data) => {
  try {
    let channel = await Channel.findByPk(data.channel_id);
    if (!channel) throw new Error("Not found");
    let message = new Message({
      ...data,
      // author_id: 2,
    });
    message = await message.save();
    channel.changed("updatedAt", true);
    await channel.save();
    return { ...message._doc, channel };
  } catch (error) {
    throw error;
  }
};

module.exports = {
  GetMessages,
  CreateMessage,
};
