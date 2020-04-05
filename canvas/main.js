const draw = () => {
    var canvas = document.getElementById('canvas');
    var ctx = canvas.getContext('2d');

    // ctx.fillStyle = 'rgb(200, 0, 0)';
    // ctx.fillRect(10, 10, 50, 50);

    // ctx.fillStyle = 'rgba(0, 0, 200, 0.5)';
    // ctx.fillRect(30, 30, 50, 50);

    // ctx.beginPath();
    // ctx.moveTo(75, 50);
    // ctx.arc(75, 50, 1, 0, Math.PI * 2, true); // Left eye
    // ctx.moveTo(75, 50);
    // ctx.arc(100, 75, 1, 0, Math.PI * 2, true); // Left eye
    // ctx.moveTo(75, 50);
    // ctx.arc(100, 25, 1, 0, Math.PI * 2, true); // Left eye

    // ctx.moveTo(75, 50);
    // ctx.lineTo(100, 75);
    // ctx.lineTo(100, 25);
    // ctx.fill();

    // ctx.beginPath();
    // ctx.moveTo(75, 50);
    // ctx.lineTo(100, 75);
    // ctx.lineTo(100, 25);
    // ctx.fill();

    // ctx.beginPath();
    // ctx.arc(75, 75, 50, 0, Math.PI * 2, true); // Outer circle
    // ctx.moveTo(110, 75);
    // ctx.arc(75, 75, 35, 0, Math.PI, false); // Mouth (clockwise)
    // ctx.moveTo(65, 65);
    // ctx.arc(60, 65, 5, 0, Math.PI * 2, true); // Left eye
    // ctx.moveTo(95, 65);
    // ctx.arc(90, 65, 5, 0, Math.PI * 2, true); // Right eye
    // ctx.stroke();


    ctx.beginPath();
    ctx.arc(75, 75, 50, (Math.PI / 180) * 0, (Math.PI / 180) * 90, false); // Outer circle
    ctx.stroke();
}