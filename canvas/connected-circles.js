const draw = () => {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    const canvasCenter = [canvas.offsetHeight / 2, canvas.offsetWidth / 2];
    const defaultRadius = 25
    const circleAngle = toRadians(360);
    const distance = 50;

    ctx.beginPath();

    const circle1 = {
        center: canvasCenter,
        radius: defaultRadius,
    };

    drawCircle(circle1);

    for (let x = 0; x < 4; x++) {
        const angle = Math.floor(Math.random() * Math.floor(360));
        const newCircle = fromCircle(circle1, toRadians(angle), distance, defaultRadius);
        drawCircle(newCircle);
        connectCircles(circle1, newCircle);
    }

    // drawPoint(getCoordinates(canvasCenter, defaultRadius, toRadians(0)));
    // drawPoint(getCoordinates(canvasCenter, defaultRadius, toRadians(90)));
    // drawPoint(getCoordinates(canvasCenter, defaultRadius, toRadians(180)));
    // drawPoint(getCoordinates(canvasCenter, defaultRadius, toRadians(270)));

    ctx.stroke();

    function fromCircle({ center, radius }, angle, distance, newRadius) {
        const newCenter = getCoordinates(center, radius + newRadius + distance, angle);

        return {
            center: newCenter,
            radius: newRadius,
        };
    }

    function drawCircle({ center, radius }) {
        const [centerX, centerY] = center;
        ctx.moveTo(centerX + radius, centerY)
        ctx.arc(centerX, centerY, radius, 0, circleAngle);
        ctx.fill();
    }

    function getCoordinates([centerX, centerY], distance, angle) {
        return [distance * Math.cos(angle) + centerX, distance * Math.sin(angle) + centerY];
    }

    function toRadians(angle) {
        return (Math.PI / 180) * angle;
    }

    function drawPoint([centerX, centerY]) {
        const radius = 2;
        ctx.moveTo(centerX + radius, centerY);
        ctx.arc(centerX, centerY, radius, 0, circleAngle);
    }

    function connectCircles(circle1, circle2) {
        ctx.moveTo(...circle1.center);
        ctx.lineTo(...circle2.center);
    }
}