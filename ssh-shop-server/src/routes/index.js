let usersRouter = require("./users");
let authRouter = require("./auth");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/auth", authRouter);
};
