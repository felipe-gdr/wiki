const express = require('express');
const usersRouter = require('./users-router');

const app = express();

const logger = (req, res, next) => {
    console.log('request received');
    next();
}

const errorCatcher = (req, res, next) => {
    console.log(req.query)
    if(req.query.forceError) {
        next('A forced error from middleware')
    } else {
        next();
    }
};

const errorHandler = (err, req, res, next) => {
    res.send('An error happened: ' + err);
};

app.use(logger, errorCatcher, errorHandler);

app.use('/users', usersRouter);

app.get('/', logger, (req, res) => {
    res.send('Hello World!');
})

app.get('/error', logger, (req, res) => {
    throw new Error('A Forced error!')
})

app.get('/maybeError', logger, (req, res) => {
    res.send('No error this time');
});


app.listen(666);