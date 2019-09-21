// A simple logger middleware
const logger = (req, res, next) => {
    console.log('request received');
    next();
}

// A middleware that will force an error if whenever the "forceError" query param is present and truthy
const errorCatcher = (req, res, next) => {
    if(req.query.forceError) {
        next('A forced error from middleware')
    } else {
        next();
    }
};

// An error handler middleware
const errorHandler = (err, req, res, next) => {
    res.send('An error happened: ' + err);
};

module.exports = {
    logger, 
    errorCatcher,
    errorHandler,
}