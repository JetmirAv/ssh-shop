let usersRouter = require("./users");
let authRouter = require("./auth");
let addressRouter = require("./address");
let channelRouter = require("./channel");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/address", addressRouter);
  app.use("/auth", authRouter);
  app.use("/channel", channelRouter);
};
