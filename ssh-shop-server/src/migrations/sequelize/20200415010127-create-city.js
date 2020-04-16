"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "cities",
          {
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
          },
          { transaction: t }
        ),
        queryInterface.addConstraint(
          "cities",
          ["country_id"],
          {
            type: "foreign key",
            name: "county_cities",
            references: {
              //Required field
              table: "countries",
              field: "id",
            },
            onDelete: "restrict",
            onUpdate: "cascade",
          },
          { transaction: t }
        ),
      ]);
    });
  },
  down: (queryInterface) => {
    return queryInterface.dropTable("cities");
  },
};
