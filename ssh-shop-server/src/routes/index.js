let usersRouter = require("./users");
let authRouter = require("./auth");
let addressRouter = require("./address");
let productsRouter = require("./products");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/address", addressRouter);
  app.use("/auth", authRouter);
  app.use("/products", productsRouter);
};
