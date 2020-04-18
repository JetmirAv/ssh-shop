"use strict";
const Sequelize = require("sequelize");
const bcrypt = require("bcrypt");
const CustomError = require("../../errors/CustomError");

module.exports = class User extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        role_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
          set(val) {
            if (parseInt(val)) this.setDataValue("role_id", parseInt(val));
          },
        },
        first_name: {
          type: Sequelize.STRING,
          allowNull: false,
          validate: {
            notEmpty: {
              msg: "Please provide a valid name",
            },
            len: {
              args: [2, 50],
              msg: "Name must be of a length between 2 and 50 characters",
            },
          },
          set(val) {
            if (val) this.setDataValue("first_name", val.trim());
          },
        },
        last_name: {
          type: Sequelize.STRING,
          validate: {
            notEmpty: {
              msg: "Please provide a valid last name",
            },
            len: {
              args: [2, 50],
              msg: "Last name must be of a length between 2 and 50 characters",
            },
          },
          set(val) {
            if (val) this.setDataValue("last_name", val.trim());
          },
        },
        email: {
          type: Sequelize.STRING,
          allowNull: false,
          unique: true,
          validate: {
            isEmail: {
              msg: "Please provide a valid email",
            },
          },
          set(val) {
            if (val) this.setDataValue("email", val.toLowerCase().trim());
          },
        },
        password: {
          type: Sequelize.STRING,
          allowNull: false,
          set(val) {
            if (val) this.setDataValue("password", bcrypt.hashSync(val, 12));
          },
          get() {
            return null;
          },
        },
        forgot_password_token: {
          type: Sequelize.STRING,
          set(val) {
            if (val) this.setDataValue("forgot_password_token", val.trim());
          },
        },
        birthdate: {
          type: Sequelize.DATEONLY,
        },
        gender: {
          type: Sequelize.ENUM("MALE", "FEMALE"),
        },
        avatar: {
          type: Sequelize.STRING,
          get() {
            return this.getDataValue("avatar") || "default.jpeg";
          },
          set(val) {
            if (val) this.setDataValue("avatar", val.trim());
          },
        },
        phone_number: {
          type: Sequelize.STRING,
          set(val) {
            if (val) this.setDataValue("phone_number", val.trim());
          },
        },
      },
      {
        tableName: "users",
        underscored: true,
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.Role, {
      foreignKey: "role_id",
      as: "Role",
    });

    // 1 - n
    this.hasMany(models.Card, {
      foreignKey: "user_id",
      as: "cards",
    });
    this.hasMany(models.Address, {
      foreignKey: "user_id",
      as: "addresses",
    });
    this.hasMany(models.WishList, {
      foreignKey: "user_id",
      as: "wishlist",
    });
    this.hasMany(models.Cart, {
      foreignKey: "user_id",
      as: "carts",
    });
    this.hasMany(models.Product, {
      foreignKey: "user_id",
      as: "products",
    });
    this.hasMany(models.Channel, {
      foreignKey: "user_id",
      as: "channels",
    });
  }

  validatePassword(password) {
    if (!bcrypt.compareSync(password, this.getDataValue("password")))
      throw new CustomError("Wrong Credentials");
    return true;
  }
};
