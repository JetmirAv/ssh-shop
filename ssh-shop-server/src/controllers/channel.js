const {
  CreateChannel,
  UpdateChannel,
  GetExistingChannel,
  FindAndCountChannels,
} = require("../services/channel");
const path = require("path");

const { GetMessages, CreateMessage } = require("../services/messages");
const CallRooms = require("../models/mongo/call-room");

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const findAll = async (req, res, next) => {
  try {
    const channels = await FindAndCountChannels(req.user);
    return res.status(200).json({ channels });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res, next) => {
  try {
    const channel = await CreateChannel({
      user_id: req.user.id,
      product_id: req.params.product_id,
    });
    return res.status(200).json({ channel });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */

const update = async (req, res, next) => {
  try {
    const channel = await UpdateChannel(
      req.params.user_id,
      req.params.product_id,
      req.body
    );
    return res.status(200).json({ channel });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */
const get = async (req, res, next) => {
  try {
    return res.status(200).json({
      channel: await GetExistingChannel(
        req.params.user_id,
        req.params.product_id
      ),
    });
  } catch (err) {
    next(err);
  }
};

const getMessages = async (req, res, next) => {
  try {
    return res.status(200).json({
      messages: await GetMessages(req.params.channel_id),
    });
  } catch (error) {
    next(error);
  }
};

const createMessage = async (req, res, next) => {
  try {
    return res.status(200).json({
      messages: await CreateMessage({
        channel_id: req.params.channel_id,
        author_id: req.user.id,
        content: req.body.content,
      }),
    });
  } catch (error) {
    next(error);
  }
};

const startCall = async (req, res, next) => {
  try {
    console.log("Niec");

    let channel_id = req.params.channel_id;
    let room = await CallRooms.findOne({ where: { channel_id } });

    if (!room) {
      console.log("Nie2c");
      room = new CallRooms({ channel_id });
      room = await room.save();
    }

    return res.status(200).json({ room });
  } catch (err) {
    next(err);
  }
};

const joinRoom = async (req, res, next) => {
  try {
    let room_id = req.params.room_id;
    console.log("Knej");

    let room = await CallRooms.find({ where: { id: room_id } });
    if (!room) throw new Error("Not found");

    res.render(path.resolve(__dirname, "../views/index"), {
      user: req.user,
    });
  } catch (error) {
    next(error);
  }
};

module.exports = {
  create,
  joinRoom,
  findAll,
  update,
  get,
  getMessages,
  createMessage,
  startCall,
};
