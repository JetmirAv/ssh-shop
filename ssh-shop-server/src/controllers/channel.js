const {
    CreateChannel,
    UpdateChannel,
    GetChannel,
  } = require("../services/channel");
  const func = async (req, res) => {
    await res.send("respond with a resource");
  };
  
  /**
   *
   * @param {Request} req
   * @param {Response} res
   */
  const create = async (req, res, next) => {
    try {
      const channel = await CreateChannel(req.body);
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
        req.params.channel_id,
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
      return res
        .status(200)
        .json({ user: await GetChannel(req.params.channel_id) });
    } catch (err) {
      next(err);
    }
  };
  
  module.exports = {
    create,
    update,
    get,
    func,
  };
  