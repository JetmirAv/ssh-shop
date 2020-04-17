"use strict";
const bcrypt = require("bcrypt");

module.exports = (sequelize, DataTypes) => {
  const User = sequelize.define(
    "User",
    {
      role_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      first_name: {
        type: DataTypes.STRING,
        allowNull: false,
        validate: {
          isAlphanumeric: {
            msg: "Please provide a valid name",
          },
          notEmpty: {
            msg: "Please provide a valid name",
          },
          len: {
            args: [2, 50],
            msg: "Name must be of a length between 2 and 50 characters",
          },
        },
        set(val) {
          if (val) this.setDataValue("email", val.trim());
        },
      },
      last_name: {
        type: DataTypes.STRING,
        validate: {
          isAlphanumeric: {
            msg: "Please provide a valid last name",
          },
          notEmpty: {
            msg: "Please provide a valid last name",
          },
          len: {
            args: [2, 50],
            msg: "Last name must be of a length between 2 and 50 characters",
          },
        },
        set(val) {
          if (val) this.setDataValue("email", val.trim());
        },
      },
      email: {
        type: DataTypes.STRING,
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
        type: DataTypes.STRING,
        allowNull: false,
        set(val) {
          if (val) this.setDataValue("email", bcrypt.hashSync(val, 12));
        },
      },
      forgot_password_token: {
        type: DataTypes.STRING,
        set(val) {
          if (val) this.setDataValue("email", val.trim());
        },
      },
      birthdate: {
        type: DataTypes.DATE,
      },
      gender: {
        type: DataTypes.ENUM("MALE", "FEMALE"),
      },
      avatar: {
        type: DataTypes.STRING,
        get() {
          return this.getDataValue("avatar") || "default.jpeg";
        },
        set(val) {
          if (val) this.setDataValue("email", val.trim());
        },
      },
      phone_number: {
        type: DataTypes.STRING,
        set(val) {
          if (val) this.setDataValue("email", val.trim());
        },
      },
    },
    {
      underscored: true,
      tableName: "users",
    }
  );
  User.associate = function (models) {
    // 1 - 1
    User.belongsTo(models.Role, {
      foreignKey: "role_id",
      as: "Role",
    });

    // 1 - n
    User.hasMany(models.Card, {
      foreignKey: "user_id",
      as: "Cards",
    });
    User.hasMany(models.Address, {
      foreignKey: "user_id",
      as: "Addresses",
    });
    User.hasMany(models.WishList, {
      foreignKey: "user_id",
      as: "WishList",
    });
    User.hasMany(models.Cart, {
      foreignKey: "user_id",
      as: "Carts",
    });
    User.hasMany(models.Product, {
      foreignKey: "user_id",
      as: "Products",
    });
    User.hasMany(models.Channel, {
      foreignKey: "user_id",
      as: "Channels",
    });
  };
  return User;
};
