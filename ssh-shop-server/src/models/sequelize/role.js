"use strict";
const Sequelize = require("sequelize");

module.exports = class Role extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        name: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        description: {
          type: Sequelize.TEXT,
        },
      },
      {
        underscored: true,
        tableName: "roles",
        sequelize,
      }
    );
  }

  static associate(models) {
    this.hasMany(models.User, {
      foreignKey: "role_id",
      as: "users",
    });
  }
};
