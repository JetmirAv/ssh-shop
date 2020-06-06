const Message = require("../models/mongo/message");
const { Channel, User } = require("../models/sequelize");
const { GetProduct } = require("./products");

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
    let product = await GetProduct(channel.product_id);
    if (!product) throw new Error("Not found");

    let user = await User.findByPk(product.user_id);

    let message = new Message({
      ...data,
      // author_id: 2,
    });
    message = await message.save();
    channel.changed("updatedAt", true);
    await channel.save();
    return {
      ...message._doc,
      channel: {
        ...channel.dataValues,
        product: { ...product._doc, user: { ...user.dataValues } },
      },
    };
  } catch (error) {
    throw error;
  }
};

module.exports = {
  GetMessages,
  CreateMessage,
};
