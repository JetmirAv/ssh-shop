"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "users",
          {
            id: {
              allowNull: false,
              autoIncrement: true,
              primaryKey: true,
              type: Sequelize.INTEGER,
            },
            role_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            first_name: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            last_name: {
              type: Sequelize.STRING,
            },
            email: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            password: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            forgot_password_token: {
              type: Sequelize.STRING,
            },
            birthdate: {
              type: Sequelize.DATEONLY,
            },
            gender: {
              type: Sequelize.ENUM("MALE", "FEMALE"),
            },
            avatar: {
              type: Sequelize.STRING,
            },
            phone_number: {
              type: Sequelize.STRING,
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
          "users",
          ["role_id"],
          {
            type: "foreign key",
            name: "role_users",
            references: {
              //Required field
              table: "roles",
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
    return queryInterface.dropTable("users");
  },
};
