"use strict";

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert(
      "cities",
      [
        {
          name: "Prishtina",
          country_id: 1,
          created_at: new Date(),
          updated_at: new Date(),
        },
        {
          name: "Istogu",
          country_id: 1,
          created_at: new Date(),
          updated_at: new Date(),
        },
        {
          name: "Kamenica",
          country_id: 1,
          created_at: new Date(),
          updated_at: new Date(),
        },
        {
          name: "Rahoveci",
          country_id: 1,
          created_at: new Date(),
          updated_at: new Date(),
        },
        {
          name: "Deqani",
          country_id: 1,
          created_at: new Date(),
          updated_at: new Date(),
        },
        {
          name: "Tirana",
          country_id: 2,
          created_at: new Date(),
          updated_at: new Date(),
        },
      ],
      {}
    );
  },

  down: (queryInterface, Sequelize) => {
    /*
      Add reverting commands here.
      Return a promise to correctly handle asynchronicity.

      Example:
      return queryInterface.bulkDelete('People', null, {});
    */
  },
};
