"use strict";
const Sequelize = require("sequelize");

module.exports = class Product extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        user_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        name: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        description: {
          type: Sequelize.TEXT,
        },
        category_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        discount_pt: {
          type: Sequelize.FLOAT,
        },
      },
      {
        underscored: true,
        tableName: "products",
        sequelize,
        defaultScope: {
          include: [
            {
              association: "user",
              attributes: ["id", "first_name", "last_name", "email"],
            },
            { association: "category" },
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
    this.belongsTo(models.Category, {
      foreignKey: "category_id",
      as: "category",
    });
  }
};
