'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert(
      'wish_lists', [
        {
          user_id:1,
          product_id:"5ef55049655728416fbb1750",
          created_at:new Date(),
          updated_at:new Date()
        },
        {
          user_id:2,
          product_id:"5ef54b55655728416fbb1731",
          created_at:new Date(),
          updated_at:new Date()
        },
        {
          user_id:3,
          product_id:"5ef55049655728416fbb1750",
          created_at:new Date(),
          updated_at:new Date()
        }

      ])
  },

  down: (queryInterface, Sequelize) => {
    /*
      Add reverting commands here.
      Return a promise to correctly handle asynchronicity.

      Example:
      return queryInterface.bulkDelete('People', null, {});
    */
  }
};
