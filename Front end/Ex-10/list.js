// function listSum() {
//     const numbers = document.querySelector('#myList');
//     var sum = 0;

//     for (var i = 0; i < numbers.length; i++) {
//         sum += numbers[i];
//     }

//     return sum;
// }

const pizzas = document.querySelector('#myList');

function listSum() {
    let sum = 0;
    var arr = pizzas.children;
    for (let i = 0; i < arr.length; i++) {
        sum += parseInt(arr[i].textContent);
    }
    return sum;
}

function listAvg() {
    let sum = listSum();
    var arr = pizzas.children;
    return sum / arr.length;
}

// var arr = pizzas.children;

// arr.forEach(element => {
//     console.log(element);
// });

console.log("Average price of pizzas: $" + listAvg());
console.log("Total price of pizzas: $" + listSum());