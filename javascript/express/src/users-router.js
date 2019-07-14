const express = require('express');

const usersRouter = express.Router();

usersRouter.get('/', (req, res) => {
    res.json([
        {id: 1, name: 'Mary'},
        {id: 2, name: 'John'},
    ])
});

module.exports = usersRouter;