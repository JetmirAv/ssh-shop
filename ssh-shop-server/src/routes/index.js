let usersRouter = require("./users");
let authRouter = require("./auth");
let testRouter = require("./test");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/auth", authRouter);
  app.use("/test", testRouter);
};
