"use strict";
module.exports = (sequelize, DataTypes) => {
  const Address = sequelize.define(
    "Address",
    {
      user_id: {
        type: DataTypes.INTEGER,
      },
      street: {
        type: DataTypes.STRING,
      },
      postal: {
        type: DataTypes.STRING,
      },
      phone_number: {
        type: DataTypes.STRING,
      },
      city_id: {
        type: DataTypes.INTEGER,
      },
    },
    {
      underscored: true,
      tableName: "addresses",
    }
  );
  Address.associate = function (models) {
    // associations can be defined here
  };
  return Address;
};
