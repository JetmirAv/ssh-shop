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
        allowNull: false,
      },
      country_id: {
        type: Sequelize.INTEGER,
        allowNull: false,
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

    await queryInterface.addConstraint("cities", ["country_id"], {
      type: "foreign key",
      name: "county_cities",
      references: {
        //Required field
        table: "countries",
        field: "id",
      },
      onDelete: "restrict",
      onUpdate: "cascade",
    });

    return Promise.resolve();
  },
  down: (queryInterface) => {
    return queryInterface.dropTable("cities");
  },
};
