"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "cards",
          {
            id: {
              allowNull: false,
              autoIncrement: true,
              primaryKey: true,
              type: Sequelize.INTEGER,
            },
            user_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            number: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            exp_month: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            exp_year: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            code: {
              type: Sequelize.STRING,
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
          "cards",
          ["user_id"],
          {
            type: "foreign key",
            name: "user_cards",
            references: {
              //Required field
              table: "users",
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
    return queryInterface.dropTable("cards");
  },
};
