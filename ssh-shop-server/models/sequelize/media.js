"use strict";
module.exports = (sequelize, DataTypes) => {
  const Media = sequelize.define(
    "Media",
    {
      reference_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      path: {
        type: DataTypes.STRING,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "media",
    }
  );
  Media.associate = function (models) {
    // associations can be defined here
  };
  return Media;
};
