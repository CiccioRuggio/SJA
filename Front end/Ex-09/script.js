// function toggleColor() {
//     const box = document.querySelector('.box');
//     if (box.style.backgroundColor === 'lightblue') {
//         box.style.backgroundColor = 'lightcoral';
//     } else {
//         box.style.backgroundColor = 'lightblue';
//     }
// }


function toggleColor() {
    const box = document.querySelector('.box');
    if (!box) return;
    box.classList.toggle('color');
}