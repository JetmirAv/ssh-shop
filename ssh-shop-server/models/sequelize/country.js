"use strict";
module.exports = (sequelize, DataTypes) => {
  const Country = sequelize.define(
    "Country",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "countries",
    }
  );
  Country.associate = function (models) {
    // associations can be defined here
  };
  return Country;
};
