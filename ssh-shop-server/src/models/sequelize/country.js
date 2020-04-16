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
    // 1 - n
    Country.hasMany(models.City, {
      foreignKey: "country_id",
      as: "Cities",
    });
  };
  return Country;
};
