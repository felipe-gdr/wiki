const MongoClient = require('mongodb').MongoClient;

const url = 'mongodb://localhost:27017';

const dbName = 'devwald';


const client = new MongoClient(url);

const connectionPromise = new Promise((resolve, reject) => {
    client.connect(function (err) {
        if (err) {
            client.close();

            reject(`Error creating MongoDB connection: ${err}`);
        } else {
            console.log("Connected successfully to server");

            resolve(client.db(dbName));
        }
    });
})


const getDb = async () => await connectionPromise;

const closeClient = async () => new Promise((resolve, reject) => {
    console.log('closing MongoDB client');
    client.close(err => {
        if(err) {
            reject('Error closing MongoDB client: ' + err);
        }
        resolve('MongoDB client closed');
    });
});


module.exports = {
    getDb,
    closeClient,
}