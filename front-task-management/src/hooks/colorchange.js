// Function to extract dominant color from background
function getDominantColorFromBackground() {
    const body = document.querySelector('body');
    const styles = window.getComputedStyle(body);
    const background = styles.backgroundImage;
    const colors = background.match(/rgb\([^\)]+\)/g);
    
    if (colors) {
        return colors[0]; // Returning the first color as the dominant one
    }
    return '#4CAF50'; // Default color if no background match
}

// Function to update button colors
function updateButtonColors() {
    const buttons = document.querySelectorAll('.form-toggle button');
    const dominantColor = getDominantColorFromBackground();
    buttons.forEach(button => {
        button.style.backgroundColor = dominantColor;
        button.addEventListener('mouseover', () => {
            button.style.backgroundColor = shadeColor(dominantColor, 20);
        });
        button.addEventListener('mouseout', () => {
            button.style.backgroundColor = dominantColor;
        });
    });
}

// Function to darken a color (used for hover effect)
function shadeColor(color, percent) {
    let R = parseInt(color.substring(4, 7));
    let G = parseInt(color.substring(9, 12));
    let B = parseInt(color.substring(14, 17));

    R = parseInt(R * (100 + percent) / 100);
    G = parseInt(G * (100 + percent) / 100);
    B = parseInt(B * (100 + percent) / 100);

    R = (R < 255) ? R : 255;
    G = (G < 255) ? G : 255;
    B = (B < 255) ? B : 255;

    return `rgb(${R}, ${G}, ${B})`;
}

// Call the function when the page is loaded
window.onload = updateButtonColors;

// Optionally, you can also call updateButtonColors every time the animation changes
document.querySelector('body').addEventListener('animationiteration', updateButtonColors);
