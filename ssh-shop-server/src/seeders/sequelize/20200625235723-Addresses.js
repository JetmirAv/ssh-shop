'use strict';

const { address } = require("faker");

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert(
      "addresses",[
        {
          user_id:1,
          street:"Rruga B",
          postal:"10000",
          phone_number:"+38349345801",
          city_id:1,
          created_at:new Date(),
          updated_at:new Date()
        },
        {
          user_id:2,
          street:"Rruga B",
          postal:"10000",
          phone_number:"+38349345801",
          city_id:1,
          created_at:new Date(),
          updated_at:new Date()
        },
        {
          user_id:3,
          street:"Rruga B",
          postal:"10000",
          phone_number:"+38349345801",
          city_id:1,
          created_at:new Date(),
          updated_at:new Date()
        },
        {
          user_id:4,
          street:"Rruga B",
          postal:"10000",
          phone_number:"+38349345801",
          city_id:1,
          created_at:new Date(),
          updated_at:new Date()
        },
        {
          user_id:5,
          street:"Rruga B",
          postal:"10000",
          phone_number:"+38349345801",
          city_id:1,
          created_at:new Date(),
          updated_at:new Date()
        },
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
