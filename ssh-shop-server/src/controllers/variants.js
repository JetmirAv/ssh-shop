const assert = require('assert');
  
const insertDocuments = function(db, variantCollections, collectionName, callback) {
    // Get the documents collection
    const collection = db.collection(collectionName);
    // Insert some documents
    for (i in variantCollections) {
        collection.insertOne(variantCollections[i], function(err, res) {
        if (err) throw err;
        console.log("Document inserted");
    }); 
  }
}; 

const findDocuments = function(db, collectionName, callback) {
  // Get the documents collection
  const collection = db.collection(collectionName);
  // Find some documents
  collection.find({}).toArray(function(err, docs) {
    assert.equal(err, null);
    console.log("Found the following records");
    console.log(docs)
    //callback(docs);
  });
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
    insertDocuments,
    findDocuments,
    updateDocument,
    removeDocument
  }