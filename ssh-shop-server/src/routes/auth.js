//auth

const express = require("express");
const router = express.Router();

const controllers = require("../controllers/auth");
const auth = require("../middleware/auth");

// Get logged user
router.get("/", auth, controllers.me);
// log in
router.post("/login", controllers.login);
// register
router.post("/register", controllers.register);
// change password
router.post("/change-password", auth, controllers.changePassword);

module.exports = router;
