const express = require('express');
const { logger, errorCatcher, errorHandler } = require('./middlewares');
const usersRouter = require('./routers/users');
const { closeClient } = require('./data');

const app = express();

// Applies the middlewares
app.use(logger, errorCatcher, errorHandler);
app.use(express.json())

// Applies the "users" router
app.use('/users', usersRouter);

app.get('/', logger, (req, res) => {
    res.send('Hello World!');
})

app.get('/error', logger, (req, res) => {
    throw new Error('A Forced error!')
})

app.listen(666);

const closeClientWrapper = async () => {
    try {
        const msg = await closeClient();
        console.log(msg);
        process.exit(0);
    } catch(err) {
        console.log(`Error shuting down node applicationn: ${err}`)
        process.exit(1);
    }
}

//catches ctrl+c event
process.on('SIGINT', closeClientWrapper);

// catches "kill pid" (for example: nodemon restart)
process.on('SIGUSR1', closeClientWrapper);
process.on('SIGUSR2', closeClientWrapper);

// catches uncaught exceptions
process.on('uncaughtException', err => {
    console.trace(`Unexpected error: ${err}`);

    closeClientWrapper()
});