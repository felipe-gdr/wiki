function execute(name) {
    console.log(`executing ${name}`);
    let i = 0;
    while(i < 100000000) {
        i++;
    }
}

let checkpointCount = 0;
function checkpoint() {
    console.log(`checkpoint ${checkpointCount++}`);
}

function runOnMain(count) {
    for (let index = 0; index < count; index++) {
        execute('main' + index);
        execute('main' + index + '.1');
    }
}

function runOnTaskQueue(count) {
    for (let index = 0; index < count; index++) {
        setTimeout(() => {
            execute('task' + index);

            setTimeout(() => {
                execute('task' + index + '.1');
            },0);

        },0)
    }
}

function runOnAnimationQueue(count) {
    for (let index = 0; index < count; index++) {
        requestAnimationFrame(() => {
            execute('animation' + index)
            requestAnimationFrame(() => {
                execute('animation' + index + '.1');
            })
        })
    }
}

function runOnMicroTaskQueue(count) {
    for (let index = 0; index < count; index++) {
        Promise.resolve().then(() => {
            execute('microtask' + index);

            Promise.resolve().then(() => {
                execute('microtask' + index + '.1');
            });
        });
    }
}

const COUNT = 5;
checkpoint();

runOnMain(COUNT);

checkpoint();

runOnTaskQueue(COUNT);

checkpoint();

runOnMicroTaskQueue(COUNT);

checkpoint();

runOnAnimationQueue(COUNT);

checkpoint();