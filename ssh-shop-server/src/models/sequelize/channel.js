"use strict";
const Sequelize = require("sequelize");

module.exports = class Channel extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        product_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        user_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        name: {
          type: Sequelize.STRING,
        },
      },
      {
        underscored: true,
        tableName: "channels",
        sequelize,
        defaultScope: {
          include: [
            {
              association: "user",
              attributes: ["id", "first_name", "last_name", "email"],
            },
            { association: "product", attributes: ["id", "name"] },
          ],
        },
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
    this.belongsTo(models.Product, {
      foreignKey: "product_id",
      as: "product",
    });
  }
};
