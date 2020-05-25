const findChannelParticipants = async (channel_id) => {
  let channel = await Channel.findOne({
    where: { id: channel_id },
    include: [
      {
        association: "product",
        include: [{ association: "user", attributes: ["id"] }],
        attributes: ["id"],
      },
      { association: "user", attributes: ["id"] },
    ],
    attributes: ["id"],
  });

  return [channel.product.user.id, channel.user.id];
};

module.exports = {
  findChannelParticipants,
};
