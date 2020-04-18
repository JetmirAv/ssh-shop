"use strict";
const Sequelize = require("sequelize");

module.exports = class Media extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        reference_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        path: {
          type: Sequelize.STRING,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "media",
        sequelize,
      }
    );
  }
};
