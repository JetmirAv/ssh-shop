//channel

const express = require("express");
const router = express.Router();

const controllers = require("../controllers/channel");
const auth = require("../middleware/auth");

// GET /
router.get('/', function(req, res, next) {
    res.render('index');
  });
  



module.exports = router;