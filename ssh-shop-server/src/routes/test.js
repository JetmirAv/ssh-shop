//test

const express = require("express");
const router = express.Router({ mergeParams: true });
const auth = require("../middleware/auth");

const Messages = require("../../messages");

const Message = require("../models/mongo/message");

const { User, Channel, Product, Sequelize } = require("../models/sequelize");
/* GET users listing. */
router.get("/users", async (req, res) => {
  const users = await User.findAll();
  res.status(200).json(users);
});
/* GET users listing. */
router.get("/channels/:id", async (req, res) => {
  let products = await Product.findAll({
    where: { user_id: req.params.id },
  });
  products = products.map((product) => product.id);
  const channels = await Channel.findAll({
    where: {
      [Sequelize.Op.or]: [
        {
          user_id: req.params.id,
        },
        {
          product_id: { [Sequelize.Op.in]: products },
        },
      ],
    },
  });
  console.log({ channels });

  res.status(200).json(channels);
});

/* GET users listing. */
router.get("/messages/:channel_id", async (req, res) => {
  const messages = Messages.filter(
    (message) => message.channel_id === +req.params.channel_id
  );
  console.log({ messages });

  res.status(200).json(messages);
});

router.post("/channels/:channel_id/messages", auth, async (req, res) => {
  try {
    let message = new Message({
      channel_id: req.params.channel_id,
      author_id: req.user.id,
      content: req.body.content,
      content2: { test: req.body.content },
    });

    message = await message.save();
    res.status(200).json({ message });
  } catch (error) {
    console.log({ error });
  }
});

router.get("/channels/:channel_id/messages", auth, async (req, res) => {
  try {
    let messages = await Message.find({ channel_id: req.params.channel_id });

    res.status(200).json({ messages });
  } catch (err) {
    console.log({ err });
  }
});

module.exports = router;
