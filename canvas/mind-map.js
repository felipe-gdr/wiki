const draw = () => {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    const canvasCenter = [canvas.offsetHeight / 2, canvas.offsetWidth / 2];
    const ratio = 0.5;

    const mainNode = {
        center: canvasCenter,
        height: 150 * ratio,
        width: 150,
        backgroundColor: 'rgba(255, 0, 0, .5)',
        text: 'Main',
        getTopLeftCorner: function() {
            const [centerX, centerY] = this.center;
            return [centerX - (this.width / 2), (centerY - (this.height / 2))];
        }
    }

    drawNode(mainNode);

    function drawNode(node) {
        const { height, width, backgroundColor, text } = node;
        // Rectangle
        ctx.beginPath();
        ctx.moveTo(...node.getTopLeftCorner())
        ctx.fillStyle = backgroundColor;
        ctx.fillRect(...node.getTopLeftCorner(), width, height);

        // Text
        ctx.fillStyle = 'rgb(255, 255, 255)';
        ctx.font = '24px serif';
        ctx.textAlign = 'center';
        ctx.textBaseline = 'middle';
        ctx.fillText(text, ...node.center);

        ctx.stroke();
    }

}