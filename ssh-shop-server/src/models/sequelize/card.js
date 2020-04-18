"use strict";
const Sequelize = require("sequelize");

module.exports = class Card extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        user_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        number: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        exp_month: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        exp_year: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        code: {
          type: Sequelize.STRING,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "cards",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
  }
};
