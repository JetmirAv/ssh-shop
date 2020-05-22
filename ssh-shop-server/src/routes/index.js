let usersRouter = require("./users");
let authRouter = require("./auth");
let channelRouter = require("./channel");
let productsRouter = require("./products");
let citiesRouter = require("./cities");
let countriesRouter = require("./countries");
let testRouter = require("./test");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/auth", authRouter);
  app.use("/channel", channelRouter);
  app.use("/products", productsRouter);
  app.use("/cities", citiesRouter);
  app.use("/countries", countriesRouter);
  app.use("/test", testRouter);
};
