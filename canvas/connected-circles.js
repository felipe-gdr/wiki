const draw = () => {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    const canvasCenter = [canvas.offsetHeight / 2, canvas.offsetWidth / 2];
    const defaultRadius = 20;
    const circleAngle = toRadians(360);
    const distance = 70;
    const distanceAdjustment = 20;
    const overlapSecurityMargin = 50;

    const mainCircle = {
        center: canvasCenter,
        radius: 40,
        text: 'Center',
        backgroundColor: 'rgba(255, 0, 0, .5)',
    };

    const bigRadius = mainCircle.radius + distance + defaultRadius;
    const bigCircumference = 2 * Math.PI * bigRadius;
    const maxNumberOfCirclesWithoutOverlap = Math.floor((bigCircumference - overlapSecurityMargin) / (defaultRadius * 2));

    console.log('main circumference: ' + bigRadius, bigCircumference, maxNumberOfCirclesWithoutOverlap);

    drawCircle(mainCircle);

    writeCircles(11);

    function writeCircles(count) {
        for (let x = 1; x <= count; x++) {
            const thisDistance = count >= maxNumberOfCirclesWithoutOverlap ?
                x % 2 === 0 ? distance + distanceAdjustment : distance - distanceAdjustment :
                distance;

            const angle = 360 / count * x;
            const newCircle = fromCircle(mainCircle, toRadians(angle), thisDistance, defaultRadius);
            connectCircles(mainCircle, newCircle);
            drawCircle(newCircle);
        }
    }

    function fromCircle({ center, radius }, angle, distance, newRadius) {
        const newCenter = getCoordinates(center, radius + newRadius + distance, angle);

        return {
            center: newCenter,
            radius: newRadius,
            backgroundColor: 'rgba(0, 0, 255, .5)',
        };
    }

    function drawCircle({ center, radius, backgroundColor }) {
        const [centerX, centerY] = center;
        ctx.beginPath();
        ctx.moveTo(centerX + radius, centerY)
        ctx.arc(centerX, centerY, radius, 0, circleAngle);
        ctx.fillStyle = backgroundColor;
        ctx.fill();
        ctx.stroke();
    }

    function getCoordinates([centerX, centerY], distance, angle) {
        return [distance * Math.cos(angle) + centerX, distance * Math.sin(angle) + centerY];
    }

    function toRadians(angle) {
        return (Math.PI / 180) * angle;
    }

    function drawPoint([centerX, centerY]) {
        const radius = 2;
        ctx.beginPath();
        ctx.moveTo(centerX + radius, centerY);
        ctx.arc(centerX, centerY, radius, 0, circleAngle);
        ctx.stroke();
    }

    function connectCircles(circle1, circle2) {
        const angle1 = Math.atan2(circle1.center[1] - circle2.center[1], circle1.center[0] - circle2.center[0]);
        const angle2 = Math.atan2(circle2.center[1] - circle1.center[1], circle2.center[0] - circle1.center[0]);

        const touch1 = getCoordinates(circle2.center, circle2.radius, angle1);
        const touch2 = getCoordinates(circle1.center, circle1.radius, angle2);

        drawPoint(touch1);
        drawPoint(touch2);

        ctx.beginPath();
        ctx.moveTo(...touch1);
        ctx.lineTo(...touch2);
        ctx.stroke();
    }
}