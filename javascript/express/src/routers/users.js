const express = require('express');
const { getDb } = require('../data');

const usersRouter = express.Router();

const collectionName = 'users';

usersRouter.get('/', async (req, res) => {
    try {
        const db = await getDb();

        db.collection(collectionName).find({}).toArray((err, users) => {

            throw new Error('this is my error');

            if (err) {
                console.error(err);
                res.status(500).send('Error fetching users from database');
            } else {
                res.json(users);
            }
        });
    } catch (err) {
        console.error(err); k
        res.status(500).send('Error fetching users from database');
    }
});

usersRouter.post('/', async (req, res) => {
    const db = await getDb();

    const body = req.body;

    try {
        const result = await db.collection(collectionName).insertOne(body);

        res.send('User added successfully');
    } catch(err) {
        res.status(500).send(err);
    }
});

module.exports = usersRouter;