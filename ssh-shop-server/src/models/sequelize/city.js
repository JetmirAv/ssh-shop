"use strict";
module.exports = (sequelize, DataTypes) => {
  const City = sequelize.define(
    "City",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      country_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "cities",
    }
  );
  City.associate = function (models) {
    // 1 - 1
    City.belongsTo(models.Country, {
      foreignKey: "country_id",
      as: "country",
    });
  };
  return City;
};
