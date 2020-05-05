let usersRouter = require("./users");
let authRouter = require("./auth");
let channelRouter = require("./channel");
let productsRouter = require("./products");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/auth", authRouter);
  app.use("/channel", channelRouter);
  app.use("/products", productsRouter);
};
