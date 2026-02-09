// Qui ho inizializzato tutte le variabili che mi servono per gestire il modal e la lista dei film.
const buttonAdd = document.querySelector('#buttonAdd');
const addModal = document.querySelector('#add-modal');
const backdrop = document.querySelector('#backdrop');
const cancelBtn = addModal.querySelector('.btn--passive');
const confirmBtn = addModal.querySelector('.btn--success');
const movieList = document.querySelector('#movie-list');
const entryText = document.querySelector('#entry-text');

// In queste funzioni gestisco l'apertura e la chiusura del modal, e l'aggiunta di un nuovo film alla lista. La funzione addMovieHandler() si occupa di prendere i valori inseriti dall'utente, creare un nuovo elemento nella lista dei film e aggiornare la UI di conseguenza.
function openAddModal() {
  addModal.classList.add('visible');
  backdrop.classList.add('visible');
}

function closeAddModal() {
  addModal.classList.remove('visible');
  backdrop.classList.remove('visible');
}

function addMovieHandler() {
  const title = document.querySelector('#title').value.trim();
  const imageUrl = document.querySelector('#image-url').value.trim();
  const rating = document.querySelector('#rating').value;

  // semplice validazione, se almeno uno dei campi è vuoto, mostro un alert e non procedo con l'aggiunta del film alla lista.
  if (title === '' || imageUrl === '' || rating === '') {
    alert('Please enter valid values.');
    return;
  }

  // Creo un nuovo elemento della lista dei film, con la struttura HTML necessaria per mostrare l'immagine, il titolo e la valutazione del film. Poi aggiungo questo nuovo elemento alla lista dei film esistente. Questo aggiornamento della UI avviene dinamicamente, senza bisogno di ricaricare la pagina. Infine chiudo il modal e pulisco i campi di input per preparare l'aggiunta di un nuovo film.
  const newMovieElement = document.createElement('li');
  newMovieElement.className = 'movie-element';
  newMovieElement.innerHTML = `
    <div class="movie-element__image">
      <img src="${imageUrl}" alt="${title}">
    </div>
    <div class="movie-element__info">
      <h2>${title}</h2>
      <p>${rating}/5 stars</p>
    </div>
  `;

  movieList.appendChild(newMovieElement);

  entryText.style.display = 'none';

  closeAddModal();
  clearInputs();
}

function clearInputs() {
  document.querySelector('#title').value = '';
  document.querySelector('#image-url').value = '';
  document.querySelector('#rating').value = '';
}


buttonAdd.addEventListener('click', openAddModal);

// Click su Cancel
cancelBtn.addEventListener('click', closeAddModal);

// Click fuori dal modal
backdrop.addEventListener('click', closeAddModal);

// Click su Add
confirmBtn.addEventListener('click', addMovieHandler);