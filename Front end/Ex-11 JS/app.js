function toggleColor() {
    const button = document.querySelector('#main-title');
    if (!button) return;
    button.classList.toggle('blue');
    return "Il testo è ora blu: " + button.classList.contains('blue');

}

function toggleBold() {
    const button = document.querySelectorAll('.list-item');
    if (!button) return;
    button.forEach(item => {
        item.classList.toggle('bold');
    });
    return "I testi sono ora in grassetto";
}

const queryAll = document.querySelectorAll('.list-item');
const getAll = document.getElementsByClassName('list-item');

function createListItm() {
    console.log(queryAll);
    console.log(getAll);
    const newElement = document.createElement('li');
    newElement.textContent = 'Item 5';
    newElement.classList.add('list-item');
    newElement.id = 'it5';
    document.querySelector('ul').appendChild(newElement);
    console.log(queryAll);
    console.log(getAll);
}