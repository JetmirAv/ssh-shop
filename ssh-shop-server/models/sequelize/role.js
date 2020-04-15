"use strict";
module.exports = (sequelize, DataTypes) => {
  const Role = sequelize.define(
    "Role",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      description: {
        type: DataTypes.TEXT,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "roles",
    }
  );
  Role.associate = function (models) {
    // associations can be defined here
  };
  return Role;
};
