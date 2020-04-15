'use strict';
module.exports = (sequelize, DataTypes) => {
  const City = sequelize.define('City', {
    name: DataTypes.STRING,
    country_id: DataTypes.INTEGER
  }, {
    underscored: true,
  });
  City.associate = function(models) {
    // associations can be defined here
  };
  return City;
};