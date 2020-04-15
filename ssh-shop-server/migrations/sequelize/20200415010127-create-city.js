"use strict";
module.exports = {
  up: async (queryInterface, Sequelize) => {
    await queryInterface.createTable("cities", {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER,
      },
      name: {
        type: Sequelize.STRING,
      },
      country_id: {
        type: Sequelize.INTEGER,
      },
      created_at: {
        allowNull: false,
        type: Sequelize.DATE,
      },
      updated_at: {
        allowNull: false,
        type: Sequelize.DATE,
      },
    });
    return queryInterface.addConstraint("cities", ["country_id"], {
      type: "foreign key",
      name: "role_users",
      references: {
        //Required field
        table: "countries",
        field: "id",
      },
      onDelete: "restrict",
      onUpdate: "cascade",
    });
  },
  down: (queryInterface, Sequelize) => {
    return queryInterface.dropTable("cities");
  },
};
