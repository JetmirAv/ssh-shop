"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "variants",
          {
            id: {
              allowNull: false,
              autoIncrement: true,
              primaryKey: true,
              type: Sequelize.INTEGER,
            },
            product_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            name: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            description: {
              type: Sequelize.TEXT,
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
          "variants",
          ["product_id"],
          {
            type: "foreign key",
            name: "product_variants",
            references: {
              //Required field
              table: "products",
              field: "id",
            },
            onDelete: "cascade",
            onUpdate: "cascade",
          },
          { transaction: t }
        ),
      ]);
    });
  },
  down: (queryInterface) => {
    return queryInterface.dropTable("variants");
  },
};
