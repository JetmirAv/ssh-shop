//test

const express = require("express");
const router = express.Router();

const Messages = require("../../messages");

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

module.exports = router;
