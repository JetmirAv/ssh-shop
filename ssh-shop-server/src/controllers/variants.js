const assert = require('assert');
const mongoose = require('../config/mongoose') 

/**
 *
 * @param {Product} data
 * @returns Product
 */
const CreateMongoProduct = async (data) => {
  try {
    //const product = "new" 
    //await product.validate();
    //await product.save();
    //return product;
  } catch (err) {
    throw err;
  }
};  

const findDocuments = function(db, collectionName, callback) {
  // Get the documents collection
//  const collection = db.collection(collectionName);
//  // Find some documents
//  console.log(db.collectionNames(callback));
//  collection.find({}).toArray(function(err, docs) {
//    assert.equal(err, null);
//    console.log("Found the following records");
//    console.log(docs)
    //callback(docs);
//  });
db.listCollections().toArray().then((docs) => {
  docs.forEach((doc, idx, array) => { console.log(doc.name) });

}).catch((err) => {

  console.log(err);
})
}

const removeDocument = function(db,collectionName, callback) {
    // Get the documents collection
    const collection = db.collection(collectionName);
    // Delete collection
    collection.drop(function(err, delOK) {
        if (err) throw err;
        if (delOK) console.log("Collection deleted");
      });
}

const updateDocument = function(db, variantCollections, collectionName, callback) {
    // Get the documents collection
    const collection = db.collection(collectionName);
        collection.remove({})
        for (i in variantCollections) {
            collection.insertOne(variantCollections[i], function(err, res) {
            if (err) throw err;
            console.log("Document updated");
        }); 
      }
  }

  module.exports = {
    CreateMongoProduct,
    findDocuments,
    updateDocument,
    removeDocument
  }