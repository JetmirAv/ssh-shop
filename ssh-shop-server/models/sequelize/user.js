"use strict";
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
      },
      last_name: {
        type: DataTypes.STRING,
      },
      email: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      password: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      forgot_password_token: {
        type: DataTypes.STRING,
      },
      birthdate: {
        type: DataTypes.DATE,
      },
      gender: {
        type: DataTypes.ENUM("MALE", "FEMALE"),
      },
      avatar: {
        type: DataTypes.STRING,
      },
      phone_number: {
        type: DataTypes.STRING,
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
