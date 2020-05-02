let usersRouter = require("./users");
let authRouter = require("./auth");
let addressRouter = require("./address");
let cardRouter = require("./card");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/address", addressRouter);
  app.use("/auth", authRouter);
  app.use("/card", cardRouter);
};
