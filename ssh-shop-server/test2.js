const io = require("socket.io-client");

const socket = io("ws://0.0.0.0:3000");

socket.on("news", (data) => {
  console.log("====================================");
  console.log({ data });
  console.log("====================================");
});
