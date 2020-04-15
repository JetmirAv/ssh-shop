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
    // associations can be defined here
  };
  return User;
};
