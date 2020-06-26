'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.bulkInsert(
      "users",
      [
      {
        role_id:2 ,
        first_name:"Laurent",
        last_name:"Arifaj",
        email:"laurent.arifaj@mail.com",
        password:"$2b$12$DxKCrayyVOCQkw13HooX2u4SnSzhSeJamD/kb0GgleVDE6aSbIjqm",
        birthdate:"1999-01-01",
        gender:"Male",
        phone_number:"+38300111222",
        created_at:new Date(),
        updated_at: new Date(),
      },
      {
        role_id:2 ,
        first_name:"Jetmir",
        last_name:"Avdullahu",
        email:"jetmir.avdullahu@mail.com",
        password:"$2b$12$DxKCrayyVOCQkw13HooX2u4SnSzhSeJamD/kb0GgleVDE6aSbIjqm",
        birthdate:"1999-01-01",
        gender:"Male",
        phone_number:"+38300111222",
        created_at:new Date(),
        updated_at: new Date(),
      },
      {
        role_id:2 ,
        first_name:"Lavdim",
        last_name:"Pireva",
        email:"lavdim.pireva@mail.com",
        password:"$2b$12$DxKCrayyVOCQkw13HooX2u4SnSzhSeJamD/kb0GgleVDE6aSbIjqm",
        birthdate:"1999-01-01",
        gender:"Male",
        phone_number:"+38300111222",
        created_at:new Date(),
        updated_at: new Date(),
      },
      {
        role_id:2 ,
        first_name:"Shpend",
        last_name:"Jahiri",
        email:"shpend.jahiri@mail.com",
        password:"$2b$12$DxKCrayyVOCQkw13HooX2u4SnSzhSeJamD/kb0GgleVDE6aSbIjqm",
        birthdate:"1999-01-01",
        gender:"Male",
        phone_number:"+38300111222",
        created_at:new Date(),
        updated_at: new Date(),
      },
      {
        role_id:2 ,
        first_name:"Medina",
        last_name:"Krelni",
        email:"medina.krelani@mail.com",
        password:"$2b$12$DxKCrayyVOCQkw13HooX2u4SnSzhSeJamD/kb0GgleVDE6aSbIjqm",
        birthdate:"1999-01-01",
        gender:"Female",
        phone_number:"+38300111222",
        created_at:new Date(),
        updated_at: new Date(),
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
